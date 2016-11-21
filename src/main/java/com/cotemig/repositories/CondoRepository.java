package com.cotemig.repositories;

import com.cotemig.models.Condo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CondoRepository extends JpaRepository<Condo, Integer> {
    @Modifying
    @Transactional
    @Query(value="delete from Condo c where c.cnpj = ?1")
    void deleteByCnpj(String cnpj);
}
