package screenIO;

import java.awt.Color;
import java.util.ArrayList;

public class ShapeData 
{
	//This class stores the colors of every shape in the game.
	//It adds these colors to an ArrayList for use in the ScreenParser class.
	public static final int iColorMargin = 15;
	
	public static final int MIN_TANK_RADIUS = 8;
	//Basic shapes
	public static final Color SQUARE_COLOR = new Color(255, 231, 107);
	public static final Color TRIANGLE_COLOR = new Color(252, 118, 119);
	public static final Color PENTAGON_COLOR = new Color(118, 141, 252);
	
	//Moving shapes
	public static final Color CRASHER_COLOR = new Color(241, 119, 221);
	
	//Team colors
	public static final Color GREEN_COLOR = new Color(0, 225, 110);
	public static final Color BLUE_COLOR = new Color(0, 178, 226);
	public static final Color RED_COLOR = new Color(240, 78, 82);
	public static final Color PURPLE_COLOR = new Color(192, 126, 242);
	
	//List of all colors for use in the ScreenParser class.
	public static ArrayList<Color> colors = new ArrayList<>();
	public static ArrayList<Color> basicShapeColors = new ArrayList<>();
	public static ArrayList<Color> teamColors = new ArrayList<>();
	
	//Put every color in an ArrayList.
	static
	{
		basicShapeColors.add(SQUARE_COLOR);
		basicShapeColors.add(TRIANGLE_COLOR);
		basicShapeColors.add(PENTAGON_COLOR);
		colors.addAll(basicShapeColors);
		
		colors.add(CRASHER_COLOR);
		
		teamColors.add(GREEN_COLOR);
		teamColors.add(BLUE_COLOR);
		teamColors.add(RED_COLOR);
		teamColors.add(PURPLE_COLOR);
		colors.addAll(teamColors);
	}
}
