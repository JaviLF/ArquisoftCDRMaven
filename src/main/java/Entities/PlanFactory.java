package Entities;

public class PlanFactory {
	public Plan generarPlanById(int id) {
		Plan plan=new PlanPrepago();
		switch(id) {
		case(1):plan=new PlanPrepago();
		case(2):plan=new PlanPostpago();
		case(3):plan=new PlanWow();
		}
		return plan;
	}
	public Plan generarPlanByName(String name) {
		Plan plan=new PlanPrepago();
		switch(name) {
		case("Prepago"):plan=new PlanPrepago();
		case("Postpago"):plan=new PlanPostpago();
		case("Wow"):plan=new PlanWow();
		}
		return plan;
	}
}
