package methods;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class ExternalLibraries 
{
	public static void registerNativeHook()
	{
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		try 
		{
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) 
		{
			System.out.println("There was a problem registering the native hook.");
			System.out.println(ex.getMessage());

			System.exit(0);
		}
	}
}
