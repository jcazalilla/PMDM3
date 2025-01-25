package moreno.cazalilla.jesusmaria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//endpoint petición de consulta
public interface PokemonAPI {
    @GET("pokemon?offset=0&limit=150")
    Call<List<PokemonData>> getPokemons();
}
