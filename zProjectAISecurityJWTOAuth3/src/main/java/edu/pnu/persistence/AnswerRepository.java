package edu.pnu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{

}
