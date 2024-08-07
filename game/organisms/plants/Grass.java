package game.organisms.plants;

import game.World;
import game.organisms.Organism;

public class Grass extends Plant
{
	public Grass(World world, int x, int y)
	{
		super(world, x, y, STRENGTH_PLANT, INITIATIVE_PLANT, NAME_GRASS, TYPE_PLANT);
	}

	@Override
	public char draw()
	{
		return DRAW_GRASS;
	}

	@Override
	public void createNew(int x, int y)
	{
		World world = this.getWorld();
		Organism grass = new Grass(world, x, y);
		world.addOrganism(grass);
	}
}
