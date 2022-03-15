package com.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.domain.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long>{
}
