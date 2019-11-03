package qf.spring.springtest.springtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentC {
    StudentA studentA;

    public StudentC(StudentA studentA) {
        this.studentA = studentA;
    }
}
