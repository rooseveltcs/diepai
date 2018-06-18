package screenIO;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyListener implements NativeKeyListener
{
	
	public static void start()
	{
		GlobalScreen.addNativeKeyListener(new KeyListener());
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) 
	{
		String sKeycode = NativeKeyEvent.getKeyText(e.getKeyCode()).toLowerCase().replaceAll(" ", "");
		aiming.AimControl.keyPressed(sKeycode);
		movement.GeneralMovement.keyPressed(sKeycode);
		main.PixelTest.keyPressed(sKeycode);
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {}

}
