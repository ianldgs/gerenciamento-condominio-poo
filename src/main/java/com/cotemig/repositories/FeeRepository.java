package com.cotemig.repositories;

import com.cotemig.models.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeeRepository extends JpaRepository<Fee, Integer> {
    @Query("select f from Fee f where f.condo.cnpj = ?1 and f.resident.cpf = ?2 and to_char(f.dueDate, ?4) = ?3")
    Fee findByCnpjAndCpfAndDateWithFormat(String cnpj, String cpf, String date, String format);

    @Query("select f from Fee f where to_char(f.dueDate, ?2) = ?1")
    List<Fee> findByDateWithFormat(String date, String format);
}
