import java.util.List;

public interface PersistenciaPlan {

	boolean planExists(String string);

	void savePlan(PlanDTO dto);

	List<Plan> loadPlans();
	

}
