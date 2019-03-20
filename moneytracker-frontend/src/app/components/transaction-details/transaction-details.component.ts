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

  getTransaction(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.transactionService.getTransaction(id)
      .subscribe(transaction => this.transaction = transaction);
  }

  goBack(): void {
    this.location.back();
  }

  ngOnInit() {
    this.getTransaction();
  }

}
