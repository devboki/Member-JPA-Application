package testjpa.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import testjpa.member.domain.History;

public interface HistoryRepository extends JpaRepository<History, Long> {

}
