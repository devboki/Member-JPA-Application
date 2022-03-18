package com.doctor.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.doctor.domain.Doctor;
import com.doctor.domain.DoctorDto;
import com.doctor.domain.MemberDto;
import com.doctor.domain.ResultFindMember;

public interface DoctorRepository extends JpaRepository<Doctor, String>{
	
	boolean existsById(String id);

	@Query(value = "select new com.doctor.domain.DoctorDto(d.id, d.password, d.phoneNumber, d.buisnessNumber) from Doctor d where d.id = :id")
	List<DoctorDto> findDoctorDto(@Param("id") String doctorId);
	
	@Query(value = "select new com.doctor.domain.MemberDto(m.name, m.gender, m.age) from Doctor d join d.members m where d.id = :id and d.password = :password")
	List<MemberDto> findMemberDto(@Param("id") String doctorId, @Param("password") String password);

	@Query(value = "select new com.doctor.domain.ResultFindMember(m.name, m.gender, m.age) from Doctor d join d.members m where d.id = :id and d.password = :password")
	List<ResultFindMember> findMemberResult(@Param("id") String doctorId, @Param("password") String password);
	
	@Query(value = "select new com.doctor.domain.MemberDto(m.id, m.name) from Doctor d join d.members m where d.id = :id")
	Page<MemberDto> findAllMember(@Param("id") String doctorId, Pageable pageable);
	
	@Query("select d from Doctor d")
	Doctor findDoctorOne(String doctorId);
	
	@Query("select d from Doctor d where d.id = :id")
	Doctor findDoctorOneId(@Param("id") String doctorId);

	@Query("select d from Doctor d where d.id = :id and d.password = :password")
	Doctor findDoctorIdPw(String doctorId, String password);
}
