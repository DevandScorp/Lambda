package com.strings;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class String_ex {
    /**
     * Counting для подсчета без обычных циклов(можно повыпендриваться)
     */
    public static Map<Boolean,Long> getCount(List<Artist> artists){
        return artists.stream().collect(groupingBy(artist ->{
            return artist.getId()%2==0;
        },counting()));
    }
    public static void main(String[] args) {
        /**
         * Хорошая альтернатива StringBuilder
         */
        List<Artist> list = new ArrayList<>();
        list.add(new Artist("Poher kto",0,true));
        list.add(new Artist("Artem",1,true));
        list.add(new Artist("Max",2,false));
        list.add(new Artist("Kolya",3,true));
        list.add(new Artist("Katya",4,false));
        list.add(new Artist("Nikita",5,true));
        /**
         * Можно так.А можно указать разделители,первую последовательность и вторую
         */
        System.out.println(list.stream().map(Artist::getName).collect(Collectors.joining()));
        System.out.println(list.stream().map(Artist::getName).collect(Collectors.joining(", ","{ "," }")));
        getCount(list).forEach((key,value)->{
            System.out.println(key + " " + value);
        });
        Stream<String> names = Stream.of("John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
        String s = names.max(Comparator.comparing(String::length)).get();
        System.out.println(s);
        Stream<String> names_1 = Stream.of("John", "Paul", "George", "John", "Paul", "John");
        Map<String, Long> collect = names_1.collect(Collectors.groupingBy(str->str,Collectors.counting()));

        collect.forEach((key,value)->{
            System.out.println(key + " " + value);
        });
        System.out.println(list.stream().flatMap(str -> Stream.of(str.toString())).collect(Collectors.joining(" , ","{","}")));

    }
}
