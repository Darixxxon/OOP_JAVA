package game.organisms.plants;

import game.World;
import game.organisms.Organism;

public class Guarana extends Plant
{
	public Guarana(World world, int x, int y)
	{
		super(world, x, y, STRENGTH_PLANT, INITIATIVE_PLANT, NAME_GURANA, TYPE_PLANT);
	}
	@Override
	public int collision(Organism orga, Organism orgd)
	{
		if (orga.getStrength() >= orgd.getStrength()) {
			orgd.setIsAlive(false);
			orga.setStrength(orga.getStrength() + 3);
			System.out.println(orga.getName() + " ate " + orgd.getName());
			return 1;
		}
		return 0;
	}
	@Override
	public char draw()
	{
		return DRAW_GUARANA;
	}

	@Override
	public void createNew(int x, int y)
	{
		World world = this.getWorld();
		Organism guarana = new Guarana(world, x, y);
		world.addOrganism(guarana);
	}
}
