package mahle.timesheet.console.service;

public class TimeSheet {

	TimeSheetService service = new TimeSheetService();
	
	public void timeSheet(String yesrMonth) {
		service.timeSheet(yesrMonth);
	}
}
