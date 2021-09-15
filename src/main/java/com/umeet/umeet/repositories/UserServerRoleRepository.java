package com.umeet.umeet.repositories;

import com.umeet.umeet.entities.UserServerRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserServerRoleRepository extends JpaRepository<UserServerRole, Long> {
}
