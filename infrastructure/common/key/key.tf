resource "aws_key_pair" "key" {
  key_name   = "instance_key"
  public_key = file("./EC2_KEY.pub")
}