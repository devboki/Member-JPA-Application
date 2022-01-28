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
	
	@Test
	@Rollback(false)
	public void 회원가입() throws Exception {
		Member member = new Member("userK", 50, "userK@naver.com", Gender.FEMALE);
		
		Long saveId = memberService.join(member);
		
		Member findMember = memberService.findOne(saveId); 
		assertEquals(member.getName(), findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외() {
		Member member1 = new Member();
		member1.setEmail("test@test.com");
		memberService.join(member1);
		
		Member member2 = new Member();
		member2.setEmail("test@test.com");
		
		assertThrows(IllegalStateException.class, () -> { 
			memberService.join(member2);
		});
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
	
	@Test
	@Rollback(false)
	public void 통증일기_조회() throws Exception {
		Diary diary = new Diary(9);
		diaryService.save(diary);
		
		Member member = new Member("userZ", 50, "userZ@naver.com", Gender.FEMALE);
		member.setDiary(diary);
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
		
		Member member = new Member("userX", 50, "userX@naver.com", Gender.FEMALE);
		member.setHistory(history);
		memberService.join(member);
		
		Member findMember = memberService.findOne(member.getId());

		System.out.println("findMemberId = " + findMember.getId());
		System.out.println("findMemberName = " + findMember.getName());
		System.out.println("findMemberHistory = " + findMember.getHistory().getHistory());
	}
}
