import { Component, OnInit } from '@angular/core';
import { UserRequestDto } from 'src/app/dtos/user-request-dto';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  user: UserRequestDto = {
    name: '',
    email: '',
    password: '',
    roleId: 2
  }

  constructor(private router: Router, private service: LoginService) { }

  ngOnInit(): void {
  }

  cadastrar(){
    this.service.createUser(this.user).subscribe(
      (res)=>{
        alert('Usuário registrado com sucesso!');
        this.router.navigate(['login']);
        console.log(res);
      },
      err =>{
        console.log(err);
        alert('Erro ao registrar Usuário..');
      }
    )
  }
}
