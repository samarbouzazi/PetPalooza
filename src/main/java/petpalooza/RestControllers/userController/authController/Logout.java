//package petpalooza.RestControllers.userController.authController;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//import petpalooza.security.payload.response.MessageResponse;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//@RequestMapping("public")
//@RestController
//@CrossOrigin
//public class Logout {
////    @PostMapping("/logout")
////    public ResponseEntity<?> logout(HttpServletRequest request) {
////        HttpSession session = request.getSession(false);
////        if (session != null) {
////            session.invalidate();
////            System.out.println("logout succes \n");
////        }
////        System.out.println("logout succes  222  \n");
////        return ResponseEntity.ok(new MessageResponse("logout succesfully successfully!"));
////
//////        return ResponseEntity.ok().build();
////    }
////}
//
//
//    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
//    public String logoutDo(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession(false);
//        SecurityContextHolder.clearContext();
//        session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        for (Cookie cookie : request.getCookies()) {
//            cookie.setMaxAge(0);
//        }
//
//        return "logout";
//    }
//
//
//}