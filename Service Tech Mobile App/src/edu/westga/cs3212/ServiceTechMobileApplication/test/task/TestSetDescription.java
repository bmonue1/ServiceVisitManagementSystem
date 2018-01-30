package edu.westga.cs3212.ServiceTechMobileApplication.test.task;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.westga.cs3212.ServiceTechMobileApplication.model.Task;

public class TestSetDescription {
	
	@Rule public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testSetToNullDescription() {
		Task task = new Task();
		
		this.exception.expect(IllegalArgumentException.class);
		task.setDescription(null);
	}
	
	@Test
	public void testSetToEmptyDescription() {
		Task task = new Task();
		
		this.exception.expect(IllegalArgumentException.class);
		task.setDescription("");
	}

	@Test
	public void testSetToValidDescription() {
		Task task = new Task();
		
		task.setDescription("setup 'the box'");
		
		assertEquals("checking status", "setup 'the box'", task.getDescription());
	}

}
