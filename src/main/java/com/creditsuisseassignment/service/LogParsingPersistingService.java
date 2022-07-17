package com.creditsuisseassignment.service;

import com.creditsuisseassignment.model.EventModel;
import com.creditsuisseassignment.repository.EventRepository;
import com.creditsuisseassignment.utilities.EventCons;
import com.creditsuisseassignment.utilities.GenericLogEvent;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Service
public class LogParsingPersistingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogParsingPersistingService.class);

    @Autowired
    private EventRepository eventRepository;

    //@Autowired
    //private ThresholdConfig thresholdConfig;
    //Or
    @Value("${time-threshold}")
    private int timeThreshold;

    @Value("${data-structure-threshold}")
    private int dataStructureThreshold;

    public void parseAndPersistEvents(String filePath) throws IOException {
        //Maintain a map of events
        Map<String, GenericLogEvent> genericLogEventMap = new HashMap<>();

        //Maintain a map of alerts
        Map<String, EventModel> eventMap = new HashMap<>();

        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(filePath);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                //Read a line from the file
                String json = sc.nextLine();

                try {
                    //Convert to Event
                	GenericLogEvent event = new Gson().fromJson(json, GenericLogEvent.class);

                    //Check if already exists in Map
                    if (genericLogEventMap.containsKey(event.getId())) {
                    	GenericLogEvent oldEvent = genericLogEventMap.get(event.getId());

                        //Identify which is STARTED and which is FINISHED
                    	GenericLogEvent startEvent = null;
                        if (EventCons.STARTED.equals(event.getState()))
                            startEvent = event;

                        if (EventCons.STARTED.equals(oldEvent.getState()))
                            startEvent = oldEvent;

                        GenericLogEvent finishedEvent = null;
                        if (EventCons.FINISHED.equals(event.getState()))
                            finishedEvent = event;

                        if (EventCons.FINISHED.equals(oldEvent.getState()))
                            finishedEvent = oldEvent;

                        if (startEvent == null || finishedEvent == null)
                            throw new NullPointerException();

                        //Get the execution time
                        long executionTime = finishedEvent.getTimestamp() - startEvent.getTimestamp();

                        //Create an Alert
                        EventModel alert = new EventModel(event.getId(), new Long(executionTime).intValue(), event.getType(), event.getHost(), false);

                        // if the execution time is more than the specified threshold, flag the alert as TRUE
                        if (executionTime > timeThreshold) {
                            alert.setFlagevent(false);
                            LOGGER.trace("The execution time for the event {} is {}ms", event.getId(), executionTime);
                        }

                        //Add in AlertsMap
                        eventMap.put(event.getId(), alert);

                        // remove from the EventsMap
                        genericLogEventMap.remove(event.getId());
                    } else {
                    	genericLogEventMap.put(event.getId(), event);
                    }
                } catch (JsonParseException ex) {
                    LOGGER.error("Unable to parse the event: {}", ex.getMessage());
                }
            }
            if (eventMap.size() != 0) {
                LOGGER.debug("Saving alerts in DB. The size of alert map is: {}", eventMap.size());
                eventRepository.saveAll(eventMap.values());
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
    }
}