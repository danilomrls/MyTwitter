package repositorio;

import java.util.Vector;
import perfil.Perfil;
import exception.*;
import perfil.PessoaFisica;

public class VectorPerfis implements IRepositorioUsuario{
	private Vector<Perfil> perfis;

	public VectorPerfis(){
		this.perfis = new Vector<Perfil>();
	}

        @Override
	public void cadastrar(Perfil usuario) throws UJCException{
		if(buscar(usuario.getUsuario()) == null){
			perfis.add(usuario);
		}else{
			throw new UJCException(usuario); //exceção UJCException
		}
	}

        @Override
	public Perfil buscar(String usuario){
		for(int i = 0;i < perfis.size();i++){
			if(perfis.get(i).getUsuario() == usuario){
				return perfis.get(i);
			}
		}
		return null;
	}

        @Override
	public void atualizar(Perfil usuario) throws UNCException{
		if(buscar(usuario.getUsuario()) != null){
			Perfil perfilDesatualizado = buscar(usuario.getUsuario());
                        if(perfilDesatualizado instanceof PessoaFisica) {
                            ((PessoaFisica) perfilDesatualizado).setCpf(((PessoaFisica) usuario).getCpf());
                        }
			//perfis.remove(perfilDesatualizado);
			//perfis.add(usuario);
		}else{
			throw new UNCException(usuario);
		}
	}
	
}