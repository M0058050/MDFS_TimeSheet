package mahle.timesheet.console.base;

import mahle.timesheet.console.service.TimeSheet;

public class MainClass {

	public static void main(String[] args) throws Exception {
		TimeSheet ts = new TimeSheet();
		ts.timeSheet("2020-06");
		
	}
}
