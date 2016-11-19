package com.cotemig.repositories;

import com.cotemig.models.Condo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondoRepository extends JpaRepository<Condo, Integer> {

}
