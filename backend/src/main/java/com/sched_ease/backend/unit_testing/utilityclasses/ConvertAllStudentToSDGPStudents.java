package com.sched_ease.backend.unit_testing.utilityclasses;

import com.sched_ease.backend.BackendApplication;
import com.sched_ease.backend.database.entities.SDGPStudent;
import com.sched_ease.backend.database.entities.Student;
import com.sched_ease.backend.database.repositories.SDGPStudentRepository;
import com.sched_ease.backend.database.repositories.StudentRepository;
import com.sched_ease.backend.database.services.SDGPStudentService;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConvertAllStudentToSDGPStudents {

    public static void main(String[] args){

        String target_class = "SDGPStudentService";
        String target_method = "getStudentByEmail()";

        ApplicationContext context;

        System.out.println("Targetting Utility at class: "+ target_class +" for method: "+ target_method);

        try {
            System.setProperty("logging.level.root", "OFF");
//            System.setProperty("logging.level.org.springframework.boot", "OFF");
            System.setProperty("logging.level.org.springframework.web", "OFF");
//            System.setProperty("spring.jpa.show-sql", "OFF");
            //System.setProperty("spring.jpa.properties.hibernate.format_sql", "OFF");

            SpringApplication app = new SpringApplication(BackendApplication.class);

            //turn off SpringBoot Banner
            app.setBannerMode(Banner.Mode.OFF);

            context = app.run(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Initialization ran into an issue...\nExiting Testing");
            return;
        }

        try {
            System.out.println("Staring Utility...");

            SDGPStudentService testingClass = context.getBean(SDGPStudentService.class);
            SDGPStudentRepository repo = context.getBean(SDGPStudentRepository.class);
            StudentRepository studentRepo = context.getBean(StudentRepository.class);

            List<Student> students = studentRepo.findAll();
            List<SDGPStudent> list = testingClass.getAllInRepo();

            Student student = testingClass.getStudentByEmail("cheran.20231111@iit.ac.lk").get();

//            SDGPStudent newSdgpStudent = new SDGPStudent(student);
            SDGPStudent newSdgpStudent = new SDGPStudent();

            newSdgpStudent.setId(student.getId());


//
//            SDGPStudent sdgpStudent = testingClass.getStudentByEmail("cheran.20231111@iit.ac.lk");

            System.out.println("############## RESULTS ##############");
//            System.out.println(sdgpStudent.toJson().toString());


            for(SDGPStudent sdgpStudent : list){
                System.out.println(sdgpStudent.toJson().toString());
            }

            System.out.println(student.toJson());
            System.out.println(newSdgpStudent.toJson());

            repo.save(newSdgpStudent);

            System.out.println("testing ended sucessfully\nEnd of Testing");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Testing ran into an issue...\nExiting Testing");
        }

    }
}
