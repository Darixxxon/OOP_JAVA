package game.organisms.animals;

import game.World;
import game.organisms.Organism;

public class Wolf extends Animal
{
	public Wolf(World world, int x, int y)
	{
		super(world, x, y, STRENGTH_WOLF, INITIATIVE_WOLF, NAME_WOLF, TYPE_ANIMAL);
	}

	@Override
	public char draw()
	{
		return DRAW_WOLF;
	}
	@Override
	public void createNew(int x, int y)
	{
		World world = this.getWorld();
		Organism wolf = new Wolf(world, x, y);
		world.addOrganism(wolf);
	}
}
