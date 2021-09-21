package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.Server;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.entities.UserServerRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserServerRoleRepository extends JpaRepository<UserServerRole, Long> {
    public List<UserServerRole> findByServer(Server server);
    public List<UserServerRole> findByUser(User user);
    Optional<UserServerRole> findByUserAndServer(User user, Server server);
}
