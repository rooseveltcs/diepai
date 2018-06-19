package screenIO;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class ShapeList 
{
	//This class stores a list of shapes. It provides many methods for retrieving shapes
	//with certain properties.
	private ArrayList<Shape> list = new ArrayList<>();
	private Screenshot screenshot;
	private Long lOrginTime;
	
	//Construct a new ShapeList object with a screenshot (representing the source of the shape data).
	public ShapeList(Screenshot screenshot)
	{
		this(screenshot.getTimeTaken());
		this.screenshot = screenshot;
	}
	
	//Construct a new ShapeList object with an origin time.
	public ShapeList(long lOrginTime)
	{
		this.lOrginTime = lOrginTime;
	}
	
	//Construct a new ShapeList object.
	public ShapeList()
	{
		this(-1);
	}
	
	//Get all the shapes that contain the specified color.
	public ShapeList getShapesWithColor(Color color)
	{
		ShapeList shapesWithColor = new ShapeList();
		for (Shape shape : list)
		{
			if (shape.isColor(color)) shapesWithColor.add(shape);
		}
		return shapesWithColor;
	}
	
	//Get all the shapes that contain any of the specified colors.
	public ShapeList getShapesWithColors(ArrayList<Color> colors)
	{
		ShapeList output = new ShapeList();
		for (Color color : colors)
		{
			output.addAll(getShapesWithColor(color));
		}
		return output;
	}
	
	//Test if this ShapeList has any shapes with the specified color.
	public boolean hasShapeWithColor(Color color)
	{
		for (Shape shape : list)
		{
			if (shape.isColor(color)) return true;
		}
		return false;
	}
	
	//Get the closest shape to the target point.
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
	
	//Get all shapes with a radius larger than the specified radius.
	public ShapeList getShapesOutsideRadius(int iRadius)
	{
		ShapeList output = new ShapeList();
		for (Shape shape : list)
		{
			if (shape.dAverageRadius > iRadius) output.add(shape);
		}
		return output;
	}
	
	//Get all shapes with a radius smaller than the specified radius.
	public ShapeList getShapesInsideRadius(int iRadius)
	{
		ShapeList output = new ShapeList();
		for (Shape shape : list)
		{
			if (shape.dAverageRadius < iRadius) output.add(shape);
		}
		return output;
	}
	
	//Test if any of the shapes contain the specified point.
	public boolean containsPoint(Point pointToCheck)
	{
		for (Shape shape : list)
		{
			if (shape.containsPoint(pointToCheck)) return true;
		}
		return false;
	}
	
	//Call the printData method on each shape.
	public void printShapes()
	{
		System.out.println(list.size() + " Shapes:");
		for (Shape shape : list)
		{
			shape.printData();
		}
	}
	
	//Implemented ArrayList method for the list of shapes.
	public int size() {return list.size();}
	public Shape get(int i) {return list.get(i);}
	public void add(Shape shape) {list.add(shape);}
	
	//Add all the shapes in the specified shape list to this shape list.
	public void addAll(ShapeList shapeList)
	{
		for (Shape shape : shapeList.list)
		{
			list.add(shape);
		}
	}
	
	//Get the origin time of this shape list.
	public long getOriginTime() {return lOrginTime;}
	
	//THIS METHOD IS FOR DEBUGGING
	//Move the mouse to each shape in the list at the specified interval.
	//Each time the mouse is moved print out the radius of the shape.
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
