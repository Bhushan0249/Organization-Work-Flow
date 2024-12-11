import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  
  empData:any[]=[];
  isRadioclid:boolean=false;
  id!:any;
  
  constructor(private service:HttpService,
    private router :Router
  ){}


  ngOnInit(): void {
    this.getDataFromBackand();
  }

  getDataFromBackand(){

    this.service.getAllEmp()
    .subscribe((response:any)=>{

      // console.log(response)
      this.empData=response;
    })    
  }

  onRadioClick(id:any){

    this.isRadioclid=true;
    this.id=id;

  }



  onUpdate(){

    if(this.isRadioclid){

    this.router.navigate(['/updateEmp',this.id])

    } else{
      alert("please select employee to be updated.....")
    }


  }

  onDelete(){
    if(this.isRadioclid){
      // Delete Logic 
      this.service.deleteData(this.id)
      .subscribe((response)=>{
        // console.log(response);
        this.getDataFromBackand();
      });
    }else{
      alert("Please Select Employee to Be Deleted...");
    }
    
  }



}
