package testjpa.member.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import testjpa.member.domain.Doctor;
import testjpa.member.repository.DoctorRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DoctorService {
	
	private final DoctorRepository doctorRepository;
	
	@Transactional
	public Long save(Doctor doctor) { 
		doctorRepository.save(doctor);
		return doctor.getId();
	}

	public Doctor findOne(long doctorId) {
		return doctorRepository.findById(doctorId).get();
	}
}
