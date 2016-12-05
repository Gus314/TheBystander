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
		try 
		{
			fos = new FileOutputStream("areas");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.close();
		}
		catch (FileNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static Map<IPath, Collection<IArea>> readData()
	{
		Map<IPath, Collection<IArea>> result = null;
        FileInputStream fis;
        ObjectInputStream ois;
		try 
		{
			fis = new FileInputStream("areas");
			ois = new ObjectInputStream(fis);
			try 
			{
				result =  (Map<IPath, Collection<IArea>>) ois.readObject();
				ois.close();
			} 
			catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}
}
