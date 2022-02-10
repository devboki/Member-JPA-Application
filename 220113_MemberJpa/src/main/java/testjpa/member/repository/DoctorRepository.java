package testjpa.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import testjpa.member.domain.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
