package testjpa.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import testjpa.member.domain.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	List<Doctor> findByName(String name);
}
