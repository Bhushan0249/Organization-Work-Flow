import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { DetailComponent } from './detail/detail.component';
import { AddempComponent } from './addemp/addemp.component';

const routes: Routes = [

  {
    path:"",component:HomeComponent
  },
  {
    path:"detail/:id",component:DetailComponent
  },
  {
    path:"addemp",component:AddempComponent
  },
  {
    path:"updateEmp/:id",component:AddempComponent
  },
  {
    path:"**",component:NotfoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
