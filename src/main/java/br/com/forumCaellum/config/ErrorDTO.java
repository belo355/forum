package br.com.forumCaellum.config;

public class ErrorDTO {
	private String campo; 
	private String e;
	
	public ErrorDTO(String campo, String e) {
		super();
		this.campo = campo;
		this.e = e;
	}

	public String getCampo() {
		return campo;
	}

	public String getE() {
		return e;
	}

}
