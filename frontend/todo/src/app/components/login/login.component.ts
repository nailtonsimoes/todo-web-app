import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
onSubmit() {
throw new Error('Method not implemented.');
}

  username: String = '';
  password: String = '';

  constructor() { }

  ngOnInit(): void {
  }

}
