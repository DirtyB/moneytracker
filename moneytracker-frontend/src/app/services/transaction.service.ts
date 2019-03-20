import {Injectable} from '@angular/core';
import {Transaction} from "../models/Transaction";
import {TRANSACTIONS} from "../mock-transactions";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor() { }

  getHeroes(): Observable<Transaction[]> {
    return of(TRANSACTIONS);
  }
}
