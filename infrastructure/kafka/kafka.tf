resource "aws_instance" "kafka" {
  ami                    = var.server_ami
  instance_type          = var.server_instance_type
  vpc_security_group_ids = [var.aws_security_group_id]
  key_name               = var.aws_key_pair_name

  user_data = <<-EOF
    #!/bin/bash
    yum update -y
    amazon-linux-extras enable docker
    yum install -y docker
    systemctl start docker
    systemctl enable docker

    # Get EC2 public IP
    PUBLIC_IP=$(curl -s http://169.254.169.254/latest/meta-data/public-ipv4)

    # Create the Kafka storage directory
    mkdir -p /var/lib/kafka/data

    # Format the metadata storage for KRaft mode
    docker run --rm -v /var/lib/kafka/data:/var/lib/kafka/data apache/kafka:3.8.1 \
      kafka-storage format --ignore-formatted \
      --config /opt/kafka/config/kraft/server.properties \
      --cluster-id $(kafka-storage random-uuid)

    # Start Kafka in KRaft mode
    docker run -d --name kafka_broker \
      -p 9092:9092 \
      -e KAFKA_NODE_ID=1 \
      -e KAFKA_PROCESS_ROLES=controller,broker \
      -e KAFKA_CONTROLLER_QUORUM_VOTERS=1@127.0.0.1:9093 \
      -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092,CONTROLLER://127.0.0.1:9093 \
      -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://$PUBLIC_IP:9092 \
      -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=PLAINTEXT:PLAINTEXT,CONTROLLER:PLAINTEXT \
      -e KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER \
      -e KAFKA_LOG_DIRS=/var/lib/kafka/data \
      apache/kafka:3.8.1
  EOF

  tags = {
    Name = "Kafka Server (KRaft Mode)"
  }
}

variable "server_ami" {
}

variable "server_instance_type" {
}

variable "aws_security_group_id" {
  description = "Security group ID for EC2"
}


variable "aws_key_pair_name" {
  description = "Key Pair Name for EC2"
}

output "kafka_ip" {
  value = aws_instance.kafka.public_ip
}