package moreno.cazalilla.jesusmaria.models;



public class PokedexData {
   String name;
   String url;

    public PokedexData(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
