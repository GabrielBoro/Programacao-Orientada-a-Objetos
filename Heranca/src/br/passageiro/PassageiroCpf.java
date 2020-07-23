package br.passageiro;

public class PassageiroCpf extends Passageiro {
	
	private int cpf;

	public PassageiroCpf( int cpf,String nome,int telefone) {
		super(nome,telefone);
		this.cpf = cpf;
	}
	
	
	
	public int getCpf() {
		return cpf;
	}

	public String toString() {
		return super.toString() + "\nCpf= " + cpf;
	}
	
	
	
}
