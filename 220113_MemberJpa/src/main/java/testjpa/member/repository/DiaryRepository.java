package testjpa.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import testjpa.member.domain.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long>{
}
