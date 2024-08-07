package game.organisms.animals;

import game.Constants;
import game.World;
import game.organisms.Organism;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public abstract class Animal extends Organism implements Constants
{
	public Animal(World world, int x, int y, int strength, int initiative, String name, char type )
	{
		super(world, x, y, strength, initiative, name, TYPE_ANIMAL);
	}

	public void action() throws IOException {
		int newX = x_;
		int newY = y_;
		Random random = new Random();
		int randomNumberInRange = random.nextInt(4);
		switch (randomNumberInRange) {
			case 0 -> newX += 1;
			case 1 -> newY += 1;
			case 2 -> newX -= 1;
			case 3 -> newY -= 1;
		}

		if (newX >= 0 && newX < world_.getTilesX() && newY >= 0 && newY < world_.getTilesY()) {
			Organism org = world_.getOrganism(newX, newY);
			if (org == null) {
				x_ = newX;
				y_ = newY;
			}
			else{
				if (org.collision(this, org) == 1) {
					x_ = newX;
					y_ = newY;
				}
			}
		}
	}
	public int collision(Organism orga, Organism orgd)
	{
		if (Objects.equals(orga.getName(), orgd.getName())) {
			this.detectFree(this.getX(), this.getY());
		}
		else {
			if (orga.getStrength() >= orgd.getStrength()) {
				orgd.setIsAlive(false);
				System.out.println(orga.getName() + " killed " + orgd.getName());
				return 1;
			}
		}
		return 0;
	}
	public abstract char draw();
	public abstract void createNew(int x, int y);
	public void detectFree(int x, int y)
	{
		while(true)
		{
			if (world_.getOrganism(x + 1, y) != null || x + 1 >= world_.getTilesX())
			{
				if (world_.getOrganism(x - 1, y) != null || x - 1 < 0)
				{
					if(world_.getOrganism(x, y + 1) != null || y + 1 >= world_.getTilesY())
					{
						if (world_.getOrganism(x, y - 1) != null || y - 1 < 0)
						{
							break;
						}
					}
				}
			}
			int newX = x;
			int newY = y;
			Random random = new Random();
			int randomNumberInRange = random.nextInt(4);
			switch (randomNumberInRange) {
				case 0 -> newX += 1;
				case 1 -> newY += 1;
				case 2 -> newX -= 1;
				case 3 -> newY -= 1;
			}
			World world_ = this.getWorld();
			if (newX >= 0 && newX < world_.getTilesX() && newY >= 0 && newY < world_.getTilesY()) {
				Organism org = world_.getOrganism(newX, newY);
				if (org == null) {
					this.createNew(newX, newY);
					return;
				}
			}
		}
	}
}
