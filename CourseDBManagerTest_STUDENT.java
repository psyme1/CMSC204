package com.example.cmscassignment4;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Test template provided to students
 * Students must implement the methods
 * 
 * @author Farnaz Eivazi
 * @version 1/31/2024
 *
 */
public class CourseDBManagerTest_STUDENT {
	CourseDBManager manager;
	@Before
	public void setUp() throws Exception {
		manager = new CourseDBManager();
	}

	@AfterEach
	public void tearDown() throws Exception {
		manager = null;
	}

	@Test
	public void testAdd() {
		manager.add("a4bd7", 25544, 3 ,"abc", "M.D");
		for(String str : manager.showAll()){
			if(!str.startsWith("Course:a4bd7")){
				fail("Wrong");
			}
		}
		manager.add("k9j7", 32, 98, "mbk", "DM");
		if(!manager.get(32).getId().equals("k9j7")){
			fail("Wrong");
		}
		manager.add("UAIWDdA", 44, 4, "AWdsa", "Ddda");
		if(!manager.get(44).getId().equals("UAIWDdA")){
			System.out.println(manager.get(44).getId());
			fail("Wrong");
		}
	}

	@Test
	public void testShowAll() {
		manager.add("UAIWDdA", 44, 4, "AWdsa", "Ddda");
		manager.add("OAWIdh", 45, 2, "AWh", "Dhdd");
		manager.add("IUAWDhb", 46, 3, "POK", "AWJdn");
		manager.add("OJWD", 47, 6, "MCK", "ADdd");
		manager.add("AWDddf", 48, 1, "AWDDAD", "AWYAWDG");


		String[] part = {"UAIWDdA", "OAWIdh", "IUAWDhb", "OJWD", "AWDddf"};
		for(int i = 0; i < manager.showAll().size(); i++){
			if(!manager.showAll().get(i).startsWith("Course:" + part[i])) {
				fail("wrong");
			}
		}
	}

	@Test
	public void testReadFile() {
		manager.add("UAIWDdA", 44, 4, "AWdsa", "Ddda");
		manager.add("CMSC216", 216, 6, "Takoma Park", "Dr. Fouche");
		String myStr = "Awdhaw 11 2 Germantown Eivazi";
		File file = new File("tessst.txt");
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(myStr + "\n");
			for(String str : manager.showAll())
			fileWriter.write(str + "\n");
			fileWriter.flush();


			fileWriter.close();

			CourseDBManager n = new CourseDBManager();
			n.readFile(file);
			for(String element : n.showAll()){
				System.out.println(element);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGet() {
		manager.add("a4bd7", 25544, 3 ,"abc", "M.D");
		manager.add("32kAWHD a", 24455, 3 ,"P l ok", "Hello There");
		String[] s = {"a4bd7", "32kAWHD a"};
		int i = 0;
		for(String str : manager.showAll()){
			if(!str.startsWith("Course:" + s[i])){
				fail("Wrong");
			}
			i++;
		}
	}

}
