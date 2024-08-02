package br.com.gfc.projeto.dto;

public class AcessDTO {
	
	private String token;
	//TODO implementar retornar usuario e liberacoes(authorities)
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AcessDTO(String token) {
		super();
		this.token = token;
	}
	
	
		
	

}
