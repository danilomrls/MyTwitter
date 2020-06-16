package MyTwitter;

import java.util.Vector;
import tweet.Tweet;
import repositorio.IRepositorioUsuario;
import perfil.*;
import exception.*;

public class MyTwitter implements ITwitter {
	private IRepositorioUsuario repositorio;

	public MyTwitter(IRepositorioUsuario repositorio) {
		this.repositorio = repositorio;
	}

        @Override
	public void criarPerfil(Perfil usuario) throws PEException {
		try {
			repositorio.cadastrar(usuario);
		} catch (UJCException ujce) {
			throw new PEException(usuario.getUsuario());
		}
	}

        @Override
	public void cancelarPerfil(String usuario) throws PIException, PDException {
		Perfil perfil = repositorio.buscar(usuario);
                if( perfil!=null ) {
			if (perfil.isAtivo()) {
				perfil.setAtivo(false);
			} else {
				throw new PDException(usuario);
			}
		} else {
			throw new PIException(usuario);
		}
	}

        @Override
	public void tweetar(String usuario, String mensagem) throws PIException, MFPException {
		if (repositorio.buscar(usuario) != null) {
			Perfil perfil = repositorio.buscar(usuario);
			if (mensagem.length() >= 1 && mensagem.length() <= 140) {
				Tweet tweet = new Tweet();
				tweet.setUsuario(usuario);
				tweet.setMensagem(mensagem);
				perfil.addTweet(tweet);
				Vector<Perfil> seguidores = perfil.getSeguidores();
				for (int i = 0; i < seguidores.size(); i++) {
					if (seguidores.get(i).isAtivo()) {
						seguidores.get(i).addTweet(tweet);
					}
				}
			} else {
				throw new MFPException(mensagem);
			}
                        System.out.println(usuario + " Tweetou:");
                        System.out.println(mensagem + "\n");
		} else {
			throw new PIException(usuario);
		}
	}

        @Override
	public Vector<Tweet> timeline(String usuario) throws PIException, PDException {
		if (repositorio.buscar(usuario) != null) {
			Perfil perfil = repositorio.buscar(usuario);
			if (perfil.isAtivo()) {
				return perfil.getTimeline();
                                                                
			} else {
				throw new PDException(usuario);
			}
		}else {
			throw new PIException(usuario);
		}                
	}                
                
        @Override
	public Vector<Tweet> tweets(String usuario) throws PIException, PDException {
		if (repositorio.buscar(usuario) != null) {
			Perfil perfil = repositorio.buscar(usuario);
			if (perfil.isAtivo()) {
				Vector<Tweet> tweets = new Vector<Tweet>();
				Vector<Tweet> timeline = new Vector<Tweet>();
				timeline = timeline(usuario); // usa o metodo da propria classe pra obter a timeline do usuario
				for (int i = 0; i < timeline.size(); i++) {
					if (timeline.get(i).getUsuario() == usuario) {// timeline � um vector de tweet
						tweets.add(timeline.get(i)); // e a class Tweet tem um getUsuario
					}
				}
				return tweets;
			} else {
				throw new PDException(usuario);
			}
		} else {
			throw new PIException(usuario);
		}
	}

        @Override
	public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException {
		if (repositorio.buscar(seguido) != null && repositorio.buscar(seguidor) != null) {
			Perfil perfilSeguido = repositorio.buscar(seguido);
			Perfil perfilSeguidor = repositorio.buscar(seguidor);
			if (perfilSeguido.isAtivo() || perfilSeguidor.isAtivo()) {
				if (!seguido.equals(seguidor)) {
					perfilSeguido.addSeguidor(perfilSeguidor);
					perfilSeguidor.addSeguido(perfilSeguido);
				} else {
					throw new SIException(seguidor);
				}
			} else {
				throw new PDException(seguidor);
			}
		} else {
			throw new PIException(seguidor);
		}
                System.out.println(seguidor +" seguiu "+ seguido + "\n");
	}

        @Override
	public int numeroSeguidores(String usuario) throws PIException, PDException {
		if (repositorio.buscar(usuario) != null) {
			Perfil perfil = repositorio.buscar(usuario);
			if (perfil.isAtivo()) {
				Vector<Perfil> seguidores = perfil.getSeguidores();
				int quantidadeSeguidoresAtivo = 0;
				for (int i = 0; i < seguidores.size(); i++) {
					if (seguidores.get(i).isAtivo()) {
						quantidadeSeguidoresAtivo++;
					}
				}
				return quantidadeSeguidoresAtivo;
			} else {
				throw new PDException(usuario);
			}
		} else {
			throw new PIException(usuario);
		}
	}

        @Override
	public Vector<Perfil> seguidores(String usuario) throws PIException, PDException {
		if (repositorio.buscar(usuario) != null) {
			Perfil perfil = repositorio.buscar(usuario);
			if (perfil.isAtivo()) {
				Vector<Perfil> seguidores = perfil.getSeguidores();
				Vector<Perfil> seguidoresAtivo = new Vector<Perfil>();
				for (int i = 0; i < seguidores.size(); i++) {
					if (seguidores.get(i).isAtivo()) {
						seguidoresAtivo.add(seguidores.get(i));
					}
				}
				return seguidoresAtivo;
			} else {
				throw new PDException(usuario);
			}
		} else {
			throw new PIException(usuario);
		}
                               
	}

        @Override
	public Vector<Perfil> seguidos(String usuario) throws PIException, PDException {
		if (repositorio.buscar(usuario) != null) {
			Perfil perfil = repositorio.buscar(usuario);
			if (perfil.isAtivo()) {
				Vector<Perfil> seguidos = perfil.getSeguidos();
				Vector<Perfil> seguidosAtivos = new Vector<Perfil>();
				for (int i = 0; i < seguidos.size(); i++) {
					if (seguidos.get(i).isAtivo()) {
						seguidosAtivos.add(seguidos.get(i));
					}
				}
				return seguidosAtivos;
			} else {
				throw new PDException(usuario);
			}
		} else {
			throw new PIException(usuario);
		}
	}
}