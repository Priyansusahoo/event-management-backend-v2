package org.priyansu.event.kafka;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class EventConsumer {

    @Incoming("event-created-in")
    @Blocking
    public void receiveEvent(EventDTO event) {
        try {
            System.out.println("\uD83D\uDCE2 New event Created: " + event.name);
            System.out.println("\uD83D\uDCC5 Date: " + event.date);
            System.out.println("\uD83D\uDCCD Location: " + event.location);
        } catch (Exception e) {
            System.err.println("Error processing event: " + e.getMessage());
        }
    }
}
