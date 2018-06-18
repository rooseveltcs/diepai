package screenIO;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

public class MouseListener implements NativeMouseListener
{
	public static void start()
	{
		GlobalScreen.addNativeMouseListener(new MouseListener());
	}

	@Override
	public void nativeMouseClicked(NativeMouseEvent e) 
	{
		main.PixelTest.mouseClicked();
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent e) 
	{

	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent e) 
	{

	}

}
