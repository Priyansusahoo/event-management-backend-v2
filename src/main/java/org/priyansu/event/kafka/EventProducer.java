package org.priyansu.event.kafka;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class EventProducer {

    @Channel("event-created")
    Emitter<EventDTO> eventDTOEmitter;

    public Uni<Void> publishEvent(EventDTO event) {

        return Uni.createFrom().completionStage(eventDTOEmitter.send(event))
                .onFailure().invoke(throwable -> {
                    System.err.println("Failed to publish event: " + throwable.getMessage());
                });
        /*CompletionStage<Void> completionStage = eventDTOEmitter.send(event);
        return Uni.createFrom().completionStage(completionStage);*/

        /*return eventDTOEmitter.send(event).replaceWithVoid();*/ // not working
    }
}
