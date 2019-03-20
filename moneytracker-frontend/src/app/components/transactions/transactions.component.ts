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

  getHeroes(): void {
    this.transactionService.getHeroes()
      .subscribe(transactions => this.transactions = transactions);
  }

  ngOnInit() {
    this.getHeroes();
  }

}
