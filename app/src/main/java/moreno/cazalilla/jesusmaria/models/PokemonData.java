package moreno.cazalilla.jesusmaria.models;

public class PokemonData {
    private String name;
    private String order;
    private String weight;
    private String height;
    private PokemonResponse apiDetails;

    public String getWeight() {
        return weight;
    }

    public String getOrder() {
        return order;
    }

    public String getHeight() {
        return height;
    }



    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokemonResponse getApiDetails() {
        return apiDetails;
    }

    public void setApiDetails(PokemonResponse apiDetails) {
        this.apiDetails = apiDetails;
    }
}
