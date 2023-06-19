import { ReadAllComponent } from "./components/Todo/read-all/read-all.component";
import { RouterModule } from "@angular/router";
import { NgModule } from "@angular/core";
import { Routes } from "@angular/router";
import { FinalizadosComponent } from "./components/Todo/finalizados/finalizados.component";
import { CreateComponent } from "./components/Todo/create/create.component";
import { UpdateComponent } from "./components/Todo/update/update.component";
import { LoginComponent } from "./components/login/login.component";
import { CreateUserComponent } from "./components/login/create-user/create-user.component";
import { HomeComponent } from "./components/home/home.component";
import { AuthComponent } from "./components/auth/auth.component";
import { AuthGuard } from "./components/auth/auth.guard";
import { ForgotPasswordComponent } from "./components/login/forgot-password/forgot-password.component";

const routes: Routes = [
  {
    path: "",
    component: HomeComponent,
    children: [
      {
        path: '',
        component: ReadAllComponent,
      },
      {
        path: 'finalizados',
        component: FinalizadosComponent,
      },
      {
        path: 'create',
        component: CreateComponent,
      },
      {
        path: 'update/:id',
        component: UpdateComponent,
      }
    ],
    canActivate:[AuthGuard]
  },
  {
    path: '',
    component: AuthComponent,
    children:[
      {path: '', redirectTo: 'login', pathMatch: 'full'},
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'create-user',
        component: CreateUserComponent
      },
      {
        path: 'forgot-password',
        component: ForgotPasswordComponent
      }
    ]
  },
  
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
