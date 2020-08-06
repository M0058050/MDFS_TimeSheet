package mahle.timesheet.console.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSUtil {
	/**
	 * Ư�� ��¥�� ���Ͽ� ������ ����(�� ~ ��)
	 * 
	 * @param date
	 * @param dateType
	 * @return
	 * @throws Exception
	 */
	public String getDateDay(String date, String dateType) throws Exception {

		String day = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat(dateType);
		Date nDate = dateFormat.parse(date);

		Calendar cal = Calendar.getInstance();
		cal.setTime(nDate);

		int dayNum = cal.get(Calendar.DAY_OF_WEEK);

		switch (dayNum) {
		case 1:
			day = "1";
			break;
		case 2:
			day = "2";
			break;
		case 3:
			day = "3";
			break;
		case 4:
			day = "4";
			break;
		case 5:
			day = "5";
			break;
		case 6:
			day = "6";
			break;
		case 7:
			day = "7";
			break;
		}

		return day;
	}
	
	public int getDayNum(String date, String dateType) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat(dateType);
		Date nDate = dateFormat.parse(date);

		Calendar cal = Calendar.getInstance();
		cal.setTime(nDate);

		int dayNum = cal.get(Calendar.DAY_OF_WEEK);

		return dayNum;
	}
	
	public Map<String,String> getNationalDayMap() throws Exception {

		Map<String,String> nationalDayMap = new HashMap<String,String>();
		nationalDayMap.put("2020-01-01", "2020-01-01");
		nationalDayMap.put("2020-01-24", "2020-01-24");
		nationalDayMap.put("2020-01-25", "2020-01-25");
		nationalDayMap.put("2020-01-27", "2020-01-27");

		return nationalDayMap;
	}
	
	public boolean isWorkDay(String date, String dateType) throws Exception {

		@SuppressWarnings("unused")
		String day = "";
		boolean isWorkDay = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateType);
		Date nDate = dateFormat.parse(date);

		Calendar cal = Calendar.getInstance();
		cal.setTime(nDate);

		int dayNum = cal.get(Calendar.DAY_OF_WEEK);
		
		switch (dayNum) {
		case 1:
			day = "1";
			isWorkDay = false;
			break;
		case 2:
			day = "2";
			isWorkDay = true;
			break;
		case 3:
			day = "3";
			isWorkDay = true;
			break;
		case 4:
			day = "4";
			isWorkDay = true;
			break;
		case 5:
			day = "5";
			isWorkDay = true;
			break;
		case 6:
			day = "6";
			isWorkDay = true;
			break;
		case 7:
			day = "7";
			isWorkDay = false;
			break;
		}
		
		Map<String,String> nationalDayMap = getNationalDayMap();
		
		String nationalDay = nationalDayMap.get(date);
		if(nationalDay == null && isWorkDay == true) {
			isWorkDay = true;
		} else {
			isWorkDay = false;
		}

		return isWorkDay;
	}
	
	public List<String> getDayList(int year, int month) throws Exception {

		List<String> dayList = new ArrayList<String>();
		int lastDay;    // �� ������ ��¥
		String currentDay = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dFormat = new SimpleDateFormat ( "yyyy-MM-dd" );
        
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, 1);
        lastDay = cal.getActualMaximum(Calendar.DATE); // �� ������ ��¥
        
        for(int day = 1; day <= lastDay; day++) {
        	currentDay = dFormat.format ( cal.getTime ());
        	cal.add(Calendar.DAY_OF_MONTH, 1);
        	dayList.add(currentDay);
        }
		
		return dayList;
	}

}
