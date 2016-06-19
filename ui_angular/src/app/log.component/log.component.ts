import { Component, OnInit } from '@angular/core';
import { AgenteService } from '../agente.service';
import 'rxjs/Rx';

@Component({
    moduleId: module.id,
    selector: 'log',
    templateUrl: 'log.component.html'
})
export class LogComponent implements OnInit {
    private lineas:String[] = [];
    constructor(
        private aService:AgenteService
    ) { }

    ngOnInit() { 
        
        this.refreshLog(3000);
    }

    refreshLog(time:number){
        setInterval(() => this.getLog(), time);
    }

    getLog(){
        console.log('refrescando');
        
        this.aService.getLog().subscribe(
            response => {
                this.lineas = response;
            },
            error => console.log(error)
        );
    }

}