import { Injectable } from '@angular/core';
import { Http } from '@angular/http';


export class Agente{
    constructor(
      public id,
      public name
    ){}
    
}

@Injectable()
export class AgenteService {


    constructor(
        private http:Http
    ) { }

    refreshLog(){
        this.http.get('');
    }

}