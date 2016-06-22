import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
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

        let body = clase;

        return this.http.put(url + 'addClass',body)
        .map(response => response);
    }

    removeClass(clase : string){
         let body = clase;

        return this.http.put(url + 'removeClass',body)
        .map(response => response);
    }

    getAgentes(){
        return this.http.get(url+'getAgentes')
        .map(response => response.json());
    }



}