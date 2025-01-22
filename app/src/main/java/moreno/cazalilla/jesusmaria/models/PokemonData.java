package moreno.cazalilla.jesusmaria.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonData {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return "It's " + getName() + "!";
    }

}
