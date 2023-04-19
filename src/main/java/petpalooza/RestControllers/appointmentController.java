package petpalooza.RestControllers;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.Appointment;
import petpalooza.Entities.Event;
import petpalooza.Entities.User;
import petpalooza.Services.AppointmentService;
import petpalooza.Services.IAppointment;

import petpalooza.Services.userServices.IUser;
import petpalooza.Services.userServices.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

@CrossOrigin
@RestController
@RequestMapping("appointment")
public class appointmentController {

	@Autowired
	AppointmentService appointmentService;

	@Autowired
	UserService userService;

	@GetMapping("/list")
	public List<Appointment> listofAppointement() {
		return appointmentService.getAllAppointment();
	}
	
	@GetMapping("/list/export")
	public void exportToExcel(HttpServletResponse response) throws IOException{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("RDVs");
		
		XSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Date");
		headerRow.createCell(2).setCellValue("Prix");
		headerRow.createCell(3).setCellValue("Meeting");
		headerRow.createCell(4).setCellValue("User");
		headerRow.createCell(5).setCellValue("Véterinaire");
		headerRow.createCell(6).setCellValue("Latitude");
		headerRow.createCell(7).setCellValue("Longitude");
		
		List<Appointment> apps = appointmentService.getAllAppointment();
		int rowNum = 1;
		for (Appointment appointment : apps) {
			XSSFRow row = sheet.createRow(rowNum);
			row.createCell(0).setCellValue(appointment.getIdAppointment());
			row.createCell(1).setCellValue(appointment.getDateRDV().toString().substring(0, 10));
			row.createCell(2).setCellValue(appointment.getPrice());
			row.createCell(3).setCellValue(appointment.getMetting());
			row.createCell(4).setCellValue(appointment.getNormalUser().getLastName()+", "+appointment.getNormalUser().getFirstName());
			row.createCell(5).setCellValue(appointment.getUserViternaire().getLastName()+", "+appointment.getUserViternaire().getFirstName());
			row.createCell(6).setCellValue(appointment.getLat());
			row.createCell(7).setCellValue(appointment.getLng());
			rowNum++;
		}
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	    response.setHeader("Content-Disposition", "attachment; filename=RDV_"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))+".xlsx");
	    
	    workbook.write(response.getOutputStream());
	    
	    workbook.close();
		
	}

	@PostMapping("/add/{idVet}/{idUser}")
	public Appointment addEvent(@RequestBody Appointment appointment, @PathVariable long idVet,
			@PathVariable long idUser) {
		User vet = userService.findUserByID(idVet);
		User user = userService.findUserByID(idUser);
		appointment.setUserViternaire(vet);
		appointment.setNormalUser(user);
		return appointmentService.addNewAppointment(appointment);
	}

	@DeleteMapping("/delete/{idAppointement}")
	public void delete(@PathVariable("idAppointement") Long idAppointement) {
		appointmentService.deleteAppointment(idAppointement);
	}

	@GetMapping("findById/{idAppointment}")
	public Appointment AppointmentById(@PathVariable("idAppointment") Long idAppointment) {
		return appointmentService.findAppointmentByID(idAppointment);
	}

	@PostMapping("/add2/{idUser1}/{idUser2}")
	public Appointment add2(@PathVariable("idUser1") Long idUser1, @PathVariable("idUser2") Long idUser2) {
//        User user1=new User();
//        User user2=new User();
//
//
//     user1= iUser.findUserByID(idUser1);
//     user2= iUser.findUserByID(idUser2);

		return appointmentService.add2(idUser1, idUser2);

	}
	
	
	//GET ORDERED BY PRICE PATHS
	@GetMapping("/price/asc")
	public List<Appointment> getByPriceAscending() {
		return appointmentService.OrderByPriceAscending();
	}
	
	@GetMapping("/price/desc")
	public List<Appointment> getByPriceDescending() {
		return appointmentService.OrderByPriceDescending();
	}
	
	//GET ORDERED BY DATE PATHS
	@GetMapping("/date/asc")
	public List<Appointment> getByDateAscending(){
		return appointmentService.orderByDateRdvAscending();
	}
	
	@GetMapping("/date/desc")
	public List<Appointment> getByDateDescending(){
		return appointmentService.orderByDateRdvDescending();
	}
	
	@GetMapping("/date/today")
	public List<Appointment> getByToday(){
		return appointmentService.orderByDateRdvDescending();
	}
	
	//GET BY USER PATHS
	@GetMapping("/list/user/{idUser}")
	public List<Appointment> listOfAppointementByUser(@PathVariable long idUser) {
		return appointmentService.findByUser(idUser);
	}
	
	@GetMapping("/list/user/{idUser}/date/desc")
	public List<Appointment> listOfAppointementByUserByDateDescending(@PathVariable long idUser) {
		return appointmentService.findByUserOrderedDateDescending(idUser);
	}
	
	@GetMapping("/list/user/{idUser}/date/asc")
	public List<Appointment> listOfAppointementByUserByDateAscending(@PathVariable long idUser) {
		return appointmentService.findByUserOrderedDateAscending(idUser);
	}
	
	@GetMapping("/list/user/{idUser}/date/today")
	public List<Appointment> listOfAppointementByUserToday(@PathVariable long idUser) {
		return appointmentService.findByUserOrderedDateDescending(idUser);
	}
	
	//GET BY VET PATHS
	@GetMapping("/list/vet/{idVet}")
	public List<Appointment> listOfAppointementByVet(@PathVariable long idVet) {
		return appointmentService.findByVet(idVet);
	}
	
	@GetMapping("/list/vet/{idVet}/date/desc")
	public List<Appointment> listOfAppointementByVetByDateDescending(@PathVariable long idVet) {
		return appointmentService.findByVetOrderedDescending(idVet);
	}
	
	@GetMapping("/list/vet/{idVet}/date/asc")
	public List<Appointment> listOfAppointementByVetByDateAscending(@PathVariable long idVet) {
		return appointmentService.findByVetOrderedAscending(idVet);
	}
	
	@GetMapping("/list/vet/{idVet}/date/today")
	public List<Appointment> listOfAppointementByVetToday(@PathVariable long idVet) {
		return appointmentService.findByVetToday(idVet);
	}

}
