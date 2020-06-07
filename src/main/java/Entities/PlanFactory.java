package Entities;

import java.util.List;

public class PlanFactory {
	
	
	public Plan generarPlanByName(String name,List<String>NumerosAmigos) {
		Plan plan=null;
		
		switch(name) {
		case("prepago"):
			plan=new PlanPrepago(new TarifaPorHoras());
			break;
		case("postpago"):
			plan=new PlanPostpago(new TarifaFija());
			break;
		case("wow"):
			plan=new PlanWow(new TarifaFija(),NumerosAmigos);
			break;
		}
		return plan;
	}
	
}
