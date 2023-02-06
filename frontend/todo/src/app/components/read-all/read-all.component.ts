import { TodoService } from './../../services/todo.service';
import { Component, OnInit } from '@angular/core';
import { Todo } from 'src/app/models/todo';

@Component({
  selector: 'app-read-all',
  templateUrl: './read-all.component.html',
  styleUrls: ['./read-all.component.css']
})
export class ReadAllComponent implements OnInit {
  
  list:Todo[] = [];
  
  constructor(private service: TodoService) { }

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.service.findAll().subscribe((res)=>{
      this.list = res;
    });
  }

}
