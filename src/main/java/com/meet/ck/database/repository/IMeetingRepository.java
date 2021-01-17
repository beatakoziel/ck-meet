package com.meet.ck.database.repository;

import com.meet.ck.database.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMeetingRepository extends JpaRepository<Meeting, Long> {
}
