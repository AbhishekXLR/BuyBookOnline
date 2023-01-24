package com.rest.books.bootrestbooks.Repositories;

import com.rest.books.bootrestbooks.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}
