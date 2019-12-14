package com.codigo.recplants.Interfaces;

import com.codigo.recplants.clases.Enfermedad;
import com.codigo.recplants.clases.Historialgeneral;
import com.codigo.recplants.clases.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Servicios {
    @GET("API/enfermedades/")
    Call<List<Enfermedad>> ObtenerEnfermedades();

    @GET("API/historialgeneral/")
    Call<List<Historialgeneral>> obtenerHistorial();

    @GET("API/historial/{id}")
    Call<List<Historialgeneral>> obtenerHistorialID(@Path("id") int id);


    @FormUrlEncoded
    @POST("API/historialregistro/")
    Call<Historialgeneral>  historialRegistro(
            @Field("Usuario") int usuario,
            @Field("Cultivo") int cultiv
            //@Field("Imagen_usuarioCultivo") String Imagen_usuarioCultivo
    );
}
