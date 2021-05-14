package com.dgr.tanahora.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dgr.tanahora.R;
import com.dgr.tanahora.helper.ConfiguracaoFirebase;
import com.dgr.tanahora.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmailLogin, editSenhaLogin;
    private TextView textCadastrar;
    private Button buttonLogin;

    private FirebaseAuth autenticacao;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        verificarUsuarioLogado();
        inicializarComponentes();

        //Evento de clique em login
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoEmail = editEmailLogin.getText().toString();
                String textoSenha = editSenhaLogin.getText().toString();

                if (!textoEmail.isEmpty()) {
                    if (!textoSenha.isEmpty()) {

                        usuario = new Usuario();
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);
                        validarLogin(usuario);

                    } else {
                        Toast.makeText(LoginActivity.this,
                                "Preencha a senha!",
                                Toast.LENGTH_SHORT);
                    }
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Preencha o e-mail!",
                            Toast.LENGTH_SHORT);
                }

            }
        });


    }

    public void verificarUsuarioLogado() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if (autenticacao.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    public void validarLogin(Usuario usuario) {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(LoginActivity.this,
                                    "Sucesso!", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "Erro ao fazer login",
                                    Toast.LENGTH_SHORT);
                        }
                    }
                });
    }

    public void abrirCadastro(View view) {

        Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(i);

    }

    public void inicializarComponentes() {
        editEmailLogin = findViewById(R.id.editEmailLogin);
        editSenhaLogin = findViewById(R.id.editSenhaLogin);
        textCadastrar = findViewById(R.id.textCadastrar);
        buttonLogin = findViewById(R.id.buttonLogin);

    }


}
