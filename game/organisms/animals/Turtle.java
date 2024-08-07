package game.organisms.animals;
import game.World;
import game.organisms.Organism;

import java.util.Objects;
import java.util.Random;

public class Turtle extends Animal
{
	public Turtle(World world, int x, int y)
	{
		super(world, x, y, STRENGTH_TURTLE, INITIATIVE_TURTLE, NAME_TURTLE, TYPE_ANIMAL);
	}

	@Override
	public void action()
	{
		Random random = new Random();
		int randomNumberInRange = random.nextInt(4);
		if (randomNumberInRange== 3)
		{
			randomNumberInRange = random.nextInt(4);
			int newX = x_;
			int newY = y_;
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
				else {
					if (org.collision(this, org) == 1) {
						x_ = newX;
						y_ = newY;
					}
				}
			}
		}
	}
	@Override
	public int collision(Organism orga, Organism orgd)
	{
		if (Objects.equals(orga.getName(), orgd.getName())) {
			this.detectFree(this.getX(), this.getY());
		}
		else {
			if (orga.getStrength() < 5)
			{
				return 0;
			}
			else if (orga.getStrength() >= orgd.getStrength()) {
				orgd.setIsAlive(false);
				System.out.println(orga.getName() + " killed " + orgd.getName());
				return 1;
			}
			return 0;
		}
		return 0;
	}
	@Override
	public char draw()
	{
		return DRAW_TURTLE;
	}
	@Override
	public void createNew(int x, int y)
	{
		World world = this.getWorld();
		Organism turtle = new Turtle(world, x, y);
		world.addOrganism(turtle);
	}
}
