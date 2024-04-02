package com.example.cmscassignment4;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Test template provided to students
 * Students must implement the methods
 * 
 * @author Farnaz Eivazi
 * @version 1/31/2024
 *
 */
public class CourseDBStructureTest_STUDENT {
	CourseDBStructure structure;
	@Before
	public void setUp() throws Exception {
		structure = new CourseDBStructure(30);
	}

	@AfterEach
	public void tearDown() throws Exception {
		structure = null;
	}

	@Test
	public void testCourseDBStructureStringInt() {
		try{
			new CourseDBStructure(1);
			new CourseDBStructure(0);
			new CourseDBStructure(2);
			new CourseDBStructure(3);
			new CourseDBStructure(4);
			new CourseDBStructure(5);
			new CourseDBStructure(6);
			new CourseDBStructure(7);
			new CourseDBStructure(8);
			new CourseDBStructure(9);
			new CourseDBStructure(130);
			new CourseDBStructure(490);
			new CourseDBStructure(183);
		}catch(Exception exception){
			fail("this should not throw an exception");
		}
	}

	@Test
	public void testCourseDBStructureInt() {
		try{
			new CourseDBStructure(1);
			new CourseDBStructure(5);
			new CourseDBStructure(20);
			new CourseDBStructure(45);
			new CourseDBStructure(76);
			new CourseDBStructure(120);
			new CourseDBStructure(2);
			new CourseDBStructure(99);
			new CourseDBStructure(108);
			new CourseDBStructure(0);
			new CourseDBStructure(100);
		}catch(Exception exception){
			fail("this should not throw an exception");
		}
	}

	@Test
	public void testAdd() {
		try{
			structure.add(new CourseDBElement("awdad", 1, 2, "AWDa", "awdWA"));
			structure.add(new CourseDBElement("AWda", 3, 2, "AWDsawd", "Adada"));
			structure.add(new CourseDBElement("Addd", 4, 1, "JWDYAa", "Aldkd"));
		}catch(Exception ex){
			fail("this should not throw an exception");
		}
		if(structure.showAll().size() != 3){
			fail("failed, wrong length");
		}
	}

	@Test
	public void testShowAll() {
		String[] s = {"awdad", "AWda", "Addd"};
		try{
			structure.add(new CourseDBElement("awdad", 1, 2, "AWDa", "awdWA"));
			structure.add(new CourseDBElement("AWda", 3, 2, "AWDsawd", "Adada"));
			structure.add(new CourseDBElement("Addd", 4, 1, "JWDYAa", "Aldkd"));
		}catch(Exception ex){
			fail("this should not throw an exception");
		}
		if(structure.showAll().size() != 3){
			fail("failed, wrong length");
		}
		ArrayList<String> all = structure.showAll();
		for(int i = 0; i < s.length; i++){
			if(!all.get(i).contains("Course:" + s[i])) fail("Nope " + all.get(i) + " " + s[i]);
		}
	}

	@Test
	public void testGet() {
		CourseDBElement el =new CourseDBElement("awdad", 1, 2, "AWDa", "awdWA");
		structure.add(el);
		try {
			if (!structure.get(1).equals(el)) fail("failed");
		}catch (IOException ex){
			ex.printStackTrace();
		}
	}

	@Test
	public void testGetTableSize() {
		if(structure.getTableSize() != 23){
			fail("Size should be 31 size is " + structure.getTableSize());
		}

		// first prime is 53, but that isn't a 4k prime
		CourseDBStructure s = new CourseDBStructure(72);
		if(s.getTableSize() != 59){
			fail("Size should be 59 size is " + structure.getTableSize());
		}
	}

	@Test
	public void testGet4KPrime() {
		// 30 divided by 1.5 is 20 and next prime greater than or equal to 20 is 23
		// 23 is also a 4k prime (4 * 5 = 20 + 3 = 23)
		if(structure.getTableSize() != 23){
			fail("Size should be 31 size is " + structure.getTableSize());
		}

		// given example in the javadocs
		CourseDBStructure s = new CourseDBStructure(500);
		if(s.getTableSize() != 347){
			fail("Size should be 347 size is " + s.getTableSize());
		}

		// 732 / 1.5 is 488
		// 491 is a 4k prime (4*122) = 488 + 3 = 491
		s = new CourseDBStructure(732);
		if(s.getTableSize() != 491){
			fail("Size should be 491 size is " + s.getTableSize());
		}

		// the soonest prime is 41 but that isn't a 4k prime
		// so the answer is 43
		s = new CourseDBStructure(58);
		if(s.getTableSize() != 43){
			fail("Size should be 43 size is " + s.getTableSize());
		}
	}

}
