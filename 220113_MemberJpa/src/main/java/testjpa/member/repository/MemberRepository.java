package testjpa.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import testjpa.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	List<Member> findByName(String name);
	List<Member> findByEmail(String email);
}
