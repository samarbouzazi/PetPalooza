package petpalooza.RestControllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import petpalooza.Entities.Responses;
import petpalooza.Services.IResponses;

@RestController
@AllArgsConstructor
@CrossOrigin
@Data
@RequestMapping("Responses")
public class ResponsesController {
	@Autowired
	IResponses iresponses;
	
	 @GetMapping("/getall")
	 public List<Responses> findAll() {
	        return iresponses.findAll();
	    }
//////update ////
	   @PutMapping("/updateresponse/{id}")
	    public Responses updateResponse(@RequestBody Responses response) {
	        return iresponses.updateResponse(response);
	    }

	   @PostMapping("/addquestion")
	   public ResponseEntity<String> addResponse(HttpServletRequest request,  Responses response) {
	       return iresponses.addResponse( request,   response);
	   }

	    @DeleteMapping("/deleteresponse/{id}")
	    public void deleteResponse(@PathVariable Long id) {
	    	iresponses.deleteResponse(id);
	    }
	    @GetMapping("/findbyUseranddate/{id}")
	    public List<Responses> findResponsessByUserOrderByCreatedDate(@PathVariable Long id){
	    	return iresponses.findResponsessByUserOrderByCreatedDate(id);
	    }
	    @GetMapping("/findbyquestion/{Questions}")
	    public  List<Responses> findAllByQuestion(@RequestBody Questions question)
	    {
	    	return iresponses.findAllByQuestion(question);
	    }
	    @GetMapping("/findbydate/{date}")
	    public List<Responses> findResponsesByCreatedDate(@PathVariable Date date){
	    	return iresponses.findResponsessByCreatedDate(date);
	    }
	
	
	
}
