import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { LoginDto } from 'src/app/dtos/login-dto'
import { Observable } from 'rxjs';
import { UserRequestDto } from 'src/app/dtos/user-request-dto';
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  baseUrl = environment.baseUrl;
  loginUrl = `${this.baseUrl}/auth/login`;
  createUserUrl = `${this.baseUrl}/users/create`;

  authentication(login: LoginDto ):Observable<LoginDto>{
    return this.http.post<LoginDto>(this.loginUrl, login);
  }

  createUser(user: UserRequestDto):Observable<UserRequestDto>{
    return this.http.post<UserRequestDto>(this.createUserUrl, user);
  }

}
