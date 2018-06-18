package screenIO;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

public abstract class Shape 
{
	public Polygon corners;
	public Color color;
	public Point centerpoint;
	public double dAverageRadius = 0;
	
	public Shape(Polygon corners, Color color)
	{
		this.corners = corners;
		this.color = color;
		setCenterpoint();
	}
	
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
	
	public abstract void setRadius();
	
	public int getDistanceFrom(Point targetPoint)
	{
		return (int) centerpoint.distance(targetPoint);
	}
	
	public boolean isColor(Color colorToCheck)
	{
		return color.equals(colorToCheck);
	}
	
	public abstract boolean containsPoint(Point pointToCheck);
	public abstract void printData();
	public abstract String getShapeType();
	
	public int getX() {return centerpoint.x;}
	public int getY() {return centerpoint.y;}
}
