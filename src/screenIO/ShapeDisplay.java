package screenIO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShapeDisplay extends JPanel
{
	public JFrame frame;
	public void setupDisplay()
	{
		frame = new JFrame();
		frame.setSize(new Dimension(ScreenData.SCREEN_WIDTH / 3, ScreenData.SCREEN_HEIGHT / 3));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g)
	{
		int iWidth = this.getWidth();
		int iHeight = this.getHeight();
		double xRatio = (double) iWidth / ScreenData.SCREEN_WIDTH;
		double yRatio = (double) iHeight / ScreenData.SCREEN_HEIGHT;
		Point topLocation = this.getLocationOnScreen();
		Point bottomLocation = new Point(topLocation.x + iWidth, topLocation.y + iHeight);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, iWidth, iHeight);
		ShapeList shapes = ScreenData.shapes;
		for (int i = 0; i < shapes.size(); i++)
		{
			Shape shape = shapes.get(i);
			Point shapeLocation = shape.centerpoint;
			if (shapeLocation.x > topLocation.x && shapeLocation.x < bottomLocation.x &&
				shapeLocation.y > topLocation.y && shapeLocation.y < bottomLocation.y)
			{
				continue;
			}
			int[] xPoints = shape.corners.xpoints;
			int[] yPoints = shape.corners.ypoints;
			int[] newXPoints = new int[shape.corners.npoints];
			int[] newYPoints = new int[shape.corners.npoints];
			for (int index = 0; index < shape.corners.npoints; index++)
			{
				newXPoints[index] = (int) (xPoints[index] * xRatio);
				newYPoints[index] = (int) (yPoints[index] * yRatio);
			}
			Polygon polygon = new Polygon(newXPoints, newYPoints, shape.corners.npoints);
			g.setColor(shape.color);
			g.fillPolygon(polygon);
			g.setColor(Color.BLACK);
			g.drawPolygon(polygon);
		}
	}
}
