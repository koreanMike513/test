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

    # Start Kafka in KRaft mode
    docker run -p 9092:9092 --name kafka_broker -d apache/kafka:3.8.1
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