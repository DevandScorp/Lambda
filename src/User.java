import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class User implements Comparable<User> {
    private int id;
    private String name;

    public Set<Integer> getData() {
        return data;
    }

    public void setData(Set<Integer> data) {
        this.data = data;
    }

    private Set<Integer> data;
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        Random r = new Random();
        data = new HashSet<>();
        for(int i = 0;i<3;++i){
            data.add(r.nextInt(100));
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int compareTo(User o) {
        if(this.id>o.id)return -1;
        if(this.id<o.id)return 1;
        return 0;
    }
}
