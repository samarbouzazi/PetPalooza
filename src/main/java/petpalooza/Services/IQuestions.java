package petpalooza.Services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import petpalooza.Entities.Questions;

public interface IQuestions {
	//////functions///
	List<Questions> findAll();
	Questions findQuestionById(Long id);
	ResponseEntity<String> addQuestion(HttpServletRequest request, Questions question);
	public Questions updateQuestion(Questions question);
	void deleteQuestion(Long id);
	List<Questions> findQuestionsByUser(Long id);
	List <Questions> findQuestionsByUserOrderByCreatedDate(Long id);
}
