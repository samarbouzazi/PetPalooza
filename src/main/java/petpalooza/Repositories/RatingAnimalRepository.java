package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import petpalooza.Entities.RatingAnimal;

import java.util.List;

@Repository
public interface RatingAnimalRepository extends JpaRepository<RatingAnimal, Long> {
    @Query("Select r from RatingAnimal r where r.liked = true and r.animal.idAnimal= ?1")
    public List<RatingAnimal> likesAnimal(long idAnimal);
    @Query("Select  r from RatingAnimal r where r.animal.idAnimal=?1 and r.user.idUser=?2 ")
    public RatingAnimal likeUserToAnimal (long idAnimal, long idUser);
    @Query("Select r from RatingAnimal r where r.liked = false and r.animal.idAnimal=?1")
    public List<RatingAnimal> dislikesAnimal(long idAnimal);
    @Query("select COUNT(*) from RatingAnimal r  WHERE r.liked=true and r.animal.idAnimal=?1")
    public int nbrLikes(long idAnimal);
    @Query("select COUNT(*) from RatingAnimal r  WHERE r.liked=false and r.animal.idAnimal=?1")
    public int nbrDisLikes(long idAnimal);

}
