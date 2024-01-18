package com.loan.app.repository;

import com.loan.app.domain.Counsel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselRepository extends JpaRepository<Counsel, Long> {
}
