package exception;

public class SIException extends java.lang.Exception{
		private String usuario;
		
		public SIException(String numero) {
			super("seguidor e seguindo iguais");
			this.usuario = usuario;
		}
		
		public String getUsuario() {
			return this.usuario;
		}
}