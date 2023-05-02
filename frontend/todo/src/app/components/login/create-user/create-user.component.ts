import { Component, OnInit } from '@angular/core';
import { UserRequestDto } from 'src/app/dtos/user-request-dto';

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

  constructor() { }

  ngOnInit(): void {
  }

  cadastrar(){
    
  }
}
