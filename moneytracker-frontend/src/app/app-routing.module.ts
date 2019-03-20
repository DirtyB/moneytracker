import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TransactionsComponent} from "./components/transactions/transactions.component";
import {TransactionDetailsComponent} from "./components/transaction-details/transaction-details.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: '/transactions',
    pathMatch: 'full'
  },
  {
    path: 'transactions',
    component: TransactionsComponent
  },
  {
    path: 'transactions/:id',
    component: TransactionDetailsComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
