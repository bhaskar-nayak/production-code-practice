package com.production.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.production.model.Home;

public interface HomeRepository extends JpaRepository<Home, Long>{

}
