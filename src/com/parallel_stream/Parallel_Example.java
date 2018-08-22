package com.parallel_stream;

import java.util.*;
import java.util.stream.IntStream;

public class Parallel_Example {
    public static int getSum(List<Integer> list){
        return list.parallelStream()
                   .mapToInt(i -> i)
                   .sum();
    }
    public static int multiplyThrough(List<Integer> linkedListOfNumbers) {
          return 5*linkedListOfNumbers.parallelStream()
                                      .reduce(1, (acc, x) -> x * acc);
    }
    public static int slowSumOfSquares(List<Integer> linkedListOfNumbers) {
          return linkedListOfNumbers.parallelStream()
                                    /**.map(x -> x * x)-дерьмокод*/
                                    .mapToInt(x->x*x).sum();/**Сразу можно было привести без map*/
    }

    public static class User{
        public User() {
        }

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    public static int getSumOfSquares(IntStream range){
        /**
         * Не забывай:map для приведения к нужному тебе типу
         */
       return  range.parallel().map(x->x*x).sum();
    }
    /**
     * Распараллелить можно не все данные.
     * Некоторые из них будут плохо работать при распараллеливании.
     * Так,например:
     *  <b>Хорошие.</b> ArrayList , массив и конструктор IntStream.range .
     *  Все эти источники поддерживают произвольную выборку, поэтому их легко разбить на любые части.
     *  <b>Нормальные.</b> HashSet  и TreeSet .
     *  Произвести их декомпозицию, сохранив сбалансированность, нелегко, но в большинстве случаев возможно.
     *  <b>Плохие</b> Некоторые структуры данных с трудом поддаются разбиению, например на это может уйти время порядка O(N). К таковым относится объект LinkedList , который вычислительно сложно разбить пополам.
     *  Длина объектов Streams.iterate  и BufferedReader.lines  изначально неизвестна, поэтому оценить, в каком месте производить разбиение, довольно трудно.
     * Производительность определяется пятью основными факторами: объем данных,
     * структура исходных данных,
     * являются ли данные упакованными или примитивными,
     * число доступных ядер и время, затрачиваемое на обработку одного элемента.
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i<5;++i){
            list.add(i);
        }
        System.out.println(getSum(list));
        /**
         * Вот тебе и заполнение массива от 0 до 9
         * Можно с parallel,можно без
         */
        int[] arr= new int[10];
        Arrays.parallelSetAll(arr,i-> 10-i);
        System.out.println(Arrays.toString(arr));
        User[] users = new User[10];
        /**
         * Как вариант заполнения массива объектов
         */
        System.out.println(Arrays.toString(users));
        Arrays.parallelSetAll(users,i->new User(Integer.toString(9-i)));
        System.out.println(Arrays.toString(users));
        Arrays.parallelSort(arr);
        System.out.println(Arrays.toString(arr));
        /**
         * Можно выпендриваться
         */
        Arrays.parallelSort(users, Comparator.comparing(User::getName));
        System.out.println(Arrays.toString(users));
//        Arrays.s
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        System.out.println(getSumOfSquares(list1.stream().mapToInt(i->i)));
        List<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        System.out.println(multiplyThrough(list2));
        System.out.println(slowSumOfSquares(list2));
    }
}
