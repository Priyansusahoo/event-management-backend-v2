package org.priyansu.event.kafka;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class EventDTODeserializer extends JsonbDeserializer<EventDTO> {
    public EventDTODeserializer() {
        super(EventDTO.class);
    }
}
