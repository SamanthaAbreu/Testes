package br.edu.discentes.ti.apprest.controller.utils;

public class RespostaOperaçao {
	private String mensagem;
	
	public RespostaOperaçao (String mensagem) {
		this.mensagem = mensagem;
	}
	public String getMensagem() {
		return mensagem;
	}
	@Override
	public String toString() {
		return this.mensagem;
	}
}
