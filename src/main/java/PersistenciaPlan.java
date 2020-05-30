import java.util.List;

public interface PersistenciaPlan {

	boolean planExists(String string);

	boolean savePlan(PlanDTO dto);

	List<Plan> loadPlans();
	

}
