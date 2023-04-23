package petpalooza.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity

@Table(name = "Response")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Responses implements Serializable{
//////////columns/////

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long idResponse;
	 String response_desription;


	public long getIdResponse() {
		return idResponse;
	}

	public void setIdResponse(long idResponse) {
		this.idResponse = idResponse;
	}

	public String getResponse_desription() {
		return response_desription;
	}

	public void setResponse_desription(String response_desription) {
		response_desription = response_desription;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private LocalDateTime createdDate;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "IdQuestion")
	    private Questions question;
	 
	    @ManyToOne
	    @JoinColumn(name = "Userid")
	    private User user;

		

}
