package br.passageiro;

public class PassageiroCnpj extends Passageiro {

	private int cnpj;

	public PassageiroCnpj(int cnpj, String nome, int telefone) {
		super(nome, telefone);
		this.cnpj = cnpj;
	}

	public int getCnpj() {
		return cnpj;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nCnpj= " + cnpj;
	}

}
