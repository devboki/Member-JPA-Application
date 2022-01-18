package testjpa.member;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import testjpa.member.domain.Gender;
import testjpa.member.domain.Member;

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
			Member member1 = new Member();
			member1.setName("userA");
			member1.setAge(20);
			member1.setEmail("userA@sample.com");
			member1.setGender(Gender.FEMALE);
			em.persist(member1);
			
			Member member2 = new Member();
			member2.setName("userB");
			member2.setAge(30);
			member2.setEmail("userB@sample.com");
			member2.setGender(Gender.MALE);
			em.persist(member2);
		}
	}
}
