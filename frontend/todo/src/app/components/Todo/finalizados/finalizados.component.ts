import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Todo } from 'src/app/models/todo';
import { TodoService } from 'src/app/services/todo.service';

@Component({
  selector: 'app-finalizados',
  templateUrl: './finalizados.component.html',
  styleUrls: ['./finalizados.component.css']
})
export class FinalizadosComponent implements OnInit {

  list: Todo[] = [];
  listFinished: Todo[] = [];

  constructor(private service: TodoService, private router: Router) { }

  ngOnInit(): void {
    this.findAll();
//      this.findAllClose();
  }

  findAll(): void {
    this.service.findAll().subscribe((res)=>{
      res.forEach((todo) => {
        if(todo.finshed){
          this.listFinished.push(todo);
        }
      });
    });
  }
  

 /* findAllClose(): void{
    this.service.findAllClose().subscribe(
      (res)=>{
        res.forEach((todo) => {
            this.list.push(todo);
        });
        console.log(this.list);
      }
    )
  }
  */


  voltar():void {
    this.router.navigate(['']);
  }

}
