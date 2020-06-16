package repositorio;

import perfil.Perfil;
import exception.*;

public interface IRepositorioUsuario{
	public void cadastrar(Perfil usuario) throws UJCException;
	public Perfil buscar(String usuario);
	public void atualizar(Perfil usuario)throws UNCException;
}