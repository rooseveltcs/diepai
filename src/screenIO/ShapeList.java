package screenIO;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class ShapeList 
{
	private ArrayList<Shape> list = new ArrayList<>();
	private Screenshot screenshot;
	private Long lOrginTime;
	
	public ShapeList(Screenshot screenshot)
	{
		this(screenshot.getTimeTaken());
		this.screenshot = screenshot;
	}
	
	public ShapeList(long lOrginTime)
	{
		this.lOrginTime = lOrginTime;
	}
	
	public ShapeList()
	{
		this(-1);
	}
	
	public ShapeList getShapesWithColor(Color color)
	{
		ShapeList shapesWithColor = new ShapeList();
		for (Shape shape : list)
		{
			if (shape.isColor(color)) shapesWithColor.add(shape);
		}
		return shapesWithColor;
	}
	
	public ShapeList getShapesWithColors(ArrayList<Color> colors)
	{
		ShapeList output = new ShapeList();
		for (Color color : colors)
		{
			output.addAll(getShapesWithColor(color));
		}
		return output;
	}
	
	public boolean hasShapeWithColor(Color color)
	{
		for (Shape shape : list)
		{
			if (shape.isColor(color)) return true;
		}
		return false;
	}
	
	public Shape getClosestShapeTo(Point targetPoint)
	{
		Shape closestShape = null;
		for (Shape shape : list)
		{
			if (closestShape == null) closestShape = shape;
			else
			{
				if (shape.getDistanceFrom(targetPoint) < closestShape.getDistanceFrom(targetPoint))
				{
					closestShape = shape;
				}
			}
		}
		return closestShape;
	}
	
	public ShapeList getShapesOutsideRadius(int iRadius)
	{
		ShapeList output = new ShapeList();
		for (Shape shape : list)
		{
			if (shape.dAverageRadius > iRadius) output.add(shape);
		}
		return output;
	}
	
	public ShapeList getShapesInsideRadius(int iRadius)
	{
		ShapeList output = new ShapeList();
		for (Shape shape : list)
		{
			if (shape.dAverageRadius < iRadius) output.add(shape);
		}
		return output;
	}
	
	public boolean containsPoint(Point pointToCheck)
	{
		for (Shape shape : list)
		{
			if (shape.containsPoint(pointToCheck)) return true;
		}
		return false;
	}
	
	public void printShapes()
	{
		System.out.println(list.size() + " Shapes:");
		for (Shape shape : list)
		{
			shape.printData();
		}
	}
	
	public int size() {return list.size();}
	public Shape get(int i) {return list.get(i);}
	public void add(Shape shape) {list.add(shape);}
	
	public void addAll(ShapeList shapeList)
	{
		for (Shape shape : shapeList.list)
		{
			list.add(shape);
		}
	}
	
	public long getOriginTime() {return lOrginTime;}
	
	public void debugWithMouse(RobotManager rm, int ms)
	{
		rm.delay(ms);
		for (Shape shape : list)
		{
			rm.moveMouseTo(shape.getX(), shape.getY());
			System.out.println("Radius: " + shape.dAverageRadius);
			rm.delay(ms);
		}
	}
}
