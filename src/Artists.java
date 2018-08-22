import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Artists {
    private List<Artist> artists;
    private static final Map<Integer,Long> cache;
    static{
        cache = new HashMap<>();
        cache.put(0,0L);
        cache.put(1,1L);
    }
    public Artists(List<Artist> artists) {
        this.artists = artists;
    }
    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            return Optional.empty();
        }
        return Optional.ofNullable(artists.get(index));
    }
    private void indexException(int index) {
        throw new IllegalArgumentException(index +" doesn't correspond to an Artist");
    }
    public String getArtistName(int index) {
            Optional<Artist> artist = getArtist(index);
            if(artist.isPresent()){
                return artist.get().getName();
            }
            return "unknown";
    }
    /**Разделение данных.Задавая условие,ты получишь мапу с Boolean в качестве ключа и значениями,найденными согласно условию*/
    public static Map<Boolean,List<Artist>> getPartition(List<Artist> artists){
        return artists.stream().collect(Collectors.partitioningBy(Artist::isSolo));
    }

    /**
     * Аналогично partitioningBy,только здесь уже нужно
     */
    public static Map<Integer,List<Artist>> getById(List<Artist> artists){
        return artists.stream().collect(Collectors.groupingBy(Artist::getId));
    }

    /**
     * Фибонакччи.Это не рекурсия,поэтому я бы запомнил.
     */
    public static long fibonacci(int x){
        return cache.computeIfAbsent(x,n -> fibonacci(n-1)+fibonacci(n-2));
    }
    public static void main(String[] args) {
        List<Artist> list = new ArrayList<>();
        list.add(new Artist("Artem",1,true));
        list.add(new Artist("Max",2,false));
        list.add(new Artist("Kolya",3,true));
        list.add(new Artist("Katya",4,false));
        list.add(new Artist("Nikita",5,true));
        Artists artists = new Artists(list);
        System.out.println(artists.getArtistName(5));
        List<String> collect = list.stream().map(Artist::getName).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println();
        list.forEach(System.out::println);
        /**
         * Преобразуем в map
         */
        Map<Integer, String> collect1 = list.stream().collect(Collectors.toMap(Artist::getId, Artist::getName));
        collect1.forEach((key,value)->{
            System.out.println(key + " " + value);
        });
        Map<Boolean, List<Artist>> partition = getPartition(list);
        partition.forEach((key,value)->{
            System.out.println(key + " " + value);
        });
        Map<Integer, List<Artist>> byId = getById(list);
        byId.forEach((key,value)->{
            System.out.println(key + " " + value);
        });
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,0);
        map.put(1,1);
        fibonacci(90);
        cache.forEach((key,value)->{
            System.out.println(key + " " + value);
        });
    }
}
