
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.text.ParseException;
//import java.util.InputMismatchException;
//import java.util.Calendar;
import java.util.Scanner;

public class VanRentalSystem {

	public static void main(String[] args) throws ParseException {
		
		 Scanner sc = null;
		try	{
			sc = new Scanner(new FileReader(args[0]));
			parseInfo(sc);		
		}
		
		catch (FileNotFoundException e){}
		finally {
			if (sc != null) sc.close();
		}
	} 
	
	public static void parseInfo(Scanner sc) throws ParseException{
		campervanBookings bookings = new campervanBookings();
		
		
		while(sc.hasNext()){
			String firstWord = sc.next();
			
			if(firstWord.equals("Location")){
				String location = sc.next();
				String name = sc.next();
				String type = sc.next();	
				
				bookings.createVan(location, name, type);
				
				if(!sc.hasNextLine()){
					break;
				} else {
					sc.nextLine();
				}
				
			} else if(firstWord.equals("Request") || firstWord.equals("Change")){
				//parses the request information
				
				String response = null;
				int id = sc.nextInt();			
				int hour1 = sc.nextInt();				
				String month1 = sc.next();
				int day1 = sc.nextInt();
				
				int hour2 = sc.nextInt();
				String month2 = sc.next();
				int day2 = sc.nextInt();
				
				int numOfAuto = sc.nextInt();
				int numOfManual = 0;
				
				if(sc.next().equals("Automatic")){
					if(sc.hasNextInt())
						numOfManual = sc.nextInt();
				} else {
					numOfManual = numOfAuto;
					if(sc.hasNextInt()){
						numOfAuto = sc.nextInt();
					}else{ 
						numOfAuto = 0;
					}
				}
				
				
				if(firstWord.equals("Request")){
					response = bookings.makeBooking(id, hour1, month1, day1, hour2, month2, day2, numOfAuto, numOfManual);
					if(response == null){
						System.out.println("Booking Rejected");
					} else {
						System.out.println("Bookings" + response);
					}
				} else {
					
					response = bookings.changeBooking(id, hour1, month1, day1, hour2, month2, day2, numOfAuto, numOfManual);
					if(response == null){
						System.out.println("Change Rejected");
					} else {
						System.out.println("Change" + response);
					}
					
				}
				
				
			} else if(firstWord.equals("Cancel")){
				
				int id = sc.nextInt();
				bookings.cancelBooking(id);
					System.out.println("Cancel" + " " + Integer.toString(id));
				
				
			} else if(firstWord.equals("Print")){
				String location = sc.next();
				System.out.println(bookings.printLocationDetails(location));					
				
			} else {
				if(!sc.hasNextLine()){
					break;
				} else {
					sc.nextLine();
				}
			}
			
			
			
			
		}
		
	}
	
	
		
		
		
		
	
	

}