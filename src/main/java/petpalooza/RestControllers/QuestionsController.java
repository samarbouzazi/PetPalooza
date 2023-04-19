package petpalooza.RestControllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import petpalooza.Entities.Questions;
import petpalooza.Services.IQuestions;


@RestController
@AllArgsConstructor
@CrossOrigin
@Data
@RequestMapping("Questions")
public class QuestionsController {
	@Autowired
	IQuestions iquestions;
	 
	@GetMapping("/getall")
	 public List<Questions> findAll() {
	        return iquestions.findAll();
	    }
	@GetMapping("/findbyid/{id}")
	public Questions findQuestionById(@PathVariable Long id) {
		return iquestions.findQuestionById(id);
	}
	@PutMapping("/updatequestion/{id}")
	public Questions updateQuestion( @RequestBody Questions question) {
     return iquestions.updateQuestion(question);
 }
///////////add////
 @PostMapping("/addquestion")
 public ResponseEntity<String> addQuestion(HttpServletRequest request,  Questions question) {
     return iquestions.addQuestion( request,   question);
 }
 
 
@DeleteMapping("/deletequestion/{id}")
 public void deleteQuestion(Long id) {
	 iquestions.deleteQuestion(id);
 } 
@GetMapping("/findbyuserid/{id}")
 public List<Questions> findQuestionsByUser(@PathVariable Long id)
 {
 return iquestions.findQuestionsByUser(id)	;
 }
 
@GetMapping("/findbyuseranddate/{id}")
 public List <Questions> findQuestionsByUserOrderByCreatedDate(@PathVariable Long id)
 {return iquestions.findQuestionsByUserOrderByCreatedDate(id);}

	
	
	
	
	
	
	
}
