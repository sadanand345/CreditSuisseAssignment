package com.creditsuisseassignment.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class GenericLogEvent {
    private String id;

    private EventCons state;

    private String type;

    private String host;

    private long timestamp;
}