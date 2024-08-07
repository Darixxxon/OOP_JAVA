package game.organisms.plants;

import game.World;
import game.organisms.Organism;

public class Sosnowsky extends Plant
{
	public Sosnowsky(World world, int x, int y)
	{
		super(world, x, y, STRENGTH_SOSNOWSKY, INITIATIVE_PLANT, NAME_SOSNOWSKY, TYPE_PLANT);
	}

	public void action()
	{
		int nowX = this.getX();
		int nowY = this.getY();

		Organism org = world_.getOrganism(nowX+1, nowY);
		if (org != null && org.getType() == 'A')
		{
			this.collision(org, this);
		}
		org = world_.getOrganism(nowX-1, nowY);
		if (org != null && org.getType() == 'A')
		{
			this.collision(org, this);
		}
		org = world_.getOrganism(nowX, nowY+1);
		if (org != null && org.getType() == 'A')
		{
			this.collision(org, this);
		}
		org = world_.getOrganism(nowX, nowY-1);
		if (org != null && org.getType() == 'A')
		{
			this.collision(org, this);
		}
	}
	@Override
	public int collision(Organism orga, Organism orgd)
	{
		orga.setIsAlive(false);
		System.out.println(orgd.getName() + " killed " + orga.getName());
		return 1;
	}
	@Override
	public void createNew(int x, int y)
	{
	}
	@Override
	public char draw()
	{
		return DRAW_SOSNOWSKY;
	}
}
