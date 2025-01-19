package moreno.cazalilla.jesusmaria.models;

public class PokemonData {

    String name;
    int indice;
    String photo;
    String type;
    int height;
    int weight;


    public PokemonData(String name, int indice, String photo, String type, int height, int weight) {
        this.name = name;
        this.indice = indice;
        this.photo = photo;
        this.type = type;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
