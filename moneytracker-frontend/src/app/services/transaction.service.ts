import {Injectable} from '@angular/core';
import {Transaction} from "../models/Transaction";
import {TRANSACTIONS} from "../mock-transactions";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor() { }

  getTransactions(): Observable<Transaction[]> {
    return of(TRANSACTIONS);
  }

  getTransaction(id: number): Observable<Transaction> {
    return of(TRANSACTIONS.find(transaction => transaction.id === id));
  }
}
