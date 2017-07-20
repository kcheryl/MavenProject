package scheduler.assignment;

public interface IDayRepo {
	public String save(Day d);

	public Day findOne(String name);

	public Day[] findAll();
}
