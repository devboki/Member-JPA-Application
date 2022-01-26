package testjpa.member;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import testjpa.member.domain.Diary;
import testjpa.member.domain.Gender;
import testjpa.member.domain.History;
import testjpa.member.domain.Member;
import testjpa.member.domain.RoleType;

@Component
@RequiredArgsConstructor
public class InitDb {

	private final InitService initService;
	
	@PostConstruct
	public void init() {
		initService.dbInit();
	}

	@Component
	@Transactional
	@RequiredArgsConstructor
	static class InitService {
		
		private final EntityManager em;
		
		public void dbInit() {
			History history1 = new History();
			history1.setHistory("피부질환");
			em.persist(history1);
			
			History history2 = new History();
			history2.setHistory("관절염");
			em.persist(history2);
			
			Diary diary1 = new Diary();
			diary1.setPainScale(5);
			em.persist(diary1);
			
			Diary diary2 = new Diary();
			diary2.setPainScale(9);
			em.persist(diary2);
			
			Member member0 = new Member();
			member0.setName("admin");
			member0.setAge(30);
			member0.setEmail("admin@sample.com");
			member0.setGender(Gender.FEMALE);
			member0.setRoleType(RoleType.ADMIN);
			em.persist(member0);
			
			Member member1 = new Member();
			member1.setName("userA");
			member1.setAge(20);
			member1.setEmail("userA@sample.com");
			member1.setGender(Gender.FEMALE);
			member1.setRoleType(RoleType.USER);
			member1.setDiary(diary1);
			member1.setHistory(history1);
			em.persist(member1);
			
			Member member2 = new Member();
			member2.setName("userB");
			member2.setAge(20);
			member2.setEmail("userB@sample.com");
			member2.setGender(Gender.MALE);
			member2.setRoleType(RoleType.USER);
			member2.setDiary(diary2);
			member2.setHistory(history2);
			em.persist(member2);
			
			
		}
	}
}
