package com.meet.ck.database.repository;

import com.meet.ck.database.entity.ContactData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactDataRepository extends JpaRepository<ContactData, Long> {
}
