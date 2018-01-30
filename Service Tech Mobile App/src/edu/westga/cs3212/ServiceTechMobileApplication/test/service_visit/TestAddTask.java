package edu.westga.cs3212.ServiceTechMobileApplication.test.service_visit;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.westga.cs3212.ServiceTechMobileApplication.model.ServiceVisit;
import edu.westga.cs3212.ServiceTechMobileApplication.model.Task;

public class TestAddTask {
	
	@Rule public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testAddNullTask() {
		ServiceVisit visit = new ServiceVisit("install service", "Bob", "### Street, City, State", LocalDateTime.now());
		
		this.exception.expect(IllegalArgumentException.class);
		visit.addTask(null);
	}

	@Test
	public void testAddToEmptyTaskList() {
		ServiceVisit visit = new ServiceVisit("install service", "Bob", "### Street, City, State", LocalDateTime.now());
		Task task1 = new Task();
		
		visit.addTask(task1);
		
		List<Task> taskList = visit.getTasks();
		assertEquals("checking size of task list", 1, taskList.size());
		assertSame("checking for task just added", task1, taskList.get(0));
	}

	@Test
	public void testAddToNonEmptyTaskList() {
		ServiceVisit visit = new ServiceVisit("install service", "Bob", "### Street, City, State", LocalDateTime.now());
		Task task1 = new Task();
		visit.addTask(task1);
		Task task2 = new Task();
		
		visit.addTask(task2);
		
		List<Task> taskList = visit.getTasks();
		assertEquals("checking size of task list", 2, taskList.size());
		assertSame("checking for task previously added", task1, taskList.get(0));
		assertSame("checking for task just added", task2, taskList.get(1));
	}

}
