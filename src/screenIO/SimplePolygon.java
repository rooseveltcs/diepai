package screenIO;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

public class SimplePolygon extends Shape
{
	public double dMaxRadius = Double.MIN_VALUE;
	public double dMinRadius = Double.MAX_VALUE;
	public double dRadiusRange;
	
	public SimplePolygon(Polygon corners, Color color)
	{
		super(corners, color);
		setRadius();
	}
	
	public void setRadius()
	{
		for (int i = 0; i < corners.npoints; i++)
		{
			Point point = new Point(corners.xpoints[i], corners.ypoints[i]);
			Double dRadius = centerpoint.distance(point);
			dAverageRadius += dRadius;
			if (dMaxRadius < dRadius) dMaxRadius = dRadius;
			if (dMinRadius > dRadius) dMinRadius = dRadius;
		}
		dRadiusRange = dMaxRadius - dMinRadius;
		dAverageRadius /= corners.npoints;
		
	}

	public boolean containsPoint(Point pointToCheck) 
	{
		if (centerpoint.distance(pointToCheck) > dMaxRadius) return false;
		if (centerpoint.distance(pointToCheck) <= dMinRadius) return true;
		return corners.contains(pointToCheck);
	}

	public void printData() 
	{
		System.out.println(getShapeType() + ":");
		System.out.println("Centerpoint: " + getX() + ", " + getY());
		System.out.println("Average Radius: " + dAverageRadius);
		/*System.out.println("Corners:");
		int[] xPoints = corners.xpoints;
		int[] yPoints = corners.ypoints;
		int nPoints = corners.npoints;
		for (int i = 0; i < nPoints; i++)
		{
			System.out.println(xPoints[i] + ", " + yPoints[i]);
		}*/
		System.out.println();
	}
	
	public String getShapeType()
	{
		return "SIMPLE";
	}
}
