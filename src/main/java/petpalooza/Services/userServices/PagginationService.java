package petpalooza.Services.userServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import petpalooza.Entities.User;
import petpalooza.Repositories.UserRepository;

@Service
public class PagginationService implements IPaggination {

    @Autowired
  UserRepository userRepository;
    @Override
    public Page<User> findPage(int pageNumber) {


        Pageable pageable = PageRequest.of(pageNumber - 1,5);
        return userRepository.findAll(pageable);
    }


    @Override
    public Page<User> getAllProducts(int pageNumber, int pageSize) {
        return null;
    }


    public Page<User> findPageSortedByDateOfRegistration(int pageNumber) {


        Pageable pageable = PageRequest.of(pageNumber - 1,5);
        return userRepository.findAll(pageable);
    }

}
