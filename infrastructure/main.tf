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
  source            = "./foods"
  docker_food_image = var.docker_food_image
}

module "orders" {
  source              = "./orders"
  docker_orders_image = var.docker_orders_image
}

module "notifications" {
  source                     = "./notifications"
  docker_notifications_image = var.docker_notifications_image
}

module "payment" {
  source               = "./payment"
  docker_payment_image = var.docker_payment_image
}

# module "api_gate_way" {
#   source                      = "./common/gateway"

#   food_load_balancer          = module.foods.food_nginx_public_ip
#   notifications_load_balancer = module.notifications.notifications_nginx_public_ip
#   orders_load_balancer        = module.orders.orders_nginx_public_ip
#   payments_load_balancer      = module.payment.payments_nginx_public_ip
# }