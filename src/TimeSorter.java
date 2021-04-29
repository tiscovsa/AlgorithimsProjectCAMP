import java.util.Comparator;

public class TimeSorter implements Comparator<BusObject> {

	   @Override
	    public int compare(BusObject o1, BusObject o2) {
	        return o1.arrival_time.compareTo(o2.arrival_time);
	    }
}
