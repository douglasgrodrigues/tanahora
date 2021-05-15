package com.dgr.tanahora.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.dgr.tanahora.R;
import com.dgr.tanahora.helper.ConfiguracaoFirebase;
import com.dgr.tanahora.model.Usuario;
import com.dgr.tanahora.model.Veiculo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VeiculoActivity extends AppCompatActivity {

    private Switch tipoVeiculo;
    private EditText editMarcaVeiculo, editModeloVeiculo, editAnoVeiculo, editPlacaVeiculo;
    private TextView nomeUsuario;
    private Button buttonCadastrarVeiculo;

    private Veiculo veiculo;
    private Usuario usuario;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDataBase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiculo);

        inicializarComponentes();

        //configura nome de exibição do usuario
        nomeUsuario.setText(autenticacao.getCurrentUser().getDisplayName());


    }


    public void cadastrarVeiculo(View view) {

        if (validarCadastroVeiculo()) {

            DatabaseReference veiculos = referencia.child("veiculos");
            String idUsuario = autenticacao.getUid();

            veiculo = new Veiculo();
            veiculo.setMarca(editMarcaVeiculo.getText().toString());
            veiculo.setModelo(editModeloVeiculo.getText().toString());
            veiculo.setAno(editAnoVeiculo.getText().toString());
            veiculo.setPlaca(editPlacaVeiculo.getText().toString());
            if (!tipoVeiculo.isChecked()) {
                veiculo.setTipoVeiculo("C");
            } else {
                veiculo.setTipoVeiculo("M");
            }
            //veiculo.salvar();
            veiculos.child(idUsuario)
                    .push()
                    .setValue(veiculo);
            finish();
        }
    }

    public Boolean validarCadastroVeiculo(){

        String textoMarca = editMarcaVeiculo.getText().toString();
        String textoModelo = editModeloVeiculo.getText().toString();
        String textoAno = editAnoVeiculo.getText().toString();
        String textoPlaca = editPlacaVeiculo.getText().toString();

        if ( !textoMarca.isEmpty() ){
            if ( !textoModelo.isEmpty() ){
                if ( !textoAno.isEmpty() ){
                    if ( !textoPlaca.isEmpty() ){
                        return true;
                    }else {
                        Toast.makeText(VeiculoActivity.this,
                                "Marca não foi preenchida!",
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }else {
                    Toast.makeText(VeiculoActivity.this,
                            "Modelo não foi preenchido!",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else {
                Toast.makeText(VeiculoActivity.this,
                        "Ano não foi preenchido!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            Toast.makeText(VeiculoActivity.this,
                    "Placa não foi preenchido!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public void inicializarComponentes() {

        editMarcaVeiculo = findViewById(R.id.editMarcaVeiculo);
        editModeloVeiculo = findViewById(R.id.editModeloVeiculo);
        editAnoVeiculo = findViewById(R.id.editAnoVeiculo);
        editPlacaVeiculo = findViewById(R.id.editPlacaVeiculo);
        tipoVeiculo = findViewById(R.id.switchTipo);
        nomeUsuario = findViewById(R.id.textNomeUsuario);
    }

}
