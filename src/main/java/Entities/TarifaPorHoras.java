package Entities;

public class TarifaPorHoras implements Tarifa{
	
	private double tarifaHorarioNormal=1.45;
	private double tarifaHorarioReducido=0.95;
	private double tarifaHorarioSuperReducido=0.70;
	
	public double getTarifa(CDR cdr) {
		double tarifaSegunHora;
		String [] horaLlamada=cdr.getHoraLlamada().split(":");
		int hora=Integer.parseInt(horaLlamada[0]);
		if((hora<21)&&(hora>=7))
			tarifaSegunHora= tarifaHorarioNormal;
		else {
			if((hora<7)&&(hora>=1))
				tarifaSegunHora= tarifaHorarioSuperReducido;
			else
				tarifaSegunHora= tarifaHorarioReducido;
		}
		return tarifaSegunHora;
	}
}
