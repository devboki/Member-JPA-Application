package testjpa.member.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import testjpa.member.domain.Diary;
import testjpa.member.domain.Doctor;
import testjpa.member.domain.Gender;
import testjpa.member.domain.History;
import testjpa.member.domain.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired DiaryService diaryService;
	@Autowired HistoryService historyService;
	@Autowired DoctorService doctorService;
	
	@Test
	@Rollback(false)
	public void 회원가입() throws Exception {
		Member member = new Member("userK", 50, "userK@naver.com", Gender.FEMALE, null, null, null);
		
		Long saveId = memberService.join(member);
		
		Member findMember = memberService.findOne(saveId); 
		assertEquals(member.getName(), findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외() {
		Member member1 = new Member("email1", 30, "test@test.com", Gender.FEMALE, null, null, null);
		memberService.join(member1);
		
		Member member2 = new Member("email1", 30, "test@test.com", Gender.FEMALE, null, null, null);
		
		assertThrows(IllegalStateException.class, () -> { 
			memberService.join(member2); //member1과 이메일이 다르면 테스트 실패
		});
	}
	
	@Test
	@Rollback(false)
	public void 전체_회원_조회() throws Exception {
		Member member1 = new Member("OOO", 20, "ooo@naver.com", Gender.FEMALE, null, null, null);
		memberService.join(member1);
		
		Member member2 = new Member("VVV", 20, "vvv@naver.com", Gender.FEMALE, null, null, null);
		memberService.join(member2);
		
		List<Member> MemberList = memberService.findMembers();
		for (Member member : MemberList) {
			System.out.println("MemberList = " + member.getName());
		}
	}
	
	@Test
	@Rollback(false)
	public void 통증일기_조회() throws Exception {
		Diary diary = new Diary(9);
		diaryService.save(diary);
		
		Member member = new Member("userZ", 50, "userZ@naver.com", Gender.FEMALE, null, diary, null);
		memberService.join(member);
		
		Member findMember = memberService.findOne(member.getId());

		System.out.println("findMemberId = " + findMember.getId());
		System.out.println("findMemberName = " + findMember.getName());
		System.out.println("findMemberDiaryDate = " + findMember.getDiary().getCreatedTimeAt());
		System.out.println("findMemberPainScale = " + findMember.getDiary().getPainScale());
	}
	
	@Test
	@Rollback(false)
	public void 병력조회() throws Exception {
		History history = new History("병력있음");
		historyService.save(history);
		
		Member member = new Member("userX", 50, "userX@naver.com", Gender.FEMALE, null, null, history);
		memberService.join(member);
		
		Member findMember = memberService.findOne(member.getId());

		System.out.println("findMemberId = " + findMember.getId());
		System.out.println("findMemberName = " + findMember.getName());
		System.out.println("findMemberHistory = " + findMember.getHistory().getHistory());
	}
	
	@Test
	@Rollback(false)
	public void 의사추가() throws Exception {
		
		Doctor findDoctor = doctorService.findOne(1L);
		System.out.println("findDoctor = " + findDoctor.getName());
		
		Member findMember = memberService.findOne(2L);
		findMember.changeDoctor(findDoctor);
		
		Member findMemberAndDoctor = memberService.findOne(findMember.getId());
		
		System.out.println("findMemberName = " + findMemberAndDoctor.getName());
		System.out.println("findMemberDoctor = " + findMemberAndDoctor.getDoctor());
	}
}
