package game.organisms.plants;

import game.World;
import game.organisms.Organism;

import java.util.Random;

public class Sow_thistle extends Plant
{
	public Sow_thistle(World world, int x, int y)
	{
		super(world, x, y, STRENGTH_PLANT, INITIATIVE_PLANT, NAME_SOW_THISTLE, TYPE_PLANT);
	}
	@Override
	public void action()
	{
		for (int i = 0; i < 3; i++) {
			Random random = new Random();
			int randomNumberInRange = random.nextInt(SPREAD_RATE);
			if(randomNumberInRange==0) {
				int newX = x_;
				int newY = y_;
				random = new Random();
				randomNumberInRange = random.nextInt(4);
				switch (randomNumberInRange) {
					case 0 -> newX += 1;
					case 1 -> newY += 1;
					case 2 -> newX -= 1;
					case 3 -> newY -= 1;
				}

				if (newX >= 0 && newX < world_.getTilesX() && newY >= 0 && newY < world_.getTilesY()) {
					Organism org = world_.getOrganism(newX, newY);
					if (org == null) {
						this.createNew(newX, newY);
					}
				}
			}
		}
	}
	@Override
	public char draw()
	{
		return DRAW_SOW_THISTLE;
	}

	@Override
	public void createNew(int x, int y)
	{
		World world = this.getWorld();
		Organism sow_thistle = new Sow_thistle(world, x, y);
		world.addOrganism(sow_thistle);
	}
}
