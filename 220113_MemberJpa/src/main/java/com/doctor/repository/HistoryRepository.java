package com.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.domain.History;

public interface HistoryRepository extends JpaRepository<History, Long> {

}
