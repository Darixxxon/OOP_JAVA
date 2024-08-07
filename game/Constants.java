package game;

import java.awt.*;

public interface Constants
{
	int MENU_SIZE = 56;
	int BUTTON_SIZE = 47;
	int BORDER = 2;

	char TYPE_ANIMAL = 'A';
	char TYPE_PLANT = 'P';

	int SPREAD_RATE = 29;

	int STRENGTH_PLANT = 0;
	int INITIATIVE_PLANT = 0;

	int STRENGTH_ANTELOPE = 4;
	int INITIATIVE_ANTELOPE = 4;
	String NAME_ANTELOPE = "ANTELOPE";
	char DRAW_ANTELOPE = 65;

	int STRENGTH_BELLADONNA = 99;
	String NAME_BELLADONNA = "BELLADONNA";
	char DRAW_BELLADONNA = 225;

	int STRENGTH_FOX = 3;
	int INITIATIVE_FOX = 7;
	String NAME_FOX = "FOX";
	char DRAW_FOX = 70;

	String NAME_GRASS = "GRASS";
	char DRAW_GRASS = 193;

	String NAME_GURANA = "GUARANA";
	char DRAW_GUARANA = 245;

	int STRENGTH_HUMAN = 5;
	int INITIATIVE_HUMAN = 4;
	String NAME_HUMAN = "HUMAN";
	char DRAW_NORMAL_HUMAN = 104;
	char DRAW_SUPER_HUMAN = 72;
	int ABILITY_SET_STRENGTH = 10;
	int ABILITY_COOLDOWN = 10;
	int ABILITY_DURATION = 5;

	int STRENGTH_SHEEP = 4;
	int INITIATIVE_SHEEP = 4;
	String NAME_SHEEP = "SHEEP";
	char DRAW_SHEEP = 83;

	int STRENGTH_SOSNOWSKY = 10;
	String NAME_SOSNOWSKY = "SOSNOWSKY";
	char DRAW_SOSNOWSKY = 197;

	String NAME_SOW_THISTLE = "SOW_THISTLE";
	char DRAW_SOW_THISTLE = 207;

	int STRENGTH_TURTLE = 2;
	int INITIATIVE_TURTLE = 1;
	String NAME_TURTLE = "TURTLE";
	char DRAW_TURTLE = 84;

	int STRENGTH_WOLF = 9;
	int INITIATIVE_WOLF = 5;
	String NAME_WOLF = "WOLF";
	char DRAW_WOLF = 87;

}
