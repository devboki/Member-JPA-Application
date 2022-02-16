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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
		Optional<Doctor> findDoctor0 = doctorService.findDoctorIdOptional("doctor0");
		Optional<Doctor> findDoctor1 = doctorService.findDoctorIdOptional("doctor1");
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
	public void 환자조회_entitymanager() {
		Doctor doctor = em.find(Doctor.class, "doctor1");
		List<Member> members = doctor.getMembers();
		
		for (Member member : members) {
			System.out.println("doctor1's members = " + member.toString());
		}
		
//		doctor1's members = member1
//		doctor1's members = member2
	}

	@Test
	public void 환자조회_repository() {
	
		Doctor doctor1 = doctorService.findDoctorById("doctor1");
		System.out.println("doctor1's members = " + doctor1.getMembers().size());
		System.out.println("doctor1's memberList = " + doctor1.getMembers().toString());
		
//		doctor1's members = 2
//		doctor1's memberList = [Member(id=2, name=member1, gender=null, age=0, email=null, roleType=USER), Member(id=3, name=member2, gender=null, age=0, email=null, roleType=USER)]

		PageRequest pageRequest = PageRequest.of(0, 1);
		Page<Doctor> page = doctorService.findAllMember(pageRequest);
		List<Doctor> content = page.getContent();
		long totalElements = page.getTotalElements();
		
		/* 쿼리 에러 확인 */
		
	}
	
	
}
