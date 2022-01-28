package testjpa.member.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import testjpa.member.domain.Diary;
import testjpa.member.domain.History;
import testjpa.member.domain.Member;
import testjpa.member.repository.MemberRepository;


@Service
@Transactional(readOnly = true) //데이터 변경 X
@RequiredArgsConstructor //final 생성자 생성
public class MemberService {

	private final MemberRepository memberRepository;
	
	@Transactional //데이터 변경 O
	public Long join(Member member) { 
		validateDuplicateMember(member); //중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}

	public Member findOne(Long memberId) {
		return memberRepository.findById(memberId).get();
	}
	
	public List<Member> findDiary(Diary diary) {
		return memberRepository.findByDiary(diary);
	}
	
	public List<Member> findHistory(History history) {
		return memberRepository.findByHistory(history);
	}
	
}
