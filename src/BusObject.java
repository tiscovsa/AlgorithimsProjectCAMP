
public class BusObject {
	public int tripId;
	public String arrival_time;
	public String departure_time;
	public String stop_id;
	public String stop_sequence;
	public String stop_headsign;
	public String pickup_type;
	public String drop_off_type;
	public String shape_dist_traveled;

	public BusObject(int tripId, String arrival_time, String departure_time, String stop_id, 
			String stop_sequence, String stop_headsign, String pickup_type, String drop_off_type, 
			String shape_dist_traveled) {
		
		this.tripId = tripId;
		this.arrival_time = arrival_time;
		this.departure_time = departure_time;
		this.stop_id = stop_id;
		this.stop_sequence = stop_sequence;
		this.stop_headsign = stop_headsign;
		this.pickup_type = pickup_type;
		this.drop_off_type = drop_off_type;
		this.shape_dist_traveled = shape_dist_traveled;
		
	}
	public String toString() {
		return("Trip ID: " + tripId +
			"\n Arrival Time: " + arrival_time +
			"\n Departure Time: " + departure_time +
			"\n Stop ID: " + stop_id +
			"\n Stop Sequence: " + stop_sequence +
			"\n Stop Headsign: " + stop_headsign +
			"\n Pick Up Type: " + pickup_type +
			"\n Drop Off Type: " + drop_off_type +
			"\n Shape Distance Traveled: " + shape_dist_traveled 
			);
	}
}
