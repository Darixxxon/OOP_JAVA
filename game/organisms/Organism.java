package game.organisms;

import game.Constants;
import game.World;

import java.io.IOException;

public abstract class Organism implements Constants
{
	protected static World world_;
	protected int x_;
	protected int y_;
	protected int strength_;
	protected int initiative_;
	protected boolean isAlive_;
	protected int age_;
	protected String name_;
	protected char type_;
	protected int abilityCD_;
	protected int abilityDUR_;


	public Organism(World world, int x, int y, int strength, int initiative, String name, char type)
	{
		Organism.world_ = world;
		this.x_ = x;
		this.y_ = y;
		this.strength_ = strength;
		this.initiative_ = initiative;
		this.age_ = 0;
		this.isAlive_ = true;
		this.name_ = name;
		this.type_ = type;
	}
	public int getX() { return x_; }
	public int getY() { return y_; }
	public int getStrength()
	{
		return strength_;
	}
	public int getInitiative()
	{
		return initiative_;
	}
	public int getAge() {
		return age_;
	}
	public World getWorld() { return world_;}
	public int getCD() { return abilityCD_; }
	public int getDUR() { return abilityDUR_; }
	public String getName() { return name_; }
	public char getType() { return type_; }
	public boolean getIsAlive()
	{
		return isAlive_;
	}
	public void setX(int x)
	{
		this.x_ = x;
	}
	public void setY(int y)
	{
		this.y_ = y;
	}
	public void setStrength(int strength)
	{
		this.strength_ = strength;
	}
	public void setInitiative(int initiative)
	{
		this.initiative_ = initiative;
	}
	public void setAge(int age)
	{
		this.age_ = age;
	}
	public void setWorld(World world)
	{
		world_ = world;
	}
	public void setCD(int abilityCD)
	{
		this.abilityCD_ = abilityCD;
	}
	public void setDUR(int abilityDUR)
	{
		this.abilityDUR_ = abilityDUR;
	}
	public void setIsAlive(boolean isAlive)
	{
		this.isAlive_ = isAlive;
	}

	public abstract void action() throws IOException;
	public abstract int collision(Organism orga, Organism orgd);
	public abstract char draw();
	public abstract void createNew(int x, int y);
}
