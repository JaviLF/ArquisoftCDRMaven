import java.util.List;

public interface PersistenciaPlan {

	public void savePlan(PlanDTO dto);

	public List<Plan> loadPlans();
	
}
