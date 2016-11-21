package com.cotemig.repositories;

import com.cotemig.models.Condo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CondoRepository extends JpaRepository<Condo, Integer> {
    @Query("select c from Condo c where c.cnpj = ?1")
    Condo findByCnpj(String cnpj);
}
