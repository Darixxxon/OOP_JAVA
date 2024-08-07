package game.organisms.animals;

import game.World;
import game.organisms.Organism;
import java.io.IOException;

public class Human extends Animal
{
	private int abilityDUR_ = 0;
	private int abilityCD_ = 0;

	public Human(World world, int x, int y)
	{
		super(world, x, y, STRENGTH_HUMAN, INITIATIVE_HUMAN,NAME_HUMAN, TYPE_ANIMAL);
	}
	@Override
	public int getDUR()
	{
		return abilityDUR_;
	}
	@Override
	public void action() throws IOException {
		if (this.abilityCD_ > 0)
		{
			if (this.abilityDUR_ > 0)
			{
				this.setStrength(this.getStrength() - 1);
				this.abilityDUR_--;
			}
			this.abilityCD_--;
		}

		int newX = x_;
		int newY = y_;
		switch (world_.getHumanMove()) {
			case 1 -> newX -= 1;
			case 2 -> newX += 1;
			case 3 -> newY -= 1;
			case 4 -> newY += 1;
			case 5 -> ability();
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
		if (this.abilityDUR_ > 0)
		{
			return DRAW_SUPER_HUMAN;
		}
		else
		{
			return DRAW_NORMAL_HUMAN;
		}
	}

	public void ability() {
		//magical potion
		if(abilityCD_==0)
		{
			this.setStrength(ABILITY_SET_STRENGTH);
			this.abilityCD_ = ABILITY_COOLDOWN;
			this.abilityDUR_ = ABILITY_DURATION;
		}
	}
	@Override
	public void createNew(int x, int y)
	{
	}

}