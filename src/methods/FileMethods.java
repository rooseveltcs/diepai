package methods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileMethods 
{
	
	public ArrayList<String> readLines(File file) throws FileNotFoundException
	{
		ArrayList<String> FileData = new ArrayList<>();
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine())
		{
			FileData.add(sc.nextLine());
		}
		sc.close();
		return FileData;
	}
	
	public ArrayList<String> readTokens(File file) throws FileNotFoundException
	{
		ArrayList<String> FileData = new ArrayList<>();
		Scanner sc = new Scanner(file);
		while (sc.hasNext())
		{
			FileData.add(sc.next());
		}
		sc.close();
		return FileData;
	}

}
