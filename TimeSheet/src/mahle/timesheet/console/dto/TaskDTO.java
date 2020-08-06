package mahle.timesheet.console.dto;

public class TaskDTO {

	// 작업자
	private String name;
	// 업무명
	private String task;
	// 월별 할당량
	private int percant;
	// 월별 할당 시간
	private int monthTime;
	// 
	private int calTime;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public int getPercant() {
		return percant;
	}
	public void setPercant(int i) {
		this.percant = i;
	}
	public int getMonthTime() {
		return monthTime;
	}
	public void setMonthTime(int monthTime) {
		this.monthTime = monthTime;
	}
	public int getCalTime() {
		return calTime;
	}
	public void setCalTime(int calTime) {
		this.calTime = calTime;
	}
}
