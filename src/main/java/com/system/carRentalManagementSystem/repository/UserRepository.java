package com.system.carRentalManagementSystem.repository;

import com.system.carRentalManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select user from User user where user.role = 0")
    List<User> findUserByRoleUSER();

    @Query("select user from User user where user.role = 1")
    List<User> findUserByRoleADMIN();

    @Query("select user from User user where user.role = 2")
    List<User> findUserByRoleEMPLOYEE();

    @Query("select user from User user where user.role = 3")
    List<User> findUserByRoleDRIVER();
}
