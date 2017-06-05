package dragonapp.com.br.easynotes.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Murilo Henrique on 31/05/2017.
 */

public abstract class GenericDAO extends SQLiteOpenHelper {


    private static final String NOME_BANCO = "unileao_manhaNota";
    private static final int VERSAO = 3;
    private String sqlTabelaNota = "CREATE TABLE IF NOT EXISTS nota(" +
            "idnota INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome VARCHAR(45) NOT NULL," +
            "descricao VARCHAR(45) NOT NULL," +
            ");";


    public GenericDAO(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlTabelaNota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
