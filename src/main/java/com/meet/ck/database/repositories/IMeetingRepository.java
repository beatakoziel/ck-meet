package com.meet.ck.database.repositories;

import com.meet.ck.database.entities.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMeetingRepository extends JpaRepository<Meeting, Long> {
}
