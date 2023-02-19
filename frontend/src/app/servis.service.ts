import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ServisService {

  constructor(private http:HttpClient) { }

  uri="http://localhost:8080";

  getAll(){

  return this.http.get(`${this.uri}/v1/banks/all`);
  }
    getAll2(){

    return this.http.get(`${this.uri}/v2/banks/all`);
    }
}
