import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    static boolean check(IntPred predicate){
        return true;
    }
    public static void getStatistics(List<Music> music){
        IntSummaryStatistics intSummaryStatistics = music.stream().mapToInt(x -> x.getCount())
                .summaryStatistics();
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getMin());
        System.out.println(intSummaryStatistics.getAverage());
        System.out.println(intSummaryStatistics.getSum());
    }
    /**
     * Пример использования flatMap
     * @param list
     * @param artist
     * @return
     */
    public static List<String> getNameAndCountry(List<Music> list,String artist){
        /**
         * по сути,ты просто последоватльено загрузил поля в один лист
         */
        return list.stream()
                    .filter(music -> music.getArtist().equals("1"))
                    .flatMap(music->Stream.of(music.getName(),music.getCountry()))
                    .collect(Collectors.toList());
    }
    public static void main(String[] args) {
        long hello = new String("hello").chars().filter(x -> x >= 97 && x <= 122).count();/**
         Можно было так,а можно было ссылкой на методы*/
        System.out.println(new String("Hello") .chars()
                                    .filter(Character::isLowerCase)
                                    .count());
        System.out.println(hello);
        new Thread(()->{
            System.out.println("Hello world");
            System.out.println("Another String");
        }).start();
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
        System.out.println("_______________________________________");
        /**
         * Даже если это Integer или другие классы-оболочки,такие операции сложения будут допустимы
         */
        Task_Add task_add = (value)->value.reduce(0,(acc,elem)->acc+elem);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        int i = task_add.addUp(list.stream());
        System.out.println(i);
        System.out.println("______________________________________________________");
        List<Music> list1 = new ArrayList<>();
        list1.add(new Music("1","1","1",3));
        list1.add(new Music("1","Test","Test",4));
        list1.add(new Music("2","2","2",2));
        list1.add(new Music("3","3","3",3));
        list1.add(new Music("4","4","4",4));
        list1.add(new Music("0","0","0",0));
        List<Music> collect2 = list1.stream().filter(music -> music.getCount() >= 3).collect(Collectors.toList());
        System.out.println(collect2);
        System.out.println("______________________________________________________");
        String[][] arr = {{"Artem"," loves", " Java"}};
        /**
         * Flat map собирает все данные в один поток.Удобно для обработки двумерных массивов
         */
        Stream<String> objectStream = Arrays.stream(arr).flatMap(x -> Arrays.stream(x));
        objectStream.forEach(System.out::println);
        List<String> nameAndCountry = getNameAndCountry(list1, "1");
        System.out.println("______________________________________________________");
        System.out.println(nameAndCountry);
        System.out.println("______________________________________________________");
        getStatistics(list1);
    }
}
