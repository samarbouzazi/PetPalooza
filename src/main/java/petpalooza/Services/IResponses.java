package petpalooza.Services;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import petpalooza.Entities.Questions;
import petpalooza.Entities.Responses;

public interface IResponses {
//////functions///
	List<Responses> findAll();
	Responses updateResponse(Responses response);
	public ResponseEntity<String> addResponse(HttpServletRequest request,  Responses response);
	void deleteResponse(Long id);
	List<Responses> findResponsessByUserOrderByCreatedDate(Long id);
	List<Responses> findResponsessByCreatedDate(Date date);
	List<Responses> findAllByQuestion(Questions question);
	
}
