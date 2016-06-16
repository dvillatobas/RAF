import { Component, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'log',
    templateUrl: 'log.component.html'
})
export class LogComponent implements OnInit {
    private lineas:String[];
    constructor() { }

    ngOnInit() { 
        this.lineas = ['hola', '[eeeee]', 'que tal'];
    }

}