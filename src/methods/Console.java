package methods;

import java.util.Scanner;

public class Console 
{	
	public static void ptn(Object o) {System.out.println(o);}
	public static void ptn(char[] ca) {System.out.println(ca);}
	public static void ptn(StringMethods s) {System.out.println(s);}
	public static void ptn(boolean b) {System.out.println(b);}
	public static void ptn(long l) {System.out.println(l);}
	public static void ptn(double d) {System.out.println(d);}
	public static void ptn(char c) {System.out.println(c);}
	public static void ptn() {System.out.println();}
	
	public static void pt(Object o) {System.out.print(o);}
	public static void pt(char[] ca) {System.out.print(ca);}
	public static void pt(StringMethods s) {System.out.print(s);}
	public static void pt(boolean b) {System.out.print(b);}
	public static void pt(long l) {System.out.print(l);}
	public static void pt(double d) {System.out.print(d);}
	public static void pt(char c) {System.out.print(c);}
	
	public static String getUserInput(String sMessage)
	{
		Scanner sc = new Scanner(sMessage);
		System.out.println(sMessage);
		String sReturn = sc.next();
		sc.close();
		return sReturn;
	}
}
