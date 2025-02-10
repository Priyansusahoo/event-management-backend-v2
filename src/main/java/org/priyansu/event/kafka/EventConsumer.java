package org.priyansu.event.kafka;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class EventConsumer {

    @Incoming("event-created")
    @Blocking
    public void receiveEvent(EventDTO event) {
        try {
            System.out.println("New event Created: " + event.name);
            System.out.println("Date: " + event.date);
            System.out.println("Location: " + event.location);
        } catch (Exception e) {
            System.err.println("Error processing event: " + e.getMessage());
        }
    }
}
