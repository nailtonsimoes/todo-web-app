import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import jwtDecode from 'jwt-decode';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-recover-password',
  templateUrl: './recover-password.component.html',
  styleUrls: ['./recover-password.component.css']
})
export class RecoverPasswordComponent implements OnInit {
  form: FormGroup;
  password: string = '';
  token: string = '';
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
      this.token = params['token'];
    });
  }

  enviar() {
    if (this.form.invalid) {
      this.openSnackBar("Preencha todos os campos obrigatórios");
      return;
    }

    this.getUserIdbyToken();
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

  getUserIdbyToken() {
    const decoded: any = jwtDecode(this.token);
    this.userId = decoded.id;
    console.log('User Id',this.userId);
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, "", { duration: 3000 });
  }

}
