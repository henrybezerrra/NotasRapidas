package dragonapp.com.br.easynotes.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import dragonapp.com.br.easynotes.R;
import dragonapp.com.br.easynotes.dao.Nota;
import dragonapp.com.br.easynotes.dao.NotaDAO;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView lista;
    private Button buttonNovaNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNovaNota = (Button) findViewById(R.id.btNovaNota);
        buttonNovaNota.setOnClickListener(this);

        lista = (ListView) findViewById(R.id.lvLista);
        atualizarListaNota();
        lista.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonNovaNota){
            Intent i = new Intent(this,CadastrarNotaActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Nota nota = (Nota) parent.getAdapter().getItem(position);
        final NotaDAO dao = new NotaDAO(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Opções");
        builder.setMessage("O que deseja fazer?");
        builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CadastrarNotaActivity.chamaTela(MainActivity.this,nota);

            }
        });

        builder.setNegativeButton("Exclir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.deletar(nota.getId());
                Toast.makeText(MainActivity.this, "Cliente "+nota.getNome()+", excluído com sucesso!",
                        Toast.LENGTH_LONG).show();
                atualizarListaNota();
            }
        });
        AlertDialog alert;
        alert = builder.create();
        alert.show();


    }
    private void atualizarListaNota(){
        NotaDAO dao = new NotaDAO(this);
        ArrayAdapter<Nota> arrayAdapter = new ArrayAdapter<Nota>(this,
                android.R.layout.simple_list_item_1, dao.listar());

        lista.setAdapter(arrayAdapter);
    }
}
