import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpService } from '../http.service';
import { Employee } from '../model/employee';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

empObj:any=<Employee>{};

constructor(private route :ActivatedRoute,
              private service:HttpService
){}
  ngOnInit(): void {
    this.getDataFromUrl();
  }



  getDataFromUrl(){
this.route.paramMap.subscribe((parm)=>{


// console.log(parm.get("id"));
this.getDataFromBackend(parm.get("id"));

  })
  }

  getDataFromBackend(id:any){
this.service.getEmpById(id).subscribe((response)=>{

  // console.log(response);

  this.empObj=response;


})

  }

}
