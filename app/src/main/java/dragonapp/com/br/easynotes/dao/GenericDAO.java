package dragonapp.com.br.easynotes.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Murilo Henrique on 31/05/2017.
 */

public abstract class GenericDAO extends SQLiteOpenHelper {

    //Nomeção do banco de dados
    private static final String NOME_BANCO = "unileao_manhaNota";
    //Versão
    private static final int VERSAO = 3;
    //Linhas de codigo SQL, com nome da tabela sendo nota
    /*se a table nao existir, irar criar a tabela nota onde tera os campos idnota, nome e descricao.
    Não é permitido caracteres especiais como ç e ã.
    abaixo o codigo SQL
    CREATE TABLE IF NOT EXISTS nota
    (idnota INTEGER PRIMARY KEY AUTOINCREMENT,
    nome VARCHAR(45) NOT NULL,
    descricao VARCHAR(45) NOT NULL);
     */
    private String sqlTabelaNota = "CREATE TABLE IF NOT EXISTS nota(" +
            "idnota INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome VARCHAR(45) NOT NULL," +
            "descricao VARCHAR(45) NOT NULL" +
            ");";


    public GenericDAO(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlTabelaNota);
    }

    //metodo de atualização da versão BD, entretando, sao esta sendo utilizado
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
