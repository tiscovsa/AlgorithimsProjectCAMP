import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BusSearch {

	public Tst createTree()
	{
	  	Tst tree = new Tst();
    	File file = new File("stops.txt");
    	Scanner input;
		try 
		{
			input = new Scanner(file).useDelimiter(",");;
	    	while(input.hasNextLine())
	    	{
	    			String id = input.next();
	    			String code = input.next();
	    			String currentRoad = input.next();
	    			if(currentRoad.startsWith("WB ") || currentRoad.startsWith("EB ") || currentRoad.startsWith("SB ") || currentRoad.startsWith("NB "))
	    			{
	    				String prefix = currentRoad.substring(0, 2);
	    				currentRoad = currentRoad.substring(3);
	    				currentRoad = currentRoad + " " + prefix;
	    			}
	    			currentRoad = currentRoad + "\n" +id;
	    			currentRoad = currentRoad + "\n" +code;
	    			currentRoad = currentRoad + "\n" +input.next();
	    			currentRoad = currentRoad + "\n" +input.next();
	    			currentRoad = currentRoad + "\n" +input.next();
	    			currentRoad = currentRoad + "\n" +input.next();
	    			currentRoad = currentRoad + "\n" + "none";
	    			currentRoad = currentRoad + "\n" + "none";
	    			currentRoad = currentRoad + "\n" + "0";
	    			tree.add(currentRoad);
	    			input.nextLine();
	    	}
	    	return tree;
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public String[][] search(String roadPrefix,Tst tree)
	{
		
		String roadListString = tree.keysWithPrefix(roadPrefix).toString();
		roadListString = roadListString.substring( 1, roadListString.length() - 1 );
		List<String> roadList = new ArrayList<String>(Arrays.asList(roadListString.split(",|\\n")));
		
		int row = 10;
		int columns = roadList.size()/row;
		String [][] busStops = new String [columns][row];
		for(int i = 0;i < columns;i++)
		{
			for(int j = 0;j < row;j++)
			{
				busStops[i][j] = roadList.get(j + i*row);
			}
		}
		return busStops;
	}
    
}       

