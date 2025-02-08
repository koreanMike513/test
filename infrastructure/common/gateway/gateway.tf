resource "aws_apigatewayv2_api" "joyeuse_planete" {
  name          = "Joyeuse_Planete_API_GATEWAY"
  description   = "API Gateway for Joyeuse_Planete"
  protocol_type = "HTTP"
}

resource "aws_apigatewayv2_integration" "foods" {
  api_id             = aws_apigatewayv2_api.joyeuse_planete.id
  integration_type   = "HTTP_PROXY"
  integration_method = "ANY"
  integration_uri    = "http://${var.food_lb_ip}"
}

resource "aws_apigatewayv2_route" "foods" {
  api_id    = aws_apigatewayv2_api.joyeuse_planete.id
  route_key = "ANY /api/v1/foods/{proxy+}"
  target    = "integrations/${aws_apigatewayv2_integration.foods.id}"
}

resource "aws_apigatewayv2_integration" "orders" {
  api_id             = aws_apigatewayv2_api.joyeuse_planete.id
  integration_type   = "HTTP_PROXY"
  integration_method = "ANY"
  integration_uri    = "http://${var.orders_lb_ip}"
}

resource "aws_apigatewayv2_route" "orders" {
  api_id    = aws_apigatewayv2_api.joyeuse_planete.id
  route_key = "ANY /api/v1/orders/{proxy+}"
  target    = "integrations/${aws_apigatewayv2_integration.orders.id}"
}

resource "aws_apigatewayv2_integration" "payment" {
  api_id             = aws_apigatewayv2_api.joyeuse_planete.id
  integration_type   = "HTTP_PROXY"
  integration_method = "ANY"
  integration_uri    = "http://${var.payments_lb_ip}"
}

resource "aws_apigatewayv2_route" "payment" {
  api_id    = aws_apigatewayv2_api.joyeuse_planete.id
  route_key = "ANY /api/v1/payment/{proxy+}"
  target    = "integrations/${aws_apigatewayv2_integration.payment.id}"
}

resource "aws_apigatewayv2_integration" "notifications" {
  api_id             = aws_apigatewayv2_api.joyeuse_planete.id
  integration_type   = "HTTP_PROXY"
  integration_method = "ANY"
  integration_uri    = "http://${var.notifications_lb_ip}"
}

resource "aws_apigatewayv2_route" "notifications" {
  api_id    = aws_apigatewayv2_api.joyeuse_planete.id
  route_key = "ANY /api/v1/notifications/{proxy+}"
  target    = "integrations/${aws_apigatewayv2_integration.notifications.id}"
}

output "joyeuse_planete_api_gateway_ip" {
  value       = aws_apigatewayv2_api.joyeuse_planete.api_endpoint
  description = "Base URL of the Joyeuse Planete API Gateway"
}