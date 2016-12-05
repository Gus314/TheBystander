package bystander;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;

import bystander.graphs.interfaces.IArea;
import bystander.graphs.interfaces.IPath;

public class Repository
{	
	public static void writeData(Map<IPath, Collection<IArea>> data)
	{
        FileOutputStream fos;
        ObjectOutputStream oos;
        String filePath = "areas";
		try 
		{
			fos = new FileOutputStream(filePath);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.close();
		}
		catch (FileNotFoundException e1)
		{
			System.out.println("Unable to open file for writing : " + filePath);
		}
		catch (IOException e1)
		{
			System.out.println("Unable to write file : " + filePath);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Map<IPath, Collection<IArea>> readData()
	{
		String filePath = "areas";
		Map<IPath, Collection<IArea>> result = null;
        FileInputStream fis;
        ObjectInputStream ois;
		try 
		{
			fis = new FileInputStream(filePath);
			ois = new ObjectInputStream(fis);
			try 
			{
				Object readResult = ois.readObject();;
				if(readResult instanceof Map<?,?>)
				{
					result =  (Map<IPath, Collection<IArea>>) readResult;
				}

				ois.close();
			} 
			catch (ClassNotFoundException e)
			{
				System.out.println("Unable to load file: " + filePath);
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e1)
		{
			System.out.println("Unable to load file: " + filePath);
		}
			catch (IOException e1)
		{
			System.out.println("Unable to load file: " + filePath);
		}
		return result;
	}
}
