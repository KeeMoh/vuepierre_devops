data "template_file" "user_data" {
        template = file("learn-terraform-provisioning/scripts/add-ssh-web-app.yaml")
}

resource "aws_instance" "web" {
        count = var.instance_count
        ami = var.ec2_ami
        key_name = var.ssh_key_name
        associate_public_ip_address = "true"
        user_data = data.template_file.user_data.rendered
        security_groups = [aws_security_group.sg_default.name]
        instance_type = var.ec2_instance
        tags = {
                Owner = "pierre.vue@ynov.com"
                Name = "${var.env}-pierre-${count.index}"
                Environment = var.env
                Groups = "app"
        }
}
resource "aws_key_pair" "deployer" {
        key_name   = var.ssh_key_name
        public_key = file("ssh/aws_key_pair.pub")
}
