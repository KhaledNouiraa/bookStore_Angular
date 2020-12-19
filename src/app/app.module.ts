import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule ,ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import {MatDialogModule} from '@angular/material/dialog';
import { AppComponent } from './app.component';
import { AddBookComponent } from './components/book/add-book/add-book.component';
import { GetBookComponent } from './components/book/get-book/get-book.component';
import {HttpClientModule} from '@angular/common/http';
import { CustomerComponent } from './components/customer/customer/customer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatListModule} from '@angular/material/list'
import {MatIconModule} from '@angular/material/icon'
import { MatPaginatorModule } from '@angular/material/paginator';
import {MatButtonModule} from '@angular/material/button';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSidenavModule} from '@angular/material/sidenav' ;
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatCardModule} from '@angular/material/card';
import { LineOrderListComponent } from './components/lineOrder/lineOrderList/line-order-list/line-order-list.component';
import {EditBookComponent} from './components/book/edit-book/edit-book.component'
import {MatStepperModule} from '@angular/material/stepper';
import {MatDividerModule} from '@angular/material/divider';
import { ListBookComponent } from './components/book/list-book/list-book.component';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import { OrderComponent } from './components/order/order/order.component';
import { AuthComponent } from './components/auth/auth.component';
@NgModule({
  declarations: [
    AppComponent,
    EditBookComponent,
    AddBookComponent,
    GetBookComponent,
    CustomerComponent,
    LineOrderListComponent,
    ListBookComponent,
    OrderComponent,
    AuthComponent

  ],
  imports: [
    MatSelectModule,
    MatDividerModule,
    MatStepperModule,
    MatSnackBarModule,
    MatDatepickerModule,
    MatDialogModule,
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,
    MatListModule,
    ReactiveFormsModule ,
    MatCardModule,
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule ,
    MatPaginatorModule,  
    MatInputModule ,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatTableModule,
    MatButtonModule,
    MatGridListModule,
    MatFormFieldModule,
    BrowserAnimationsModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
