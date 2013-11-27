package test;

import java.util.Calendar;
import java.util.Date;

//Calendarクラスの使い方復習
public class CalendarTest {
	
	private Calendar cal = Calendar.getInstance();
	
	public CalendarTest(){
	}
	
	public static void main(String[] args){
		CalendarTest calTest = new CalendarTest();
		calTest.cal.set(2013,4,1);
		System.out.println(
				calTest.cal.get(Calendar.YEAR) + "/" +
				(calTest.cal.get(Calendar.MONTH) + 1 ) + "/" +
				calTest.cal.get(Calendar.DAY_OF_MONTH)
		);
		calTest.cal.add(Calendar.DAY_OF_MONTH, -10);
		System.out.println(
				calTest.cal.get(Calendar.YEAR) + "/" +
				(calTest.cal.get(Calendar.MONTH) + 1 ) + "/" +
				calTest.cal.get(Calendar.DAY_OF_MONTH)
		);
	}
	

}
