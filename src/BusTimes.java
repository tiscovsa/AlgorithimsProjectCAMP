
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class BusTimes {
	
	public static String[][] serachByTripEnd(String time) {
		
		
		int index = 0;
		//error check input 
//		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//		try {
//		  Date dt = formatter.parse(time);
//		  Calendar cal = Calendar.getInstance();
//		  cal.setTime(dt);
//		  int inputHour = cal.get(Calendar.HOUR);
//		  int inputMinute = cal.get(Calendar.MINUTE);
//		  int inputSecond = cal.get(Calendar.SECOND);
//		} catch (ParseException e) {
//		  // This can happen if you are trying to parse an invalid date, e.g., 25:19:12.
//		  System.out.println("You must enter a valid time");
//		  e.printStackTrace();
//		}
		//read data from file
		
		//reading file data to an arraylist
        try {
            File f = new File("stop_times.txt");
            Scanner sc = new Scanner(f);
            sc.nextLine();//skip first line

          //  List<BusObject> people = new ArrayList<Person>();
            ArrayList<BusObject> stops = new ArrayList<BusObject>(); // Create an ArrayList object
//            
            BST<Integer, BusObject> tree = new BST<Integer, BusObject>();

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] details = line.split(",");
                String trip_id = details[0];
                String arrival_time = details[1];
                String departure_time = details[2];
                String stop_id = details[3];
                String stop_sequence = details[4];
                String stop_headsign = details[5];
                String pickup_type = details[6];
                String drop_off_type = details[7];
                String shape_dist_traveled;
                if(details.length > 8) {
                	 shape_dist_traveled = details[8];
                }else {
                	 shape_dist_traveled = "";
                }
                BusObject bus = new BusObject(Integer.parseInt(trip_id), arrival_time, departure_time, 
                		stop_id, stop_sequence, stop_headsign, pickup_type,
                		drop_off_type, shape_dist_traveled);
                stops.add(bus);
                
//                tree.put(arrival_time, bus);
//                index++;
//                System.out.println("added line!");
               // Person p = new Person(gender, name, age);
               // people.add(p);
            }
            //sort the array
            stops.sort(new TimeSorter());
            
            //find min and max index of the range for matching times
            int low = lower_bound(stops, time, 0, stops.size());
//            System.out.println(low);
           // int high = upper_bound(stops, time, 0, stops.size());
//            int high = binarySearch(stops, 0, stops.size()-1, time);
//            long answer = 0;
//    		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//    		try {
//    		  Date dt = formatter.parse(time);
//    		  Calendar cal = Calendar.getInstance();
//    		  cal.setTime(dt);
//    		  int inputHour = cal.get(Calendar.HOUR);
//    		  System.out.println(inputHour);
//    		  int inputMinute = cal.get(Calendar.MINUTE);
//    		  System.out.println(inputMinute);
//    		  int inputSecond = cal.get(Calendar.SECOND);
//    		  System.out.println(inputSecond);
//    		   answer = (inputHour * 60 * 60) + (inputMinute* 60) + inputSecond;
//    		} catch (ParseException e) {
//    		  // This can happen if you are trying to parse an invalid date, e.g., 25:19:12.
//    		  System.out.println("You must enter a valid time");
//    		  e.printStackTrace();
//    		}
            //////
            LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
            int hour = localTime.get(ChronoField.CLOCK_HOUR_OF_DAY);
            int minute = localTime.get(ChronoField.MINUTE_OF_HOUR);
            int second = localTime.get(ChronoField.SECOND_OF_MINUTE);
            int answer = (hour * 60 * 60) + (minute* 60) + second;
            //prints "hour: 13, minute: 24, second: 40":
//            System.out.println(String.format("hour: %d, minute: %d, second: %d", hour, minute, second));
//            ///////
//            
//    		System.out.println(answer);
            int high = binarySearch(stops, 0, stops.size() -1 , answer);
            
//            System.out.println(high);
            

            
            
            ArrayList<BusObject> results = new ArrayList<BusObject>(); // Create an ArrayList for results
//            for(int i = 0; i < stops.size(); i++) {
////            	System.out.println(stops.get(i).arrival_time);
//            	if(stops.get(i).arrival_time.equals(time)) {
////            		System.out.print(b.stop_id);
////            		System.out.println("match found");
//            		results.add(stops.get(i));
//            		
//            	}
//            	//System.out.print(b.tripId);
//            }
//            display results
            
          for(int i = low; i < high; i++) {
//    	System.out.println(stops.get(i).arrival_time);
//    	if(stops.get(i).arrival_time.equals(time)) {
//    		System.out.print(b.stop_id);
//    		System.out.println("match found");
    		results.add(stops.get(i));
    		
//    	}
    	//System.out.print(b.tripId);
    }
            
          
          //make string array for the UI
          String [][] s = new String[results.size()][9];
          
            //results.sort(new TripIdSorter());
            System.out.println("Here are the results with arrival time: " + time);
            int j = 0;
          for(BusObject p: results){
        	  
        	  s[j][0] = Integer.toString(p.tripId);
        	  s[j][1] = p.arrival_time;
        	  s[j][2] = p.departure_time;
        	  s[j][3] = p.stop_id;
        	  s[j][4] = p.stop_sequence;
        	  s[j][5] = p.stop_headsign;
        	  s[j][6] = p.pickup_type;
        	  s[j][7] = p.drop_off_type;
        	  s[j][8] = p.shape_dist_traveled;
        	  
        	  
        	  j++;
          System.out.println(p + "\n\n");
          
          
          }
          return s; 

//            for(Person p: people){
//                System.out.println(p.toString());
//            }

        } catch (FileNotFoundException e) {         
            e.printStackTrace();
            return new String[0][0];
        }
       
      
	}
	
	public static int lower_bound(ArrayList<BusObject> stops, String key, int low, int high)
	{
	    if (low > high)
	        //return -1;
	        return low;

	    int mid = low + ((high - low) >> 1);
	    //if (arr[mid] == key) return mid;

	    //Attention here, we go left for lower_bound when meeting equal values
//	    if (stops.get(mid).arrival_time >= key) 
	    if (stops.get(mid).arrival_time.compareTo(key) >= 0) 
	        return lower_bound(stops, key, low, mid - 1);
	    else
	        return lower_bound(stops, key, mid + 1, high);
	}
