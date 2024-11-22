package com.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Utillities {

	private RegistrationRepo registrationRepo;
	private static MasterCourseRepository masterCourseRepository;
	private static UserDetailsRepo userDetailsRepo;

	@Autowired
	public Utillities(RegistrationRepo registrationRepo, MasterCourseRepository masterCourseRepository,
			UserDetailsRepo userDetailsRepo) {
		this.registrationRepo = registrationRepo;
		this.masterCourseRepository = masterCourseRepository;
		this.userDetailsRepo = userDetailsRepo;
	}

	public static String getCurrentDateAndTime() {
		LocalDateTime now = LocalDateTime.now();
		// Format the current date and time
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);
		return formattedDateTime;
	}

	public static String getCurrentTime() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return currentTime.format(formatter);
	}

	public static String getCurrentDate() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return currentDate.format(formatter);
	}

	public static List<String> getCoursesListAsOptions() {
		List<String> courseNames = new ArrayList<>();
		try {
			List<MasterCourse> masterCourseList = masterCourseRepository.findAll();
			for (MasterCourse masterEachCourse : masterCourseList) {
				courseNames.add(masterEachCourse.getCourseName());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return courseNames;
	}

	public static boolean isUserNameAlreadyExists(String userName) {
		Optional<UserDetails> userDetailsByUsername = userDetailsRepo.findByUserName(userName);
		if (userDetailsByUsername.isPresent()) {
			return true;
		}
		return false;
	}

}
