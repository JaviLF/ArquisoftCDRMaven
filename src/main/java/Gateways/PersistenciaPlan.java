package Gateways;
import java.util.List;

import DTOs.PlanDTO;
import Entities.Plan;

public interface PersistenciaPlan {

	boolean planExists(String string);

	boolean savePlan(PlanDTO dto);

	List<Plan> loadPlans();
	

}