//
//	public static int upper_bound(ArrayList<BusObject> stops, String key, int low, int high)
//	{
//	    if (low > high)
//	        //return -1;
//	        return low;
//
//	    int mid = low + ((high - low) >> 1);
//	    //if (arr[mid] == key) return mid;
//
//	    //Attention here, we go right for upper_bound when meeting equal values
////	    if (arr[mid] > key) 
//	    if (stops.get(mid).arrival_time.compareTo(key) > 0) 
//	        return upper_bound(stops, key, low, mid - 1);
//	    else
//	        return upper_bound(stops, key, mid + 1, high);
//	}
//	
//    public static int binarySearch(ArrayList<BusObject> stops, int l, int r, String x)
//    {
//        if (r >= l) {
//            int mid = l + (r - l) / 2;
// 
//            // If the element is present at the
//            // middle itself
//            if (stops.get(mid).arrival_time.equals(x))
//                return mid;
// 
//            // If element is smaller than mid, then
//            // it can only be present in left subarray
//            if (stops.get(mid).arrival_time.compareTo(x) > 0)
//                return binarySearch(stops, l, mid - 1, x);
// 
//            // Else the element can only be present
//            // in right subarray
//            return binarySearch(stops, mid + 1, r, x);
//        }
// 
//        // We reach here when element is not present
//        // in array
//        return -1;
//    }
//    
//    static int binarySearch(ArrayList<BusObject> stops, String x)
//    {
//        int l = 0, r = stops.size() - 1;
//        while (l <= r) {
//            int m = l + (r - l) / 2;
//  
//            int res = x.compareTo(stops.get(m).arrival_time);
//  
//            // Check if x is present at mid
//            if (res == 0)
//                return m;
//  
//            // If x greater, ignore left half
//            if (res > 0)
//                l = m + 1;
//  
//            // If x is smaller, ignore right half
//            else
//                r = m - 1;
//        }
//  
//        return -1;
//    }
    
    public static int binarySearch(ArrayList<BusObject> stops, int l, int r, int x)
    {
    	
        if (r >= l) {
            int mid = l + (r - l) / 2;
            
            int answer = 0;
//    		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//    		try {
//    		  Date dt = formatter.parse(stops.get(mid).arrival_time);
//    		  Calendar cal = Calendar.getInstance();
//    		  cal.setTime(dt);
//    		  int inputHour = cal.get(Calendar.HOUR);
//    		  int inputMinute = cal.get(Calendar.MINUTE);
//    		  int inputSecond = cal.get(Calendar.SECOND);
//    		   answer = (inputHour * 60 * 60) + (inputMinute* 60) + inputSecond;
//    		} catch (ParseException e) {
//    		  // This can happen if you are trying to parse an invalid date, e.g., 25:19:12.
//    		  System.out.println("You must enter a valid time");
//    		  e.printStackTrace();
//    		}
            try {
            LocalTime localTime = LocalTime.parse(stops.get(mid).arrival_time, DateTimeFormatter.ofPattern("HH:mm:ss"));
            int hour = localTime.get(ChronoField.CLOCK_HOUR_OF_DAY);
            int minute = localTime.get(ChronoField.MINUTE_OF_HOUR);
            int second = localTime.get(ChronoField.SECOND_OF_MINUTE);
             answer = (hour * 60 * 60) + (minute* 60) + second;
            //prints "hour: 13, minute: 24, second: 40":
//            System.out.println(answer + "time: " + stops.get(mid).arrival_time);
    		} catch (DateTimeParseException e) {
  		  // This can happen if you are trying to parse an invalid date, e.g., 25:19:12.
  		  System.out.println("You must enter a valid time" + stops.get(mid).arrival_time);
  		  answer = 0x7FFFFFFF;
//  		  e.printStackTrace();
  		}
 
            // If the element is present at the
            // middle itself
            if (answer == x)
                return mid;
 
            // If element is smaller than mid, then
            // it can only be present in left subarray
            else if (answer > x) {
//            	System.out.println("going to left array");
                return binarySearch(stops, l, mid - 1, x);
            }
 
            // Else the element can only be present
            // in right subarray
            else {
            return binarySearch(stops, mid + 1, r, x);
            }
        }
 
        // We reach here when element is not present
        // in array
        return -1;
    }
    
 
 

}


