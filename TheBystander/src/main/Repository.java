package main;

import main.graphs.interfaces.IArea;
import main.graphs.interfaces.IPath;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Repository {
    private static int pathsWithNAreas(int n, Map<IPath, Collection<IArea>> data) {
        int result = 0;

        for (IPath path : data.keySet()) {
            if (data.get(path).size() == n) {
                result++;
            }
        }

        return result;
    }

	public static void writeData(Map<IPath, Collection<IArea>> data)
	{
        for (int i = 2; i <= 4; i++) {
            String filePath = "areas" + i;
            try {
                FileOutputStream fos = new FileOutputStream(filePath);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeInt(pathsWithNAreas(i, data));
                for (IPath path : data.keySet()) {
                    if (data.get(path).size() == i) {
                        oos.writeObject(path);
                        oos.writeObject(data.get(path));
                    }
                }
                oos.close();
            } catch (FileNotFoundException e1) {
                System.out.println("Unable to open file for writing : " + filePath);
            } catch (IOException e1) {
                System.out.println("Unable to write file : " + filePath);
            }
        }
	}

    public static Map<IPath, Collection<IArea>> readData(int numAreas) {
        String filePath = "areas" + numAreas;
        Map<IPath, Collection<IArea>> result = new HashMap<IPath, Collection<IArea>>();
        FileInputStream fis;
        ObjectInputStream ois;
		try 
		{
			fis = new FileInputStream(filePath);
			ois = new ObjectInputStream(fis);
            try {
                int count = ois.readInt();
                for (int i = 0; i < count; i++) {
                    Object readResult = ois.readObject();
                    IPath path = (IPath) readResult;
                    readResult = ois.readObject();
                    Collection<IArea> areas = (Collection<IArea>) readResult;
                    result.put(path, areas);
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
