package com.aperez.aplicacionpetagram;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aperez.aplicacionpetagram.adapter.PageAdapter;
import com.aperez.aplicacionpetagram.adapter.RestApiAdapter;
import com.aperez.aplicacionpetagram.model.UsuarioResponse;
import com.aperez.aplicacionpetagram.restApi.ConstantesRestApi;
import com.aperez.aplicacionpetagram.restApi.IEndpointsApi;
import com.aperez.aplicacionpetagram.vista.fragment.PerfilFragment;
import com.aperez.aplicacionpetagram.vista.fragment.RecyclerViewFragment;
import com.aperez.recyclerviewfragments.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final String KEY_EXTRA_NAME = "name";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        Log.e("MainActivity", "onCreate");
        setUpViewPager();

        if (toolbar != null){
            setSupportActionBar(toolbar);
            toolbar.setTitle("Petagram " + ConstantesRestApi.TRANSMITTER_ACCOUNT_NAME);
        }

        context = this;

    }

    private ArrayList<Fragment> agregarFragments(){
        Log.e("MainActivity", "agregarFragments");
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_contacts);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_notification) {
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this,  new OnSuccessListener<InstanceIdResult>() {
                @Override
                public void onSuccess(InstanceIdResult instanceIdResult) {
                    String newToken = instanceIdResult.getToken();
                    enviarTokenResgistro(newToken); }});
        }
        return super.onOptionsItemSelected(item);
    }

    private void enviarTokenResgistro(String token){
        Log.d("Token", token);

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        IEndpointsApi endpoints = restApiAdapter.establecerConexionRestApi();
        Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarUsuario(token, ConstantesRestApi.ACCESS_TOKEN, ConstantesRestApi.ACCOUNT);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                if(usuarioResponse != null){
                    Log.d("ID_DISPOSITIVO", usuarioResponse.getIdDispositivo());
                    Log.d("ID_USUARIO_INSTAGRAM", usuarioResponse.getIdUsuarioInstagram());
                    Log.d("NAME_USUARIO_INSTAGRAM", usuarioResponse.getNameUsuarioInstagram());

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Información enviada a Realtime Database Firebase")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // FIRE ZE MISSILES!
                                    dialog.dismiss();
                                }
                            });
                    // Create the AlertDialog object and return it
                    builder.create();
                    builder.show();
                }else
                    Toast.makeText(context, "Intentar nuevamente, inconvenientes con registro.", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }
}
