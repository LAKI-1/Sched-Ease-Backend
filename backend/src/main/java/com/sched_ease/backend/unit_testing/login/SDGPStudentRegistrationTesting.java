package com.sched_ease.backend.unit_testing.login;

import com.sched_ease.backend.BackendApplication;
import com.sched_ease.backend.database.entities.SDGPStudent;
import com.sched_ease.backend.database.entities.Student;
import com.sched_ease.backend.database.repositories.SDGPStudentRepository;
import com.sched_ease.backend.database.services.SDGPStudentService;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class SDGPStudentRegistrationTesting {

    public static void main(String[] args){

        String testing_class = "SDGPStudentService";
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

            SDGPStudentService testingClass = context.getBean(SDGPStudentService.class);
            SDGPStudentRepository repo = context.getBean(SDGPStudentRepository.class);


            Student student = testingClass.getStudentByEmail("cheran.20231111@iit.ac.lk").get();

            SDGPStudent newSdgpStudent = new SDGPStudent();

            newSdgpStudent.setId(student.getId());


//            System.out.println(sdgpStudent.toJson().toString());




            System.out.println(student.toJson());
            System.out.println(newSdgpStudent.toJson());

            repo.save(newSdgpStudent);

            System.out.println("testing ended sucessfully\nEnd of Testing");
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
