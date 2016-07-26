package edu.acc.java2.displayStaff;

import java.io.*;
import java.util.*;

public class DisplayStaff{
	public static void main(String[] args){
		ArrayList<String> employee = new ArrayList<String>();
		String line;
		int numberLoaded = 0;
		int lineLoaded = 0;
		int badRecord = 0;
		try(BufferedReader br = new BufferedReader(new FileReader("staff.txt"))){
			while ((line = br.readLine()) != null){
				lineLoaded++;
				//comments in file
				if (line.contains("#")){
					continue;
				}
				
				String[] info = line.split(",");
				int itemCount = info.length;
				//if more than 4 attributes to an employee
				if (itemCount != 4){
					System.out.printf("Line %d is invalid. Expected 4 fields, found %d.\n", lineLoaded, itemCount);
					badRecord++;
					continue;
				}
				//if employee ID is not an integer
				String employeeID = info[2];
				if (!employeeID.matches("^-?\\d+$")){
					System.out.printf("Line %d is invalid. Expected integer ID, found %s.\n", lineLoaded, employeeID);
					badRecord++;
					continue;
				}
					
				//add employees to ArrayList
				employee.add(line);
				numberLoaded++;
			}
			System.out.printf("\n%d Employees loaded.\n", numberLoaded);
			System.out.printf("\nSkipped %d bad records.\n\n", badRecord);
			for (String str: employee){
				String[] info = str.split(",");
				String fullName = info[1] + " " + info[0];
				System.out.printf("Employee[name:%s, id:%s, title:%s]\n", fullName, info[2], info[3]);
			}
		} catch (IOException ioe){
			System.out.println("Read Error: " + ioe.getMessage());		
		}
	}
}
