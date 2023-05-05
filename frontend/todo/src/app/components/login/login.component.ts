import { Component, OnInit } from '@angular/core';
import { LoginDto } from 'src/app/dtos/login-dto';
import { LoginService } from './login.service';
import { Router } from '@angular/router';

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
  
  constructor(private service: LoginService, private router: Router) { 
   
  }
  
  ngOnInit(): void {
  }

  async authLogin() {
    try {
      const res = await this.service.authentication(this.login);
      console.log(`login efetuado:${res}`);
      alert('Login feito com sucesso!');
      this.router.navigate(['']);
    } catch (err) {
      console.log(err);
      alert('Erro na tentativa de Login..');
    }
  }
}