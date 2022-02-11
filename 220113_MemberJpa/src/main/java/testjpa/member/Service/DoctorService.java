package testjpa.member.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import testjpa.member.domain.Doctor;
import testjpa.member.domain.Member;
import testjpa.member.repository.DoctorRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DoctorService {
	
	private final DoctorRepository doctorRepository;
	
	@Transactional
	public Long join(Doctor doctor) { 
		doctorRepository.save(doctor);
		return doctor.getId();
	}

	public Doctor findOne(long doctorId) {
		return doctorRepository.findById(doctorId).get();
	}
	
	public List<Doctor> findDoctorName(String name) {
		return doctorRepository.findByName(name);
	}
	
}
