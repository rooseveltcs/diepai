package screenIO;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

public class MouseListener implements NativeMouseListener
{
	//This class is used to listen for mouse events by using JNativehook.
	
	//Start a mouseListener with a new instance of this class.
	public static void start()
	{
		GlobalScreen.addNativeMouseListener(new MouseListener());
	}

	//This method is called whenever the mouse is clicked.
	@Override
	public void nativeMouseClicked(NativeMouseEvent e) 
	{
		main.PixelTest.mouseClicked();
	}

	//Other required methods.
	@Override
	public void nativeMousePressed(NativeMouseEvent e) {}

	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {}

}
