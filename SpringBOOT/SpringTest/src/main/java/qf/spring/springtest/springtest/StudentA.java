package qf.spring.springtest.springtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class StudentA {
   // @Autowired
    StudentB studentB;

 /*   @Autowired
    StudentC studentC;*/
    public StudentA(StudentB studentB) {
        this.studentB = studentB;
    }
    @PostConstruct
    public void test(){
        System.out.println("");
    }
}
