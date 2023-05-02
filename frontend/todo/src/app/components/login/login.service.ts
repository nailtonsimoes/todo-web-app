import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { LoginDto } from 'src/app/dtos/login-dto'
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  baseUrl = environment.baseUrl;
  loginUrl = this.baseUrl + '/auth/login';

  authentication(login: LoginDto ):Observable<LoginDto>{
    console.log('base URL:'+ this.loginUrl)
    return this.http.post<LoginDto>(this.loginUrl, login);
  }

}
