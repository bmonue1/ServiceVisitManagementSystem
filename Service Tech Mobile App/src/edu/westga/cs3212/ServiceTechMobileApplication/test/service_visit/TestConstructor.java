package edu.westga.cs3212.ServiceTechMobileApplication.test.service_visit;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.westga.cs3212.ServiceTechMobileApplication.model.ServiceVisit;

public class TestConstructor {
	
	@Rule public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testNullDescription() {
		LocalDateTime scheduledStartTime = LocalDateTime.now();

		this.exception.expect(IllegalArgumentException.class);
		new ServiceVisit(null, "Bob", "### Street, City, State", scheduledStartTime);
	}

	@Test
	public void testNullCustomerName() {
		LocalDateTime scheduledStartTime = LocalDateTime.now();

		this.exception.expect(IllegalArgumentException.class);
		new ServiceVisit("install service", null, "### Street, City, State", scheduledStartTime);
	}

	@Test
	public void testNullCustomerAddress() {
		LocalDateTime scheduledStartTime = LocalDateTime.now();

		this.exception.expect(IllegalArgumentException.class);
		new ServiceVisit("install service", "Bob", null, scheduledStartTime);
	}

	@Test
	public void testNullScheduledStartTime() {
		LocalDateTime scheduledStartTime = LocalDateTime.now();

		this.exception.expect(IllegalArgumentException.class);
		new ServiceVisit("install service", "Bob", "### Street, City, State", null);
	}

	@Test
	public void testCreateValidVisit() {
		LocalDateTime scheduleStartTime = LocalDateTime.now();
		
		ServiceVisit visit = new ServiceVisit("install service", "Bob", "### Street, City, State", scheduleStartTime);
		
		assertEquals("checking description", "install service", visit.getDescription());
		assertEquals("checking customer name", "Bob", visit.getCustomerName());
		assertEquals("checking customer address", "### Street, City, State", visit.getCustomerAddress());
		assertEquals("checking scheduled start time", scheduleStartTime, visit.getScheduledStartTime());
		assertNull("checking actual start time", visit.getActualStartTime());
		assertNull("checking completion time", visit.getCompletionTime());
		assertEquals("checking number of tasks", 0, visit.getTasks().size());
	}

}
