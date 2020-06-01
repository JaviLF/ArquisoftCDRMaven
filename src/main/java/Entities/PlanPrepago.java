package Entities;
public class PlanPrepago implements Plan{
	//propiedades plan
	int id=1;
	
	private double tarifaHorarioNormal=1.45;
	private double tarifaHorarioReducido=0.95;
	private double tarifaHorarioSuperReducido=0.70;
		
		
	public PlanPrepago(){	}
	
	public int getId() {
		return this.id;
	}
		
	public double getTarifaPorMinuto(CDR cdr) {
		double tarifaSegunHora;
		String [] horaLlamada=cdr.getHoraLlamada().split(":");
		int hora=Integer.parseInt(horaLlamada[0]);
		int minuto=Integer.parseInt(horaLlamada[1]);
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
		/*
		public void setTarifaHorarioNormal(double tarifaHorarioNormal) {
			this.tarifaHorarioNormal=tarifaHorarioNormal;
		}
		public void setTarifaHorarioReducido(double tarifaHorarioReducido) {
			this.tarifaHorarioReducido=tarifaHorarioReducido;
		}
		public void setTarifaHorarioSuperReducido(double tarifaHorarioSuperReducido) {
			this.tarifaHorarioSuperReducido=tarifaHorarioSuperReducido;
		}
		*/
}
