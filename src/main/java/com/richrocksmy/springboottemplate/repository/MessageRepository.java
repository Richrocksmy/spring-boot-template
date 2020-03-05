package com.richrocksmy.springboottemplate.repository;

import com.richrocksmy.springboottemplate.model.db.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends CrudRepository<Message, UUID> {}
