package com.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001" })
@Controller
public class GeneralController {
	@Autowired
	private RegistrationRepo registrationRepo;
	@Autowired
	private UserDetailsRepo userDetailsRepo;
	@Autowired
	private MasterCourseRepository masterCourseRepositoryNew;

	@RequestMapping("/")
	public String getHomePage() {
		return "home";
	}

	public List<UserDetails> userDetailsList;
	public static String MOBILE_NUMBER;

	@RequestMapping(path = "/checkMobileNumExist", method = RequestMethod.POST)
	public String checkMobileNumberExist(@RequestParam("mobileNumber") String mobileNumber, Model model) {
		// --->redirect, if existing user is registered for new course
		String mobileNum = "";
		try {
			MOBILE_NUMBER = mobileNumber;
			userDetailsList = userDetailsRepo.findByMobileNum(mobileNumber);
			for (UserDetails userDetails : userDetailsList) {
				if ((userDetails.getMobileNum()).equals(mobileNumber)) {
					String name = userDetails.getName();
					String email = userDetails.getEmail();
					mobileNum = userDetails.getMobileNum();
					String whatsAppNum = userDetails.getWhatsAppNum();
					String messsage = userDetails.getMessage();
					String createdDate = userDetails.getCreatedDate();
					String createdTime = userDetails.getCreatedTime();
					String userGrade = userDetails.getUserGrade();
					String userName = userDetails.getUserName();
					model.addAttribute("user", userDetails);

					return "registration";// -->redirect to pre-filled page
				}
			}

		} catch (Exception e) {
			System.out.println("Hello Mr.Exception: " + e);
			e.printStackTrace();
		}
		// --->redirect to new registration page
		UserDetails neoUserData = new UserDetails();
		neoUserData.setMobileNum(mobileNumber);
		model.addAttribute("user", neoUserData);
		return "registration";
	}

