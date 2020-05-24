
public class PlanPrepago implements Plan{
	//propiedades plan
	int id=1;
	private double tarifaHorarioNormal=1.45;
	private double tarifaHorarioReducido=0.95;
	private double tarifaHorarioSuperReducido=0.70;
		
		
	PlanPrepago(){	}
	
	public int getId() {
		return this.id;
	}
		
	public double getTarifaPorMinuto(CDR cdr) {
		double tarifaSegunHora;
		int horaLlamada=cdr.getHoraLlamada();
		if((horaLlamada<21)&&(horaLlamada>=7))
			tarifaSegunHora= tarifaHorarioNormal;
		else {
			if((horaLlamada<7)&&(horaLlamada>=1))
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
