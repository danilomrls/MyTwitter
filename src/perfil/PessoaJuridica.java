package perfil;

public class PessoaJuridica extends Perfil{

	private long cnpj;

	public PessoaJuridica(String usuario){
		super(usuario);
	}

	public void setCpf(long cnpj){
		this.cnpj = cnpj;
	}

	public long getCpf(){
		return this.cnpj;
	}
}