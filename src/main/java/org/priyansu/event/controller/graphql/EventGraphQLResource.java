package org.priyansu.event.controller.graphql;

import io.quarkus.security.Authenticated;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.priyansu.event.entity.Event;
import org.priyansu.event.kafka.EventDTO;
import org.priyansu.event.kafka.EventProducer;
import org.priyansu.event.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@GraphQLApi
@ApplicationScoped
@Authenticated
public class EventGraphQLResource {

    @Inject
    EventRepository eventRepository;

    @Inject
    EventProducer eventProducer;

    @Query("allEvents")
    @RolesAllowed({"USER", "ADMIN"})
    public List<Event> getAllEvents() {
        return eventRepository.listAll();
    }

    @Query("event")
    @RolesAllowed({"USER", "ADMIN"})
    public Optional<Event> getEvent(@Name("id") Long id) {
        return eventRepository.findByIdOptional(id);
    }

    @Transactional
    @Mutation("createEvent")
    @RolesAllowed("ADMIN")
    @Blocking
    public Uni<Event> createEvent(@Name("name") String name,
                                  @Name("description") String description,
                                  @Name("date") String date,
                                  @Name("location") String location,
                                  @Name("capacity") int capacity) {
        Event event = new Event(name, description, java.time.LocalDateTime.parse(date), location, capacity);
        eventRepository.persist(event);

        EventDTO eventDTO = new EventDTO(event.getId(),
                                         event.getName(),
                                         event.getDescription(),
                                         event.getDate().toString(),
                                         event.getLocation(),
                                         event.getCapacity());
        /*eventProducer.publishEvent(eventDTO).subscribe().with(unused -> {}); // I addded ".subscribe().with(unused -> {})" to remove warning
        return event;*/
        return eventProducer.publishEvent(eventDTO).onItem().transform(unused -> event);
    }
}
