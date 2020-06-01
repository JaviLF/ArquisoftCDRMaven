package Entities;

public class PlanFactory {
	public Plan generarPlanById(int id) {
		Plan plan=new PlanPrepago();
		switch(id) {
		case(1):
			plan=new PlanPrepago();
			break;
		case(2):
			plan=new PlanPostpago();
			break;
		case(3):
			plan=new PlanWow();
			break;
		}
		return plan;
	}
	public Plan generarPlanByName(String name) {
		Plan plan=new PlanPrepago();
		switch(name) {
		case("Prepago"):
			plan=new PlanPrepago();
			break;
		case("Postpago"):
			plan=new PlanPostpago();
			break;
		case("Wow"):
			plan=new PlanWow();
			break;
		}
		return plan;
	}
}
