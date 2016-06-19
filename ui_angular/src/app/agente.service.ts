import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/Rx';


export class Agente{
    constructor(
      public id,
      public name
    ){}
    
}

const url = 'http://localhost:8080/';

@Injectable()
export class AgenteService {


    constructor(
        private http:Http
    ) { }

    getLog(){
        return this.http.get(url + 'log')
        .map(response => response.json());
    }

    addClass(clase : string){

        let body = JSON.stringify(clase);

        return this.http.put(url + 'addClass',body)
        .map(response => response.json());
    }

    getAgentes(){
        return this.http.get(url+'getAgentes')
        .map(response => response.json());
    }

}