import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  form: FormGroup;

  constructor(private router: Router, private service: LoginService, private formBuilder: FormBuilder, private _snackBar: MatSnackBar) {
    this.form = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]]
    });
   }

  ngOnInit(): void {
  }

  enviar(){}
}
