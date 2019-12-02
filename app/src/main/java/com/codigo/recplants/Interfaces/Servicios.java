package com.codigo.recplants.Interfaces;

import com.codigo.recplants.clases.Enfermedad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Servicios {
    @GET("API/enfermedades/")
    Call<List<Enfermedad>> ObtenerEnfermedades();
}
