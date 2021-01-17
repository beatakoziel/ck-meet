package com.meet.ck.database.repository;

import com.meet.ck.database.entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRelationshipRepository extends JpaRepository<Relationship, Long> {
}
