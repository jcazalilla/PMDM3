package moreno.cazalilla.jesusmaria.models;

public class PokemonData {
    private String name;

    private PokemonResponse apiDetails;



    // Getters y setters
    public String getName() {
        return name;
    }


    public PokemonResponse getApiDetails() {
        return apiDetails;
    }

    public void setApiDetails(PokemonResponse apiPokemon) {
        this.apiDetails=apiDetails;
    }
}
