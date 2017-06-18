package dragonapp.com.br.easynotes.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dragonapp.com.br.easynotes.R;
import dragonapp.com.br.easynotes.dao.Nota;
import dragonapp.com.br.easynotes.dao.NotaDAO;

public class CadastrarNotaActivity extends AppCompatActivity implements View.OnClickListener {

    private static Nota nota=null;

    private Button buttonSalvarNota;
    private Button buttonTelaPrincipal;

    public static void chamaTela(Context context, Nota nota) {
        CadastrarNotaActivity.nota = nota;
        Intent intent = new Intent(context, CadastrarNotaActivity.class);
        context.startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_nota);

        final EditText edtNomedaNota = (EditText) findViewById(R.id.edtNome);
        final EditText edtDescricaodaNota = (EditText) findViewById(R.id.edtDescricao);
        buttonSalvarNota = (Button) findViewById(R.id.btSalvar);
        buttonTelaPrincipal = (Button) findViewById(R.id.btTelaPrincipal);
        buttonTelaPrincipal.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Nova Nota"); // Titulo da tela

        //Sem este If, o metodo editar nao irar funcionar pois ele ira preencher os campos
        if (nota != null) {
            edtNomedaNota.setText(nota.getNome());
            edtDescricaodaNota.setText(nota.getDescricao());
        }


        buttonSalvarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Se os campos não porem preenchidos, a nota não ira ser salva
                if(edtNomedaNota.getText().toString().isEmpty()||
                        edtDescricaodaNota.getText().toString().isEmpty()){
                    Toast.makeText(CadastrarNotaActivity.this, "Preencha todos os campos!",
                            Toast.LENGTH_SHORT).show();
                }
                // Se forem preenchidos, ira salvar ou editar
                else {
                    NotaDAO notaDAO = new NotaDAO(CadastrarNotaActivity.this);
                    if(nota==null) {
                        nota = new Nota();
                    }
                    nota.setNome(edtNomedaNota.getText().toString());
                    nota.setDescricao(edtDescricaodaNota.getText().toString());
                    if (nota.getId()==0) {
                        notaDAO.salvar(nota);
                    }else {
                        notaDAO.atualizar(nota);
                    }
                    Toast.makeText(CadastrarNotaActivity.this, "Nota Salva!",
                            Toast.LENGTH_SHORT).show();

                    //limpa os campos
                    edtNomedaNota.setText("");
                    edtDescricaodaNota.setText("");
                    CadastrarNotaActivity.nota = null;

                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_bar,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                startActivity(new Intent(this, PrincipalActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            case R.id.iAjuda:
                startActivity(new Intent(this, AjudaActivity.class));
                finishAffinity();
                break;
            case R.id.iInfo:
                startActivity(new Intent(this, InfoActivity.class));
                finishAffinity();
                break;
            default:break;
        }
        return true;
    }



    //Voltar pra a tela principal
    public void onClick(View v) {
        if (v == buttonTelaPrincipal){
            //Limpar os campos
            CadastrarNotaActivity.nota = null;
            Intent i = new Intent(this,PrincipalActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed(){ //Botão BACK padrão do android
        startActivity(new Intent(this, PrincipalActivity.class)); //O efeito ao ser pressionado do botão (no caso abre a activity)
        finishAffinity(); //Método para matar a activity e não deixa-lá indexada na pilhagem
        return;
    }
}
