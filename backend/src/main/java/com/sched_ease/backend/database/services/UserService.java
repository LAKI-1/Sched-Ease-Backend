package com.sched_ease.backend.database.services;

import org.springframework.stereotype.Service;


//This file is most like y useless

//@Service
public class UserService {

    private SDGPStudentService sdgpStudentService;

    public UserService(SDGPStudentService sdgpStudentService){
        this.sdgpStudentService = sdgpStudentService;
    }


}
