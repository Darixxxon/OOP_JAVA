package game.organisms.animals;

import game.World;
import game.organisms.Organism;

import java.util.Random;

public class Antelope extends Animal
{
	public Antelope(World world, int x, int y)
	{
		super(world, x, y, STRENGTH_ANTELOPE, INITIATIVE_ANTELOPE, NAME_ANTELOPE, TYPE_ANIMAL);
	}

	@Override
	public void action() {
		int newX = x_;
		int newY = y_;
		Random random = new Random();
		int randomNumberInRange = random.nextInt(4);
		switch (randomNumberInRange) {
			case 0 -> newX += 2;
			case 1 -> newY += 2;
			case 2 -> newX -= 2;
			case 3 -> newY -= 2;
		}

		if (newX >= 0 && newX < world_.getTilesX() && newY >= 0 && newY < world_.getTilesY()) {
			Organism org = world_.getOrganism(newX, newY);
			if (org == null) {
				x_ = newX;
				y_ = newY;
			} else {
				if (org.collision(this, org) == 1) {
					x_ = newX;
					y_ = newY;
				}
			}
		}
	}
	@Override
	public int collision(Organism orga, Organism orgd)
	{
		if (orga.getName() == orgd.getName()) {
			this.detectFree(this.getX(), this.getY());
		}
		else {
			Random random = new Random();
			int randomNumberInRange = random.nextInt(4);
			if (randomNumberInRange == 1)
			{
				int f = 0;
				while (f!=4)
				{
					f = 0;
					if (world_.getOrganism(orgd.getX() + 1, orgd.getY()) != null || orgd.getX() + 1 >= world_.getTilesX())
					{
						f += 1;
						if (world_.getOrganism(orgd.getX() - 1, orgd.getY()) != null || orgd.getX() - 1 < 0)
						{
							f += 1;
							if (world_.getOrganism(orgd.getX(), orgd.getY() + 1) != null || orgd.getY() + 1 >= world_.getTilesY())
							{
								f += 1;
								if (world_.getOrganism(orgd.getX(), orgd.getY() - 1) != null || orgd.getY() - 1 < 0)
								{
									f += 1;
								}
							}
						}
					}
					int orgX = orgd.getX();
					int orgY = orgd.getY();
					random = new Random();
					randomNumberInRange = random.nextInt(4);
					switch (randomNumberInRange) {
						case 0 -> orgX += 1;
						case 1 -> orgY += 1;
						case 2 -> orgX -= 1;
						case 3 -> orgY -= 1;
					}
					Organism org1 = world_.getOrganism(orgX, orgY);
					if (orgX >= 0 && orgX < world_.getTilesX() && orgY >= 0 && orgY < world_.getTilesY() && org1 == null)
					{
						orgd.setX(orgX);
						orgd.setY(orgY);
						return 0;
					}
				}
			}
			else
			{
				if (orga.getStrength() >= orgd.getStrength())
				{
					orgd.setIsAlive(false);
					System.out.println(orga.getName() + " killed " + orgd.getName());
					return 1;
				}
				else
				{
					return 0;
				}
			}
		}
		return 0;
	}

	public char draw()
	{
		return (char)DRAW_ANTELOPE;
	}

	public void createNew(int x, int y)
	{
		World world = this.getWorld();
		Organism antelope = new Antelope(world, x, y);
		world.addOrganism(antelope);
	}
}
