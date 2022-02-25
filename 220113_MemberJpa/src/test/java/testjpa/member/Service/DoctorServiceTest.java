package testjpa.member.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import testjpa.member.domain.Doctor;
import testjpa.member.domain.DoctorDto;
import testjpa.member.domain.MemberDto;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DoctorServiceTest {

	@Autowired DoctorService doctorService;
	@Autowired MemberService memberService;
	
	@PersistenceContext EntityManager em;
	
	@Test
	public void 의사_단건조회_Dto() {
		Optional<Doctor> findDoctor0 = doctorService.findDoctorIdOptional("doctor0");
		Optional<Doctor> findDoctor1 = doctorService.findDoctorIdOptional("doctor1");
		System.out.println("findDoctor0 = " + findDoctor0);
		System.out.println("findDoctor1 = " + findDoctor1);
		
//		findDoctor0 = Optional.empty
//		findDoctor1 = Optional[Doctor(id=doctor1, password=abcd, phoneNumber=000-0000-0000, buisnessNumber=123-45-12345)]
		
		Optional<DoctorDto> findDoctorDto0 = findDoctor0.map(d -> new DoctorDto(d.getId(), d.getPassword(), d.getPhoneNumber(), d.getBuisnessNumber()));
		Optional<DoctorDto> findDoctorDto1= findDoctor1.map(d -> new DoctorDto(d.getId(), d.getPassword(), d.getPhoneNumber(), d.getBuisnessNumber()));
		System.out.println("findDoctorDto0 = " + findDoctorDto0);
		System.out.println("findDoctorDto1 = " + findDoctorDto1);
		
//		findDoctorDto0 = Optional.empty
//		findDoctorDto0 = Optional[DoctorDto(id=doctor1, password=abcd, phoneNumber=000-0000-0000, buisnessNumber=123-45-12345)]
					
		Optional<DoctorDto> findDoctorDto3 = doctorService.findDoctorIdOptional("doctor3")
													.map(d -> new DoctorDto(d.getId(), d.getPassword(), d.getPhoneNumber(), d.getBuisnessNumber()));
		System.out.println("findDoctorDto3 = " + findDoctorDto3);
		
//		findDoctorDto3 = Optional[DoctorDto(id=doctor3, password=null, phoneNumber=null, buisnessNumber=null)]
	}
	
	@Test
	@Rollback(false)
	public void 의사삭제() {
		doctorService.delete("doctor1");
		
//		delete from doctor where doctor_id='doctor1';
	}
	
	@Test
	public void 환자조회_fetch_join_Dto() {
		
		List<MemberDto> doctor3Members = doctorService.findMemberDto("doctor3");
		System.out.println(doctor3Members);
		
//		[MemberDto(id=4, name=member3, gender=null, age=0, email=null, roleType=null, diary=null, history=null), 
//		MemberDto(id=5, name=member4, gender=null, age=0, email=null, roleType=null, diary=null, history=null), 
//		MemberDto(id=6, name=member5, gender=null, age=0, email=null, roleType=null, diary=null, history=null), 
//		MemberDto(id=7, name=member6, gender=null, age=0, email=null, roleType=null, diary=null, history=null),
//		MemberDto(id=8, name=member7, gender=null, age=0, email=null, roleType=null, diary=null, history=null)]
	}

	@Test
	public void 환자조회_paging_Dto() {
		
		PageRequest pageRequest = PageRequest.of(0, 3); //doctor3인 member 첫 페이지는 0, 한 페이지에 3개 보이도록
		Page<MemberDto> result = doctorService.findAllMember("doctor3", pageRequest);  
		 			
	    System.out.println(result.getTotalPages()); 	//총 데이터 5개를 3개씩 : 2페이지
	    System.out.println(result.getTotalElements());  //총 데이터 5개
	    System.out.println(result.nextPageable());		//Page request [number: 1, size 3]

	    List<MemberDto> content = result.getContent();
		System.out.println(content.size());				//조회된 데이터 : 3개
		System.out.println(content.stream().collect(Collectors.toList()));
		
//		2
//		5
//		Page request [number: 1, size 3, sort: UNSORTED]
//		3
//		[MemberDto(id=4, name=member3, gender=null, age=0, email=null, roleType=null, diary=null, history=null), 
//		MemberDto(id=5, name=member4, gender=null, age=0, email=null, roleType=null, diary=null, history=null), 
//		MemberDto(id=6, name=member5, gender=null, age=0, email=null, roleType=null, diary=null, history=null)]
	}
	
	@Test
	@Rollback(false)
	public void 의사등록() {
		Doctor doctor = Doctor.builder()
				.id("TESTdoctor")
				.password("AAaa123!")
				.phoneNumber("010-1234-5678")
				.buisnessNumber("123-12-12345")
				.build();
		
		String doctorId = doctorService.join(doctor);
		List<DoctorDto> joinDoctor = doctorService.findDoctorDto(doctorId);
		System.out.println("joinDoctor = " + joinDoctor);
		
//		joinDoctor = [DoctorDto(id=TESTdoctor, password=AAaa123!, phoneNumber=010-1234-5678, buisnessNumber=123-12-12345)]
	}
	
	@Test
	@Rollback(false)
	public void 의사수정() {
		Doctor findDoctor = doctorService.findDoctorOneId("doctor3");
		findDoctor.changeDoctor("Abc1234!", "010-4560-7890");
		List<DoctorDto> updateDoctor = doctorService.findDoctorDto(findDoctor.getId());
		System.out.println("updateDoctor = " + updateDoctor);
		
//		updateDoctor = [DoctorDto(id=doctor3, password=Abc1234!, phoneNumber=010-4560-7890, buisnessNumber=null)]
	}
}
