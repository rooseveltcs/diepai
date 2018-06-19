package screenIO;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

public abstract class Shape 
{
	//An abstract class used to represent shapes. The two types of shapes,
	//SimplePolygon and Circle, extend this class. Circle is not used in this version
	//of the program but is intended for use in the future.
	public Polygon corners;
	public Color color;
	public Point centerpoint;
	public double dAverageRadius = 0;
	
	//Construct a new shape object with corners and color.
	public Shape(Polygon corners, Color color)
	{
		this.corners = corners;
		this.color = color;
		setCenterpoint();
	}
	
	//Set the centerpoint variable based on the average of the corner points.
	public void setCenterpoint()
	{
		int xCenter = 0;
		int yCenter = 0;
		for (int i = 0; i < corners.npoints; i++)
		{
			xCenter += corners.xpoints[i];
			yCenter += corners.ypoints[i];
		}
		xCenter /= corners.npoints;
		yCenter /= corners.npoints;
		this.centerpoint = new Point(xCenter, yCenter);
	}
	
	//Set the radius variable
	public abstract void setRadius();
	
	//Get the distance from the center of this shape to the target point. 
	public int getDistanceFrom(Point targetPoint)
	{
		return (int) centerpoint.distance(targetPoint);
	}
	
	//Check if the color of this shape matches colorToCheck exactly.
	public boolean isColor(Color colorToCheck)
	{
		return color.equals(colorToCheck);
	}
	
	//More abstract methods.
	public abstract boolean containsPoint(Point pointToCheck);
	public abstract void printData();
	public abstract String getShapeType();
	
	//Return the x and y coordinates of the centerpoint of this shape.
	public int getX() {return centerpoint.x;}
	public int getY() {return centerpoint.y;}
}
