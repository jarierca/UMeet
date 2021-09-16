package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.MessageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageFileRepository extends JpaRepository<MessageFile, Long> {
}
