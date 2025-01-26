package moreno.cazalilla.jesusmaria;

import retrofit2.Call;
import retrofit2.http.GET;

//endpoint petición de consulta
public interface PokedexAPI {
    String endPoint = "pokemon?offset=0&limit=150";

    @GET(endPoint)
    Call<PokedexResponse> getPokedex();
}
