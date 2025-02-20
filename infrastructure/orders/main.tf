module "servers" {
  source                = "../common/server"
  app_region            = "eu-west-2"
  server_ami            = "ami-0cbf43fd299e3a464"
  server_instance_type  = "t3.medium"
  server_count          = 2

  docker_image          = var.docker_orders_image
  aws_security_group_id = var.aws_security_group_id
  aws_key_pair_name     = var.aws_key_pair_name
  KAFKA_SERVER_IP       = var.KAFKA_SERVER_IP
  MONITORING_SERVER_IP  = var.MONITORING_SERVER_IP
  DATABASE_URL          = var.DATABASE_URL
  DATABASE_USERNAME     = var.DATABASE_USERNAME
  DATABASE_PASSWORD     = var.DATABASE_PASSWORD
  TOSS_SECRET_KEY       = var.TOSS_SECRET_KEY
  REDIS_HOST            = var.REDIS_HOST
  REDIS_PORT            = var.REDIS_PORT
  REDIS_PASSWORD        = var.REDIS_PASSWORD
}

module "nginx" {
  source                = "../common/load_balancer"
  app_region            = "eu-west-2"
  server_ami            = "ami-0cbf43fd299e3a464"
  server_instance_type  = "t3.medium"

  aws_security_group_id = var.aws_security_group_id
  aws_key_pair_name     = var.aws_key_pair_name
  servers               = module.servers.server_ips
}

variable "aws_security_group_id" {
}

variable "aws_key_pair_name" {
}

variable "KAFKA_SERVER_IP" {
}

variable "MONITORING_SERVER_IP" {
}

variable "DATABASE_URL" {
}

variable "DATABASE_USERNAME" {
}

variable "DATABASE_PASSWORD" {
}

variable "TOSS_SECRET_KEY" {
}

variable "REDIS_HOST" {
}

variable "REDIS_PORT" {
}

variable "REDIS_PASSWORD" {
}

variable "docker_orders_image" {
  description = "Docker orders image name"
  type        = string
}

output orders_nginx_public_ip {
  value = module.nginx.load_balancer_public_id
}