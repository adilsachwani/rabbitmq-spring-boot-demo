package com.adilsachwani.consumerservice.repository;

import com.adilsachwani.consumerservice.model.Cricketer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CricketerRepository extends MongoRepository<Cricketer, UUID> {
}
