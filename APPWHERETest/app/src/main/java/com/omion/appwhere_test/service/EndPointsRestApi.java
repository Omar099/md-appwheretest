package com.omion.appwhere_test.service;

import com.omion.appwhere_test.models.Login;
import com.omion.appwhere_test.models.Merchant;
import com.omion.appwhere_test.models.MerchantResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EndPointsRestApi {
    @GET(ConstantsRestAPI.KEY_GET_LOGIN)
    Call<Login> getLogin(
            @Query(ConstantsRestAPI.QUERY_EMAIL) String email,
            @Query(ConstantsRestAPI.QUERY_PASSWORD) String password
    );

    @GET(ConstantsRestAPI.KEY_GET_MERCHANTS)
    Call<MerchantResponse> getMerchants();

    @POST(ConstantsRestAPI.KEY_POST_MERCHANTS)
    Call<MerchantResponse> postMerchant(@Body Merchant merchant);
/*
    @GET(ConstantsRestAPI.KEY_GET_SESION)
    Call<ArrayList<TareasUsuario>> getTareas(
            @Query(ConstantesRestAPI.QUERY_SUCURSAL_ID) int sucursalId,
            @Query(ConstantesRestAPI.QUERY_RESPUESTA_ID) int RespuestaId
    );

    @GET(ConstantsRestAPI.KEY_GET_SESION)
    Call<ArrayList<Empleado>> getEmpleados(
            @Query(ConstantesRestAPI.QUERY_SUCURSAL_ID) int sucursalId
    );

    @POST(ConstantesRestAPI.KEY_POST_GUARDAR_TAREA)
    Call<RespuestaTareaFinal> guardarTarea(@Body RespuestaTareaFinal usuarioRespuestaFinal);

    @POST(ConstantesRestAPI.KEY_POST_GUARDAR_IMG)
    Call<Boolean> guardarImagen(@Body Foto file);

    @GET(ConstantesRestAPI.KEY_GET_SESION)
    Call<ArrayList<UsuarioAsignado>> getResponsables(
            @Query(ConstantesRestAPI.QUERY_SUCURSAL_ID) String SucursalId,
            @Query(ConstantesRestAPI.QUERY_TAREA_ID) int TareaId
    );

    @DELETE(ConstantesRestAPI.KEY_DELETE_RESPONSABLE)
    Call<RespuestaTareaFinal> deleteSesion(
            //@Path("usuarioRespuestaFinal") int responsableId,int usuarioId,int sucursalId,int tareaId
            @Query(ConstantesRestAPI.QUERY_RESPONSABLE_ID) int responsableId,
            @Query(ConstantesRestAPI.QUERY_USUARIO_ID) int usuarioId,
            @Query(ConstantesRestAPI.QUERY_SUCURSAL_ID) int sucursalId,
            @Query(ConstantesRestAPI.QUERY_TAREA_ID) int tareaId
    );*/
}
