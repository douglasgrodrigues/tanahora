package com.dgr.tanahora.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dgr.tanahora.R;
import com.dgr.tanahora.helper.ConfiguracaoFirebase;
import com.dgr.tanahora.model.Troca;
import com.dgr.tanahora.model.Veiculo;
import com.dgr.tanahora.ui.dashboard.DashboardFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TrocaActivity extends AppCompatActivity {

    private TextView campoPlacaExibicao;
    private EditText editDataTroca, editTipoOleo, editKmTroca, editProximaTroca;

    private Veiculo veiculo;
    private Troca troca;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDataBase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_troca);

        inicializarComponentes();

    }


    public void registrarTroca(View view) {



        //DatabaseReference trocas = referencia.child("trocas");
        DatabaseReference trocas = referencia.child("trocas");
        String idUsuario = autenticacao.getUid();

        troca = new Troca();
        troca.setDataTroca(editDataTroca.getText().toString());
        troca.setTipoOleo(editTipoOleo.getText().toString());
        troca.setKmTroca(editKmTroca.getText().toString());
        troca.setProximaTroca(editProximaTroca.getText().toString());

        trocas.child(idUsuario)
                .push()
                .setValue(troca);


    }


    public void inicializarComponentes() {

        //Inicializando componentes
        editDataTroca = findViewById(R.id.editDataTroca);
        editTipoOleo = findViewById(R.id.editTipoOleo);
        editKmTroca = findViewById(R.id.editKmTroca);
        editProximaTroca = findViewById(R.id.editProximaTroca);

    }

}
