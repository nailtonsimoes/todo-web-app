import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { UserRequestDto } from 'src/app/dtos/user-request-dto';
import jwtDecode from 'jwt-decode';
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  baseUrl = environment.baseUrl;
  loginUrl = `${this.baseUrl}/auth/login`;
  createUserUrl = `${this.baseUrl}/users/create`;

  async authentication(login: any) {
    const res = await this.http.post<any>(this.loginUrl, login).toPromise();
    if (res && res.token) {
      window.localStorage.setItem('token', res.token);
      window.localStorage.setItem('userId', res.userId);
      return true;
    }
    return false;
  }

  createUser(user: UserRequestDto): Observable<UserRequestDto> {
    return this.http.post<UserRequestDto>(this.createUserUrl, user);
  }

  getAuthorizationToken() {
    const token = window.localStorage.getItem('token');
    return token;
  }

  getTokenExpirationDate(token: string): Date | null {
    const decoded: any = jwtDecode(token);

    if (decoded.exp === undefined) {
      return null;
    }

    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }

  isTokenExpired(token?: string): boolean {
    if (!token) {
      return true;
    }
    const date = this.getTokenExpirationDate(token);

    if (date === null) {
      return false;
    }

    return !(date.valueOf() > new Date().valueOf());
  }

  isUserLoggedIn() {
    const token = this.getAuthorizationToken();

    if (!token) {
      return false;
    } else if (this.isTokenExpired(token)) {
      return false;
    }
    return true;
  }

  getUserIdbyToken(): string {
    const token = this.getAuthorizationToken();
    if (!token) {
      throw new Error('Token de autorização não encontrado');
    }
    const decoded: any = jwtDecode(token);
    return decoded.id;
  }

}
