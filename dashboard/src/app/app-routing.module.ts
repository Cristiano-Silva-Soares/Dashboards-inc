import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OutsetComponent } from './outset/outset.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [

  { path: '', redirectTo: 'login', pathMatch: 'full' },
  
  { path: 'register', component: RegisterComponent },
  { path: 'outset/:id', component: OutsetComponent }

];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
