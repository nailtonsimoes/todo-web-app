import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { Todo } from '../../../models/todo';
import { TodoService } from '../../../services/todo.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-read-all',
  templateUrl: './read-all.component.html',
  styleUrls: ['./read-all.component.css']
})
export class ReadAllComponent implements OnInit {

  closed = 0;
  list: Todo[] = [];
  listFinished: Todo[] = [];

  constructor(private service: TodoService, private router: Router, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.findAll();

  }

  findAll(): void {
    this.service.findAll().subscribe((res) => {
      res.forEach((todo) => {
        if (todo.finished) {
          this.listFinished.push(todo);
        } else {
          this.list.push(todo);
        }
      });

      this.closed = this.listFinished.length;

    });
  }

  countClosed(): void {
    for (let todo of this.list) {
      if (todo.finished) {
        this.closed++;
      }
    }
  }

  delete(id: any): void {
    this.service.delete(id).subscribe(
      (res) => {
        if (res === null) {
          this.openSnackBar('Tarefa Deletada com Sucesso!');
          this.list = this.list.filter(todo => todo.id !== id);
        }
      },
      (err) => {
        this.openSnackBar('Erro ao deletar tarefa!');
      }
    )
  }

  finalizar(item: Todo): void {
    item.finished = true;
    this.service.update(item).subscribe(
      () => {
        this.openSnackBar('Tarefa Finalizada com Sucesso!');
        this.list = this.list.filter(todo => todo.id !== item.id);
        this.closed++;
      }
    );
  }

  navegateToFinished(): void {
    this.router.navigate(['finalizados']);
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, '', { duration: 3000 });
  }

}
