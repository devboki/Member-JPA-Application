package testjpa.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;
import testjpa.member.domain.Info;
import testjpa.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	List<Member> findByName(String name);
	List<Member> findByEmail(String email);
	List<Member> findByInfo(Info info);
}