	// ---> New user Registration comes here.
	@ResponseBody
	@RequestMapping(path = "/submitRegister", method = RequestMethod.POST)
	public String regsiterUser(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "areaOfInterest", required = false) String areaOfInterest,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "mobileNum", required = false) String mobileNum,
			@RequestParam(name = "whatsAppNum", required = false) String whatsAppNum,
			@RequestParam(name = "message", required = false) String message,
			@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password) {

		try {
			// if the user is already existed, course Up-dation only, then the logic
			// follows.
			List<UserDetails> userDetailsList = userDetailsRepo.findByMobileNum(mobileNum);
			if (!userDetailsList.isEmpty()) {
				for (UserDetails userDetailsEachNew : userDetailsList) {
					String cource_id = areaOfInterest;
					List<Long> courceIdsList = registrationRepo.findCourseIdsByUserId((userDetailsEachNew.getId()));

					// Validation for user is already registered for the cource.
					if ((userDetailsEachNew.getMobileNum().equals(GeneralController.MOBILE_NUMBER))
							&& courceIdsList.contains(cource_id)) {
						return "You have Already Registered for this Cource..!";
					}

					if (!((userDetailsEachNew.getPassword()).equals(password))) {
						return "Invalid Password, Please Try Again..! OR Contact Admin.";
					}

					// Up-dation of user_id & cource_id in registration table for existing user.
					if ((userDetailsEachNew.getMobileNum().equals(GeneralController.MOBILE_NUMBER))
							&& !courceIdsList.contains(cource_id)) {
						Registartion registartion = new Registartion();
						registartion.setCource_id(Long.parseLong(areaOfInterest));
						registartion.setUser_id(userDetailsEachNew.getId());

						registartion.setCreatedDate(Utillities.getCurrentDate());
						registartion.setCreatedTime(Utillities.getCurrentTime());

						registrationRepo.save(registartion);

						return "Course Registration is Successful for Existing User.";
					}

				}
			}

			// Registration for the new user.
			if (Utillities.isUserNameAlreadyExists(username)) {
				return "Uername is Already taken..! ";
			}
			UserDetails userDetails = new UserDetails();
			if (userDetails != null) {
				userDetails.setName(name);
				userDetails.setEmail(email);
				userDetails.setMobileNum(mobileNum);
				userDetails.setWhatsAppNum(whatsAppNum);
				userDetails.setMessage(message);
				userDetails.setCreatedDate(Utillities.getCurrentDate());
				userDetails.setCreatedTime(Utillities.getCurrentTime());
				// USER GRADES: 1-->Admin, 2-->Student, 3-->Instructor
				userDetails.setUserGrade("2");
				// Validating weather this username is already taken or not.?
				userDetails.setUserName(username);
				userDetails.setPassword(password);
			}

			userDetailsRepo.save(userDetails);

			Registartion registartion = new Registartion();
			registartion.setUser_id(userDetails.getId());
			registartion.setCource_id(Long.parseLong(areaOfInterest));

			registartion.setCreatedDate(Utillities.getCurrentDate());
			registartion.setCreatedTime(Utillities.getCurrentTime());

			registrationRepo.save(registartion);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return "Registartion is Successfull for new user.";
	}

	@ResponseBody
	@RequestMapping(path = "/handleAreaOfInterestChange", method = RequestMethod.POST)
	public String handleAreaOfInterestChange(@RequestParam("areaOfInterest") String areaOfInterest) {
		List<UserDetails> userDetailsList = userDetailsRepo.findByMobileNum(GeneralController.MOBILE_NUMBER);
		// If user is registered with one course & Again he is trying to register that
		// course again
		if (!userDetailsList.isEmpty()) {
			for (UserDetails userDetails : userDetailsList) {
				if (userDetails.getMobileNum().equals(GeneralController.MOBILE_NUMBER)) {
					long userID = userDetails.getId();
					String courseID = areaOfInterest;
					Long dBCourceId = registrationRepo.findCourceIdByUserIdAndCourceId(userID,
							Long.parseLong(courseID));
					if (dBCourceId != null) {
						return "User is Already Registered for this Course.";
					}
				}
			}
		}

		// No need to return any message for other scenarios
		return "";
	}

	@RequestMapping(path = "/getCourceDropDown", method = RequestMethod.POST)
	@ResponseBody // This annotation is needed to return the string directly
	public String getCourceDropDown(Model model) {
		// logic to get drop-down
		long courceId;
		String courseName = "";
		String courseDropDown = "<option value=''>Select Area of Interest</option>"; // Add a default option
		List<MasterCourse> courceDetailsList = masterCourseRepositoryNew.findAll();
		if (!courceDetailsList.isEmpty()) {
			for (MasterCourse course : courceDetailsList) {
				courseName = course.getCourseName();
				courceId = course.getCourseId();
				courseDropDown += "<option value='" + courceId + "'>" + courseName + "</option>";
			}
		}
		return courseDropDown;
	}

	@PostMapping("/checkUserNameAlreadyExist")
	@ResponseBody
	public String checkUserNameAlreadyExist(@RequestParam("inputUsername") String inputUsername) {
		if (Utillities.isUserNameAlreadyExists(inputUsername))
			return "Username is Already taken, Please Use another username..!";
		return "";

	}

//	REact All ENd Points Starts from Here------------->

	@PostMapping("/submitRegisterForReact")
	public ResponseEntity<?> submitRegisterForUser(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "mobileNum", required = false) String mobileNum,
			@RequestParam(name = "whatsAppNum", required = false) String whatsAppNum,
			@RequestParam(name = "message", required = false) String message,
			@RequestParam(name = "areaOfInterest", required = false) String areaOfInterest,
			@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password) {

		boolean isExistingUser = false;
		UserDetails userDetailsGen = new UserDetails();

		List<UserDetails> userList = userDetailsRepo.findByMobileNum(mobileNum);

		for (UserDetails userDetails : userList) {
			if (userDetails != null) {
				if (userDetails.getMobileNum().equals(mobileNum)) {
					isExistingUser = true;
				} else {
					isExistingUser = false;
				}
				userDetailsGen = userDetails;
			}
		}

		// New User Registration
		if (!isExistingUser) {
			UserDetails userDetails = new UserDetails();
			userDetails.setCreatedDate(Utillities.getCurrentDate());
			userDetails.setCreatedTime(Utillities.getCurrentTime());
			userDetails.setName(name);
			userDetails.setUserGrade("2");
			userDetails.setMobileNum(mobileNum);
			userDetails.setWhatsAppNum(whatsAppNum);
			userDetails.setMessage(message);
			userDetails.setUserName(username);
			userDetails.setPassword(password);
			userDetails.setEmail(email);
			UserDetails savedUserDeatils = userDetailsRepo.save(userDetails);
			if (savedUserDeatils != null) {
				Registartion userReg = new Registartion();
				userReg.setCource_id(Long.parseLong(areaOfInterest));
				userReg.setUser_id(savedUserDeatils.getId());
				userReg.setCreatedDate(Utillities.getCurrentDate());
				userReg.setCreatedTime(Utillities.getCurrentTime());
				Registartion regSave = registrationRepo.save(userReg);
				if (regSave != null) {
					return ResponseEntity.ok().build();
				}
			}
		}

		// Existing User Registration--> only Course Up-dation is REquired
		if (isExistingUser) {
			String dBMobileNum = userDetailsGen.getMobileNum();
			Long userID = userDetailsGen.getId();
			if (!dBMobileNum.isEmpty()) {
				List<Long> courseIdsList = registrationRepo.findCourseIdsByUserId(userID);
				if (!courseIdsList.isEmpty()) {
					if (courseIdsList.contains(Long.parseLong(areaOfInterest))) {
						return ResponseEntity.status(HttpStatus.CONFLICT)
								.body("User is Already Registered to this Course..!");
					}

					if (!courseIdsList.contains(areaOfInterest)) {
						// Validation
						String dbPassowrd = userDetailsGen.getPassword();
						String dbMobileNum = userDetailsGen.getMobileNum();
						if (password.equals(dbPassowrd) && mobileNum.equals(dbMobileNum)) {
							Registartion regUser = new Registartion();
							regUser.setCource_id(Long.parseLong(areaOfInterest));
							regUser.setUser_id(userID);
							regUser.setCreatedDate(Utillities.getCurrentDate());
							regUser.setCreatedTime(Utillities.getCurrentTime());
							Registartion save = registrationRepo.save(regUser);
							if (save != null) {
								return ResponseEntity.ok().build();
							}
						} else {
							return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
						}
					}
				}
			}
		}

		return ResponseEntity.internalServerError().build();
	}

	@PostMapping("/isAlreadyRegistered")
	public ResponseEntity isAlreadyRegistered(@RequestParam("mobileNumber") String mobileNumber, Model model) {

		HashMap<String, Object> response = new HashMap();
		List<UserDetails> ud = userDetailsRepo.findByMobileNum(mobileNumber);

		if (!userDetailsRepo.findByMobileNum(mobileNumber).isEmpty()) {
			List<UserDetails> userDetails = userDetailsRepo.findByMobileNum(mobileNumber);
			for (UserDetails userDetails2 : userDetails) {
				if (userDetails2 != null) {
					response.put("isAlreadyExist", true);
					response.put("name", userDetails2.getName());
					response.put("userName", userDetails2.getUserName());
					response.put("email", userDetails2.getEmail());
					response.put("message", userDetails2.getMessage());
					response.put("password", userDetails2.getPassword());
					response.put("whatsAppNum", userDetails2.getWhatsAppNum());
					response.put("mobileNum", userDetails2.getMobileNum());
					return ResponseEntity.ok(response);
				}
			}
		}

		response.put("isAlreadyExist", false);
		response.put("info", "user not registered");
		return ResponseEntity.ok(response);
	}

	@PostMapping("/validateUser")
	public ResponseEntity<String> validateUser(@RequestParam("userName") String userName,
			@RequestParam("password") String password) {
		try {
			Optional<UserDetails> userDetails = userDetailsRepo.findByUserName(userName);
			if (userDetails.isPresent()) {
				UserDetails userDetailsInfo = userDetails.get();
				if (userDetailsInfo != null) {
					String dbUserName = userDetailsInfo.getUserName();
					String dbPassword = userDetailsInfo.getPassword();
					if (!dbUserName.equals(userName)) {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username Passed..!");
					}
					if (!dbPassword.equals(password)) {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password..!");
					}

					return ResponseEntity.ok("Validation Successfull..!");
				}

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An Error Occured " + e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An Error Occured..!");
	}

}
