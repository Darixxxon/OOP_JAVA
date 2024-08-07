package game.organisms.animals;

import game.World;
import game.organisms.Organism;

public class Sheep extends Animal
{
	public Sheep(World world, int x, int y)
	{
		super(world, x, y, STRENGTH_SHEEP, INITIATIVE_SHEEP, NAME_SHEEP, TYPE_ANIMAL);
	}

	@Override
	public char draw()
	{
		return (char)DRAW_SHEEP;
	}
	@Override
	public void createNew(int x, int y)
	{
		World world = this.getWorld();
		Organism sheep = new Sheep(world, x, y);
		world.addOrganism(sheep);
	}
}