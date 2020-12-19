import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GetBookComponent } from './components/book/get-book/get-book.component';
import { AddBookComponent } from './components/book/add-book/add-book.component';
import { LineOrderListComponent } from './components/lineOrder/lineOrderList/line-order-list/line-order-list.component';
import { EditBookComponent } from './components/book/edit-book/edit-book.component';
import { ListBookComponent } from './components/book/list-book/list-book.component';
import { AuthComponent } from './components/auth/auth.component';
const routes: Routes = [
  {
    path: 'book/get',
    component: GetBookComponent
    } ,
    {path: "add/book", 
    component: AddBookComponent}, 
    {path: "add/Line", 
    component: LineOrderListComponent},
    { path: 'post/:postId/edit', component: EditBookComponent }  ,
    { path: 'list/shop', component: ListBookComponent }  ,
    { path: '', component: AuthComponent}  ,
    { path: 'post/:postId/add', component: LineOrderListComponent} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
