package com.meet.ck.database.repositories;

import com.meet.ck.database.entities.Comment;
import com.meet.ck.database.entities.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRelationshipRepository extends JpaRepository<Relationship, Long> {
}
