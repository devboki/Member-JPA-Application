package testjpa.member.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import testjpa.member.domain.Doctor;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DoctorServiceTest {

	@Autowired DoctorService doctorService;
	@Autowired MemberService memberService;
	
	@Test
	public void 환자조회() {
		List<Doctor> doctorA = doctorService.findDoctorName("doctorA");
		//System.out.println("doctorA = " + doctorA.toString());
		
	}

}
