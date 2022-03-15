package com.doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.domain.Diary;
import com.doctor.domain.Doctor;
import com.doctor.domain.History;
import com.doctor.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	List<Member> findByName(String name);
	List<Member> findByEmail(String email);
	List<Member> findByDiary(Diary diary);
	List<Member> findByHistory(History history);
	List<Member> findByDoctor(Doctor doctor);
}
