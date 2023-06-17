import { TodoService } from 'src/app/services/todo.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Todo } from 'src/app/models/todo';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  todo: Todo = {
    title:'',
    description:'',
    dateForFinalize: new Date(),
    finished: false
  };

  constructor(private router: Router, private service: TodoService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {

  }

  create(){
    this.formataData();
    this.service.create(this.todo).subscribe(
      (res)=>{
        this.openSnackBar('Tarefa Criada com sucesso.');
        this.router.navigate(['']);
      },
      err =>{
        this.openSnackBar('Erro ao criar tarefa!');
      }
    );
  }

  formataData(): void {
    let data = new Date(this.todo.dateForFinalize);
    this.todo.dateForFinalize = `${data.getDate()}/${data.getMonth()+1}/${data.getFullYear()}`;
  }

  cancel(): void {
    this.router.navigate(['']);
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, '', { duration: 3000 });
  }

}
