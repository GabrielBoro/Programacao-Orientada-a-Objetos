package br.passageiro;

public abstract class Passageiro extends Object {
	
	protected String nome;
	protected int telefone;
	
	public String getNome() {
		return nome;
	}
	public int getTelefone() {
		return telefone;
	}
	
	public Passageiro(String nome, int telefone) {
		super();
		this.nome = nome;
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Nome= " + nome + "\nTelefone= " + telefone;
	}
	
	
	
	
	
}
