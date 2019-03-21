import {Component, OnInit} from '@angular/core';
import {Location} from "@angular/common";
import {Transaction} from "../../models/Transaction";
import {ActivatedRoute} from "@angular/router";
import {TransactionService} from "../../services/transaction.service";

@Component({
  selector: 'app-transaction-details',
  templateUrl: './transaction-details.component.html',
  styleUrls: ['./transaction-details.component.scss']
})
export class TransactionDetailsComponent implements OnInit {

  transaction: Transaction;

  constructor(
    private route: ActivatedRoute,
    private transactionService: TransactionService,
    private location: Location
  ) { }

  getTransaction(id: number): void {
    this.transactionService.getTransaction(id)
      .subscribe(transaction => this.onTransactionReceived(transaction));
  }

  onTransactionReceived(t: Transaction): void {
    if(!t){
      this.goBack();
    }
    this.transaction = t;
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.transactionService.saveTransaction(this.transaction)
      .subscribe(() => this.goBack());
  }

  delete(): void{
    this.transactionService.deleteTransaction(this.transaction.id)
      .subscribe(() => this.goBack());
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id === "new") {
      this.transaction = {
        amount: null,
        description: null,
        date: null,
        id: null,
      };
    } else {
      this.getTransaction(+id);
    }

  }

}
