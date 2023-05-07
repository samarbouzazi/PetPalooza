package petpalooza.RestControllers.userController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import petpalooza.Entities.User;
import petpalooza.Services.userServices.IVerifyAccount;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin()
@RequestMapping("public/user")
public class VerifyAccountController {
    @Autowired
    IVerifyAccount iVerifyAccount;
@Autowired
JavaMailSender mailSender;
static  String linkk="";

    @PostMapping("/verifyAccountLink")
    public ResponseEntity<?> VerifyAccountLink(HttpServletRequest request) {
        String email = request.getParameter("email");
        System.out.println("email is" + email);


        try {
            User user = iVerifyAccount.getUserByEmail(email);
            iVerifyAccount.UpdateActiveStatus(email);
            System.out.println("utlity is "+Utility.getSiteURL(request) );
//            String verifyAccountLink = Utility.getSiteURL(request)+ "http://localhost:8088/"+"verifyAccountLink?email"+email ;
//linkk=verifyAccountLink;
            return ResponseEntity.ok("You have successfully activated your account  \n "
                   );
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.ok("error" + e.getMessage());


        }

    }



    @PostMapping("sendVerification")
    public void sendVerificationEmail(String email, String verifyAccountLink ) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("timoumimahmoud616@gmail.com", "Admin ");
        helper.setTo(email);
//       String li="http://localhost:8088/"+"verifyAccountLink?email"+email;

       String Ang="http://localhost:4200/verifyAccountLink/?email="+ email;
//        System.out.println("the link static is"+ li);

        String  vLink="http://localhost:8888/public/user/verifyAccountLink/?email="+email;

        System.out.println("\n the link is herer \n "+ verifyAccountLink);
        String subject = " Link to verify your account ";
        String content ="<!doctype html>\n" +
                "<html lang=\"en-US\">\n" +
                "\n" +
                "<head>\n" +
                "  <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
                "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                "  <title>Verify Account Email Template</title>\n" +
                "  <meta name=\"description\" content=\"Reset Password Email Template.\">\n" +
                "  <style type=\"text/css\">\n" +
                "    a:hover {\n" +
                "      text-decoration: underline !important;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
                "  <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\" style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
                "    <tr>\n" +
                "      <td>\n" +
                "        <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <br>\n" +
                "              <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:570px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
                "\n" +
                "                <tr>\n" +
                "\n" +
                "                  <td style=\"padding:0 35px;\">\n" +
                "                    <h1 style=\"color:#1d9bf0; font-weight:500; margin-top:2px;font-size:15px;font-family:Arial, Helvetica, sans-serif;text-align:center ; line-height:2\"><span style=\"font-weight:750; color:#3574e3; font-size:60px\"></span> </h1>\n" +
                "                    <h1 style=\"color:#455056; font-weight:530; margin:0;font-size:15px;font-family:'Rubik',sans-serif; text-align:start;  \">Hello, </h1>\n" +
                "                    <h1 style=\"color:#455056; font-weight:500; margin-top:2px;font-size:15px;font-family:'Rubik',sans-serif;text-align:start; line-height:2\">Congratulations on signing up! We are excited to have you get started; please take a moment to confirm your email address. </h1>\n" +
                "               \n" +
                "                    \n" +
                "  \n" +
                "          <p>  <a href=\""+Ang+"\"> Verify My account </a> </p>\n" +
                "                    \n" +
                "                    \n" +
                "                    \n" +
                "                    <h1 style=\"color:#455056; font-weight:500; margin-top:2px;font-size:15px;font-family:'Rubik',sans-serif;text-align:start; line-height:2\">Thank you for verifying your email & being a part of utobo learning community. </h1>\n" +
                "\n" +
                "                    <h1 style=\"color:#455056; font-weight:500; margin-top:2px;font-size:18px;font-family:Arial, Helvetica, sans-serif;text-align:center ; line-height:2\"><span style=\"font-weight:750; color:#ff7a59\">Happy Learning!!!</span> </h1>\n" +
                "\n" +
                "                    <h1 style=\"color:#455056; font-weight:500; margin-top:2px;font-size:15px;font-family:Arial, Helvetica, sans-serif;text-align:start ; line-height:2\">Cheers, <span style=\"font-weight:750; color:#3574e3\">ByteStorm Inc.</span> </h1>\n" +
                "                    </p>\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                  <td style=\"height:100px; width : 100%; background-color:#0c5394\">\n" +
                "                    <br><br>\n" +
                "                    <i class=\"fa fa-facebook-f\" style=\"font-size:24px; color:white\"></i>&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "                    <i class=\"fa fa-linkedin\" style=\"font-size:24px; color:white\"></i>&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "                    <i class=\"fa fa-twitter\" style=\"font-size:24px; color:white\"></i>&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "                    <i class=\"fa fa-youtube-play\" style=\"font-size:24px ;color:white\"></i>\n" +
                "\n" +
                "                    <br><br>\n" +
                "                    <h1 style=\"color:white;padding:0 35px; font-weight:500; margin-top:2px;font-size:15px;font-family:'Rubik',sans-serif;vertical-align:start; line-height:2\">Tunisia  Ariana , 800 W El Camino , +(216) 21-431-055 </h1>\n" +
                "                    <br>\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </td>\n" +
                "          <tr>\n" +
                "            <td style=\"height:20px;\">&nbsp;</td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "</body>\n" +
                "\n" +
                "</html>";

        helper.setSubject(subject);
        helper.setText(content, true);

        System.out.println("email is sending    !!");
        mailSender.send(message);
    }




}
