package scheduler.assignment;

public interface IGameRepo {
	public String save(Game g);

	public Game findOne(String name);

	public Game[] findAll();
}
