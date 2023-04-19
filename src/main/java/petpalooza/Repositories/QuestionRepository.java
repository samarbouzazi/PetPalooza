package petpalooza.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import petpalooza.Entities.Questions;

public interface QuestionRepository extends JpaRepository<Questions,Long> {

////////functions////
	     Long countQuestionsByUser(Long id);

	     Questions findQuestionById(Long id);

	    List<Questions> findQuestionsByUserOrderByCreatedDate(Long id);
	    List<Questions> findQuestionsByUser(Long id);
}
