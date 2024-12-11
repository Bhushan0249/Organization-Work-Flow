import { HttpClient } from '@angular/common/http';
import { ReturnStatement } from '@angular/compiler';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http:HttpClient) {  }

  baseUrl:string="http://localhost:8080/api/";

  getAllEmp(){
   return ( this.http.get(`${this.baseUrl}getAllemp`))
  }

getEmpById(id:any){

 return( this.http.get(`${this.baseUrl}getByemp/${id}`));
}


getAllCountry(){
 return( this.http.get(`${this.baseUrl}getAllCountry`));
}

postMapping(obj:any){
 return ( this.http.post(`${this.baseUrl}addemp`,obj,{
  responseType:'text'
 }));

}

updateEmpData(obj:any){

  return ( this.http.put(`${this.baseUrl}updateemp/${obj.id}`,
    obj,{
      responseType:'text'
    }));

}

deleteData(id:any){

  return ( this.http.delete(`${this.baseUrl}deleteemp/${id}`,{
    responseType:'text'
  }));


}

}
//  deleteData(id:any){
//   return (this.http.delete(`${this.baseUrl}deleteemp/${id}`,{
//     responseType:'text'
//   }));
// }