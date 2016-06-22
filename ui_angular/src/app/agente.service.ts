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
        .map(response => response.json());
    }

    removeClass(clase : string){
         let body = clase;

        return this.http.put(url + 'removeClass',body)
        .map(response => response.json());
    }

    getAgentes(){
        return this.http.get(url+'getAgentes')
        .map(response => response.json());
    }

    getAgencias(){
        return this.http.get(url+'getAgencias')
        .map(response => response.json());
    }

    sendAgent(agente:string, agencia:string){
        console.log(agencia);
        
        let body = JSON.stringify([agente, agencia]);
        let headers = new Headers({
            'Content-Type': 'application/json'
        });
        let options = new RequestOptions({ headers });
        return this.http.put(url + 'sendAgent',body,options)
        .map(response => response.json());
    }



}