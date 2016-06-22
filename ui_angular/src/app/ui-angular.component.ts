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
  private agentes: String[] = [];
  private agentesCargados: Agente[] = [];
  private last = 0;



  constructor(
    private aService: AgenteService
  ) {
    this.cargar = true;
    this.aService.getAgentes().subscribe(
      agentes => this.agentes = agentes
    );


  }

  mostrarAgentes() {
    this.cargar = !this.cargar;
  }

  cargarAgente(agente: string) {


    this.aService.addClass(agente).subscribe(
      clase => {
        if (clase != 'error') {
          this.last++;
          let a = new Agente(this.last, agente);
          this.agentesCargados.push(a);
          this.cargar = !this.cargar;
        }
      }
    );
  }

  eliminarAgente(agente: Agente) {

    this.aService.removeClass(agente.name).subscribe(
      clase => {
        if (clase != 'error') {
          this.agentesCargados.splice(this.agentesCargados.indexOf(agente), 1);
        }
      }
    );

  }




}