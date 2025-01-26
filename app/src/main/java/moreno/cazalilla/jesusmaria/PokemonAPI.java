package moreno.cazalilla.jesusmaria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//endpoint petición de consulta
public interface PokemonAPI {
    String endPoint = "pokemon?offset=0&limit=150";

    @GET(endPoint)
    Call<PokemonResponse> getPokemons();
}
