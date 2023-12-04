package pe.usat.moviles.rapidisimoapp.retrofit;

import pe.usat.moviles.rapidisimoapp.response.DetalleSolicitudResponse;
import pe.usat.moviles.rapidisimoapp.response.GenericoResponse;
import pe.usat.moviles.rapidisimoapp.response.SolicitudListadoActivasResponse;
import pe.usat.moviles.rapidisimoapp.response.SolicitudListadoResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    /*
    @FormUrlEncoded
    @POST("/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("clave") String clave);

    @GET("/ciudad/listar")
    Call<CiudadListadoResponse> listarCiudad();

    @FormUrlEncoded
    @POST("/cliente/insertar")
    Call<ClienteInsertarReponse> insertarCliente(@Field("nombre") String nombre, @Field("direccion") String direccion, @Field("email") String email, @Field("ciudad_id") String ciudadId);


    */
    @FormUrlEncoded
    @POST("/solicitud/conductor")
    Call<SolicitudListadoActivasResponse> listadoSolicitudesAtendidas(@Field("conductor_id") int conductorId);

    @GET("/solicitud/listar/0")
    Call<SolicitudListadoResponse> listadoSolicitudes();

    @GET("/solicitud/detalle/{solicitudId}")
    Call<DetalleSolicitudResponse> obtenerDetalleSolicitud(@Path("solicitudId") int solicitudId);

    @FormUrlEncoded
    @POST("/vehiculo/estado")
    Call<GenericoResponse> registrarNuevoEstado(
            @Field("solicitud_servicio_id") int solicitud_servicio_id,
            @Field("vehiculo_id") int vehiculo_id,
            @Field("conductor_id") int conductor_id,
            @Field("nombre_estado") String nombre_estado,
            @Field("latitud") double latitud,
            @Field("longitud") double longitud,
            @Field("observacion") String observacion
    );


}
