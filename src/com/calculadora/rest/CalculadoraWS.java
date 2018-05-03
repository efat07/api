package com.calculadora.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/calculator")
public class CalculadoraWS {

	@GET
	@Path("/add/{subResources:.*}")
	public String sumar(@PathParam("subResources") String subResources) {
		List<Integer> listaEnteros = obtenerListaEnteros(subResources);
		int resultado = 0;
		for (Integer integer : listaEnteros) {
			resultado = resultado + integer;
		}
		return String.valueOf(resultado);
	}
	
	@GET
	@Path("/subs/{subResources:.*}")
	public String restar(@PathParam("subResources") String subResources) {
		List<Integer> listaEnteros = obtenerListaEnteros(subResources);
		int resultado = 0;
		if (listaEnteros != null && listaEnteros.size()>0) {
			resultado = listaEnteros.get(0) - resultado;
		}
		for (int i = 1; i < listaEnteros.size(); i++) {
			resultado = resultado - listaEnteros.get(i);
		}
		
		return String.valueOf(resultado);
	}
	
	@GET
	@Path("/mult/{subResources:.*}")
	public String multiplicar(@PathParam("subResources") String subResources) {
		List<Integer> listaEnteros = obtenerListaEnteros(subResources);
		int resultado = 1;
		for (Integer integer : listaEnteros) {
			resultado = resultado * integer;
		}
		return String.valueOf(resultado);
	}
	
	@GET
	@Path("/div/{subResources:.*}")
	public String get(@PathParam("subResources") String subResources) {
		List<Integer> listaEnteros = obtenerListaEnteros(subResources);
		int resultado = 1;
		try {
			if (listaEnteros != null && listaEnteros.size()>0) {
				resultado = listaEnteros.get(0) / resultado;
			}
			for (int i = 1; i < listaEnteros.size(); i++) {
				resultado = resultado / listaEnteros.get(i);
			}
			
			return String.valueOf(resultado);
		} catch (Exception e) {
			return "Error en la división";
		}
		
	}

	private List<Integer> obtenerListaEnteros(String subResources) {
		List<Integer> listaENteros = new ArrayList<Integer>();
		String numerosAcumulados = "";
		for (int i = 0; i < subResources.length(); i++) {
			if (Character.isDigit(subResources.charAt(i))) {
				//Validar el que sigue
				if ( (i+1) != subResources.length()) {
					if (Character.isDigit(subResources.charAt(i+1))) {
						numerosAcumulados = numerosAcumulados + subResources.charAt(i);
					}
					else {
						numerosAcumulados = numerosAcumulados + subResources.charAt(i); 
						listaENteros.add(Integer.parseInt(numerosAcumulados));
						numerosAcumulados = "";
					}
				}else {
					numerosAcumulados = numerosAcumulados + subResources.charAt(i); 
					listaENteros.add(Integer.parseInt(numerosAcumulados));
					numerosAcumulados = "";
				}
			}else {
				if (subResources.charAt(i) == '-') {
					//Validar el que sigue
					if ( (i+1) != subResources.length()) {
						if (Character.isDigit(subResources.charAt(i+1))) {
							numerosAcumulados = numerosAcumulados + subResources.charAt(i);
						}
					}
				}
			}
		}
		return listaENteros;
	}
}
