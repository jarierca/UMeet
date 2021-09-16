package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.Category;
import com.umeet.umeet.entities.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public List<Category> findByServer(Server server);
}
