package petpalooza.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import petpalooza.Entities.Questions;
import petpalooza.Entities.Responses;

public interface ResponsesRepository extends JpaRepository<Responses,Long>  {
////////functions////
    Long countResponsessByUser(Long id);
    Long countResponsesByquestion(Long id);
    List<Responses> findAllByQuestion(Questions question);
    List<Responses> findResponsessByUserOrderByCreatedDate(Long id);
    List<Responses> findResponsesByquestion(Long id);
    List<Responses> findResponsesByCreatedDate(Date date);
}
