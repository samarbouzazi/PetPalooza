package petpalooza.Services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import petpalooza.Entities.Questions;
import petpalooza.Entities.Responses;

import petpalooza.Repositories.ResponsesRepository;

@Service
@Data
@AllArgsConstructor
public class ResponseService implements IResponses {

	  @Autowired
	  ResponsesRepository responsesRepository ;
	  
	//////getall///
	    public List<Responses> findAll() {
	        return responsesRepository.findAll();
	    }

	   //////update////
	    public Responses updateResponse(Responses response) {
	        return responsesRepository.save(response);
	    }

	    
	 
	    
////////add/////	    
	    public ResponseEntity<String> addResponse(HttpServletRequest request,  Responses response) {
	        String userInput = response.getResponse_desription();
	        if (containsBadWord(userInput)) {
	            return ResponseEntity.badRequest().body("Your answer contains inappropriate language.");
	        }
	        responsesRepository.save(response);
	        return ResponseEntity.ok("Answer added successfully.");
	    }

	    private boolean containsBadWord(String input) {
	        List<String> badWords = Arrays.asList("badword1", "badword2", "badword3");
	        return badWords.stream().anyMatch(input::contains);
	    }
	    
	    
	    
	    
	    public void deleteResponse(Long id) {
	    	responsesRepository.deleteById(id);
	    }
	    
	    public List<Responses> findResponsessByUserOrderByCreatedDate(Long id){
	    	return responsesRepository.findResponsessByUserOrderByCreatedDate(id);
	    }
	    
	    public  List<Responses> findAllByQuestion(Questions question)
	    {
	    	return responsesRepository.findAllByQuestion(question);
	    }
	    
	    public List<Responses> findResponsessByCreatedDate(Date date){
	    	return responsesRepository.findResponsesByCreatedDate(date);
	    }


		


}
