package com.codigo.recplants.Interfaces;

import com.codigo.recplants.clases.Enfermedad;
import com.codigo.recplants.clases.Historialgeneral;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Servicios {
    @GET("API/enfermedades/")
    Call<List<Enfermedad>> ObtenerEnfermedades();

    @GET("API/historialgeneral/")
    Call<List<Historialgeneral>> obtenerHistorial();

    @GET("API/historial/{id}")
    Call<List<Historialgeneral>> obtenerHistorialID(@Path("id") int id);
}
