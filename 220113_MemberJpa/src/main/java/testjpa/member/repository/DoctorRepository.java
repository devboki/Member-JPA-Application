package testjpa.member.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import testjpa.member.domain.Doctor;
import testjpa.member.domain.MemberDto;

public interface DoctorRepository extends JpaRepository<Doctor, String>{
	boolean existsById(String id);

	@Query(value = "select new testjpa.member.domain.MemberDto(m.id, m.name) from Doctor d join d.members m where d.id = :id")
	List<MemberDto> findDoctorMember(@Param("id") String doctorId);

	@Query(value = "select new testjpa.member.domain.MemberDto(m.id, m.name) from Doctor d join d.members m where d.id = :id")
	Page<MemberDto> findAllMember( @Param("id") String doctorId, Pageable pageable);
	
	Doctor findDoctorById(String id);
}
