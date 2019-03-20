import {Injectable} from '@angular/core';
import {Transaction} from "../models/Transaction";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private url = "http://localhost:8080/api/transactions";

  constructor(
    private http: HttpClient
  ) { }

  getTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.url);
  }

  getTransaction(id: number): Observable<Transaction> {
    return this.http.get<Transaction>(this.url+'/'+id);

  }
}
