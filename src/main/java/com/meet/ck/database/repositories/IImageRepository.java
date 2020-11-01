package com.meet.ck.database.repositories;

import com.meet.ck.database.entities.Image;
import com.meet.ck.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
}
