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
import com.doctor.service.DoctorService;
import com.doctor.service.MemberService;

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
			
			Member admin = Member.MemberBuilder()
					.name("admin")
					.age(40)
					.email("admin@admin.com")
					.roleType(RoleType.ADMIN)
					.gender(Gender.FEMALE)
					.build();
			em.persist(admin);
			
			for(int i=1; i<11; i++) {
				
				Doctor doctor = Doctor.builder()
						.id("doctor" + i)
						.password("Aaaa1234!")
						.phoneNumber("010-0000-0000")
						.buisnessNumber("5298600830")
						.build();
				em.persist(doctor);
				
				Member member = Member.MemberBuilder()
						.name("member"+i)
						.gender(Gender.FEMALE)
						.doctor(doctor)
						.age(20)
						.build();
				em.persist(member);
			}
			
			em.flush();
			em.clear();
		}
	}
}
