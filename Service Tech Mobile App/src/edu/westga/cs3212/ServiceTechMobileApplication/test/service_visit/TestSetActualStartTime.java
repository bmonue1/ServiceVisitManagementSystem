package edu.westga.cs3212.ServiceTechMobileApplication.test.service_visit;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.westga.cs3212.ServiceTechMobileApplication.model.ServiceVisit;

public class TestSetActualStartTime {
	
	@Rule public ExpectedException exception = ExpectedException.none();

	@Test
	public void testSetNullActualStartTime() {
		LocalDateTime scheduledStartTime = LocalDateTime.now();
		ServiceVisit visit = new ServiceVisit("install service", "Bob", "### Street, City, State", scheduledStartTime);

		this.exception.expect(IllegalArgumentException.class);
		visit.setActualStartTime(null);
	}

	@Test
	public void testSetValidActualStartTimeWithActualStartTimeAlreadySet() {
		LocalDateTime scheduledStartTime = LocalDateTime.now();
		ServiceVisit visit = new ServiceVisit("install service", "Bob", "### Street, City, State", scheduledStartTime);
		visit.setActualStartTime(scheduledStartTime);

		this.exception.expect(IllegalStateException.class);
		visit.setActualStartTime(scheduledStartTime);
	}

	@Test
	public void testSetInitialValidActualStartTime() {
		LocalDateTime scheduledStartTime = LocalDateTime.now();
		ServiceVisit visit = new ServiceVisit("install service", "Bob", "### Street, City, State", scheduledStartTime);

		visit.setActualStartTime(scheduledStartTime);
		
		assertEquals("checking actual start time", scheduledStartTime, visit.getActualStartTime());
	}

}
