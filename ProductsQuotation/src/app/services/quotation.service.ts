import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable, BehaviorSubject} from "rxjs";
import {Quotation} from "../model/quotation";
import {Palindrome} from "../model/palindrome";



@Injectable()
export class QuotationService {

  
  private dateValue = new BehaviorSubject('');
  private result : String;
  currentDate = this.dateValue.asObservable();

  private baseUrl = 'http://localhost:8080/quotation/product/quotation/getQuotationListByDate';
  private onLoadUrl = 'http://localhost:8080/quotation/product/quotation/getQuotationListBySelectedDate';
  
  private addProductUrl = 'http://localhost:8080/quotation/product/quotation/addProduct';
  private addPalindromeUrl = 'http://localhost:8080/quotation/product/quotation/addPalindrome';
  private getPalindromeUrl = 'http://localhost:8080/quotation/product/quotation/getPalindrome';
  
  private checkExisitingUrl = 'http://localhost:8080/quotation/product/quotation/checkExisiting';
  
  
    constructor(private http:HttpClient) {

    }

    getQuotationListByDate(quotationDateId: number): Observable<any> {
      return this.http.get<Quotation>(`${this.baseUrl}/${quotationDateId}`);
    }

    passDateValue(date : string){
      this.dateValue.next(date);
    }
    getQuotationListBySelectedDate(dateValue : Date): Observable<any>{
      return this.http.get<Quotation>(`${this.onLoadUrl}/${dateValue}`);
    }
    addProduct(product : Object): Observable<any>{
      return this.http.post(`${this.addProductUrl}`,product);
    }
    getPalindrome() :Observable<any> {
      return this.http.get<Palindrome>(`${this.getPalindromeUrl}`);
    }
    addPalindrome(palindrome : object): Observable<any>{
        return this.http.post(`${this.addPalindromeUrl}`,palindrome,{responseType:'text'});
    }
}