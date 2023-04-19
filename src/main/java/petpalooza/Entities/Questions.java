package petpalooza.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity

@Table(name = "Questions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Questions implements Serializable {
//////////columns/////
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	 String question_desription;
	 
	 public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion_desription() {
		return question_desription;
	}

	public void setQuestion_desription(String question_desription) {
		this.question_desription = question_desription;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public List<Responses> getResponses() {
		return responses;
	}

	public void setResponses(List<Responses> responses) {
		this.responses = responses;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private LocalDateTime createdDate;
	 
	 @OneToMany(mappedBy = "question")
	    private List<Responses> responses;
	 
	 @ManyToOne
	    @JoinColumn(name = "idUser")
	    private User user;
	
}
