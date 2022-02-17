package testjpa.member.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import testjpa.member.domain.DoctorDto;
import testjpa.member.domain.Member;

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
	public void 환자조회_entitymanager_getMembers() {
		Doctor doctor = em.find(Doctor.class, "doctor1");
		List<Member> members = doctor.getMembers();
		
		for (Member member : members) {
			System.out.println("doctor1's members = " + member.toString());
		}
		
//		doctor1's members = member1
//		doctor1's members = member2
	}

	@Test
	public void 환자조회_repository_getMembers() {
	
		Doctor doctor1 = doctorService.findDoctorById("doctor1");
		System.out.println("doctor1's members = " + doctor1.getMembers().size());
		System.out.println("doctor1's memberList = " + doctor1.getMembers().toString());
		
//		doctor1's members = 2
//		doctor1's memberList = [Member(id=2, name=member1, gender=null, age=0, email=null, roleType=USER), Member(id=3, name=member2, gender=null, age=0, email=null, roleType=USER)]
	}
	
	@Test
	public void 환자조회_paging() {
		
		PageRequest pageRequest = PageRequest.of(0, 3); //doctor3인 member 첫 페이지는 0, 한 페이지에 3개 보이도록
		Page<Doctor> result = doctorService.findAllMember("doctor3", pageRequest);  
		 			
	    System.out.println(result.getTotalPages()); 	//총 데이터 5개를 3개씩 : 2페이지
	    System.out.println(result.getTotalElements());  //총 데이터 5개
	    System.out.println(result.nextPageable());		//Page request [number: 1, size 3]

	    List<Doctor> content = result.getContent();
		System.out.println(content.size());				//조회된 데이터 : 3개
		System.out.println(content.stream().collect(Collectors.toList()));
		
		/* Object 출력 이슈
		[[Ljava.lang.Object;@6c225adb, [Ljava.lang.Object;@69cc49ec, [Ljava.lang.Object;@6b71e98f] */		
	}
	
	@Test
	public void 환자조회_fetch_join() {
		
		List<Doctor> doctor3 = doctorService.findDoctorMember("doctor3");
		for (Doctor doctor : doctor3) {
			System.out.println("doctor3MemberList = " + doctor);
		}
		
//		doctor3MemberList = Doctor(id=doctor3, password=null, phoneNumber=null, buisnessNumber=null)
//		doctor3MemberList = Doctor(id=doctor3, password=null, phoneNumber=null, buisnessNumber=null)
//		doctor3MemberList = Doctor(id=doctor3, password=null, phoneNumber=null, buisnessNumber=null)
//		doctor3MemberList = Doctor(id=doctor3, password=null, phoneNumber=null, buisnessNumber=null)
//		doctor3MemberList = Doctor(id=doctor3, password=null, phoneNumber=null, buisnessNumber=null)
		
		/* MemberList X DoctorList 출력 이슈 */
	}
	
}
