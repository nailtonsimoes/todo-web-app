
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

  constructor(private http: HttpClient) { }

  findAll():Observable<Todo[]>{
    return this.http.get<Todo[]>(this.baseUrl);
  }

}
