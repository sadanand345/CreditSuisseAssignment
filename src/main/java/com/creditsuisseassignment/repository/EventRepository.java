package com.creditsuisseassignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.creditsuisseassignment.model.EventModel;


@Repository
public interface EventRepository extends CrudRepository<EventModel, String> {

}
