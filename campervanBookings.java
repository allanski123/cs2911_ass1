import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/*	This class is responsible for creating vehicle objects, make, cancel and change bookings
 *  as well as printing those details
 */
public class campervanBookings {
	
	private ArrayList<Vehicle>van;
	
	
	public campervanBookings(){
		this.van = new ArrayList<Vehicle>();
	}
	
	/*
	 * Appends new object to the list
	 * post: van.size() + 1
	 */
	public void createVan(String location, String name, String type){
		Vehicle obj = new Vehicle(location, name, type);
		van.add(obj);
	}
	
	public ArrayList<Vehicle> getVan() {
		return van;
	}
	
	/*
	 * This function takes in the parsed information and processes it.
	 * @param hour1, month1, day1, hour2, month2, day2 are used to create date objects
	 * 	all integers are >= 0
	 * @return string of locations and names of request
	 */
	public String makeBooking(int id, int hour1, String month1, int day1, 
								int hour2, String month2, int day2, int numOfAuto, int numOfManual) throws ParseException{
		
		
		Calendar startDate = createDate(hour1, month1, day1);
		Calendar endDate = createDate(hour2, month2, day2);
		ArrayList<String>a = new ArrayList<>();
		
		//Goes through the van array: if a vehicle is available, the location and name is added to he list a
		//which is used for printing purposes
		int auto = 0; 
		for(int i = 0; i < van.size(); i++){
			if(auto == numOfAuto) break;
			if(van.get(i).getType().equals("Automatic")){
				if(!van.get(i).overlap(startDate, endDate) == true){
					van.get(i).addBooking(id, startDate, endDate);
						auto++;
						
						a.add(van.get(i).getLocation());
						a.add(van.get(i).getName());
				}	
			}
				
		}	
		//Goes through the van array: if a vehicle is available, the location and name is added to he list a
		//which is used for printing purposes
		int manual = 0;
		for(int j = 0; j < van.size(); j++){
			if(manual == numOfManual) break;
			if(van.get(j).getType().equals("Manual")){
				if(!van.get(j).overlap(startDate, endDate) == true){
					van.get(j).addBooking(id, startDate, endDate);
						manual++;
						
						a.add(van.get(j).getLocation());
						a.add(van.get(j).getName());
				}	
			}
		}
		//if the request is not 100 per cent meant return null
		String response = null;
		if(auto != numOfAuto || manual != numOfManual){
			return response;
		}
		
		response = " " + id + " " + printBookings(a);
		return response;
	}
	
	/*
	 * @param interger variables > 0 and month String is a standard Calendar month String
	 * @return Calendar date with set values
	 */
	public Calendar createDate(int hour, String month, int day) throws ParseException{
			
		Date date1 = new SimpleDateFormat("MMM").parse(month);
		Calendar setDate1 = Calendar.getInstance();
		setDate1.setTime(date1);
		setDate1.set(Calendar.HOUR_OF_DAY, hour);
		setDate1.set(Calendar.DAY_OF_MONTH, day);
		return setDate1;
	}
	
	/*
	 * Cancels booking by iterating though an array of arrays and removing  spcified elements
	 * @param id >= 0
	 */
	public void cancelBooking(int id){
		
		int i = 0;
		
		while(i < van.size()){		
			int j = 0;
			while(j < van.get(i).getBookings().size()){				
				if(van.get(i).getBookings().get(j).getID() == id){					
					van.get(i).getBookings().remove(j);
				} else {
					j++;
				}	
			}
			i++;
		}
		
	}
	
	//calls the cancel and make booking functions
	public String changeBooking(int id, int hour1, String month1, int day1, 
			int hour2, String month2, int day2, int numOfAuto, int numOfManual) throws ParseException{
		cancelBooking(id);
		String output = makeBooking(id, hour1, month1, day1, hour2, month2, day2, numOfAuto, numOfManual);
		return output;
	
	}
	
	/*
	 * prints out the requested vehicles in a specific format
	 * @param a is a list of locations and name of vehicle i.e. <location><vehicle><location><vehicle etc.
	 * @returns a String comprising of location and vehicle names 
	 */
	public String printBookings(ArrayList<String> a){
		String location = null;
		if(!a.isEmpty()) location = a.get(0);
		String output = "";
		
		int i; 
		for(i = 0; i < a.size(); i++){
			if(i == 0){
				output = location + " " + a.get(++i);
				i++;
			}
			if(i >= a.size()) break;
			if(a.get(i).equals(location)){
				output += "," + " " + a.get(++i);
				int j = i + 1;
					while(j < a.size()){			
						if(a.get(j).equals(location)){
							a.remove(j);
							output += "," + " " + a.get(j);
							a.remove(j);
						} else {
							j = j++;
						}
						j++;
						if(j >= a.size()) break;
					}
			} else {
				location = a.get(i);
				output += ";" + " " + location + " " + a.get(++i);
			}
			
		}
		
		return output;
	}
	
	//prints the booking details of a location 
	public String printLocationDetails(String location){
		String output = "";
		
		for(int i = 0; i < van.size(); i++){
			if(van.get(i).getLocation().equals(location)){
				
				for(int j = 0; j < van.get(i).getBookings().size(); j++){
					output += location + " " + van.get(i).getName() + " " ;
					output += van.get(i).getBookings().get(j).printStartDate() + " ";
					output += van.get(i).getBookings().get(j).printEndDate() + " ";
					output += "\n";
					
				}				
			}			
		}
		return output;
	}
}

















