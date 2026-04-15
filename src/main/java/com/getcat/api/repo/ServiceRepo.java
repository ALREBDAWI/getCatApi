package com.getcat.api.repo;

import com.getcat.api.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<Service,Integer> {
}
