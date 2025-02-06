terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = "eu-west-2"
}

module "foods" {
  source                = "./foods"
  docker_food_image     = var.DOCKER_FOOD_IMAGE
  aws_security_group_id = module.securities.security_group_id
  aws_key_pair_name     = module.key_pair.aws_key_pair_name
}

module "orders" {
  source                = "./orders"
  docker_orders_image   = var.DOCKER_ORDERS_IMAGE
  aws_security_group_id = module.securities.security_group_id
  aws_key_pair_name     = module.key_pair.aws_key_pair_name
}

module "notifications" {
  source                     = "./notifications"
  docker_notifications_image = var.DOCKER_NOTIFICATIONS_IMAGE
  aws_security_group_id      = module.securities.security_group_id
  aws_key_pair_name          = module.key_pair.aws_key_pair_name
}

module "payment" {
  source                = "./payment"
  docker_payment_image  = var.DOCKER_PAYMENT_IMAGE
  aws_security_group_id = module.securities.security_group_id
  aws_key_pair_name     = module.key_pair.aws_key_pair_name
}

module "key_pair" {
  source = "./common/key"
}

module "securities" {
  source = "./common/security"
}

# module "api_gate_way" {
#   source                      = "./common/gateway"

#   food_load_balancer          = module.foods.food_nginx_public_ip
#   notifications_load_balancer = module.notifications.notifications_nginx_public_ip
#   orders_load_balancer        = module.orders.orders_nginx_public_ip
#   payments_load_balancer      = module.payment.payments_nginx_public_ip
# }