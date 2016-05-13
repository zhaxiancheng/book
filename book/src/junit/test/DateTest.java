package junit.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) throws ParseException {
		String dateStr = "2014-01-01";
		DateFormat dateFormate  = new SimpleDateFormat("yyyy-MM-dd");
		Date today = dateFormate.parse(dateStr);
		Date today30After =  getDate(today);
		System.out.println(today30After.toLocaleString());
		
	}
	public static Date getDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, 30);
		return c.getTime();
	}
}
