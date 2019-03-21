//import java.text.ParseException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

/*
 * This class has the responsibility to store the details of the Vehicles,
 * add bookings and check for overlaps within bookings
 * 
 */
public class Vehicle {
	private String name;
	private String location;
	private String type;
	private ArrayList<BookingDetails>bookings;

	
	//Vehicle Constructor
	public Vehicle(String location, String name, String type){
		this.name = name;
		this.location = location;
		this.type = type;
		this.bookings = new ArrayList<>();
		
	}
	
	
	/*	
	 * adds bookings to the bookings array
	 * 
	 * @param id > 0
	 * @param start and end for this is assumed to be set to dates this year i.e. 2017
	 */
	public void addBooking(int id, Calendar start, Calendar end) throws ParseException{
			bookings.add(new BookingDetails(id, start, end));
				
	} 
		
	/*
	 * Checks if campervan is available 
	 * 
	 * @param start and end for this is assumed to be set to dates this year i.e. 2017
	 * @returns false is vehicle is available 
	 */
	public boolean overlap(Calendar start, Calendar end){
		//if empty list the object does not overlap.
		if(bookings.isEmpty()) return false;
		
		//Check the first element in the list before checking elements in between
		if(end.before(bookings.get(0).getStartDate()) || start.after(bookings.get(0).getEndDate())){			
			if(bookings.size() == 1) return false;
			
			int i = 0; int j = i + 1;
			while (i < bookings.size()){
				if(start.after(bookings.get(i).getEndDate()) || end.before(bookings.get(j).getStartDate())) {
					return false;
					
				} else {
					break;
				}
			}
		} 			
		return true;
	}
	
	
	public String getName(){
		return this.name;  
	}
		
	public String getLocation(){
		return this.location;
	}
	
	public String getType(){
		return this.type;
	}
	
	public ArrayList<BookingDetails> getBookings(){
		return bookings;
	}
	
	
}
