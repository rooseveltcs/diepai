package screenIO;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

public class Circle extends Shape
{
	public Circle(Polygon corners, Color color)
	{
		super(corners, color);
	}
	
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
	
	public boolean containsPoint(Point pointToCheck) 
	{
		return centerpoint.distance(pointToCheck) <= dAverageRadius;
	}

	public void printData() 
	{
		System.out.println(getShapeType() + ":");
		System.out.println("Centerpoint: " + getX() + ", " + getY());
		System.out.println("Radius: " + dAverageRadius);
		System.out.println();
	}
	
	public String getShapeType()
	{
		return "CIRCLE";
	}
}
