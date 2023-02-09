import { MatSnackBar} from '@angular/material/snack-bar';

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Todo } from '../models/todo';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private snack: MatSnackBar) { }

  findAll():Observable<Todo[]>{
    return this.http.get<Todo[]>(this.baseUrl);
  }

  findById(id: any):Observable<Todo>{
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Todo>(url);
  }

  delete(id: any):Observable<void> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete<void>(url);
  }

  update(todo: Todo): Observable<Todo>{
    const url = `${this.baseUrl}/${todo.id}`
    return this.http.put<Todo>(url, todo);
  }

  findAllClose():Observable<Todo[]>{
    const url = `${this.baseUrl}/${"close"}`;
    console.log(url);
    return this.http.get<Todo[]>(url);
  }

  create(todo: Todo):Observable<Todo>{
    return this.http.post<Todo>(this.baseUrl, todo);
  }

  message(msg: String):void {
    this.snack.open(`${msg}`, 'fechar',{
      duration: 4000,
    })
  }

}
