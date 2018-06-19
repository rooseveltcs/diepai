package screenIO;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Screenshot 
{
	//This class stores a buffered image with the time the screenshot was created.
	private BufferedImage image;
	private long lTime;
	
	//Create a new screenshot object with an image and time.
	public Screenshot(BufferedImage image, long lTime)
	{
		this.image = image;
		this.lTime = lTime;
	}
	
	//Get the buffered image object.
	public BufferedImage getImage()
	{
		return image;
	}
	
	//Get the pixel at x, y from the buffered image object.
	public Color getPixel(int x, int y)
	{
		if (x < 0 || y < 0 || x >= image.getWidth() || y >= image.getHeight())
		{
			return null;
		}
		return new Color(image.getRGB(x, y));
	}
	
	//Get the time the screenshot was taken.
	public long getTimeTaken()
	{
		return lTime;
	}
}

