package testjpa.member.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import testjpa.member.domain.Doctor;
import testjpa.member.domain.Member;
import testjpa.member.repository.DoctorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DoctorServiceTest {

	@Autowired DoctorService doctorService;
	@Autowired MemberService memberService;
	
	@PersistenceContext EntityManager em;
	
	@Test
	public void 의사조회() {
		Optional<Doctor> findDoctor0 = doctorService.findDoctorId("doctor0");
		Optional<Doctor> findDoctor1 = doctorService.findDoctorId("doctor1");
		System.out.println("findDoctor0 = " + findDoctor0);
		System.out.println("findDoctor1 = " + findDoctor1);
		
//		findDoctor0 = Optional.empty
//		findDoctor1 = Optional[Doctor(id=doctor1, password=abcd, phoneNumber=000-0000-0000, buisnessNumber=123-45-12345)]
	}
	
	@Test
	@Rollback(false)
	public void 의사삭제() {
		doctorService.delete("doctor1");
		
//		delete from doctor where doctor_id='doctor1';
	}

	@Test
	public void 환자조회() {
		Doctor doctor = em.find(Doctor.class, "doctor1"); //EntityManager가 아닌 Service + Repository로 변경하기
		List<Member> members = doctor.getMembers();
		
		for ( Member member : members) {
			System.out.println("doctor1's members = " + member.getName());
		}
		
//		doctor1's members = member1
//		doctor1's members = member2
	}
	
	
	
	
}
