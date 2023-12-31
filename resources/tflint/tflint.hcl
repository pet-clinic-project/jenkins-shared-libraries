config {
    module = true
    force = false
    disabled_by_default = false
    varfile = [var.tfvars_file]
}

plugin "aws" {
    enabled = true
    version = "0.26.0"
    source = "github.com/terraform-linters/tflint-ruleset-aws"
}


rule "terraform_required_version" {
enabled = false
}

rule "terraform_required_providers" {
enabled = false
}

rule "terraform_unused_declarations" {
enabled = false
}

rule "terraform_deprecated_interpolation" {
enabled = false
}
