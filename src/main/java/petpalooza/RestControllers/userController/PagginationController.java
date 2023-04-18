package petpalooza.RestControllers.userController;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import petpalooza.Entities.Role;
import petpalooza.Entities.User;
import petpalooza.Services.userServices.IPaggination;
import petpalooza.Services.userServices.PagginationService;
import petpalooza.security.payload.response.MessageResponse;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/test/user/admin")
@AllArgsConstructor
public class PagginationController {

    IPaggination iPaggination;

    PagginationService pagginationService;


    @GetMapping("/page")
    public   List<User>  getAllPages(){
        return getOnePage( 1);
    }


    @GetMapping("page/{pageNumber}")

    public  List<User> getOnePage(@PathVariable("pageNumber") int currentPage){
        Page<User> page = pagginationService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<User> userss = page.getContent();
        ModelAndView mav = new ModelAndView("role/list");

//       return ResponseEntity.ok(new MessageResponse("the total number of pages "+ totalPages +
//               "\n  and the TotalItems is  "+ totalItems + " \n "+ " and the content of this page is  \n \n "
//
//               + userss
//               ));

        System.out.println("\n  \"the total number of pages "+  totalPages +
                "//               \"\\n  and the TotalItems is  "  + totalItems +  "\n and the content of this page is  " + userss   );

        return userss;



//        mav.addObject("currentPage", currentPage);
//        mav.addObject("totalPages", totalPages);
//        mav.addObject("totalItems", totalItems);
//        mav.addObject("role", role);

    }
}
