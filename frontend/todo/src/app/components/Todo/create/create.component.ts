import { TodoService } from 'src/app/services/todo.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Todo } from 'src/app/models/todo';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  
  todoForm!: FormGroup;

  todo: Todo = {
    title: '',
    description: '',
    dateForFinalize: new Date(),
    finished: false
  };

  constructor(private router: Router, private service: TodoService, private _snackBar: MatSnackBar, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.todoForm = this.formBuilder.group({
      dateForFinalize: ['', Validators.required],
      title: ['', Validators.required],
      description: ['', Validators.required],
    })
  }

  create() {

    if (this.todoForm.invalid) {
      this.openSnackBar('Dados invalidos! Preencha todos os campos corretamente.');
      return;
    }

    this.todo = {
      title: this.todoForm.value.title,
      description: this.todoForm.value.description,
      dateForFinalize: this.todoForm.value.dateForFinalize,
      finished: false
    };

    this.formataData(this.todo);
  
    this.service.create(this.todo).subscribe(
      (res) => {
        this.openSnackBar('Tarefa Criada com sucesso.');
        this.router.navigate(['']);
      },
      (err) => {
        this.openSnackBar('Erro ao criar tarefa!');
      }
    );
  }

  formataData(todo: Todo): void {
    let data = new Date(todo.dateForFinalize);
    this.todo.dateForFinalize = `${data.getDate()}/${data.getMonth() + 1}/${data.getFullYear()}`;
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, '', { duration: 3000 });
  }

}
