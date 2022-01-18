package testjpa.member.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import testjpa.member.domain.Member;
import testjpa.member.repository.MemberRepository;


@Service
@Transactional(readOnly = true) //데이터 변경 X
@RequiredArgsConstructor //final 생성자 생성
public class MemberService {

	private final MemberRepository memberRepository;
	
	/* 회원 가입 */
	@Transactional //데이터 변경 O
	public Long join(Member member) { 
		validateDuplicateMember(member); //중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}

	/* 중복 회원 검증 */
	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	
	/* 전체 회원 조회 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}

	/* 한명 조회 */
	public Member findOne(Long memberId) {
		return memberRepository.findById(memberId).get();
	}
	
}
