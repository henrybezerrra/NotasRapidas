package dragonapp.com.br.easynotes.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dragonapp.com.br.easynotes.R;
import dragonapp.com.br.easynotes.dao.Nota;
import dragonapp.com.br.easynotes.dao.NotaDAO;

public class CadastrarNotaActivity extends AppCompatActivity implements View.OnClickListener {

    private static Nota nota=null;

    private FloatingActionButton floatingActionButtonSalvarNota;
    private FloatingActionButton floatingActionButtonTelaPrincipal;

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
        floatingActionButtonSalvarNota = (FloatingActionButton) findViewById(R.id.fabSalvar);
        floatingActionButtonSalvarNota.setOnClickListener(this);
        floatingActionButtonTelaPrincipal = (FloatingActionButton) findViewById(R.id.fabTelaPrincipal);
        floatingActionButtonTelaPrincipal.setOnClickListener(this);

        //Sem este If, o metodo editar nao irar funcionar pois ele ira preencher os campos
        if (nota != null) {
            edtNomedaNota.setText(nota.getNome());
            edtDescricaodaNota.setText(nota.getDescricao());
        }


        floatingActionButtonSalvarNota.setOnClickListener(new View.OnClickListener() {
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

    //Voltar pra a tela principal
    public void onClick(View v) {
        if (v == floatingActionButtonTelaPrincipal){
            //Limpar os campos
            CadastrarNotaActivity.nota = null;
            Intent i = new Intent(this,PrincipalActivity.class);
            startActivity(i);
        }
    }
}
