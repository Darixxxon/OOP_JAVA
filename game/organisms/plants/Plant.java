package game.organisms.plants;

//import game.Tile;
import game.World;
import game.organisms.Organism;

import java.util.Random;

public abstract class Plant extends Organism
{

	public Plant(World world, int x, int y, int strength, int initiative, String name, char type )
	{
		super(world, x, y, strength, initiative, name, TYPE_PLANT);
	}

	public void action()
	{
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

			if (newX >= 0 && newX < world_.getTilesX() && newY >= 0 && newY < world_.getTilesX()) {
				Organism org = world_.getOrganism(newX, newY);
				if (org == null) {
					this.createNew(newX, newY);
				}
			}
		}

	}
	public int collision(Organism orga, Organism orgd) {
		if (orga.getStrength() >= orgd.getStrength()) {
			orgd.setIsAlive(false);
			System.out.println(orga.getName() + " ate " + orgd.getName());
			return 1;
		}
		return 0;
	}
}
