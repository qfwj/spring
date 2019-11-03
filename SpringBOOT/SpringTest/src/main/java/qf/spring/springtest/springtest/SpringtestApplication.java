package qf.spring.springtest.springtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 通过构造函数形成的调用cycle会导致bean创建报错  BeanCurrentlyInCreationException
 *
 * The dependencies of some of the beans in the application context form a cycle:
 *
 * ┌─────┐
 * |  studentA defined in file [F:\projects\spring\SpringBOOT\SpringTest\target\classes\qf\spring\springtest\springtest\StudentA.class]
 * ↑     ↓
 * |  studentB defined in file [F:\projects\spring\SpringBOOT\SpringTest\target\classes\qf\spring\springtest\springtest\StudentB.class]
 * ↑     ↓
 * |  studentC defined in file [F:\projects\spring\SpringBOOT\SpringTest\target\classes\qf\spring\springtest\springtest\StudentC.class]
 * └─────┘
 */
@SpringBootApplication
public class SpringtestApplication {

    public static void main(String[] args) {


    SpringApplication.run(SpringtestApplication.class, args);
    }

}
