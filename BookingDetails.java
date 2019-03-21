import java.text.ParseException;

import java.util.Calendar;

/*
 * Stores the details of the bookings
 */

public class BookingDetails {
	private int ID;
	private Calendar startDate;
	private Calendar endDate;
	
	public BookingDetails(int id, Calendar start, Calendar end) throws ParseException{
		this.ID = id;
		this.startDate = start;
		this.endDate = end;
		
	}
	
	public int getID(){
		return ID; 
	}
	
	public Calendar getStartDate(){
		return startDate;
	}
	
	public Calendar getEndDate(){
		return endDate;
	}

	
	public String printStartDate(){
		
		String hour = Integer.toString(startDate.get(Calendar.HOUR_OF_DAY));
		String month = intToMonth(startDate.get(Calendar.MONTH));
		String day = Integer.toString(startDate.get(Calendar.DAY_OF_MONTH));
		
		String startOutput = hour + ":00" + " " + month + " " + day;
		return  startOutput;
	}
	
	
	public String printEndDate(){
		
		String hour = Integer.toString(endDate.get(Calendar.HOUR_OF_DAY));
		String month = intToMonth(endDate.get(Calendar.MONTH));
		String day = Integer.toString(endDate.get(Calendar.DAY_OF_MONTH));
		
		String endOutput = hour + ":00" + " " + month + " " + day;
		return  endOutput;
	}

	/*
	 * this function is used for both printStartDate and printEndDate to convert an
	 * integer representation of the month
	 * 
	 * @param monthNum >= 0 && monthNum <= 12 
	 * @return String representation of month
	 */
	public String intToMonth(int monthNum){
		String month = null;
		switch (monthNum) {
        case 0:  month = "Jan"; break;
        case 1:  month = "Feb"; break;
        case 2:  month = "Mar"; break;
        case 3:  month = "Apr"; break;
        case 4:  month = "May"; break;
        case 5:  month = "Jun"; break;
        case 6:  month = "Jul"; break;
        case 7:  month = "Aug"; break;
        case 8:  month = "Sep"; break;
        case 9:  month = "Oct"; break;
        case 10: month = "Nov"; break;
        case 11: month = "Dec"; break; }
		return month;
	}

	
}

