package dragonapp.com.br.easynotes.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
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
    }
}
