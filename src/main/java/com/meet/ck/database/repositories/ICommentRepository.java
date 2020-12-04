package com.meet.ck.database.repositories;

import com.meet.ck.database.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
}
