import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/*
 * The GraphMaker handles making a graph by reading from a file.
 * The makeGraphFromFile method takes in the name of a file. 
 * The code should then read in the input file and produce the corresponding graph.
 * The first line of the file will be the number of vertices. 
 * The next lines will give the edges as a table where table(i,j) gives the 
 * edge weight between vertices i and j. 
 * The two provided files are in this format.
 * 
 * @Revised by Caleb Dunham
 * @Authoer Scott Spurlock
 * @version 2021-28-04
 */
public class GraphMaker {
	public static AdjListGraph makeGraphFromFile(int size) {
		
		// Create a new directed AdjListGraph and read from the file to
		// add DijkstraVertex and Edge object to the graph.

		File file = new File("transfers.txt");
		File sT = new File("stop_times.txt");
		size = size+500;
		
		try {
			//Open file
			Scanner sc = new Scanner(file);
			
			
			//Can skip line since it won't be useful info
			sc.nextLine();
			
			//Initialize Arrays and Graphs O(n)
			AdjListGraph graph = new AdjListGraph(true);
			DijkstraVertex[] vertArray = new DijkstraVertex[size+1];
			double[][] edgeWeights = new double[size+1][size+1];
			
			
			//Add info to vertNames and edgeWeights
			int idxCounter = 0;
			HashMap<String, Integer> s = new HashMap<String, Integer>();
			
			while(sc.hasNextLine()) {
				String[] lineInfo = sc.nextLine().split(",");
				String iD1 = lineInfo[0].trim();
				String iD2 = lineInfo[1].trim();
				//Check if there isn't a key at the first or second value
				if(lineInfo.length > 3 && !s.containsKey(iD1) && !s.containsKey(iD2)) {
					
					vertArray[idxCounter] = (DijkstraVertex) graph.addVertex(new DijkstraVertex(iD1));
					s.put(iD1, idxCounter);
					idxCounter++;
					//System.out.print("No [0] or [1]: " + idxCounter + " ");
					vertArray[idxCounter] = (DijkstraVertex) graph.addVertex(new DijkstraVertex(iD2));
					s.put(iD2, idxCounter);
					idxCounter++;
					//System.out.println(idxCounter);
					edgeWeights[s.get(iD1)][s.get(iD2)] = Double.parseDouble(lineInfo[3]);
				}
				//if lineInfo[0] is in map, but lineInfo[1] isn't
				else if(lineInfo.length > 3 && s.containsKey(iD1) && !s.containsKey(iD2)) {
					vertArray[idxCounter] = (DijkstraVertex) graph.addVertex(new DijkstraVertex(iD2));
					s.put(iD2, idxCounter);
					idxCounter++;
					//System.out.println("[0] but no [1]: " + idxCounter);
					edgeWeights[s.get(iD1)][s.get(iD2)] = Double.parseDouble(lineInfo[3]);
				}
				//if lineInfo[1] is in map, but lineInfo[0] isn't
				else if(lineInfo.length > 3 && !s.containsKey(iD1) && s.containsKey(iD2)) {
					vertArray[idxCounter] = (DijkstraVertex) graph.addVertex(new DijkstraVertex(iD1));
					s.put(iD1, idxCounter);
					idxCounter++;
					//System.out.println("[1] but no [0]: " + idxCounter);
					edgeWeights[s.get(iD1)][s.get(iD2)] = Double.parseDouble(lineInfo[3]);
				}
				else if(lineInfo.length > 3) {
					//We know that lineInfo will always be len 3
					edgeWeights[s.get(iD1)][s.get(iD2)] = Double.parseDouble(lineInfo[3]);
				}
				else if(lineInfo.length == 3&& !s.containsKey(iD1) && !s.containsKey(iD2)) {
					vertArray[idxCounter] = (DijkstraVertex) graph.addVertex(new DijkstraVertex(iD1));
					graph.addEdge(vertArray[idxCounter], vertArray[idxCounter], 0.0);
					s.put(iD1, idxCounter);
					idxCounter++;
					vertArray[idxCounter] = (DijkstraVertex) graph.addVertex(new DijkstraVertex(iD2));
					graph.addEdge(vertArray[idxCounter], vertArray[idxCounter], 0.0);
					s.put(iD2, idxCounter);
					idxCounter++;
				}
				else if(lineInfo.length == 3&& s.containsKey(iD1) && !s.containsKey(iD2)) {
					vertArray[idxCounter] = (DijkstraVertex) graph.addVertex(new DijkstraVertex(iD2));
					graph.addEdge(vertArray[idxCounter], vertArray[idxCounter], 0.0);
					s.put(iD2, idxCounter);
					idxCounter++;
				}
				else if(lineInfo.length == 3&& !s.containsKey(iD1) && s.containsKey(iD2)) {
					vertArray[idxCounter] = (DijkstraVertex) graph.addVertex(new DijkstraVertex(iD1));
					graph.addEdge(vertArray[idxCounter], vertArray[idxCounter], 0.0);
					s.put(iD1, idxCounter);
					idxCounter++;
				}
				else if(lineInfo.length == 3) {
					//We know that lineInfo will always be len 3
					edgeWeights[s.get(iD1)][s.get(iD2)] = 0.0;
				}
				
			}
			sc.close();
			
			//System.out.println(Arrays.toString(vertArray));
			//System.out.println(Arrays.deepToString(edgeWeights));
			
			//Add Edges and weights to graph O(N^2)
			for(int i = 0; i < vertArray.length; i++) {
				for(int j = 0; j < vertArray.length; j++) {
					if(edgeWeights[i][j] != 0) {
						graph.addEdge(vertArray[i], vertArray[j], edgeWeights[i][j]);
					}
					
				}
			}
			//graph.print();
			sc.close();
			
			//This is added to create edges from stop_times.txt
			//However, the caveat in the activity feels redundant. 
			//In theory someone would be able to change buses and since 
			//the nature of this graph doesn't account for time, then 
			//a transfer from stops would not affect overall distance
			
			Scanner sc2 = new Scanner(sT);
			
			//Can skip line since it won't be useful info
			sc2.nextLine();
			
			String prev_id = null;
			int master_trip_id = 0;
			
			while(sc2.hasNextLine()) {
				String[] lineInfo = sc2.nextLine().split(",");
				int trip_id = Integer.parseInt(lineInfo[0]);
				String stop_id = lineInfo[3].trim();
				
				if(!s.containsKey(stop_id)) {
					vertArray[idxCounter] = (DijkstraVertex) graph.addVertex(new DijkstraVertex(stop_id));
					s.put(stop_id, idxCounter);
					idxCounter++;
				}
				
				if(lineInfo.length == 8) {
					prev_id = stop_id;
					master_trip_id = trip_id;
				}
				else if(lineInfo.length > 8 && s.containsKey(stop_id) && 
						master_trip_id == trip_id) {
					edgeWeights[s.get(prev_id)][s.get(stop_id)] = Double.parseDouble(lineInfo[8]);
					prev_id = stop_id;
				}
			}
			sc2.close();
			
			//Add Edges and weights to graph O(N^2)
			for(int i = 0; i < vertArray.length; i++) {
				for(int j = 0; j < vertArray.length; j++) {
					if(edgeWeights[i][j] != 0) {
						graph.addEdge(vertArray[i], vertArray[j], edgeWeights[i][j]);
					}
					
				}
			}
			
			return graph;
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			return null;
		}
		
	}



}
