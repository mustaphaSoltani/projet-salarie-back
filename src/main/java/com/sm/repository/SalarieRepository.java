package com.sm.repository;

import com.sm.model.Salarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface SalarieRepository extends JpaRepository<Salarie, String> {
}
