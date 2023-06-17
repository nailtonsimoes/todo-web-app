import { Component, OnInit } from '@angular/core';
import { LoginDto } from 'src/app/dtos/login-dto';
import { LoginService } from './login.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private service: LoginService,
    private router: Router,
    private _snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      name: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  async authLogin() {
    if (this.loginForm.invalid) {
      return;
    }

    try {
      const res = await this.service.authentication(this.loginForm.value);
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
