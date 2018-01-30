package edu.westga.cs3212.ServiceTechMobileApplication.test.task;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.westga.cs3212.ServiceTechMobileApplication.model.Task;
import edu.westga.cs3212.ServiceTechMobileApplication.model.TaskStatus;

public class TestSetStatus {
	
	@Rule public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testSetToNullStatus() {
		Task task = new Task();
		
		this.exception.expect(IllegalArgumentException.class);
		task.setStatus(null);
	}

	@Test
	public void testSetToValidStatus() {
		Task task = new Task();
		
		task.setStatus(TaskStatus.COMPLETE);
		
		assertEquals("checking status", TaskStatus.COMPLETE, task.getStatus());
	}

}
