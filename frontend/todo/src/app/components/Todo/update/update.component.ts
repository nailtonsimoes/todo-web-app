import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Todo } from 'src/app/models/todo';
import { TodoService } from 'src/app/services/todo.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  todo: Todo = {
    title:'',
    description:'',
    dateForFinalize: new Date(),
    finished: false
  };

  constructor(
    private router: Router,
    private service: TodoService,
    private activatedRouter: ActivatedRoute,
    private _snackBar: MatSnackBar
    ) { }

  ngOnInit(): void {
    this.todo.id = parseInt(this.activatedRouter.snapshot.paramMap.get("id")!);
    this.findById();

  }

  findById(): void {
    this.service.findById(this.todo.id).subscribe(
      (res)=>{
        this.todo = res;
      }
    )
  }

  update(){
    this.formataData();
    this.service.update(this.todo).subscribe(
      (res)=>{
        this.openSnackBar('Informações Atualizadas com sucesso.');
        this.router.navigate(['']);
      },
      err =>{
        this.openSnackBar('Erro ao Atualizar tarefa!');
      }
    )
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
