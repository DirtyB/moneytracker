import {Component, OnInit} from '@angular/core';
import {Transaction} from "../../models/Transaction";
import {TransactionService} from "../../services/transaction.service";

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.scss']
})
export class TransactionsComponent implements OnInit {

  transactions: Transaction[];

  constructor(private transactionService: TransactionService) { }

  getTransactions(): void {
    this.transactionService.getTransactions()
      .subscribe(transactions => this.transactions = transactions);
  }

  deleteTransaction(id: number){
    this.transactionService.deleteTransaction(id)
      .subscribe(() => this.getTransactions());
  }

  ngOnInit() {
    this.getTransactions();
  }

}
