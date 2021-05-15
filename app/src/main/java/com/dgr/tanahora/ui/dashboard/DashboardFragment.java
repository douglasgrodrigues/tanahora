package com.dgr.tanahora.ui.dashboard;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.dgr.tanahora.R;
import com.dgr.tanahora.helper.ConfiguracaoFirebase;
import com.dgr.tanahora.model.Usuario;
import com.dgr.tanahora.model.Veiculo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private TextView campoPlacaExibicao;

    private Veiculo veiculo;
    private DatabaseReference dadosVeiculo;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        /*dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        //Exibe dados da postagem
        Uri uriPostagem = Uri.parse(postagem.getCaminhoFoto());
        Glide.with(VisualizarPostagemActivity.this)
                .load(uriPostagem)
                .into(imagePostagemSelecionada);
        textDescricaoPostagem.setText(postagem.getDescricao());

         */

        //Configuerações iniciais
        campoPlacaExibicao = view.findViewById(R.id.placaExibicao);







        return view;
    }



}