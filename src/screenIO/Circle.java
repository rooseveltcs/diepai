package screenIO;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

public class Circle extends Shape
{
	//This class is used to represent a circle and extends Shape.
	//This class is not used in this version of the program 
	//but is intended for use in the future.
	
	//Construct a new circle object with corners and color.
	public Circle(Polygon corners, Color color)
	{
		super(corners, color);
		setRadius();
	}
	
	//Set the radius of the circle.
	public void setRadius()
	{
		for (int i = 0; i < corners.npoints && i < 10; i++)
		{
			Point point = new Point(corners.xpoints[i], corners.ypoints[i]);
			Double dRadius = centerpoint.distance(point);
			dAverageRadius += dRadius;
		}
		dAverageRadius /= corners.npoints;
	}
	
	//Check if the circle contains pointToCheck.
	public boolean containsPoint(Point pointToCheck) 
	{
		return centerpoint.distance(pointToCheck) <= dAverageRadius;
	}
	
	//Print data about the circle.
	public void printData() 
	{
		System.out.println(getShapeType() + ":");
		System.out.println("Centerpoint: " + getX() + ", " + getY());
		System.out.println("Radius: " + dAverageRadius);
		System.out.println();
	}
	
	//Get the type of this shape. Overrides an abstract method in the Shape class.
	public String getShapeType()
	{
		return "CIRCLE";
	}
}
