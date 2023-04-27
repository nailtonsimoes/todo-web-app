import { ReadAllComponent } from './components/Todo/read-all/read-all.component';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { Routes } from '@angular/router';
import { FinalizadosComponent } from './components/Todo/finalizados/finalizados.component';
import { CreateComponent } from './components/Todo/create/create.component';
import { UpdateComponent } from './components/Todo/update/update.component';

const routes: Routes = [
  {
    path:'',
    component: ReadAllComponent
  },
  {
    path:'finalizados',
    component: FinalizadosComponent
  },
  {
    path:'create',
    component: CreateComponent
  },
  {
    path:'update/:id',
    component: UpdateComponent
  }
]; 

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
