package testjpa.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import testjpa.member.domain.Diary;
import testjpa.member.domain.Doctor;
import testjpa.member.domain.History;
import testjpa.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	List<Member> findByName(String name);
	List<Member> findByEmail(String email);
	List<Member> findByDiary(Diary diary);
	List<Member> findByHistory(History history);
	List<Member> findByDoctor(Doctor doctor);
}
