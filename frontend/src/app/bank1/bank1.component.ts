import { Component, OnInit,ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {ServisService} from '../servis.service';
import {BankModel} from 'src/models/bankmodel';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';

export interface BankModelElement {
  bic:string;
  name:string;
  countryCode:string;
  auth:string;
  products:Array<string>;
}


@Component({
  selector: 'app-bank1',
  templateUrl: './bank1.component.html',
  styleUrls: ['./bank1.component.css']
})
export class Bank1Component implements OnInit {

 displayedColumns: string[] = ['bic', 'name', 'countryCode', 'auth','products'];
  dataSource : MatTableDataSource<BankModel>;
  displayedColumns1:string[]= ['bic', 'name', 'countryCode', 'auth'];
 dataSource1 : MatTableDataSource<BankModel>;
  @ViewChild("MatPaginator") paginator: MatPaginator;
  @ViewChild("MatPaginator1") paginator1:MatPaginator;

  constructor(private router: Router, private servis: ServisService) { }

  banks:BankModel[]=[];
  banks1:BankModel[]=[];
  ngOnInit(): void {

  this.servis.getAll().subscribe((res:BankModel[])=>{
  this.banks=res;
  console.log('banks',this.banks);
  console.log(this.banks[0].countryCode);

       //Assign the data to the data source for the table to render
                this.dataSource = new MatTableDataSource(this.banks);
                this.dataSource.paginator = this.paginator;
  })
    this.servis.getAll2().subscribe((res:BankModel[])=>{
    this.banks1=res;
    console.log('banks1',this.banks1);
    console.log(this.banks1[0].countryCode);

         //Assign the data to the data source for the table to render
                  this.dataSource1 = new MatTableDataSource(this.banks1);
                  this.dataSource1.paginator=this.paginator1;

    })


  }

}
