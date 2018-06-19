package screenIO;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyListener implements NativeKeyListener
{
	//This class is used to listen for key events by using JNativehook.
	
	//Start a keyListener with a new instance of this class.
	public static void start()
	{
		GlobalScreen.addNativeKeyListener(new KeyListener());
	}
	
	//This method is called whenever a key is pressed.
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) 
	{
		String sKeycode = NativeKeyEvent.getKeyText(e.getKeyCode()).toLowerCase().replaceAll(" ", "");
		aiming.AimControl.keyPressed(sKeycode);
		movement.GeneralMovement.keyPressed(sKeycode);
		main.PixelTest.keyPressed(sKeycode);
	}
	
	//Other required methods.
	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {}

}
