package petpalooza.Services.userServices;

import org.springframework.data.domain.Page;
import petpalooza.Entities.User;

public interface IPaggination {
    public Page<User> findPage(int pageNumber);
    Page<User> getAllProducts(int pageNumber, int pageSize);

}
