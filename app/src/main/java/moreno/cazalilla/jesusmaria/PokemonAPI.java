package moreno.cazalilla.jesusmaria;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonAPI {

    //endpoint por parámetro
    @GET("pokemon/{name}")
    Call<PokemonResponse> getPokemons(@Path("name") String name);

}
