package com.spring.jwt.jwtrevision.repo;


import com.spring.jwt.jwtrevision.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepo extends JpaRepository<Authorities, Long> {
}
