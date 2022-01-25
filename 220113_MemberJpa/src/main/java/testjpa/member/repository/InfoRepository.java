package testjpa.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import testjpa.member.domain.Info;

public interface InfoRepository extends JpaRepository<Info, Long>{
	List<Info> findByhistory(String history);
}
