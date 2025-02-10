package org.priyansu.event.controller.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.priyansu.event.entity.Event;
import org.priyansu.event.entity.UserRegistration;
import org.priyansu.event.repository.EventRepository;
import org.priyansu.event.repository.UserRegistrationRepository;

import java.util.List;
import java.util.Optional;

@GraphQLApi
@ApplicationScoped
public class UserRegistrationGraphQLResource {

    @Inject
    UserRegistrationRepository userRegistrationRepository;

    @Inject
    EventRepository eventRepository;

    @Query("allRegistrations")
    public List<UserRegistration> getAllRegistrations() {
        return userRegistrationRepository.listAll();
    }

    @Mutation("registerUser")
    @Transactional
    public UserRegistration registerUser(@Name("eventId") Long eventId,
                                         @Name("userId") String userId) {
        Optional<Event> event = eventRepository.findByIdOptional(eventId);

        if (event.isEmpty()) {
            throw new RuntimeException("Event not found");
        }
        UserRegistration registration = new UserRegistration(event.get(), userId, java.time.LocalDateTime.now());
        userRegistrationRepository.persist(registration);
        return registration;
    }
}
