package com.creditsuisseassignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Event")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class EventModel {

    @Id
    private String id;

    private int duration;

    private String eventType;

    private String host;

    private boolean flagevent;
    
}
