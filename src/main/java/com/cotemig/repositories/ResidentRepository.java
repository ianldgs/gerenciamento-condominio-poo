package com.cotemig.repositories;

import com.cotemig.models.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository  extends JpaRepository<Resident, Integer> {

}
