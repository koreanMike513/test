resource "aws_instance" "kafka" {
  ami                    = var.server_ami
  instance_type          = var.server_instance_type
  vpc_security_group_ids = [var.aws_security_group_id]
  key_name               = var.aws_key_pair_name
  iam_instance_profile   = aws_iam_instance_profile.ec2_instance_profile.name 


  user_data = <<-EOF
    #!/bin/bash
    yum update -y
    amazon-linux-extras enable docker
    yum install -y docker
    systemctl start docker
    systemctl enable docker

    PUBLIC_IP=$(curl -s http://169.254.169.254/latest/meta-data/public-ipv4)

    docker run -d --name kafka_broker \
      -p 9092:9092 \
      -e KAFKA_NODE_ID=1 \
      -e KAFKA_PROCESS_ROLES=controller,broker \
      -e KAFKA_CONTROLLER_QUORUM_VOTERS=1@$PUBLIC_IP:9093 \
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

resource "aws_iam_policy" "describe_instances_policy" {
  name        = "EC2DescribeInstancesPolicy"
  description = "Allows EC2 instances to describe other instances"

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect   = "Allow"
        Action   = "ec2:DescribeInstances"
        Resource = "*"
      }
    ]
  })
}

resource "aws_iam_role" "ec2_role" {
  name = "KafkaInstanceRole"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect = "Allow"
        Principal = {
          Service = "ec2.amazonaws.com"
        }
        Action = "sts:AssumeRole"
      }
    ]
  })
}

resource "aws_iam_policy_attachment" "ec2_attach_policy" {
  name       = "EC2DescribeInstancesAttachment"
  policy_arn = aws_iam_policy.describe_instances_policy.arn
  roles      = [aws_iam_role.ec2_role.name]
}

resource "aws_iam_instance_profile" "ec2_instance_profile" {
  name = "KafkaInstanceProfile"
  role = aws_iam_role.ec2_role.name
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