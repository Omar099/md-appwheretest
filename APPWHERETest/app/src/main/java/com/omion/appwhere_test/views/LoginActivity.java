package com.omion.appwhere_test.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.omion.appwhere_test.R;
import com.omion.appwhere_test.models.Login;
import com.omion.appwhere_test.service.EndPointsRestApi;
import com.omion.appwhere_test.service.RestApiAdapter;
import com.omion.appwhere_test.utilities.Constants;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView tvNewAccount;
    FloatingActionButton fabEnter;
    EditText etUser,etPassword;
    SharedPreferences preferences;
    Switch stRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initData();
        generateCred();
        setup();
    }

    private void validaLogin(View v) {
        String sUser = etUser.getText().toString();
        String sPass = etPassword.getText().toString();
        if(login(sUser,sPass)){
            if(stRecorder.isChecked()){
                guardarPreferencias(sUser,sPass,true);
            }else {
                guardarPreferencias("","",false);
            }
            existeUsuario(sUser,sPass,v);
        }
    }

    private void existeUsuario(String sEmail, String sClave, final View v) {
        RestApiAdapter restAPIAdapter = new RestApiAdapter();
        EndPointsRestApi puntoAccesoGetUser = restAPIAdapter.establecerConexionServidor();

        Call<Login> sesionCall = puntoAccesoGetUser.getLogin(sEmail,sClave);
        sesionCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()){
                    Login userLogin = response.body();
                    try {
                        if (userLogin != null) {
                            if (userLogin.isSuccessful()){
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                //intent.putExtra(ConstantesPutExtra.SESION_DATA, sesion);
                                startActivity(intent);
                            }else{
                                Snackbar.make(v,"Su usuario o contraseña es incorrecta",Snackbar.LENGTH_LONG).show();
                                //progressDialog.dismiss();
                            }
                        }

                    }catch (Exception e){
                        Snackbar.make(v,"Su usuario o contraseña es incorrecta",Snackbar.LENGTH_LONG).show();
                        //progressDialog.dismiss();
                    }


                }else {
                    Snackbar.make(v,"Estamos tieniendo algunos problemas, intenteló mas tarde",Snackbar.LENGTH_LONG).show();//La respuesta del servidos no es satisfactoria, pero si existe una conexión
                    //progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Snackbar.make(v,"Problemas de conexión, verifica tu conexión",Snackbar.LENGTH_LONG).show();    //No existe la conexion con la URL
                if (t instanceof IOException){
                    //progressDialog.dismiss();
                }
            }
        });
    }

    private void guardarPreferencias(String email,String password,boolean on){
        //Almacenamos los datos de las preferences
        SharedPreferences.Editor editor = preferences.edit(); //Se guarda en memoria interna
        editor.putString(Constants.PREFERENCE_EMAIL,email);
        editor.putString(Constants.PREFERENCE_PASSWORD,password);      //Almacenamos con llave y valor
        editor.putBoolean(Constants.PREFERENCE_RECORD,on);
        editor.commit();                            //Se inicia un proceso asíncrono
        editor.apply();
    }

    private void generateCred(){
        String user=getUserMailPreferences();
        String pass = getPassPreferences();
        stRecorder.setChecked(preferences.getBoolean(Constants.PREFERENCE_RECORD,false));

        if(!TextUtils.isEmpty(user)&&!TextUtils.isEmpty(pass)){
            etUser.setText(user);
            etPassword.setText(pass);
        }
    }

    private String getUserMailPreferences(){
        return preferences.getString(Constants.PREFERENCE_EMAIL,"");
    }

    private String getPassPreferences(){
        return preferences.getString(Constants.PREFERENCE_PASSWORD,"");
    }

    private void initData(){
        preferences = getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        stRecorder = findViewById(R.id.st_recorder);
        tvNewAccount = findViewById(R.id.tv_new_account);
        fabEnter = findViewById(R.id.fab_enter);
        etUser = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_address);
    }

    private void setup(){
        fabEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaLogin(v);
            }
        });
    }
    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && email.length()>1 && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPass(String pass){
        return !TextUtils.isEmpty(pass) && pass.length()>1;
    }

    private boolean login(String email,String password){
        if(!isValidEmail(email)){
            Toast.makeText(this,"Introduzca un email valido",Toast.LENGTH_SHORT).show();
            return false;
        }else if(!isValidPass(password)){
            Toast.makeText(this,"Introduzca un password valido",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
}
