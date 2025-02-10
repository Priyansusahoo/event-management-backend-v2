package org.priyansu.event.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.priyansu.event.entity.Event;

@ApplicationScoped
public class EventRepository implements PanacheRepository<Event> {
}
