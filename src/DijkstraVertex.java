/* Subclass of Vertex specific to Dijkstra. Adds a distance
 * field and the ability to compare to DijkstraVertex objects.
 * 
 * @Revised by: Caleb Dunham
 * @author: sspurlock
 * @version 2021-04-04
 */

public class DijkstraVertex extends Vertex implements Comparable<DijkstraVertex> {
	private double distance;
	private int ID;
	
	public DijkstraVertex(String name) {
		super(name);	
	}
	
	public void setDistance(double dist){
		distance = dist;
	}
	
	public double getDistance(){
		return distance;
	}
	public void setID(int id){
		ID = id;
	}
	
	public int getID(){
		return ID;
	}
	
	public int compareTo(DijkstraVertex j) {
		if(this.distance < j.distance) return -1;
		else if (this.distance > j.distance) return 1;
		else return 0;
	}
}
