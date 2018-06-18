package methods;

public class StringMethods 
{
	public static boolean containsIgnoreCase(String s1, String s2)
	{
		if (s1.toLowerCase().contains(s2.toLowerCase())) return true;
		return false;
	}
	public static boolean startsWithIgnoreCase(String s1, String s2)
	{
		if (s1.toLowerCase().startsWith(s2.toLowerCase())) return true;
		return false;
	}
	public static boolean endsWithIgnoreCase(String s1, String s2)
	{
		if (s1.toLowerCase().endsWith(s2.toLowerCase())) return true;
		return false;
	}
	public static int indexOfIgnoreCase(String s1, String s2)
	{
		return s1.toLowerCase().indexOf(s2.toLowerCase());
	}
	
	public static String removeFromStart(String s1, int iAmount)
	{
		return s1.substring(iAmount);
	}
	public static String removeFromEnd(String s1, int iAmount)
	{
		return s1.substring(0, s1.length() - iAmount);
	}
}
