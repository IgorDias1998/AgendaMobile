package com.example.agendacontatos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button btnCadastrar, btnApagar, btnConsultar;
    private EditText edNome, edCelular, edEmail;
    private TextView txtId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnApagar = (Button) findViewById(R.id.btnApagar);
        btnConsultar = (Button) findViewById(R.id.btnConsultar);

        edNome = (EditText) findViewById(R.id.edNome);
        edCelular = (EditText) findViewById(R.id.edCelular);
        edEmail = (EditText) findViewById(R.id.edEmail);
        txtId = (TextView) findViewById(R.id.txtId);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contato contato;
                contato = new Contato();
                contato.setNome(edNome.getText().toString());
                contato.setCelular(edCelular.getText().toString());
                contato.setEmail(edEmail.getText().toString());

                ContatoDAO dao;
                dao = new ContatoDAO(MainActivity.this);
                dao.salvarContato(contato);
                Toast.makeText(MainActivity.this, "Contato salvo com sucesso...", Toast.LENGTH_SHORT).show();
                limparCampos();
            }
        });

        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContatoDAO dao;
                dao = new ContatoDAO(MainActivity.this);
                dao.excluirContato(Integer.parseInt(txtId.getText().toString()));
                Toast.makeText(MainActivity.this, "Contato excluído com sucesso...", Toast.LENGTH_SHORT).show();
                limparCampos();
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contato contato;
                contato = new Contato();
                ContatoDAO dao;
                dao = new ContatoDAO(MainActivity.this);
                contato = dao.consultarContatoPorNome(edNome.getText().toString());
                if (contato != null) {
                    txtId.setText(String.valueOf(contato.getId()));
                    edNome.setText(contato.getNome());
                    edCelular.setText(contato.getCelular());
                    edEmail.setText(contato.getEmail());
                } else {
                    Toast.makeText(MainActivity.this, "Contato não cadastrado", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void limparCampos() {
        txtId.setText("");
        edNome.setText("");
        edCelular.setText("");
        edEmail.setText("");
    }
}