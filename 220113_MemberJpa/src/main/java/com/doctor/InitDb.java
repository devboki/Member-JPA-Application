package com.doctor;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.domain.Diary;
import com.doctor.domain.Doctor;
import com.doctor.domain.DoctorDto;
import com.doctor.domain.Gender;
import com.doctor.domain.History;
import com.doctor.domain.Member;
import com.doctor.domain.RoleType;

import lombok.RequiredArgsConstructor;

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
			
			/*
			History history1 = new History("피부질환");
			History history2 = new History("관절염");
			em.persist(history1);
			em.persist(history2);
			
			Diary diary1 = new Diary(5);
			Diary diary2 = new Diary(9);
			em.persist(diary1);
			em.persist(diary2);
			
			Doctor doctor1 = new Doctor(null, "doctorA", "doctor@sample.com", "치과A", RoleType.DOCTOR, null);
			em.persist(doctor1);
			
			Member memberAdmin = new Member("admin", 30, "admin@sample.com", Gender.FEMALE, RoleType.ADMIN, null, null, null);
			Member memberUser1 = new Member("userA", 20, "userA@sample.com", Gender.FEMALE, RoleType.USER, diary1, history1, doctor1);
			Member memberUser2 = new Member("userB", 20, "userB@sample.com", Gender.MALE, RoleType.USER, diary2, history2, doctor1);
			em.persist(memberAdmin);
			em.persist(memberUser1);
			em.persist(memberUser2);
			*/
			
			Doctor doctor1 = Doctor.builder()
					.id("doctor1")
					.password("Aaaa1234!")
					.phoneNumber("010-0000-0000")
					.buisnessNumber("5298600830")
					.build();
			
			Doctor doctor2 = Doctor.builder()
					.id("doctor2")
					.password("Aaaa1234!")
					.phoneNumber("010-0000-0000")
					.buisnessNumber("5298600830")
					.build();
			
			Doctor doctor3 = Doctor.builder()
					.id("doctor3")
					.password("Aaaa1234!")
					.phoneNumber("010-0000-0000")
					.buisnessNumber("5298600830")
					.build();
			
			Doctor doctor4 = Doctor.builder()
					.id("doctor4")
					.password("Aaaa1234!")
					.phoneNumber("010-0000-0000")
					.buisnessNumber("5298600830")
					.build();
			
			Doctor doctor5 = Doctor.builder()
					.id("doctor5")
					.password("Aaaa1234!")
					.phoneNumber("010-0000-0000")
					.buisnessNumber("5298600830")
					.build();
			
			em.persist(doctor1);
			em.persist(doctor2);
			em.persist(doctor3);
			em.persist(doctor4);
			em.persist(doctor5);
			
			Member admin = Member.MemberBuilder()
					.name("admin")
					.roleType(RoleType.ADMIN)
					.build();
			
			Member member1 = Member.MemberBuilder()
					.name("member1")
					.build();
			
			Member member2 = Member.MemberBuilder()
					.name("member2")
					.build();
			
			Member member3 = Member.MemberBuilder()
					.name("member3")
					.build();
			
			Member member4 = Member.MemberBuilder()
					.name("member4")
					.build();
			
			Member member5 = Member.MemberBuilder()
					.name("member5")
					.build();
			
			Member member6 = Member.MemberBuilder()
					.name("member6")
					.build();
			
			Member member7 = Member.MemberBuilder()
					.name("member7")
					.build();
			
			Member member8 = Member.MemberBuilder()
					.name("member8")
					.build();
			
			Member member9 = Member.MemberBuilder()
					.name("member9")
					.build();
			
			em.persist(admin);
			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			em.persist(member4);
			em.persist(member5);
			em.persist(member6);
			em.persist(member7);
			em.persist(member8);
			em.persist(member9);
			
			member1.changeDoctor(doctor1);
			member2.changeDoctor(doctor1);
			member3.changeDoctor(doctor3);
			member4.changeDoctor(doctor3);
			member5.changeDoctor(doctor3);
			member6.changeDoctor(doctor3);
			member7.changeDoctor(doctor3);
			
			em.flush();
			em.clear();
		}
	}
}
