package testjpa.member.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import testjpa.member.domain.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String>{
	boolean existsById(String id);

	/* 개발중 : .getContent() -> <Doctor> X <Object> 출력되는 이슈 */
	@Query(value = "SELECT d, m FROM Doctor d LEFT JOIN d.members m where d.id in :id")
	Page<Doctor> findAllMember( @Param("id") String doctorId, Pageable pageable);
	
	@Query(value = "SELECT d, m FROM Doctor d LEFT JOIN d.members m where d.id in :id")
	List<Doctor> findDoctorMember(@Param("id") String doctorId);
	
	Doctor findDoctorById(String id);
}
