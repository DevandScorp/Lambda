public class Music {
    private String artist;
    private String name;
    private String country;
    private int count;

    @Override
    public String toString() {
        return "Music{" +
                "artist='" + artist + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", count=" + count +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Music(String artist, String name, String country, int count) {
        this.artist = artist;
        this.name = name;
        this.country = country;
        this.count = count;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Music(String artist, String name, String country) {
        this.artist = artist;
        this.name = name;
        this.country = country;
    }
}
