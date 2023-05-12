import { MatSnackBar} from '@angular/material/snack-bar';

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Todo } from '../models/todo';
import { LoginService } from '../components/login/login.service';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private snack: MatSnackBar, private loginService: LoginService) { }

  findAll():Observable<Todo[]>{
    const userId = this.loginService.getUserIdbyToken();
    const findAllUrl = `${this.baseUrl}/todos/${userId}/listAllByUserId`;
    console.log(findAllUrl);
    return this.http.get<Todo[]>(findAllUrl);
  }

  findById(id: any):Observable<Todo>{
    const findUrl = `${this.baseUrl}/todos/${id}`;
    return this.http.get<Todo>(findUrl);
  }

  delete(id: any):Observable<void> {
    const url = `${this.baseUrl}/todos/${id}/delete`;
    return this.http.delete<void>(url);
  }

  update(todo: Todo): Observable<Todo>{
    const url = `${this.baseUrl}/todos/${todo.id}/update`
    return this.http.put<Todo>(url, todo);
  }

  create(todo: Todo):Observable<Todo>{
    const userId = this.loginService.getUserIdbyToken();
    const createUrl = `${this.baseUrl}/todos/${userId}/create`
    return this.http.post<Todo>(createUrl, todo);
  }

  message(msg: String):void {
    this.snack.open(`${msg}`, 'fechar',{
      duration: 4000,
    })
  }

}
