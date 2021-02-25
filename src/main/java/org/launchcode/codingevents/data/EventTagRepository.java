package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.EventTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTagRepository extends CrudRepository<EventTag, Integer> {
}
