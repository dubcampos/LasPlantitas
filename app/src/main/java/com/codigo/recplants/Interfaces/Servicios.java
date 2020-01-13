package com.codigo.recplants.Interfaces;

import com.codigo.recplants.clases.HistorialGeneralRegistro;
import com.codigo.recplants.clases.Historialgeneral;
import com.codigo.recplants.clases.afeccion;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Servicios {
    @GET("API/enfermedades/")
    Call<List<afeccion>> ObtenerEnfermedades();

    @GET("API/historialgeneral/")
    Call<List<Historialgeneral>> obtenerHistorial();

    @GET("API/historial/{id}")
    Call<List<Historialgeneral>> obtenerHistorialID(@Path("id") int id);

    /*@Multipart
    @FormUrlEncoded
    @POST("API/historialregistro/")
    Call<Historialgeneral>  historialRegistro(
            @Part("Usuario") int usuario,
            @Part("Cultivo") int cultiv
            //@Part MultipartBody.Part image,
    );*/

    @Multipart
    @POST("API/historialregistro/")
    Call<HistorialGeneralRegistro> historialRegistro(
            @Part MultipartBody.Part image,
            @Part("Usuario") RequestBody usuario,
            @Part("Cultivo") RequestBody cultivo);
}
