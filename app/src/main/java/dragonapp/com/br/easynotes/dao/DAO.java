package dragonapp.com.br.easynotes.dao;

import java.util.List;

/**
 * Created by Murilo Henrique on 31/05/2017.
 */

public interface DAO<T> {

    public boolean salvar(T t);
    public List<T> listar();
    public boolean deletar(int id);
    public boolean atualizar(T t);

}
