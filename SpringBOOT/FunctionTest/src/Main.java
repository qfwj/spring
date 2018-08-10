import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> list = new ArrayList();
        list.add("1");
        list.add("34");
        /*Predicate*/
        Predicate predicate1 = f -> {return (boolean) f;};
        Predicate predicate2 = f -> {return (boolean) f;};
        System.out.println("predicate test:" + predicate1.and(predicate2).test(false)); //false


        /*Funciton*/
        Function function1 = f -> {return Integer.parseInt((String)f);};
        Function function2 = f -> {return f.toString();};
        Function function3 = f -> {return (int)f + 3;};
        Function function4 = function1.compose(function2).andThen(function3); //15



        /*Consumer*/
        Consumer Consumer1 = f -> { f.toString();};

        //Consumer Consumer4 = function1.compose(function2).andThen(Consumer);


        System.out.println("function test:" + function4.apply(12));



        // list.add("Ster");
        list.stream().map(f -> {
            System.out.println("哈哈1");
            return Integer.parseInt(f);
        }).filter(f -> {
            System.out.println("哈哈2");
            return (int)f >12;

        }).forEach(f -> {
            System.out.println("哈哈3"+ f);
        });

        //collect(Collectors.toList());



        Function<Integer, Integer> age = new Function<Integer, Integer>(){
            public Integer apply(Integer t){
                return t;
            }
        } ;





        Function<Integer, Integer> age2 = e -> e*e;

        System.out.println(age.compose(age2).apply(2));
        System.out.println(age.andThen(age2).apply(2));
        System.out.println(age2.compose(age).apply(2));
        //new test().as("sfd");

    }


}
class test{
    <v> int as(v daas){
        System.out.println(daas.toString());
        int i = 0;
        return 12;
    }
}
class test1 extends test{

}
class test2 extends test1{

}