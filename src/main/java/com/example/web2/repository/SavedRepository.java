package com.example.web2.repository;

import com.example.web2.dto.Saved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedRepository extends JpaRepository<Saved,Long>{
}
