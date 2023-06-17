import { Component, OnInit } from '@angular/core';
import { LoginDto } from 'src/app/dtos/login-dto';
import { LoginService } from './login.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: LoginDto = {
    name: '',
    password: ''
  }

  constructor(private service: LoginService, private router: Router, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  async authLogin() {
    try {
      const res = await this.service.authentication(this.login);
      console.log(`login efetuado:${res}`);
      this.openSnackBar('Login feito com sucesso!');
      this.router.navigate(['']);
    } catch (err) {
      console.error(err);
      this.openSnackBar('Erro na tentativa de Login..');
    }
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, '', { duration: 3000 });
  }
}