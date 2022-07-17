package com.creditsuisseassignment.creditsuisseassignment;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.creditsuisseassignment.model.EventModel;
import com.creditsuisseassignment.repository.EventRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
class CreditsuisseassignmentApplicationTests {

	@Autowired
	private EventRepository eventRepository;

	@Test
	public void testFindById() {
		EventModel event = new EventModel();
		event.setId("event-100");
		event.setDuration(5);
		event.setHost("xyz");
		event.setEventType("APPLICATION_LOG");
		eventRepository.save(event);
		assertThat(eventRepository.findById("event-100")).isInstanceOf(Optional.class);
	}

	@Test
	public void testFindAll() {
		EventModel event = new EventModel();
		event.setId("event-1000");
		event.setDuration(3);
		event.setHost("127.0.0.1");
		event.setEventType("APPLICATION_LOG");
		event.setFlagevent(Boolean.FALSE);
		eventRepository.save(event);

		event = new EventModel();
		event.setId("event-2000");
		event.setDuration(7);
		event.setHost("XYZ");
		event.setEventType(null);
		event.setFlagevent(Boolean.TRUE);
		eventRepository.save(event);

		event = new EventModel();
		event.setId("event-3000");
		event.setDuration(1);
		event.setHost("localhost");
		event.setEventType("APPLICATION_LOG");
		event.setFlagevent(Boolean.FALSE);
		eventRepository.save(event);

		assertThat(eventRepository.findAll()).isInstanceOf(List.class);
	}

}
