import java.util.Comparator;

public class TripIdSorter implements Comparator<BusObject> {

	   @Override
	    public int compare(BusObject o1, BusObject o2) {
		  if( o2.tripId > (o1.tripId)) {
	        return 1;
		  }
		  else return -1;
	    }
}
