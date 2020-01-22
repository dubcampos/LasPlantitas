package com.codigo.recplants.Interfaces;

import com.codigo.recplants.clases.HistorialGeneralRegistro;
import com.codigo.recplants.clases.Historialgeneral;
import com.codigo.recplants.clases.afeccion;
import com.codigo.recplants.clases.respuesta;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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

    @Multipart
    @POST("API/historialregistro/")
    Call<HistorialGeneralRegistro> historialRegistro(
            @Part MultipartBody.Part image,
            @Part("usuario") RequestBody usuario,
            @Part("Cultivo") RequestBody cultivo);


    @Multipart
    @POST("API/RegistrarDiagnosticoCultivo/")
    Call<respuesta> uploadImage(
            @Part("usuario") RequestBody usuario,
            @Part("enfermedad") RequestBody enfermedad,
            @Part("cultivo") RequestBody cultivo,
            @Part("image\"; filename=\"myfile.jpg\" ") RequestBody file
    );


}
