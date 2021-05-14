package com.dgr.tanahora.helper;


import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dgr.tanahora.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.auth.User;

public class UsuarioFirebase {

    public static FirebaseUser getUsuarioAtual() {

        FirebaseAuth usuario = ConfiguracaoFirebase.getFirebaseAutenticacao();
        return usuario.getCurrentUser();
    }

    public static String getIdentificadorUsuario(){
        return getUsuarioAtual().getUid();
    }


    public static void atualizarNomeUsuario(String nome) {

        try {

            //Usuyario logado no app
            FirebaseUser usuarioLogado = getUsuarioAtual();

            //configurar o objeto para alteracao do perfil
            UserProfileChangeRequest profile = new UserProfileChangeRequest
                    .Builder()
                    .setDisplayName(nome)
                    .build();
            usuarioLogado.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                        Log.d("Perfil", "Erro ao atualziar o nome de perfil");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean atualizarFotoUsuario(Uri url) {

        try {

            FirebaseUser usuarioLogado = getUsuarioAtual();
            UserProfileChangeRequest profile = new UserProfileChangeRequest
                    .Builder()
                    .setPhotoUri(url)
                    .build();
            usuarioLogado.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                        Log.d("Perfil", "Erro ao atualizar foto de perfil.");
                    }
                }
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Usuario getDadosUsuarioLogado() {
        FirebaseUser firebaseUser = getUsuarioAtual();

        Usuario usuario = new Usuario();
        usuario.setEmail(firebaseUser.getEmail());
        usuario.setNome(firebaseUser.getDisplayName());
        usuario.setId(firebaseUser.getUid());

        return usuario;
    }

}
