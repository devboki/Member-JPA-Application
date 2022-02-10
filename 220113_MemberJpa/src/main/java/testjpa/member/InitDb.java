package testjpa.member;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import testjpa.member.domain.Diary;
import testjpa.member.domain.Doctor;
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
			History history1 = new History("피부질환");
			History history2 = new History("관절염");
			em.persist(history1);
			em.persist(history2);
			
			Diary diary1 = new Diary(5);
			Diary diary2 = new Diary(9);
			em.persist(diary1);
			em.persist(diary2);
			
			Doctor doctor1 = new Doctor(null, "doctorA", "doctor@sample.com", "신촌세브란스병원", RoleType.DOCTOR, null);
			em.persist(doctor1);
			
			Member memberAdmin = new Member("admin", 30, "admin@sample.com", Gender.FEMALE, RoleType.ADMIN, null, null);
			Member memberUser1 = new Member("userA", 20, "userA@sample.com", Gender.FEMALE, RoleType.USER, diary1, history1);
			Member memberUser2 = new Member("userB", 20, "userB@sample.com", Gender.MALE, RoleType.USER, diary2, history2);
			em.persist(memberAdmin);
			em.persist(memberUser1);
			em.persist(memberUser2);
		}
	}
}
