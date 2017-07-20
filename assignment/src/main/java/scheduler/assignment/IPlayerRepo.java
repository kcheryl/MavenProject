package scheduler.assignment;

public interface IPlayerRepo {
	public String save(Player p);

	public Player findOne(String name);

	public Player[] findAll();
}
