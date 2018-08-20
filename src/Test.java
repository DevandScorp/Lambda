import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    static boolean check(IntPred predicate){
        return true;
    }

    public static void main(String[] args) {
        Runnable noArguments = () -> {
            System.out.println("Hello world");
            System.out.println("Another String");
        };
        Long l = new Long(5);
        Function<Long,Long> func_test = x -> x+1;
        System.out.println(func_test.apply(new Long(1834)));
        System.out.println("_____________________________________________");
        check(x -> x>5);
        List<User> users = new ArrayList<>();
        for(int i = 0;i<10;++i){
            users.add(new User(i,Integer.toString(i)));
        }
        /**
         * Есть в Stream энергичные операции,есть отложенные.Отложенные возвращают Stream,энергичные-void
         * Огромным плюсом Stream является то,что мы обойдем коллекцию один раз.
         */
        long result = users.stream()
                           .filter(user-> {
                               System.out.println(user);
                               return user.getId()%2==0;
                           })
                           .count();
        System.out.println(result);
        List<String> collected = Stream.of("a","b","c").collect(Collectors.toList());
        /**
         * Меняем каждый элемент
         */
        List<String> collected_1 = Stream.of("a","b","c")
                                            .map(string->string.toUpperCase())
                                            .collect(Collectors.toList());
        /**
         * Тестил вывод через потоки
         */
        collected_1.stream()
                   .filter(user ->
                   {
                       System.out.println(user);
                       return true;
                   }).count();
        /**
         * Max или Min по id;
         */
        User first_user = users.stream().max(Comparator.comparing(user->user.getId())).get();
        System.out.println(first_user);
        /**
         * Вычислени суммы через reduce.Reduce нужен,когда нам на выходе нужен ожин конечный результат.
         * Ты объявляешь начальное значение и аккумулятор acc-то,куда будут заноситься изменения
         */
        int sum = Stream.of(1,2,3).reduce(0,(acc,elem)->acc+elem);
        System.out.println(sum);
        /**
         * то же самое,но через бинарные операторы.Чисто для рофла
         */
        BinaryOperator<Integer> binaryOperator = (x,y)->x+y;
        Integer apply = binaryOperator.apply(binaryOperator.apply(1, 2), 3);
        System.out.println(apply);
        /**
         * Mpa меняет вывод каждого из изначальных на то,что ты сказал взять
         */
        Set<String> collect = users.stream().filter(user -> user.getId() % 2 == 0).map(user -> user.getName()).collect(Collectors.toSet());
        System.out.println(collect);
        /**
         * forEach
         */
        System.out.println("FOREACH");
        users.stream().forEach(user->{
                    System.out.print("id: " + user.getId() + " ");
                user.getData().forEach(data->{
                            System.out.print(data + " ");
                        }
                        );
                    System.out.println();
                }
                );
        System.out.println("Trying to sort");
        users.stream().forEach(user->{
            System.out.print(user.getId() + " " + user.getName() + " ");
            user.getData().stream().sorted().forEach(s-> System.out.print(s + " "));
            System.out.println();
        });
        /**
         * Сортировка в одну строку.Правда,необходима реализация Comparable
         */
        List<User> collect1 = users.stream().sorted().collect(Collectors.toList());
        System.out.println(collect1);

    }
}
