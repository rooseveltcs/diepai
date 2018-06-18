package tankInfo;

import static methods.PrimativeMethods.isCloseTo;

import java.awt.Color;
import java.util.ArrayList;

import screenIO.RobotManager;
import screenIO.ScreenData;
import screenIO.Shape;
import screenIO.ShapeData;
import screenIO.ShapeList;

public class TankTest 
{
	public static Color teamColor;
	public static ArrayList<Color> enemyColors;
	public static RobotManager rm = new RobotManager();
	
	public static void setTeamColor()
	{
		if (teamColor == null)
		{
			ShapeList shapes = ScreenData.shapes;
			ShapeList tanks = new ShapeList();
			for (Color color : ShapeData.teamColors)
			{
				tanks.addAll(shapes.getShapesWithColor(color));
			}
			tanks = tanks.getShapesOutsideRadius(ShapeData.MIN_TANK_RADIUS);
			if (tanks.size() > 0)
			{
				Shape shape = tanks.getClosestShapeTo(ScreenData.CENTER);
				teamColor = shape.color;
			}
		}
	}
	
	public static void setEnemyColors()
	{
		if (enemyColors == null)
		{
			setTeamColor();
			if (teamColor != null)
			{
				ArrayList<Color> colors = new ArrayList<>();
				colors.add(ShapeData.GREEN_COLOR);
				colors.add(ShapeData.BLUE_COLOR);
				colors.add(ShapeData.RED_COLOR);
				colors.add(ShapeData.PURPLE_COLOR);
				for (int i = 0; i < 4; i++)
				{
					if (isColor(colors.get(i), teamColor))
					{
						colors.remove(i);
						break;
					}
				}
				if (colors.size() == 3)
				{
					enemyColors = colors;
				}
			}
		}
	}
	
	private static boolean isColor(Color color, Color targetColor)
	{
		if (color == null ^ targetColor == null) return false;
		int iMargin = ShapeData.iColorMargin;
		if (!isCloseTo(color.getRed(), targetColor.getRed(), iMargin)) return false;
		if (!isCloseTo(color.getGreen(), targetColor.getGreen(), iMargin)) return false;
		if (!isCloseTo(color.getBlue(), targetColor.getBlue(), iMargin)) return false;
		return true;
	}
}
