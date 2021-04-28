import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BusSearch {

	public Tst createTree(String textFile)
	{
	  	Tst tree = new Tst();
    	File file = new File(textFile);
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
	    			currentRoad = currentRoad + "\n ID: " +id;
	    			currentRoad = currentRoad + "\n Code: " +code;
	    			currentRoad = currentRoad + "\n Stop Description: " +input.next();
	    			currentRoad = currentRoad + "\n Stop Lat: " +input.next();
	    			currentRoad = currentRoad + "\n Stop Lon: " +input.next();
	    			currentRoad = currentRoad + "\n Stop Zone: " +input.next();
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
	public void search(String roadPrefix)
	{
		Tst tree = createTree("stops.txt");
		String roadListString = tree.keysWithPrefix(roadPrefix).toString();
		roadListString = roadListString.substring( 1, roadListString.length() - 1 );
		List<String> roadList = new ArrayList<String>(Arrays.asList(roadListString.split(",")));
		for(int i = 0;i < roadList.size();i++)
		{	
			System.out.println(roadList.get(i) +"\n");
			System.out.println("^^^^^^^^^^^^^^^^^^^^^\n");
		}
	}
    public static void main(String[] args) {
    	
    	BusSearch searcher = new BusSearch();
    	boolean go = true;
    	Scanner input = new Scanner(System.in);
    	while(go)
    	{
    		System.out.println("Search for street or enter 'exit' to leave");
    		String userEntry = input.nextLine();
    		if(userEntry == "exit")
    		{
    			go = false;
    		}
    		searcher.search(userEntry);
    		
    	}
    }
    
}       

