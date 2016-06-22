"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var agente_service_1 = require('./agente.service');
var ng2_bootstrap_1 = require('ng2-bootstrap/ng2-bootstrap');
var log_component_1 = require('./log.component/log.component');
var http_1 = require('@angular/http');
var UiAngularAppComponent = (function () {
    function UiAngularAppComponent(aService) {
        var _this = this;
        this.aService = aService;
        this.agentes = [];
        this.agentesCargados = [];
        this.agencias = [];
        this.last = 0;
        this.cargar = true;
        this.showAgencys = false;
        this.aService.getAgentes().subscribe(function (agentes) { return _this.agentes = agentes; });
    }
    UiAngularAppComponent.prototype.mostrarAgentes = function () {
        this.cargar = !this.cargar;
    };
    UiAngularAppComponent.prototype.cargarAgente = function (agente) {
        this.last++;
        var a = new agente_service_1.Agente(this.last, agente);
        this.agentesCargados.push(a);
        this.cargar = !this.cargar;
        this.aService.addClass(agente).subscribe();
    };
    UiAngularAppComponent.prototype.eliminarAgente = function (agente) {
        this.agentesCargados.splice(this.agentesCargados.indexOf(agente), 1);
        this.aService.removeClass(agente.name).subscribe();
    };
    UiAngularAppComponent.prototype.mostrarAgencias = function (a) {
        var _this = this;
        this.selected = a;
        this.showAgencys = !this.showAgencys;
        if (this.showAgencys) {
            this.aService.getAgencias().subscribe(function (resp) {
                _this.agencias = resp;
            });
        }
        else {
            this.agencias = [];
            this.selected = undefined;
        }
    };
    UiAngularAppComponent.prototype.enviarAgente = function (agente, agencia) {
        this.agencias = [];
        this.aService.sendAgent(agente.name, agencia).subscribe();
    };
    UiAngularAppComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'ui-angular-app',
            templateUrl: 'ui-angular.component.html',
            styleUrls: ['ui-angular.component.css'],
            directives: [ng2_bootstrap_1.CollapseDirective, log_component_1.LogComponent],
            providers: [agente_service_1.AgenteService, http_1.HTTP_PROVIDERS]
        }), 
        __metadata('design:paramtypes', [agente_service_1.AgenteService])
    ], UiAngularAppComponent);
    return UiAngularAppComponent;
}());
exports.UiAngularAppComponent = UiAngularAppComponent;
//# sourceMappingURL=../ui-angular.component.js.map