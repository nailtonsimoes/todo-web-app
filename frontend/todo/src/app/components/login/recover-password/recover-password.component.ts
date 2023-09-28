import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-recover-password',
  templateUrl: './recover-password.component.html',
  styleUrls: ['./recover-password.component.css']
})
export class RecoverPasswordComponent implements OnInit {
  form: FormGroup;
  password: string = '';
  userId: string = '';

  constructor(
    private router: Router,
    private activateRoute: ActivatedRoute,
    private service: UserService,
    private formBuilder: FormBuilder,
    private _snackBar: MatSnackBar
  ) {
    this.form = this.formBuilder.group({
      password: ["", [Validators.required, Validators.minLength(6)]],
    });
  }

  ngOnInit(): void {
    this.activateRoute.queryParams.subscribe(params => {
      this.userId = params['id'];
    });
  }

  enviar() {
    if (this.form.invalid) {
      this.openSnackBar("Preencha todos os campos obrigatórios");
      return;
    }
    this.password = this.form.controls["password"].value;

    this.service.recoverPassword(this.userId, this.password).subscribe(
      (res) => {
        this.openSnackBar("Senha alterado com sucesso!");
        this.router.navigate(["login"]);
      },
      (err) => {
        this.openSnackBar("Erro ao alterar Senha..");
      }
    );
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, "", { duration: 3000 });
  }

}
