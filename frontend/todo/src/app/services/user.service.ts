import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { UserRequestDto } from '../dtos/user-request-dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
 
  constructor(private http: HttpClient) { }

  baseUrl = environment.baseUrl;
  createUserUrl = `${this.baseUrl}/users/create`;
  forgotPasswordUrl = `${this.baseUrl}/email/forgot-password`;
  

  createUser(user: UserRequestDto): Observable<UserRequestDto> {
    return this.http.post<UserRequestDto>(this.createUserUrl, user);
  }

  forgotPassword(email: string): Observable<string> {
    return this.http.post<string>(this.forgotPasswordUrl, email);
  }

  recoverPassword(id: string, password: string) {
    const recoverPasswordUrl = `${this.baseUrl}/users/recover-password/${id}`;
    return this.http.put<string>(recoverPasswordUrl, password);
  }
}
