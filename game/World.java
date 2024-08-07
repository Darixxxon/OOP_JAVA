package game;

import game.organisms.Organism;
import game.organisms.animals.*;
import game.organisms.plants.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class World implements Constants
{
	public ArrayList<Organism> organisms_ = new ArrayList<>();
	private int tilesX;
	private int tilesY;
	public int isHumanLive_ = 0;
	public int movement = 0;


	public World(int width, int height)
	{
		this.tilesX = width;
		this.tilesY = height;
		generateWorld(height, width, this);
	}
	public void generateWorld(int height, int width, World world)
	{
		int r = (width * height) / 100;

		Random random = new Random();
		int randomX = random.nextInt(width);
		int randomY = random.nextInt(height);
		Organism human = new Human(world, randomX, randomY);
		world.addOrganism(human);
		for (int i = 0; i <= r; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				randomX = random.nextInt(width);
				randomY = random.nextInt(height);
				Organism org = world.getOrganism(randomX, randomY);
				if (org == null)
				{
					if(j==0)
					{
						Organism antelope = new Antelope(world, randomX, randomY);
						world.addOrganism(antelope);
					}
					else if (j == 1)
					{
						Organism belladonna = new Belladonna(world, randomX, randomY);
						world.addOrganism(belladonna);
					}
					else if (j == 2)
					{
						Organism fox = new Fox(world, randomX, randomY);
						world.addOrganism(fox);
					}
					else if (j == 3)
					{
						Organism grass = new Grass(world, randomX, randomY);
						world.addOrganism(grass);
					}
					else if (j == 4)
					{
						Organism guarana = new Guarana(world, randomX, randomY);
						world.addOrganism(guarana);
					}
					else if (j == 5)
					{
						Organism sheep = new Sheep(world, randomX, randomY);
						world.addOrganism(sheep);
					}
					else if (j == 6)
					{
						Organism sosnowsky = new Sosnowsky(world, randomX, randomY);
						world.addOrganism(sosnowsky);
					}
					else if (j == 7)
					{
						Organism sow_thistle = new Sow_thistle(world, randomX, randomY);
						world.addOrganism(sow_thistle);
					}
					else if (j == 8)
					{
						Organism turtle = new Turtle(world, randomX, randomY);
						world.addOrganism(turtle);
					}
					else
					{
						Organism wolf = new Wolf(world, randomX, randomY);
						world.addOrganism(wolf);
					}
				}
			}
		}
	}
	public Organism getOrganism(int x, int y)
	{
		for(Organism organism : organisms_)
		{
			if(organism.getX() == x && organism.getY() == y){
				return organism;
			}
		}
		return null;
	}
	public void addOrganism(Organism organism)
	{
		organisms_.add(organism);
		organism.setWorld(this);
	}

	public void makeTurn() throws IOException {
		// sort organisms in descending order based on initiative and age
		organisms_.sort((o1, o2) -> {
			if (o1.getInitiative() != o2.getInitiative()) {
				return o2.getInitiative() - o1.getInitiative();
			} else {
				return o1.getAge() - o2.getAge();
			}
		});
		Organism organism;
		for (int i = 0; i < organisms_.size(); i++)
		{
			organism = organisms_.get(i);
			if (organism.getAge() > 0 && organism.getIsAlive())
			{
				organism.action();
				if ((organism.getName() == NAME_HUMAN) && organism.getStrength() == 1)
				{
					return;
				}
			}
		}
		for (int i = 0; i < organisms_.size(); i++) {
			organism = organisms_.get(i);
			if (!organism.getIsAlive()) {
				organisms_.remove(i);
			}
			organism.setAge(organism.getAge() + 1);
		}
	}
	public int getTilesX()
	{
		return tilesX;
	}
	public int getTilesY()
	{
		return tilesY;
	}
	public int getHumanMove(){
		int dir = this.movement;
		movement = 0;
		return dir;
	}
	public void save(File file) throws IOException
	{
		StringBuilder str = new StringBuilder();
		str.append(this.getTilesX());
		str.append("\n");
		str.append(this.getTilesY());
		str.append("\n");
		Organism organism;
		str.append(organisms_.size());
		str.append("\n");
		for (int i = 0; i < organisms_.size(); i++)
		{
			organism = organisms_.get(i);
			if (organism != null) {
				str.append(organism.draw() + "\n");
				str.append(organism.getX() + "\n");
				str.append(organism.getY() + "\n");
				str.append(organism.getStrength() + "\n");
				str.append(organism.getInitiative() + "\n");
				str.append(organism.getAge() + "\n");
				str.append(organism.getIsAlive() + "\n");
				if (organism.draw() == 'h' || organism.draw() == 'H') {
					str.append(organism.getCD() + "\n");
					str.append(organism.getDUR() + "\n");
				}
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(str.toString());
		writer.close();
	}
	public void open(File file) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		int tilesX = Integer.parseInt(reader.readLine());
		int tilesY = Integer.parseInt(reader.readLine());
		this.tilesX= tilesX;
		this.tilesY= tilesY;
		this.organisms_.clear();
		int num = Integer.parseInt(reader.readLine());
		for(int i = 0; i<num; i++) {
			char draw = reader.readLine().charAt(0);
			if(draw == DRAW_NORMAL_HUMAN || draw == DRAW_SUPER_HUMAN)
			{
				int x = Integer.parseInt(reader.readLine());
				int y = Integer.parseInt(reader.readLine());
				Organism human = new Human(this, x, y);
				this.addOrganism(human);
				human.setStrength(Integer.parseInt(reader.readLine()));
				human.setInitiative(Integer.parseInt(reader.readLine()));
				human.setAge(Integer.parseInt(reader.readLine()));
				human.setIsAlive(Boolean.parseBoolean(reader.readLine()));
				human.setCD(Integer.parseInt(reader.readLine()));
				human.setDUR(Integer.parseInt(reader.readLine()));
			}
			else if(draw == DRAW_ANTELOPE)
			{
				int x = Integer.parseInt(reader.readLine());
				int y = Integer.parseInt(reader.readLine());
				Organism antelope = new Antelope(this, x, y);
				this.addOrganism(antelope);
				antelope.setStrength(Integer.parseInt(reader.readLine()));
				antelope.setInitiative(Integer.parseInt(reader.readLine()));
				antelope.setAge(Integer.parseInt(reader.readLine()));
				antelope.setIsAlive(Boolean.parseBoolean(reader.readLine()));
			}
			else if(draw == DRAW_BELLADONNA)
			{
				int x = Integer.parseInt(reader.readLine());
				int y = Integer.parseInt(reader.readLine());
				Organism belladonna = new Belladonna(this, x, y);
				this.addOrganism(belladonna);
				belladonna.setStrength(Integer.parseInt(reader.readLine()));
				belladonna.setInitiative(Integer.parseInt(reader.readLine()));
				belladonna.setAge(Integer.parseInt(reader.readLine()));
				belladonna.setIsAlive(Boolean.parseBoolean(reader.readLine()));
			}
			else if(draw == DRAW_FOX)
			{
				int x = Integer.parseInt(reader.readLine());
				int y = Integer.parseInt(reader.readLine());
				Organism fox = new Fox(this, x, y);
				this.addOrganism(fox);
				fox.setStrength(Integer.parseInt(reader.readLine()));
				fox.setInitiative(Integer.parseInt(reader.readLine()));
				fox.setAge(Integer.parseInt(reader.readLine()));
				fox.setIsAlive(Boolean.parseBoolean(reader.readLine()));
			}
			else if(draw == DRAW_SHEEP)
			{
				int x = Integer.parseInt(reader.readLine());
				int y = Integer.parseInt(reader.readLine());
				Organism sheep = new Sheep(this, x, y);
				this.addOrganism(sheep);
				sheep.setStrength(Integer.parseInt(reader.readLine()));
				sheep.setInitiative(Integer.parseInt(reader.readLine()));
				sheep.setAge(Integer.parseInt(reader.readLine()));
				sheep.setIsAlive(Boolean.parseBoolean(reader.readLine()));
			}
			else if(draw == DRAW_GRASS)
			{
				int x = Integer.parseInt(reader.readLine());
				int y = Integer.parseInt(reader.readLine());
				Organism grass = new Grass(this, x, y);
				this.addOrganism(grass);
				grass.setStrength(Integer.parseInt(reader.readLine()));
				grass.setInitiative(Integer.parseInt(reader.readLine()));
				grass.setAge(Integer.parseInt(reader.readLine()));
				grass.setIsAlive(Boolean.parseBoolean(reader.readLine()));
			}
			else if(draw == DRAW_GUARANA)
			{
				int x = Integer.parseInt(reader.readLine());
				int y = Integer.parseInt(reader.readLine());
				Organism guarana = new Guarana(this, x, y);
				this.addOrganism(guarana);
				guarana.setStrength(Integer.parseInt(reader.readLine()));
				guarana.setInitiative(Integer.parseInt(reader.readLine()));
				guarana.setAge(Integer.parseInt(reader.readLine()));
				guarana.setIsAlive(Boolean.parseBoolean(reader.readLine()));
			}
			else if(draw == DRAW_SOSNOWSKY)
			{
				int x = Integer.parseInt(reader.readLine());
				int y = Integer.parseInt(reader.readLine());
				Organism sosnowsky = new Sosnowsky(this, x, y);
				this.addOrganism(sosnowsky);
				sosnowsky.setStrength(Integer.parseInt(reader.readLine()));
				sosnowsky.setInitiative(Integer.parseInt(reader.readLine()));
				sosnowsky.setAge(Integer.parseInt(reader.readLine()));
				sosnowsky.setIsAlive(Boolean.parseBoolean(reader.readLine()));
			}
			else if(draw == DRAW_SOW_THISTLE)
			{
				int x = Integer.parseInt(reader.readLine());
				int y = Integer.parseInt(reader.readLine());
				Organism sow_thistle = new Sow_thistle(this, x, y);
				this.addOrganism(sow_thistle);
				sow_thistle.setStrength(Integer.parseInt(reader.readLine()));
				sow_thistle.setInitiative(Integer.parseInt(reader.readLine()));
				sow_thistle.setAge(Integer.parseInt(reader.readLine()));
				sow_thistle.setIsAlive(Boolean.parseBoolean(reader.readLine()));
			}
			else if(draw == DRAW_TURTLE)
			{
				int x = Integer.parseInt(reader.readLine());
				int y = Integer.parseInt(reader.readLine());
				Organism turtle = new Turtle(this, x, y);
				this.addOrganism(turtle);
				turtle.setStrength(Integer.parseInt(reader.readLine()));
				turtle.setInitiative(Integer.parseInt(reader.readLine()));
				turtle.setAge(Integer.parseInt(reader.readLine()));
				turtle.setIsAlive(Boolean.parseBoolean(reader.readLine()));
			}
			else if(draw == DRAW_WOLF)
			{
				int x = Integer.parseInt(reader.readLine());
				int y = Integer.parseInt(reader.readLine());
				Organism wolf = new Wolf(this, x, y);
				this.addOrganism(wolf);
				wolf.setStrength(Integer.parseInt(reader.readLine()));
				wolf.setInitiative(Integer.parseInt(reader.readLine()));
				wolf.setAge(Integer.parseInt(reader.readLine()));
				wolf.setIsAlive(Boolean.parseBoolean(reader.readLine()));
			}
		}
		reader.close();
	}
}
