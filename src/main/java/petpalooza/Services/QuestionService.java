package petpalooza.Services;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import petpalooza.Entities.Questions;
import petpalooza.Repositories.QuestionRepository;


@Service
@Data
@AllArgsConstructor
public class QuestionService implements IQuestions{
	
	@Autowired
	  QuestionRepository questionsRepository ;
	
	 public List<Questions> findAll() {
	        return questionsRepository.findAll();
	    }
	
	public Questions findQuestionById(Long id) {
		return questionsRepository.findQuestionById(id);
	}
	public Questions updateQuestion(Questions question) {
        return questionsRepository.save(question);
    }

   
//////////add///
    
    public ResponseEntity<String> addQuestion(HttpServletRequest request,  Questions question) {
        String userInput = question.getQuestion_desription();
        if (containsBadWord(userInput)) {
            return ResponseEntity.badRequest().body("Your question contains inappropriate language.");
        }
        questionsRepository.save(question);
        return ResponseEntity.ok("Question added successfully.");
    }

    private boolean containsBadWord(String input) {
        List<String> badWords = Arrays.asList("badword1", "badword2", "badword3");
        return badWords.stream().anyMatch(input::contains);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void deleteQuestion(Long id) {
    	questionsRepository.deleteById(id);
    } 
	
    public List<Questions> findQuestionsByUser(Long id)
    {
    return questionsRepository.findQuestionsByUser(id)	;
    }
    
    
    public List <Questions> findQuestionsByUserOrderByCreatedDate(Long id)
    {return questionsRepository.findQuestionsByUserOrderByCreatedDate(id);}

}
