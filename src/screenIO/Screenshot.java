package screenIO;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Screenshot 
{
	private BufferedImage image;
	private long lTime;
	
	public Screenshot(BufferedImage image, long lTime)
	{
		this.image = image;
		this.lTime = lTime;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	public Color getPixel(int x, int y)
	{
		if (x < 0 || y < 0 || x >= image.getWidth() || y >= image.getHeight())
		{
			return null;
		}
		return new Color(image.getRGB(x, y));
	}
	
	public long getTimeTaken()
	{
		return lTime;
	}
}

