package org.priyansu.event.kafka;

public class EventDTO {
    public Long id;
    public String name;
    public String description;
    public String date;
    public String location;
    public int capacity;

    public EventDTO() {
    }

    public EventDTO(Long id, String name, String description, String date, String location, int capacity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.capacity = capacity;
    }
}
