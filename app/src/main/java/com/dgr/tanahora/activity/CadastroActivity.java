package com.dgr.tanahora.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dgr.tanahora.R;
import com.dgr.tanahora.helper.ConfiguracaoFirebase;
import com.dgr.tanahora.helper.UsuarioFirebase;
import com.dgr.tanahora.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private EditText editNomeCadastro, editEmailCadastro, editSenhaCadastro;
    private Button buttonCadastro;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializarComponentes();

        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoNome = editNomeCadastro.getText().toString();
                String textoEmail = editEmailCadastro.getText().toString();
                String textoSenha = editSenhaCadastro.getText().toString();

                if (!textoNome.isEmpty()) {
                    if (!textoEmail.isEmpty()) {
                        if (!textoSenha.isEmpty()) {

                            usuario = new Usuario();
                            usuario.setNome(textoNome);
                            usuario.setEmail(textoEmail);
                            usuario.setSenha(textoSenha);
                            cadastrar(usuario);

                        } else {
                            Toast.makeText(CadastroActivity.this,
                                    "Preencha a senha!",
                                    Toast.LENGTH_SHORT);
                        }
                    } else {
                        Toast.makeText(CadastroActivity.this,
                                "Preencha o E-mail!",
                                Toast.LENGTH_SHORT);
                    }
                } else {
                    Toast.makeText(CadastroActivity.this,
                            "Preencha o nome!",
                            Toast.LENGTH_SHORT);
                }
            }
        });
    }


    public void cadastrar(final Usuario usuario) {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(
                this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            try {

                                //Salvar dados de  usuario no fcirebase
                                String idUsuario = task.getResult().getUser().getUid(); //acessando id usuario no firebas
                                usuario.setId(idUsuario);
                                usuario.salvar();

                                //salvar os dados no profile do firebase
                                UsuarioFirebase.atualizarNomeUsuario(usuario.getNome());


                                Toast.makeText(CadastroActivity.this,
                                        "Cadastrado com sucesso!",
                                        Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            } catch (Exception e) {

                            }
                        } else {

                            String erroExcessao = "";
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                erroExcessao = "Digite uma senha mais forte";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                erroExcessao = "Digite um e-mail valido";
                            } catch (FirebaseAuthUserCollisionException e) {
                                erroExcessao = "Esta conta jรก existe";
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(CadastroActivity.this,
                                    "Erro" + erroExcessao,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    public void inicializarComponentes() {

        editNomeCadastro = findViewById(R.id.editNomeCadastro);
        editEmailCadastro = findViewById(R.id.editEmailCadastro);
        editSenhaCadastro = findViewById(R.id.editSenhaCadastro);
        buttonCadastro = findViewById(R.id.buttonCadastro);
    }



}
