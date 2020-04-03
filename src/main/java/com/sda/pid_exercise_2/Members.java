package com.sda.pid_exercise_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Members {

	private List<Member> memberList;
	private Scanner keyboard;
	private List<Member> attendanceList;

	public Members() {
		keyboard = new Scanner(System.in);
		memberList = new ArrayList<>();
		attendanceList = new ArrayList<>();
	}
	
	public List<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<Member> newMemberList) {
		this.memberList = newMemberList;
	}
	public List<Member> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List<Member> newAttendanceList) {
		this.attendanceList = newAttendanceList;
	}
	
	public void loadMemberData(FileReader listReader) {
		List<Member> newMemberList = new ArrayList<>();
		JSONParser parser = new JSONParser();
		try {
			
			Object object = parser.parse(listReader);
			JSONObject jsonObject = (JSONObject) object;
			JSONArray memberListInfo = (JSONArray) jsonObject.get("members");
			for(int i = 0; i < memberListInfo.size(); i++) {
				JSONObject newObject = (JSONObject) memberListInfo.get(i);
				String name = (String) newObject.get("name");
				String id = (String) newObject.get("id");
				newMemberList.add(new Member(name, id));
			}			
			System.out.println("\n" + "Data was loaded!");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		setMemberList(newMemberList);
	}
	
	public void checkRepeatedID(List<Member> list) {
		boolean repeated = false;
		for(int i = 0; i < list.size(); i++) {
			for(int j = i + 1; j < list.size(); j++) {
				if(list.get(i).getId().equals(list.get(j).getId())) {
					repeated = true;
				}
			}
		}
		if(repeated == true) {
			System.out.println("\n" + "Repeated id-s were found! Id-s are supposed to be unique!" + "\n");
		} else {
			System.out.println("\n" + "No repeated id-s found." + "\n");
		}
	}
	
	public void checkAttendance() {
		List<Member> newAttendanceList = new ArrayList<>();
		System.out.println("Enter the names of people who attended (i.e. David Yu, Ric Glassey): ");
		String input = keyboard.nextLine();
		for(Member member : memberList) {
			if(input.contains(member.getName())) {
				member.setAttended(true);
				newAttendanceList.add(member);
			}
		}
		setAttendanceList(newAttendanceList);
		System.out.println("\n" + "List of members who attended: " + "\n");
	}

	@SuppressWarnings("unchecked")
	public void saveAttendanceData(FileWriter listWriter) {
		JSONObject object = new JSONObject();
		JSONArray attendedMembers = new JSONArray();
		for(Member member : attendanceList) {
			JSONObject outputObject = new JSONObject();
			outputObject.put("name", member.getName());
			outputObject.put("id", member.getId());
			attendedMembers.add(outputObject);
		}
		object.put("attendance", attendedMembers);
		try {
			listWriter.write(object.toJSONString());
			System.out.println("Successfully saved list to file!");
			System.out.println("\nJSON Object: " + object);
			listWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void saveMemberData(FileWriter listWriter) {
		JSONObject object = new JSONObject();
		JSONArray Members = new JSONArray();
		for(Member member : memberList) {
			JSONObject outputObject = new JSONObject();
			outputObject.put("name", member.getName());
			outputObject.put("id", member.getId());
			Members.add(outputObject);
		}
		object.put("members", Members);
		try {
			listWriter.write(object.toJSONString());
			System.out.println("Successfully saved list to file!");
			System.out.println("\nJSON Object: " + object);
			listWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadAttendanceData(FileReader listReader) {
		List<Member> newAttendanceList = new ArrayList<>();
		JSONParser parser = new JSONParser();
		try {
			
			Object object = parser.parse(listReader);
			JSONObject jsonObject = (JSONObject) object;
			JSONArray attendedMemberListInfo = (JSONArray) jsonObject.get("attendance");
			for(int i = 0; i < attendedMemberListInfo.size(); i++) {
				JSONObject newObject = (JSONObject) attendedMemberListInfo.get(i);
				String name = (String) newObject.get("name");
				String id = (String) newObject.get("id");
				newAttendanceList.add(new Member(name, id));
			}
			System.out.println("\n" + "Data was loaded!" + "\n");
		
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		setAttendanceList(newAttendanceList);
	}
	
	
	public void printMemberData(List<Member> list) {
		System.out.println("List: ");
		for(Member member : list) {
			System.out.println("Name: " + member.getName() + ", id: " + member.getId());			
		}
	}
	
	public void createNewMemberList() {
		List<Member> newMemberList = new ArrayList<>();
		setMemberList(newMemberList);
		System.out.println("New empty member list created!");
	}
	
	public void addMember() {
		System.out.println("Enter new member details: " + "\n" + "Name: ");
		String name = keyboard.nextLine();
		System.out.println("ID: ");
		String id = keyboard.nextLine();
		boolean idRepeated = false;
		for(Member member : memberList) {
			if(id.equals(member.getId())) {
				idRepeated = true;
				System.out.println("Entered ID already exists! IDs must be unique.");
			}
		}
		if(idRepeated == false) {
			memberList.add(new Member(name, id));
			System.out.println("\n" + "Member added!");
		}
	}
	
	public void removeMember() {
		System.out.println("Enter ID of member to be removed: ");
		String id = keyboard.nextLine();
		boolean idFound = false;
		Iterator<Member> itr = memberList.iterator();
		while(itr.hasNext()) {
			Member temp = itr.next();
			if(id.equals(temp.getId())) {
				idFound = true;
				itr.remove();
				System.out.println("Member removed.");
			}
		}
		if(idFound == false) {
			System.out.println("Member not found.");
		}
	}
	
	public void showAvailableFiles() {
		String[] pathnames;
		File file = new File(".");
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File file, String name) {
				return name.endsWith(".json");
			}
		};
		
		pathnames = file.list(filter);
		System.out.println("Available JSON files in folder: " + "\n");
		for(String pathname : pathnames) {
			System.out.println(pathname);
		}
	}
	
	public void displayApplicationHeader() {
		System.out.println("Attendance Handler System" + "\n"  + "\n" + "---------------------" + "\n"
		+ "\n" + "Welcome to AHS" + "\n");
	}
	
	public void displayOptionMenu() {
		System.out.println("\n" + "Pick an option:" + "\n" + "(1) Load member list from file" + "\n"
	    + "(2) Set/take attendance" + "\n" + "(3) Save attendance list to file" + "\n" 
		+ "(4) Load/display saved attendance file" + "\n" + "(5) Create new member list" + "\n" 
	    + "(6) Edit member list (add/remove members)" + "\n" + "(7) Save member list to file" 
	    + "\n" + "(8) Quit" + "\n" + "\n" + "Select operation: ");
	}
	
}
