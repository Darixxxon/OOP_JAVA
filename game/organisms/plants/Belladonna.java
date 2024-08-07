package game.organisms.plants;

import game.World;
import game.organisms.Organism;

public class Belladonna extends Plant
{
	public Belladonna(World world, int x, int y)
	{
		super(world, x, y, STRENGTH_BELLADONNA, INITIATIVE_PLANT, NAME_BELLADONNA, TYPE_PLANT);
	}

	@Override
	public int collision(Organism orga, Organism orgd)
	{
		orga.setIsAlive(false);
		System.out.println(orgd.getName() + " killed " + orga.getName());
		return 0;
	}

	@Override
	public char draw()
	{
		return DRAW_BELLADONNA;
	}

	@Override
	public void createNew(int x, int y)
	{
		World world = this.getWorld();
		Organism belladonna = new Belladonna(world, x, y);
		world.addOrganism(belladonna);
	}
}

