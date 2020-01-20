package com.aperez.aplicacionpetagram.vista.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aperez.aplicacionpetagram.MainActivity;
import com.aperez.aplicacionpetagram.adapter.RestApiAdapter;
import com.aperez.aplicacionpetagram.model.UsuarioResponse;
import com.aperez.aplicacionpetagram.restApi.ConstantesRestApi;
import com.aperez.aplicacionpetagram.restApi.IEndpointsApi;
import com.aperez.recyclerviewfragments.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {
    private TextView tvNamePet;
    private ImageView imgImagePet;
    private Button btnSendTouch;
    private Activity mainAcitivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        tvNamePet = (TextView) v.findViewById(R.id.namePet);
        imgImagePet = (ImageView) v.findViewById(R.id.imgFotoPet);
        btnSendTouch = (Button) v.findViewById(R.id.btnSendTouch);

        tvNamePet.setText(ConstantesRestApi.RECEIVER_ACCOUNT_NAME);
        imgImagePet.setImageDrawable(getResources().getDrawable(ConstantesRestApi.IMAGE));
        btnSendTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toqueAnimal(view);
            }
        });

        return v;
    }

    public  void toqueAnimal(View v){
        Log.d("TOQUE_ANIMAL", "true");
        final UsuarioResponse usuarioResponse = new UsuarioResponse(ConstantesRestApi.RECEIVER_IDENTIFIER, "","", ConstantesRestApi.TRANSMITTER_ACCOUNT_NAME);
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        IEndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApi();
        Call<UsuarioResponse> usuarioResponseCall = endpointsApi.toqueAnimal(usuarioResponse.getId(), usuarioResponse.getNameUsuarioInstagram());
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse1 = response.body();
                if(usuarioResponse1 != null){
                    Log.d("ID_FIREBASE", usuarioResponse1.getId());
                    //Log.d("TOKEN_FIREBASE", usuarioResponse1.getIdDispositivo());
                    //Log.d("ID_USUARIO_FIREBASE", usuarioResponse1.getIdUsuarioInstagram());
                    Log.d("ANIMAL_FIREBASE", usuarioResponse1.getNameUsuarioInstagram());
                }else
                    Toast.makeText(getContext(), "Ir a menu y pinchar 'Recibir Notificaciones'.", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });

    }
}

