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

  authLogin(){
    this.service.authentication(this.login).subscribe(
      (res)=>{
        alert('Login Feito com sucesso!');
        this.router.navigate(['']);
      },
      err =>{
        alert('Erro na tentativa de Login..');
      }
    )
  }
}