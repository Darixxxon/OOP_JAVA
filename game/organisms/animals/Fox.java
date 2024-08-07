package game.organisms.animals;

import game.World;
import game.organisms.Organism;

import java.util.Random;

public class Fox extends Animal
{
	public Fox(World world, int x, int y)
	{
		super(world, x, y, STRENGTH_FOX, INITIATIVE_FOX, NAME_FOX, TYPE_ANIMAL);
	}
	@Override
	public void action() {
		int newX;
		int newY;
		while (true) {
			newX = x_;
			newY = y_;
			Random random = new Random();
			int randomNumberInRange = random.nextInt(4);
			switch (randomNumberInRange) {
				case 0 -> newX += 1;
				case 1 -> newY += 1;
				case 2 -> newX -= 1;
				case 3 -> newY -= 1;
			}
			Organism org = world_.getOrganism(newX, newY);
			if (org == null) {
				break;
			} else if (org.getStrength() <= this.strength_) {
				break;
			}
			if ((world_.getOrganism(x_ + 1, y_) != null && (world_.getOrganism(x_ + 1, y_).getStrength() > this.strength_)) || x_ + 1 >= world_.getTilesX()) {
				if ((world_.getOrganism(x_ - 1, y_) != null && (world_.getOrganism(x_ - 1, y_).getStrength() > this.strength_)) || x_ - 1 < 0) {
					if ((world_.getOrganism(x_, y_ + 1) != null && (world_.getOrganism(x_, y_ + 1).getStrength() > this.strength_)) || y_ + 1 >= world_.getTilesY()) {
						if ((world_.getOrganism(x_, y_ - 1) != null && (world_.getOrganism(x_, y_ - 1).getStrength() > this.strength_) || y_ - 1 < 0)) {
							break;
						}
					}
				}
			}
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
	@Override
	public char draw(){
		return DRAW_FOX;
	}

	@Override
	public void createNew(int x, int y) {
		World world = this.getWorld();
		Organism fox = new Fox(world, x, y);
		world.addOrganism(fox);
	}
}
