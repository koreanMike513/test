# resource "aws_api_gateway_rest_api" "joyeuse_planete" {
#   name        = "Joyeuse_Planete_API_GATEWAY"
#   description = "API Gateway for Joyeuse_Planete"
# }

# ### 1️⃣ Define API Paths `/api/v1/{service}`
# resource "aws_api_gateway_resource" "api" {
#   rest_api_id = aws_api_gateway_rest_api.joyeuse_planete.id
#   parent_id   = aws_api_gateway_rest_api.joyeuse_planete.root_resource_id
#   path_part   = "api"
# }

# resource "aws_api_gateway_resource" "v1" {
#   rest_api_id = aws_api_gateway_rest_api.joyeuse_planete.id
#   parent_id   = aws_api_gateway_resource.api.id
#   path_part   = "v1"
# }

# # /api/v1/foods
# resource "aws_api_gateway_resource" "foods" {
#   rest_api_id = aws_api_gateway_rest_api.joyeuse_planete.id
#   parent_id   = aws_api_gateway_resource.v1.id
#   path_part   = "foods"
# }

# # /api/v1/payments
# resource "aws_api_gateway_resource" "payments" {
#   rest_api_id = aws_api_gateway_rest_api.joyeuse_planete.id
#   parent_id   = aws_api_gateway_resource.v1.id
#   path_part   = "payments"
# }

# # /api/v1/notifications
# resource "aws_api_gateway_resource" "notifications" {
#   rest_api_id = aws_api_gateway_rest_api.joyeuse_planete.id
#   parent_id   = aws_api_gateway_resource.v1.id
#   path_part   = "notifications"
# }

# # /api/v1/orders
# resource "aws_api_gateway_resource" "orders" {
#   rest_api_id = aws_api_gateway_rest_api.joyeuse_planete.id
#   parent_id   = aws_api_gateway_resource.v1.id
#   path_part   = "orders"
# }

# ### 2️⃣ Define GET Methods for Each API
# resource "aws_api_gateway_method" "foods_get" {
#   rest_api_id   = aws_api_gateway_rest_api.joyeuse_planete.id
#   resource_id   = aws_api_gateway_resource.foods.id
#   http_method   = "GET"
#   authorization = "NONE"
# }

# resource "aws_api_gateway_method" "payments_get" {
#   rest_api_id   = aws_api_gateway_rest_api.joyeuse_planete.id
#   resource_id   = aws_api_gateway_resource.payments.id
#   http_method   = "GET"
#   authorization = "NONE"
# }

# resource "aws_api_gateway_method" "notifications_get" {
#   rest_api_id   = aws_api_gateway_rest_api.joyeuse_planete.id
#   resource_id   = aws_api_gateway_resource.notifications.id
#   http_method   = "GET"
#   authorization = "NONE"
# }

# resource "aws_api_gateway_method" "orders_get" {
#   rest_api_id   = aws_api_gateway_rest_api.joyeuse_planete.id
#   resource_id   = aws_api_gateway_resource.orders.id
#   http_method   = "GET"
#   authorization = "NONE"
# }

# ### 3️⃣ Integrate API Gateway with Nginx Load Balancer
# resource "aws_api_gateway_integration" "foods_integration" {
#   rest_api_id             = aws_api_gateway_rest_api.joyeuse_planete.id
#   resource_id             = aws_api_gateway_resource.foods.id
#   http_method             = aws_api_gateway_method.foods_get.http_method
#   integration_http_method = "GET"
#   type                    = "HTTP_PROXY"
#   uri                     = "http://${module.nginx.load_balancer_public_id}/api/v1/foods"
# }

# resource "aws_api_gateway_integration" "payments_integration" {
#   rest_api_id             = aws_api_gateway_rest_api.joyeuse_planete.id
#   resource_id             = aws_api_gateway_resource.payments.id
#   http_method             = aws_api_gateway_method.payments_get.http_method
#   integration_http_method = "GET"
#   type                    = "HTTP_PROXY"
#   uri                     = "http://${module.nginx.load_balancer_public_id}/api/v1/payments"
# }

# resource "aws_api_gateway_integration" "notifications_integration" {
#   rest_api_id             = aws_api_gateway_rest_api.joyeuse_planete.id
#   resource_id             = aws_api_gateway_resource.notifications.id
#   http_method             = aws_api_gateway_method.notifications_get.http_method
#   integration_http_method = "GET"
#   type                    = "HTTP_PROXY"
#   uri                     = "http://${module.nginx.load_balancer_public_id}/api/v1/notifications"
# }

# resource "aws_api_gateway_integration" "orders_integration" {
#   rest_api_id             = aws_api_gateway_rest_api.joyeuse_planete.id
#   resource_id             = aws_api_gateway_resource.orders.id
#   http_method             = aws_api_gateway_method.orders_get.http_method
#   integration_http_method = "GET"
#   type                    = "HTTP_PROXY"
#   uri                     = "http://${module.nginx.load_balancer_public_id}/api/v1/orders"
# }

# ### 4️⃣ Deploy API Gateway
# resource "aws_api_gateway_deployment" "deploy" {
#   depends_on  = [
#     aws_api_gateway_integration.foods_integration,
#     aws_api_gateway_integration.payments_integration,
#     aws_api_gateway_integration.notifications_integration,
#     aws_api_gateway_integration.orders_integration
#   ]
#   rest_api_id = aws_api_gateway_rest_api.joyeuse_planete.id
#   stage_name  = "dev"
# }

# ### 5️⃣ Output API URLs
# output "api_gateway_url" {
#   value = aws_api_gateway_deployment.deploy.invoke_url
# }

# output "foods_api_url" {
#   value = "${aws_api_gateway_deployment.deploy.invoke_url}/api/v1/foods"
# }

# output "payments_api_url" {
#   value = "${aws_api_gateway_deployment.deploy.invoke_url}/api/v1/payments"
# }

# output "notifications_api_url" {
#   value = "${aws_api_gateway_deployment.deploy.invoke_url}/api/v1/notifications"
# }

# output "orders_api_url" {
#   value = "${aws_api_gateway_deployment.deploy.invoke_url}/api/v1/orders"
# }
