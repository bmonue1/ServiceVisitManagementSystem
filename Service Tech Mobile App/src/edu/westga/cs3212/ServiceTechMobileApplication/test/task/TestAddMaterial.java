package edu.westga.cs3212.ServiceTechMobileApplication.test.task;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.westga.cs3212.ServiceTechMobileApplication.model.Task;

public class TestAddMaterial {
	
	@Rule public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testAddNullMaterial() {
		Task task = new Task();
		
		this.exception.expect(IllegalArgumentException.class);
		task.addMaterial(null);
	}
	
	@Test
	public void testAddEmptyMaterial() {
		Task task = new Task();
		
		this.exception.expect(IllegalArgumentException.class);
		task.addMaterial("");
	}

	@Test
	public void testAddToEmptyMaterialsList() {
		Task task = new Task();
		
		task.addMaterial("splitter");
		
		List<String> materialsList = task.getMaterials();
		assertEquals("checking size of materials list", 1, materialsList.size());
		assertEquals("checking for material just added", "splitter", materialsList.get(0));
	}

	@Test
	public void testAddToNonEmptyMaterialsList() {
		Task task = new Task();
		task.addMaterial("splitter");
		
		task.addMaterial("coax cable");
		
		List<String> materialsList = task.getMaterials();
		assertEquals("checking size of materials list", 2, materialsList.size());
		assertEquals("checking for material previously added", "splitter", materialsList.get(0));
		assertEquals("checking for material just added", "coax cable", materialsList.get(1));
	}

}
