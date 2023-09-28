import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { MatSnackBar } from "@angular/material/snack-bar";
import { UserService } from "src/app/services/user.service";

@Component({
  selector: "app-forgot-password",
  templateUrl: "./forgot-password.component.html",
  styleUrls: ["./forgot-password.component.css"],
})
export class ForgotPasswordComponent implements OnInit {
  form: FormGroup;
  email: string = '';

  constructor(
    private router: Router,
    private service: UserService,
    private formBuilder: FormBuilder,
    private _snackBar: MatSnackBar
  ) {
    this.form = this.formBuilder.group({
      email: ["", [Validators.required, Validators.email]],
    });
  }

  ngOnInit(): void {}

  enviar() {
    if (this.form.invalid) {
      this.openSnackBar("Preencha todos os campos obrigatórios");
      return;
    }
    this.email = this.form.controls["email"].value;

    this.service.forgotPassword(this.email).subscribe(
      (res) => {
        this.openSnackBar("Email enviado com sucesso!");
        this.router.navigate(["login"]);
      },
      (err) => {
        this.openSnackBar("Erro ao enviar email..");
      }
    );
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, "", { duration: 3000 });
  }
}
