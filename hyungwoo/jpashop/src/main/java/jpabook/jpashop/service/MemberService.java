package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepositoryOld;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true) // 데이터 변경은 기본적으로 트랜잭션 안에서 실행되야 한다. (그래야 지연로딩도 나가고 한다)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepositoryOld memberRepositoryOld;

	/**
	 * 회원 가입
	 */
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member); // 중복 회원 검증
		memberRepositoryOld.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		//EXCEPTION
		List<Member> findMembers = memberRepositoryOld.findByName(member.getName());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	/**
	 * 회원 전체 조회
	 */
	public List<Member> findMembers() {
		return memberRepositoryOld.findAll();
	}

	public Member findOne(Long memberId) {
		return memberRepositoryOld.findOne(memberId);
	}

	/**
	 * 회원 수정
	 */
	@Transactional
	public void update(Long id, String name) {
		Member member = memberRepositoryOld.findOne(id);
		member.setName(name);
	}
}
