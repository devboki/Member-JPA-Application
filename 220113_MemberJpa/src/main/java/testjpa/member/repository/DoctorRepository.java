package testjpa.member.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import testjpa.member.domain.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String>{
	boolean existsById(String id);

	/* 개발중 */
	@Query(value = "select d from Doctor d left join Member m")
	Page<Doctor> findAllMember(Pageable pageable);
	
	Doctor findDoctorById(String id);
}
