package testjpa.member.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import testjpa.member.domain.Gender;
import testjpa.member.domain.Member;
import testjpa.member.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	@Test
	@Rollback(false)
	public void 회원가입() throws Exception {
		Member member = new Member("userK", 50, "userK@naver.com", Gender.FEMALE);
		
		Long saveId = memberService.join(member);
		
		Member findMember = memberService.findOne(saveId); 
		assertEquals(member.getName(), findMember.getName());
	}
	
	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() throws Exception {
		Member member1 = new Member();
		member1.setName("kim");
		
		Member member2 = new Member();
		member2.setName("kim");

		memberService.join(member1);
		memberService.join(member2);

		fail("예외가 발생해야 한다.");
	}
	
	@Test
	@Rollback(false)
	public void 전체_회원_조회() throws Exception {
		Member member1 = new Member();
		member1.setName("OOO");
		member1.setAge(20);
		member1.setEmail("ooo@naver.com");
		member1.setGender(Gender.FEMALE);
		memberService.join(member1);
		
		Member member2 = new Member();
		member2.setName("VVV");
		member2.setAge(20);
		member2.setEmail("vvv@naver.com");
		member2.setGender(Gender.FEMALE);
		memberService.join(member2);
		
		List<Member> MemberList = memberService.findMembers();
		for (Member member : MemberList) {
			System.out.println("MemberList = " + member.getName());
		}
	}
	
}
