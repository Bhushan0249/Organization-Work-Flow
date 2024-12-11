import { Component, OnInit } from '@angular/core';
import { Employee } from '../model/employee';
import { HttpService } from '../http.service';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { __param } from 'tslib';

@Component({
  selector: 'app-addemp',
  templateUrl: './addemp.component.html',
  styleUrls: ['./addemp.component.css']
})
export class AddempComponent implements OnInit {
  
allCountry:any[]=[];

  addData:any=<Employee>{};

  isUpdated:boolean=false;
  constructor(private service:HttpService,
    private router:Router,
    private route :ActivatedRoute
  ){}


ngOnInit(): void {

 this.getAllDataBackend();
 this.getDataFromUrl();
 
}


onSubmit(){

  if(this.isUpdated){

    // upadate.

    this.addData.updatedBy="bhushan";
    this.addData.updatedDate= Date.now();
    this.service.updateEmpData(this.addData).subscribe((response)=>{
      console.log(response);
      this.router.navigate(['']);
    })

  }


    this.addData.createdBy="Admin";
    this.addData.createdDate= Date.now();
    this.addData.updatedBy="Admin";
    this.addData.updatedDate=Date.now();
    console.log(this.addData);
    
    this.service.postMapping(this.addData).subscribe((response)=>{
      console.log(response);
      this.router.navigate(['']);
      
    })

  
  
}



getAllDataBackend(){
  
  this.service.getAllCountry()
  .subscribe((response:any)=>{
    // console.log(response)
    
    this.allCountry=response;
    
    
  })
}



getDataFromUrl(){
  this.route.paramMap.subscribe((param)=>{
    // console.log(param)
    this.isUpdated=true;
    this.getEmpByIdFromBackednd(param.get("id"));


})

}

getEmpByIdFromBackednd(id:any){
  this.service.getEmpById(id).subscribe((response)=>{
    // console.log(response);
    this.addData=response;
  })

}


}


  


