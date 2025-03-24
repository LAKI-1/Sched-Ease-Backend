package com.sched_ease.backend.unit_testing.gcalendar;

import com.sched_ease.backend.BackendApplication;
import com.sched_ease.backend.database.entities.*;
import com.sched_ease.backend.database.repositories.SDGPStudentRepository;
import com.sched_ease.backend.database.services.SDGPStudentService;
import com.sched_ease.backend.google.calendar.GoogleCalendarService;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SessionCalendarInviteTesting {

    public static void main(String[] args){

        String testing_class = "GoogleCalendarService";
        String testing_method = "getStudentByEmail";

        List<String> tests = new ArrayList<>();

        tests.add("Initialization");
        tests.add("get Beans");
        tests.add("Create Tutorial Group");
        tests.add("Create Students");
        tests.add("Add students to tutorial group");
        tests.add("Create SDGP Students");
        tests.add("Create SDGP Group");
        tests.add("Create Feedback Instructing Lecturer");
        tests.add("Create Hall");
        tests.add("Create Feedback Session");
        tests.add("Make calendar Event");

        List<Boolean> results = new ArrayList<>();

        for (String each: tests) { results.add(false); }

        ApplicationContext context;

        System.out.println("Starting unit testing for class: "+ testing_class +" for method: "+ testing_method + "()");

        try {

            /***********************************************
             * Innitializing system
             ***********************************************/

//            System.setProperty("logging.level.root", "OFF");
//            System.setProperty("logging.level.org.springframework.boot", "OFF");
//            System.setProperty("logging.level.org.springframework.web", "OFF");
//            System.setProperty("spring.jpa.show-sql", "OFF");
            //System.setProperty("spring.jpa.properties.hibernate.format_sql", "OFF");

            SpringApplication app = new SpringApplication(BackendApplication.class);

            //turn off SpringBoot Banner
            app.setBannerMode(Banner.Mode.OFF);

            context = app.run(args);

            results.set(0, true);

            System.out.println("Staring testing...");

            /***********************************************
             * getting GoogleCalendar Service Bean
             ***********************************************/

            GoogleCalendarService googleCalendarService = context.getBean(GoogleCalendarService.class);

            results.set(1, true);

            /***********************************************
             * Creating a tutorial Group
             ***********************************************/

            TutorialGroup tutorialGroup = new TutorialGroup("07", "Semester 02", "Computer Science", null, new ArrayList<>());

            results.set(2, true);

            /***********************************************
             * Creating students for a SDGP Group
             ***********************************************/

            Student student1 = new Student(20231167l, "Lakindu Samarasinghe",               "Computer Science", "lakindu.20231167@iit.ac.lk",   "Semeser 02", "2024", tutorialGroup);
            Student student2 = new Student(20231190l, "Faaeq Fazal",                        "Computer Science", "faaeq.20231190@iit.ac.lk",     "Semeser 02", "2024", tutorialGroup);
            Student student3 = new Student(20231111l, "Cheran Li",                          "Computer Science", "cheran.20231111@iit.ac.lk",    "Semeser 02", "2024", tutorialGroup);
            Student student4 = new Student(20231691l, "Thishakya Wikramasinghearachchi",    "Computer Science", "thishakya.20231691@iit.ac.lk", "Semeser 02", "2024", tutorialGroup);
            Student student5 = new Student(20231490l, "Pehansa Dharmapala",                 "Computer Science", "ranumi.20231490@iit.ac.lk",    "Semeser 02", "2024", tutorialGroup);
            Student student6 = new Student(20231910l, "Kavindya Bandara",                   "Computer Science", "kavindya.20231910@iit.ac.lk",  "Semeser 02", "2024", tutorialGroup);

            results.set(3, true);

            List<Student> students = new ArrayList<>();

            students.add(student1);
            students.add(student2);
            students.add(student3);
            students.add(student4);
            students.add(student5);
            students.add(student6);

            tutorialGroup.setStudents(students);

            results.set(4, true);

            SDGPStudent sdgpStudent1 = new SDGPStudent(student1, true);
            SDGPStudent sdgpStudent2 = new SDGPStudent(student2);
            SDGPStudent sdgpStudent3 = new SDGPStudent(student3);
            SDGPStudent sdgpStudent4 = new SDGPStudent(student4);
            SDGPStudent sdgpStudent5 = new SDGPStudent(student5);
            SDGPStudent sdgpStudent6 = new SDGPStudent(student6);

            results.set(5, true);

            /***********************************************
             * Creating SDGP Group
             ***********************************************/

            List<SDGPStudent> sdgpStudents = new ArrayList<>();

            sdgpStudents.add(sdgpStudent1);
            sdgpStudents.add(sdgpStudent2);
            sdgpStudents.add(sdgpStudent3);
            sdgpStudents.add(sdgpStudent4);
            sdgpStudents.add(sdgpStudent5);
            sdgpStudents.add(sdgpStudent6);

            SDGPGroup sdgpGroup = new SDGPGroup(66, "Computer Science", true, sdgpStudents);

            results.set(6, true);

            /***********************************************
             * Creating Feedback Session for SDGP Group
             ***********************************************/

            Lecturer lecturer = new Lecturer("Naflan Mohamed", "NAM", "naflan.20232173@iit.ac.lk");
            SDGPLecturer sdgpLecturer = new SDGPLecturer(lecturer);

            sdgpLecturer.setFeedbackInstructorFlag(true);

            results.set(7, true);

            Hall hall = new Hall("3LB", "Spenser", new ArrayList<>());

            results.set(8, true);

            ZonedDateTime startDateTime = ZonedDateTime.of(2025, 3, 24, 12, 0, 0, 0, ZoneId.of("Asia/Kolkata"));
            ZonedDateTime endDateTime = ZonedDateTime.of(2025, 3, 24, 13, 0, 0, 0, ZoneId.of("Asia/Kolkata"));

            FeedbackSession feedbackSession = new FeedbackSession(startDateTime, endDateTime, true, "Booked", "Feedback Session", sdgpLecturer, hall, sdgpGroup);

            results.set(9, true);
            /***********************************************
             * Creating calendar Invites and Inviting Participants
             ***********************************************/

//            googleCalendarService.inviteByFeedbackSession(feedbackSession);

            results.set(10, googleCalendarService.inviteByFeedbackSession(feedbackSession));

            System.out.println("testing ended sucessfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Testing ran into an issue...");
        } finally {
            System.out.println("############## RESULTS ##############");

            for (int i = 0; i < tests.size(); i++){
                System.out.println("Test " + i + ": " + results.get(i) + " -> " + tests.get(i));
            }
        }

    }
}
