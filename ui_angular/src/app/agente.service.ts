import { Injectable } from '@angular/core';


export class Agente{
    constructor(
      public id,
      public name
    ){}
    
}

@Injectable()
export class AgenteService {


    constructor() { }

}