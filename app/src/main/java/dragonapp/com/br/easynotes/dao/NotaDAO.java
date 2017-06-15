package dragonapp.com.br.easynotes.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murilo Henrique on 31/05/2017.
 */

public class NotaDAO extends GenericDAO implements DAO<Nota> {

    private SQLiteDatabase dataBase;

    public NotaDAO(Context context){
        super(context);
        dataBase = getWritableDatabase();

    }

    @Override
    public boolean salvar(Nota nota) {
        dataBase.execSQL("INSERT INTO nota(nome, descricao)" +
                        " VALUES(?,?)",
                new Object[] {nota.getNome(), nota.getDescricao()});
        return false;
    }

    @Override
    public List<Nota> listar() {
        Cursor cursor = dataBase.rawQuery("SELECT * FROM nota", null);
        List<Nota> notas = new ArrayList<>();
        if(cursor!=null){
            if(cursor.getCount()>0){
                int idxCodigo = cursor.getColumnIndex("idnota");
                int idxNome = cursor.getColumnIndex("nome");
                int idxDescricao = cursor.getColumnIndex("descricao");
                cursor.moveToFirst();
                do{
                    Nota nota = new Nota();
                    nota.setId(cursor.getInt(idxCodigo));
                    nota.setNome(cursor.getString(idxNome));
                    nota.setDescricao(cursor.getString(idxDescricao));

                    notas.add(nota);

                }while(cursor.moveToNext());
            }
        }
        return notas;
    }

    @Override
    public boolean deletar(int id) {
        dataBase.execSQL("DELETE FROM nota WHERE idnota=?",
                new Object[]{id});
        return false;
    }

    @Override
    public boolean atualizar(Nota nota) {
        dataBase.execSQL("UPDATE nota SET nome=?, descricao=?" +
                        " WHERE idnota=?",
                new Object[]{nota.getNome(), nota.getDescricao(), nota.getId()});
        return false;
    }
}
