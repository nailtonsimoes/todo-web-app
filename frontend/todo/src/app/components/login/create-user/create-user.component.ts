import { Component, OnInit } from "@angular/core";
import { UserRequestDto } from "src/app/dtos/user-request-dto";
import { Router } from "@angular/router";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { MatSnackBar } from "@angular/material/snack-bar";
import { UserService } from "src/app/services/user.service";

@Component({
  selector: "app-create-user",
  templateUrl: "./create-user.component.html",
  styleUrls: ["./create-user.component.css"],
})
export class CreateUserComponent implements OnInit {
  form: FormGroup;

  constructor(
    private router: Router,
    private service: UserService,
    private formBuilder: FormBuilder,
    private _snackBar: MatSnackBar
  ) {
    this.form = this.formBuilder.group({
      name: ["", [Validators.required, Validators.minLength(3)]],
      email: ["", [Validators.required, Validators.email]],
      password: ["", [Validators.required, Validators.minLength(6)]],
    });
  }

  ngOnInit(): void {}

  cadastrar() {
    if (this.form.invalid) {
      this.openSnackBar("Preencha todos os campos obrigatórios");
      return;
    }

    const user: UserRequestDto = {
      name: this.form.controls["name"].value,
      email: this.form.controls["email"].value,
      password: this.form.controls["password"].value,
      roleId: 2,
    };

    this.service.createUser(user).subscribe(
      (res) => {
        this.openSnackBar("Usuário registrado com sucesso!");
        this.router.navigate(["login"]);
      },
      (err) => {
        this.openSnackBar("Erro ao registrar Usuário..");
      }
    );
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, "", { duration: 3000 });
  }
}
