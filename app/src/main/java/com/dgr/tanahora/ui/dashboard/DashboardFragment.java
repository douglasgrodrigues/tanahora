package com.dgr.tanahora.ui.dashboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.dgr.tanahora.R;
import com.dgr.tanahora.activity.LoginActivity;
import com.dgr.tanahora.activity.TrocaActivity;
import com.dgr.tanahora.helper.ConfiguracaoFirebase;
import com.dgr.tanahora.helper.UsuarioFirebase;
import com.dgr.tanahora.model.Troca;
import com.dgr.tanahora.model.Usuario;
import com.dgr.tanahora.model.Veiculo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private TextView campoPlacaExibicao;
    private EditText editDataTroca, editTipoOleo, editKmTroca, editProximaTroca;
    private Button buttonRegistrarTroca;

    private Veiculo veiculo;
    private Usuario usuario;
    private Troca troca;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDataBase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //Inicializando componentes
        editDataTroca = view.findViewById(R.id.editDataTroca);
        editTipoOleo = view.findViewById(R.id.editTipoOleo);
        editKmTroca = view.findViewById(R.id.editKmTroca);
        editProximaTroca = view.findViewById(R.id.editProximaTroca);
        buttonRegistrarTroca = view.findViewById(R.id.buttonRegistrarTroca);

        //configurações iniciais
        usuario = UsuarioFirebase.getDadosUsuarioLogado();
        firebaseRef = ConfiguracaoFirebase.getFirebaseDataBase();

        campoPlacaExibicao = view.findViewById(R.id.placaExibicao);
        veiculo = new Veiculo();


      //  String placa = firebaseRef.child("usuarios")
      //          .child(autenticacao.getCurrentUser().getUid())
       //         .child(troca.getId())

     //           .getKey();

        //campoPlacaExibicao.setText(autenticacao.getCurrentUser().getDisplayName());


        buttonRegistrarTroca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), TrocaActivity.class);
                startActivity(i);

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
        });


        return view;
    }







}