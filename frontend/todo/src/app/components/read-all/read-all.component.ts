import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { Todo } from '../../models/todo';
import { TodoService } from '../../services/todo.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-read-all',
  templateUrl: './read-all.component.html',
  styleUrls: ['./read-all.component.css']
})
export class ReadAllComponent implements OnInit {
  
  closed = 0;
  list: Todo[] = [];
  listFinished: Todo[] = [];
  
  constructor(private service: TodoService, private router: Router) { }

  ngOnInit(): void {
    this.findAll();

  }

  findAll(): void {
    this.service.findAll().subscribe((res)=>{
      res.forEach((todo) => {
        if(todo.finalizado){
          this.listFinished.push(todo);
        } else{
          this.list.push(todo);
        }
      });
      
      this.closed = this.listFinished.length;

    });
  }

  countClosed(): void {
    for(let todo of this.list){
      if(todo.finalizado){
        this.closed++;
      }
    }
  }

  delete(id: any):void {
    
    this.service.delete(id).subscribe(
      (res)=> {
        if(res === null){
          alert('Tarefa Deletada com Sucesso!');
          this.list = this.list.filter(todo => todo.id !== id);
        }
      }
    ) 
  }

  finalizar(item: Todo): void {
    item.finalizado = true;
    this.service.update(item).subscribe(
      ()=> {
        alert('Tarefa Finalizada com Sucesso!');
          this.list = this.list.filter(todo => todo.id !== item.id);
          this.closed++;
      }
    );
  }

  navegateToFinished(): void {
    this.router.navigate(['finalizados']);
  }

}
