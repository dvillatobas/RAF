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
var http_1 = require('@angular/http');
require('rxjs/Rx');
var Agente = (function () {
    function Agente(id, name) {
        this.id = id;
        this.name = name;
    }
    return Agente;
}());
exports.Agente = Agente;
var url = 'http://localhost:8080/';
var AgenteService = (function () {
    function AgenteService(http) {
        this.http = http;
    }
    AgenteService.prototype.getLog = function () {
        return this.http.get(url + 'log')
            .map(function (response) { return response.json(); });
    };
    AgenteService.prototype.addClass = function (clase) {
        var body = clase;
        return this.http.put(url + 'addClass', body)
            .map(function (response) { return response; });
    };
    AgenteService.prototype.removeClass = function (clase) {
        var body = clase;
        return this.http.put(url + 'removeClass', body)
            .map(function (response) { return response; });
    };
    AgenteService.prototype.getAgentes = function () {
        return this.http.get(url + 'getAgentes')
            .map(function (response) { return response.json(); });
    };
    AgenteService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], AgenteService);
    return AgenteService;
}());
exports.AgenteService = AgenteService;
//# sourceMappingURL=../agente.service.js.map