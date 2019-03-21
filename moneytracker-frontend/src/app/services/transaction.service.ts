import {Injectable} from '@angular/core';
import {Transaction} from "../models/Transaction";
import {Observable, of} from "rxjs";
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {catchError, map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private url = "http://localhost:8080/api/transactions";

  constructor(
    private http: HttpClient
  ) { }

  getTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.url)
      .pipe(
        catchError(this.handleError<Transaction[]>([]))
      );
  }

  getTransaction(id: number): Observable<Transaction> {
    return this.http.get<Transaction>(this.url+'/'+id)
      .pipe(
        catchError(this.handleError<Transaction>())
      );
  }

  saveTransaction(transaction: Transaction): Observable<Transaction> {
    const url = this.url + ( transaction.id === null ? '' :  '/'+transaction.id );
    return this.http.post<Transaction>(
        url,
        transaction,
      )
      .pipe(
        catchError(this.handleError<Transaction>())
      );
  }

  deleteTransaction(id: number): Observable<boolean> {
    return this.http.delete<null>(this.url+'/'+id, { observe: "response" })
      .pipe(
        map(response => (response.status === 200)),
        catchError(this.handleError<boolean>(false)),
      );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param result? - optional value to return as the observable result
   */
  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
