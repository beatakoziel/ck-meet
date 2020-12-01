package com.meet.ck.database.repositories;

import com.meet.ck.database.entities.ContactData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactDataRepository extends JpaRepository<ContactData, Long> {
}
