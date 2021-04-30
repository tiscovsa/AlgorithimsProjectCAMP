/*
 * Uses the GraphMaker to make the graph, asks the user for the source vertex 
 * and destination vertex, and then runs Dijkstra's algorithm. The shortest 
 * path length as well as the actual path should be printed to the screen, 
 * then the program should terminate. See the examples in assignment for the 
 * appropriate formatting.
 * 
 * @author sspurlock
 * @version 2019-10-21
 */

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

/*
 * 1. Finding shortest paths between 2 bus stops (as input by the user), 
 * 	  returning the list of stops en route as well as the associated “cost”.
 * 
 *  Stops are listed in stops.txt and connections (edges) between them come 
 *  from stop_times.txt and transfers.txt files. All lines in transfers.txt 
 *  are edges (directed), while in stop_times.txt an edge should be added only 
 *  between 2 consecutive stops with the same trip_id.
 *  
 *  	eg first 3 entries in stop_times.txt are
 *  		9017927, 5:25:00, 5:25:00,646,1,,0,0,
 *  		9017927, 5:25:50, 5:25:50,378,2,,0,0,0.3300
 *  		9017927, 5:26:28, 5:26:28,379,3,,0,0,0.5780
 *  
 *  This should add a directed edge from 646 to 378, and a directed edge from 
 *  378 to 379 (as they’re on the same trip id 9017927). Cost associated with 
 *  edges should be as follows: 1 if it comes from stop_times.txt, 2 if it comes 
 *  from transfers.txt with transfer type 0 (which is immediate transfer possible), 
 *  and for transfer type 2 the cost is the minimum transfer time divided by 100.
 */

public class Dijkstras {
	private static HashMap<String, Integer> StopIDs = new HashMap<String, Integer>();
	private static HashMap<Integer, String> IDsStop = new HashMap<Integer, String>();
	static HashMap<DijkstraVertex, DijkstraVertex> parent = new HashMap<DijkstraVertex, DijkstraVertex>();
	static ArrayList<DijkstraVertex> distances = new ArrayList<DijkstraVertex>();

	// Constructor: prompt user to enter file name, then
	// call runShortestPath with the file name.
	public Dijkstras(String start, String end) {
		//Scanner s = new Scanner(System.in);
		//System.out.println("Enter the file name ");
		runShortestPath("transfers.txt", start, end);
		//s.close();
	}
	
	private static void stopIDMap() {
		File file = new File("stops.txt");
		
		try {
			//Open file
			Scanner sc = new Scanner(file);
			
			//Can skip line since it will be not needed
			sc.nextLine();
			
			while(sc.hasNextLine()) {
				
				//Get id and stops
				String[] lineInfo = sc.nextLine().split(",");
				int id = Integer.parseInt(lineInfo[0]);
				String stopName = lineInfo[2];
				
				//Put name and id into Map
				StopIDs.put(stopName, id);
				IDsStop.put(id, stopName);
				
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	
	public static String[][] runShortestPath(String fileName, String start, String endV){
		
		//Making Graph from transfers.txt since we know
		//"All lines in transfers.txt are edges (directed)"
		stopIDMap();
		
		AdjListGraph graph = GraphMaker.makeGraphFromFile(StopIDs.size());
		
		// Print the graph out and prompt the user to enter the starting 
		// and ending vertices.
		// TODO
		
		//graph.print();
		String temp = Integer.toString(StopIDs.get(start));
		Vertex source = null;
		for(Vertex v : graph.getVertices()) {
			if(v.getName().equals(temp)) {
				source = v;
				break;
			}
		}

		temp = Integer.toString(StopIDs.get(endV));
		Vertex end = null;
		for(Vertex v : graph.getVertices()) {
			if(v.getName().equals(temp)) {
				end = v;
				break;
			}
		}
		
		// Call the shortestPath method with the graph and the source Vertex.
		// TODO
		
		shortestPath(graph, (DijkstraVertex) source);
		
		// Get the distance to the destination Vertex and print it out.
		// TODO
		 
		System.out.println("The shortest path is " + distances.get(distances.indexOf(end)).getDistance());
		
		// Find and print the path by following back from the destination Vertex to each
		// parent. Note that, in the shortestPath method, you'll have stored 
		// the parent for each Vertex in the HashMap declared at the top of this file).
		// TODO
		
		String path = "";
		DijkstraVertex findPath = (DijkstraVertex) end;
		List<String[]> returnValue = new ArrayList<String[]>();
		
		if(parent.get(end) == null) {
			path = end.getName();
		}
		else {
			while(findPath != null) {
				String[] temp1 = new String[1];
				if(findPath != end) {
					path = IDsStop.get(Integer.parseInt(findPath.getName())) + "::" + path;
					temp1[0] = IDsStop.get(Integer.parseInt(findPath.getName()));
				}
				else {
					path = IDsStop.get(Integer.parseInt(findPath.getName())) + path;
					temp1[0] = IDsStop.get(Integer.parseInt(findPath.getName()));
				}
				returnValue.add(temp1);
				findPath = parent.get(findPath);
			}
		}
		System.out.println(path);
        String[][] returnArray = new String[returnValue.size()][1];
        returnArray = returnValue.toArray(returnArray);
		
		return returnArray;
	}

	// Given the graph and source vertex, run Dijkstra's algorithm.
	public static void shortestPath(AdjListGraph graph, DijkstraVertex source){
		// Initialize the distance to all the vertices in the graph to infinity,
		// except the source vertex, which should be 0.
		// TODO
		source.setDistance(0.0);
		distances.add(source);

		for(Vertex v : graph.getVertices()) {
			if(v.getName() != source.getName()) {
				DijkstraVertex dv = (DijkstraVertex) v;
				dv.setDistance(Double.POSITIVE_INFINITY);
				distances.add(dv);
			}
		}
		
		// Make a PriorityQueue (imported above in Java.Util.PriorityQueue)
		// of DijkstraVertex objects and add all the vertices.
		// TODO
		
		PriorityQueue<DijkstraVertex> PQ = new PriorityQueue<DijkstraVertex>();
		for(DijkstraVertex v : distances) {
				PQ.add(v);
		}
		//System.out.println(PQ);
		
		// Keep looping as long as the priorty queue is not empty, doing the following:
		// - get the next closest Vertex from the priority queue
		// - get its adjacent vertices
		// - for each adjacent vertex, check if the distance to get there from the 
		//   current vertex would be shorter than its current distance. If so, remove
		//   it from the priority queue, update its distance, and re-add it. Keep track
		//   of which vertex led to this vertex using the parent HashMap declared
		//   at the top of the file.
		// TODO
		while(!PQ.isEmpty()) {
			DijkstraVertex next = PQ.remove();
			Collection<Vertex> adjVerts = next.getAdjacentVertices();
			double currDist = next.getDistance();
			for(Vertex v : adjVerts) {
				//isolate getv to make code nicer
				int index = distances.indexOf(v);
				DijkstraVertex getv = distances.get(index);
				
				if(currDist+graph.getWeight(next, getv) < getv.getDistance() && v != source){
					PQ.remove(getv);
					getv.setDistance(graph.getWeight(next, getv) + currDist);
					PQ.add(getv);
					parent.put(getv, next);
				}
			}
		}	
}
}
