package moreno.cazalilla.jesusmaria.models;

public class PokemonResponse {
    private String name;

    private Types types;
    private Sprites sprites;
    private String id;
    private String weight;
    private String height;

    public String getId() {
        return id;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public Types getTypes() {
        return types;
    }


    public String getName() {
        return name;
    }


    public Sprites getSprites() {
        return sprites;
    }

}

