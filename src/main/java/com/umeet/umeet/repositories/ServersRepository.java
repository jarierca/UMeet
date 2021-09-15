/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.Server;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aran
 */
public interface ServersRepository extends JpaRepository<Server, Long>{ 
 
    public List <Server> findByDescriptionContaining(String description);
    public List <Server> findByNameContaining(String name);
    
}
