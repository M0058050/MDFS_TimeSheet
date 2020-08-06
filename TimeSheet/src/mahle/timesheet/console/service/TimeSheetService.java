package mahle.timesheet.console.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import mahle.timesheet.console.dto.TaskDTO;

public class TimeSheetService {
	
	public void timeSheet(String yesrMonth) {
		try {
			Map<String, Map<String,  TaskDTO>> taskDataMaster = getTaskMaster();
		    int year = 2020;
	        int month = Calendar.JULY;
	        TSUtil util = new TSUtil();
	        List<String> dayList = util.getDayList(year, month);
	        List<String> workDayList = new ArrayList<String>();
	        
	        // 워킹데이 조회
	        for (String currentday : dayList) {
	        	if(util.isWorkDay(currentday, "yyyy-MM-dd")) {
	        		workDayList.add(currentday);
	        	}
			}
	        
	        Map<String,  TaskDTO> taskData = null;
	        Iterator<String> taskDataMasterKeys = taskDataMaster.keySet().iterator();
	        String taskDataMasterkey = null;
	        String taskDatakey = null;
	        Iterator<String> taskDataKeys = null;
	        TaskDTO task = null;
	        while( taskDataMasterKeys.hasNext() ){
				taskDataMasterkey = taskDataMasterKeys.next();
//				System.out.println( String.format("member : %s", taskDataMasterkey ) );
		        
				taskData = taskDataMaster.get(taskDataMasterkey);
				taskDataKeys = taskData.keySet().iterator();
				
				while( taskDataKeys.hasNext() ) {
					taskDatakey = taskDataKeys.next();
					task = taskData.get(taskDatakey);
					
//					System.out.println( "================================================");
//					System.out.println( String.format("task : %s", taskDatakey ) );
//					System.out.println( "Name : " + task.getName() );
//					System.out.println( "task : " + task.getTask() );
//					System.out.println( "Percent : " + task.getPercant() );
//					System.out.println( "Month : " + task.getMonthTime() );
//					System.out.println( "CalTime : " + task.getCalTime() );
//					System.out.println( "================================================");
					
				}
				
				// 날짜별 처리
		        for (String currentday : workDayList) {
		        	for(int today = 2 ; today <= 8; today = today+2) {
		        		System.out.print(currentday  + "\t" );
//		        		System.out.print("today" + today);
		        		taskData = analyzeDayWork(taskData);
		        	}
				}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String,  TaskDTO> analyzeDayWork(Map<String,  TaskDTO> taskData) {
		
		String key = getAvailableTask(taskData);
		
//		System.out.println("key : " + key);
		TaskDTO task = taskData.get(key);
		
		System.out.println( task.getName() + "\t" + task.getTask() +"\t" );
		
//		System.out.println( "================================================"	);
//		System.out.println( "Name : " + task.getName() );
//		System.out.println( "task : " + task.getTask() );
//		System.out.println( "Percent : " + task.getPercant() );
//		System.out.println( "Month : " + task.getMonthTime() );
//		System.out.println( "CalTime : " + task.getCalTime() );
//		System.out.println( "================================================"	);
		
		int calTime = task.getCalTime();		
		task.setCalTime(calTime - 2);
		taskData.put((String) key, task);
		
		return taskData;
	}
	
	
	public String getAvailableTask(Map<String,  TaskDTO> taskData) {
		Object[] crunchifyKeys = taskData.keySet().toArray();
		String key = String.valueOf(crunchifyKeys[new Random().nextInt(crunchifyKeys.length)]);
		
		TaskDTO task = taskData.get(key);
		
		if(task.getCalTime() <= 0) {
			return getAvailableTask(taskData);
		} else {
			// nothing
		}
		
		return key;
	}
	
	// member, task, time
	public Map<String, Map<String,  TaskDTO>> getTaskMaster() throws Exception {
		
		Map<String, Map<String,  TaskDTO>> taskDataMaster = new HashMap<String, Map<String,  TaskDTO>>();
		Map<String,  TaskDTO> taskData = null;
		
		Map<String, List<TaskDTO>> workTimeData = getWorkTimeData();
		
		int year = 2020;
        int month = Calendar.JULY;
        TSUtil util = new TSUtil();
        List<String> dayList = util.getDayList(year, month);
        List<String> workDayList = new ArrayList<String>();
        
        List<TaskDTO> taskdata = null;
        Iterator<String> keys = workTimeData.keySet().iterator();
        
       
		// 워킹데이 조회
		
        for (String currentday : dayList) {
        	if(util.isWorkDay(currentday, "yyyy-MM-dd")) {
        		workDayList.add(currentday);
        	}
		}
        
		
		while( keys.hasNext() ){
			String key = keys.next();
//			System.out.println( String.format("member : %s", key ) );
			taskdata = workTimeData.get(key);

			for(TaskDTO data : taskdata) {
				
				taskData = taskDataMaster.get(data.getName());
				if(taskData == null) {
					taskData =  new HashMap<String,  TaskDTO>();
				}
//				System.out.println(data.getName() + "/" + data.getTask() + "/" + data.getPercant() + "/" + data.getPercant() * workDayList.size() * 8 / 100 );
				data.setMonthTime(data.getPercant() * workDayList.size() * 8 / 100);
				data.setCalTime(data.getPercant() * workDayList.size() * 8 / 100 + 2);
				
				
				taskData.put(data.getTask(), data);
				taskDataMaster.put(data.getName(), taskData);
				
			}
		}
		
		return taskDataMaster;
	}
	
	
	public Map<String, List<TaskDTO>> getWorkTimeData() {
		Map<String, List<TaskDTO>> workTimeData = new HashMap<String, List<TaskDTO>>();
		List<TaskDTO> member = null;
		String[][] memberData = 
			{
				{"sunghwan.jang","MESSystem","30"}
				,{"sunghwan.jang","SCMSystem","15"}
				,{"sunghwan.jang","M-QMSSystem","10"}
				,{"sunghwan.jang","MoldManagementSystem","5"}
				,{"sunghwan.jang","FTASystem","15"}
				,{"sunghwan.jang","BasicNotes","20"}
				,{"sunghwan.jang","PCSupport","5"}
				,{"dongwoo.ha","BasicNotes","5"}
				,{"dongwoo.ha","PCSupport","5"}
				,{"dongwoo.ha","EngPCSupport","10"}
				,{"dongwoo.ha","SAPSupport","20"}
				,{"dongwoo.ha","VPN","10"}
				,{"dongwoo.ha","Server/Network","15"}
				,{"dongwoo.ha","caduser","25"}
				,{"dongwoo.ha","caduserlight","10"}
				,{"sukyoung.park","BasicNotes","10"}
				,{"sukyoung.park","PCSupport","30"}
				,{"sukyoung.park","VPN","10"}
				,{"sukyoung.park","Printer","10"}
				,{"sukyoung.park","Server/Network","15"}
				,{"sukyoung.park","AVAYA","25"}
				,{"jaehong.lee","FTASystem","10"}
				,{"jaehong.lee","MFTSystem","20"}
				,{"jaehong.lee","PCSupport","35"}
				,{"jaehong.lee","VPN","5"}
				,{"jaehong.lee","Server/Network","30"}
				,{"chunghyo.shin","BasicNotes_BU3","20"}
				,{"chunghyo.shin","PCSupport_BU3","25"}
				,{"chunghyo.shin","EngPCSupport_BU3","10"}
				,{"chunghyo.shin","Printer_BU3","10"}
				,{"chunghyo.shin","Server/Network_BU3","20"}
				,{"chunghyo.shin","AVAYA_BU3","15"}
				,{"taeyoung.lee","BasicNotes_BU3","20"}
				,{"taeyoung.lee","PCSupport_BU3","25"}
				,{"taeyoung.lee","EngPCSupport_BU3","10"}
				,{"taeyoung.lee","Printer_BU3","10"}
				,{"taeyoung.lee","Server/Network_BU3","20"}
				,{"taeyoung.lee","AVAYA_BU3","15"}
			}
		;
		
		TaskDTO dto = null;
		for(int i = 0 ; i < memberData.length; i++) {
			dto = new TaskDTO();
			dto.setName(memberData[i][0]);
			dto.setTask(memberData[i][1]);
			dto.setPercant(Integer.parseInt(memberData[i][2]));
			
			
			member = workTimeData.get(memberData[i][0]);
			
			if(member == null) {
				member = new ArrayList<TaskDTO>(); 
			}
			member.add(dto);
			workTimeData.put(memberData[i][0], member);

		}
		
		return workTimeData;
	}
}
