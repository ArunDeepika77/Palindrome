import { Component, OnInit, QueryList, Input } from '@angular/core';
import { QuotationService } from '../services/quotation.service';
import {Quotation} from "../model/quotation";
import { Router, ActivatedRoute ,Params  } from '@angular/router';
import {Palindrome} from "../model/palindrome";
import {FormControl} from '@angular/forms';
import * as $ from 'jquery';


@Component({
  selector: 'app-list-view',
  templateUrl: './list-view.component.html',
  styleUrls: ['./list-view.component.css']
})
export class ListViewComponent implements OnInit {
  quotationDateId:number = 0;
   dateValue: string;
   date : Date;
   palindromeObj : Palindrome = new Palindrome();
   myControl = new FormControl();
   options: string[] = ['One', 'Two', 'Three']
   
  public quotations: Array<Quotation>;
  public palindrome: Array<Palindrome>;
  inputkeyword: string="";
  constructor(private route: ActivatedRoute,private router: Router,private _quotationservice: QuotationService) { }



  ngOnInit() {
  this._quotationservice.getPalindrome().subscribe(data=>{
     this.palindrome = JSON.parse(JSON.stringify(data));
    
  });
  }
  onChange(quationDateValue) {
    this.quotationDateId = quationDateValue;
    var dateString;
    this._quotationservice.getQuotationListByDate(this.quotationDateId).subscribe(data => {
      this.quotations = data;
    });
}
addPalindrome(){
 if(this.inputkeyword !== this.inputkeyword.split('').reverse().join('')){
   alert("Input keyword is not palindrome");
   return;
 }
  this.palindromeObj.list = this.inputkeyword;
  this._quotationservice.addPalindrome(this.palindromeObj)
  .subscribe((data)=>{
    console.log(data);
    (err) => console.log(err)
  });
  
    alert("Palindrome");
    
  this._quotationservice.getPalindrome().subscribe(data=>{
    this.palindrome = JSON.parse(JSON.stringify(data));
   
 });

}


}
