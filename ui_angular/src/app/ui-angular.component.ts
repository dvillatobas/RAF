import {
  Component,
  Inject
} from '@angular/core';
import {
  AgenteService,
  Agente
} from './agente.service';
import {
  CollapseDirective
} from 'ng2-bootstrap/ng2-bootstrap';
import {
  LogComponent
} from './log.component/log.component';
import {
  Http,
  HTTP_PROVIDERS
} from '@angular/http';

@Component({
  moduleId: module.id,
  selector: 'ui-angular-app',
  templateUrl: 'ui-angular.component.html',
  styleUrls: ['ui-angular.component.css'],
  directives: [CollapseDirective, LogComponent],
  providers: [AgenteService, HTTP_PROVIDERS]
})



export class UiAngularAppComponent {



  private cargar;
  private agentes: string[] = [];
  private agentesCargados: Agente[] = [];
  private agencias: string[] = [];
  private showAgencys:boolean;
  private selected : Agente;
  private last = 0;



  constructor(
    private aService: AgenteService
  ) {
    this.cargar = true;
    this.showAgencys = false;
    this.aService.getAgentes().subscribe(
      agentes => this.agentes = agentes
    );


  }

  mostrarAgentes() {
    this.cargar = !this.cargar;
  }

  cargarAgente(agente: string) {

    this.last++;
    let a = new Agente(this.last, agente);
    this.agentesCargados.push(a);
    this.cargar = !this.cargar;

    this.aService.addClass(agente).subscribe();
  }

  eliminarAgente(agente: Agente) {
    this.agentesCargados.splice(this.agentesCargados.indexOf(agente), 1);
    this.aService.removeClass(agente.name).subscribe();

  }

  mostrarAgencias(a:Agente){
    this.selected = a;
    this.showAgencys = !this.showAgencys;
    if(this.showAgencys){
      this.aService.getAgencias().subscribe(
        resp => {
          this.agencias = resp;
        }
      );
    }else{
      this.agencias = [];
      this.selected = undefined;
    }
    
    
  }

  enviarAgente(agente:Agente, agencia:string){
    this.agencias = [];
    this.aService.sendAgent(agente.name,agencia).subscribe();
  }




}