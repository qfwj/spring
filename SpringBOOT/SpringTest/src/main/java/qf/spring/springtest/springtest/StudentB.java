package qf.spring.springtest.springtest;


import org.springframework.stereotype.Service;

@Service
public class StudentB {

    StudentC studentC;

    public StudentB(StudentC studentC) {
        this.studentC = studentC;
    }
}
