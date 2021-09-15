package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.Category;
import com.umeet.umeet.entities.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
    public List<Channel> findByCategory(Category category);
}
