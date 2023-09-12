package com.api.pokemon.repositories;

import com.api.pokemon.models.Role;
import com.api.pokemon.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<UserEntity, Long> {

    Optional<Role> findByName(String name);


}
