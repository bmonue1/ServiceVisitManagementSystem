package edu.westga.cs3212.ServiceTechMobileApplication.test.service_visit;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.westga.cs3212.ServiceTechMobileApplication.model.ServiceVisit;

public class TestSetCompletionTime {
	
	@Rule public ExpectedException exception = ExpectedException.none();

	@Test
	public void testSetNullCompletionTime() {
		LocalDateTime scheduledStartTime = LocalDateTime.now();
		ServiceVisit visit = new ServiceVisit("install service", "Bob", "### Street, City, State", scheduledStartTime);
		visit.setActualStartTime(scheduledStartTime);

		this.exception.expect(IllegalArgumentException.class);
		visit.setCompletionTime(null);
	}

	@Test
	public void testSetInitialValidCompletionTimeWithActualStartTimeNotSet() {
		LocalDateTime scheduledStartTime = LocalDateTime.now();
		LocalDateTime completionTime = scheduledStartTime.plusDays(1);
		ServiceVisit visit = new ServiceVisit("install service", "Bob", "### Street, City, State", scheduledStartTime);

		this.exception.expect(IllegalStateException.class);
		visit.setCompletionTime(completionTime);
	}

	@Test
	public void testSetValidCompletionTimeWithCompletionTimeAlreadySet() {
		LocalDateTime scheduledStartTime = LocalDateTime.now();
		LocalDateTime completionTime = scheduledStartTime.plusDays(1);
		ServiceVisit visit = new ServiceVisit("install service", "Bob", "### Street, City, State", scheduledStartTime);
		visit.setActualStartTime(scheduledStartTime);
		visit.setCompletionTime(completionTime);

		this.exception.expect(IllegalStateException.class);
		visit.setCompletionTime(completionTime);
	}

	@Test
	public void testSetInitialValidCompletionTime() {
		LocalDateTime scheduledStartTime = LocalDateTime.now();
		LocalDateTime completionTime = scheduledStartTime.plusDays(1);
		ServiceVisit visit = new ServiceVisit("install service", "Bob", "### Street, City, State", scheduledStartTime);
		visit.setActualStartTime(scheduledStartTime);
		
		visit.setCompletionTime(completionTime);
		
		assertEquals("checking completion time", completionTime, visit.getCompletionTime());
	}

}
