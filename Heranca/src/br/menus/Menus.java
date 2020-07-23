package br.menus;

import javax.swing.JOptionPane;

import br.passageiro.Passageiro;
import br.passageiro.PassageiroCnpj;
import br.passageiro.PassageiroCpf;

public class Menus {

	public int telaPrincipal(int ind) {
		int resp = Integer.parseInt(JOptionPane.showInputDialog(
				"1.Realizar reserva.\n2.Pesquisar reserva.\n3.Cancelar reserva.\n4.Visualizar registros\n5.Sair."));

		while (resp > 5 || resp < 1) {
			resp = Integer.parseInt(JOptionPane.showInputDialog(
					"<html><b style='color:red'>Digite novamente.</b>\n1.Realizar reserva.\n2.Pesquisar reserva.\n3.Cancelar reserva.\n4.Visualizar registros\n5.Sair."));
		}
		while (ind == 0 && (resp == 2 || resp == 3 || resp == 4)) {
			resp = Integer.parseInt(JOptionPane.showInputDialog(
					"<html><b style='color:red'>Nenhum passageiro.</b>\n1.Realizar reserva.\n2.Pesquisar reserva.\n3.Cancelar reserva.\n4.Visualizar registros\n5.Sair."));

		}
		return resp;
	}
	
	public void tela1(int ind, Passageiro passageiro[], Passageiro listaDeEspera[], int indEspera) {
		char esperaResp;
		if (ind < passageiro.length) {
			realizarReserva(passageiro, ind, listaDeEspera, indEspera);

		} else if (indEspera == listaDeEspera.length) {
			JOptionPane.showMessageDialog(null, "Assentos e vagas na lista de espera esgotados.");
		} else {
			esperaResp = (JOptionPane
					.showInputDialog("Assentos esgotados, gostaria de entrar na lista de espera? (S/N)")
					.toUpperCase().charAt(0));
			while (esperaResp != 'S' && esperaResp != 'N') {
				esperaResp = (JOptionPane.showInputDialog(
						"<html><b style=color:red>Digite corretamente (S/N).</b>\nAssentos esgotados, gostaria de entrar na lista de espera? (S/N)")
						.toUpperCase().charAt(0));
			}
			if (esperaResp == 'S') {
				realizarReserva(passageiro, ind, listaDeEspera, indEspera);
			}
		}
	}

	public void realizarReserva(Passageiro passageiro[], int ind, Passageiro listaDeEspera[], int indEspera) {
		String nome = JOptionPane.showInputDialog("Digite o seu nome:");
		int telefone = Integer.parseInt(JOptionPane.showInputDialog("Digite o seu telefone:"));
		for (int x = 0; x < ind; x++) {
			if (passageiro[x].getTelefone() == telefone) {
				telefone = Integer.parseInt(JOptionPane.showInputDialog(
						"<html><b style='color:red'>Esse n�mero j� est� cadastrado.</b>\nDigite o seu telefone:"));
				x = -1;
			}
		}
		if (indEspera > 0) {
			for (int x = 0; x < indEspera; x++) {

				if (listaDeEspera[x].getTelefone() == telefone) {
					telefone = Integer.parseInt(JOptionPane.showInputDialog(
							"<html><b style='color:red'>Esse n�mero j� est� cadastrado.</b>\nDigite o seu telefone:"));
					x = -1;
				}
			}
		}

		int resp = Integer.parseInt(JOptionPane.showInputDialog("1.Pessoa F�sica.\n2.Pessoa Jur�dica"));
		while (resp != 1 && resp != 2) {
			resp = Integer.parseInt(JOptionPane.showInputDialog(
					"<html><b style='color:red'>Digite Corretamente.</b>\n1.Pessoa F�sica.\n2.Pessoa Jur�dica"));

		}
		if (resp == 1) {
			int cpf = Integer.parseInt(JOptionPane.showInputDialog("Digite o seu cpf:"));
			for (int x = 0; x < ind; x++) {
				if (passageiro[x] instanceof PassageiroCpf && ((PassageiroCpf) passageiro[x]).getCpf() == cpf) {
					cpf = Integer.parseInt(JOptionPane.showInputDialog(
							"<html><b style='color:red'>Esse cpf j� est� cadastrado.</b>\nDigite o seu cpf:"));
					x = -1;
				}
			}
			if (indEspera > 0) {
				for (int x = 0; x < indEspera; x++) {

					if (listaDeEspera[x] instanceof PassageiroCpf
							&& ((PassageiroCpf) listaDeEspera[x]).getCpf() == cpf) {
						cpf = Integer.parseInt(JOptionPane.showInputDialog(
								"<html><b style='color:red'>Esse cpf j� est� cadastrado.</b>\nDigite o seu cpf:"));
						x = -1;
					}
				}
			}
			if (ind < passageiro.length) {
				passageiro[ind] = new PassageiroCpf(cpf, nome, telefone);
			} else {
				listaDeEspera[indEspera] = new PassageiroCpf(cpf, nome, telefone);
			}
		} else {
			int cnpj = Integer.parseInt(JOptionPane.showInputDialog("Digite o seu cnpj:"));
			for (int x = 0; x < ind; x++) {
				if (passageiro[x] instanceof PassageiroCnpj && ((PassageiroCnpj) passageiro[x]).getCnpj() == cnpj) {
					cnpj = Integer.parseInt(JOptionPane.showInputDialog(
							"<html><b style='color:red'>Esse cnpj j� est� cadastrado.</b>\nDigite o seu cnpj:"));
					x = -1;
				}
			}
			if (indEspera > 0) {
				for (int x = 0; x < indEspera; x++) {

					if (listaDeEspera[x] instanceof PassageiroCnpj
							&& ((PassageiroCnpj) listaDeEspera[x]).getCnpj() == cnpj) {
						cnpj = Integer.parseInt(JOptionPane.showInputDialog(
								"<html><b style='color:red'>Esse cnpj j� est� cadastrado.</b>\nDigite o seu cnpj:"));
						x = -1;
					}
				}
			}
			if (ind < passageiro.length) {
				passageiro[ind] = new PassageiroCnpj(cnpj, nome, telefone);
			} else {
				listaDeEspera[indEspera] = new PassageiroCnpj(cnpj, nome, telefone);
			}
		}
	}

	public void pesquisarVaga(Passageiro passageiro[], Passageiro listaDeEspera[], int passMai) {
		int telefone = Integer.parseInt(JOptionPane.showInputDialog("Digite um telefone para pesquisar"));
		for (int x = 0; x < passMai; x++) {
			if (passageiro.length > x) {
				if (passageiro[x] != null) {
					if (passageiro[x].getTelefone() == telefone) {
						JOptionPane.showMessageDialog(null,
								"Passageiro no " + (x+1) + "� assento:\n" + passageiro[x]);
						x = passMai;
					}
				}
			}
			if (listaDeEspera.length > x) {
				if (listaDeEspera[x] != null) {
					if (listaDeEspera[x].getTelefone() == telefone) {
						JOptionPane.showMessageDialog(null,
								"Passageiro na " + (x + 1) + "� posi��o de espera:\n" + listaDeEspera[x]);
						x = passMai;
					}
				}
			}
			if (x == passMai - 1) {
				telefone = Integer.parseInt(JOptionPane
						.showInputDialog("<html><b style='color:red'>N�mero n�o encontrado, digite novamente:</b>"));
				x = -1;
			}
		}
	}

	public void cancelarVaga(Passageiro passageiro[], Passageiro listaDeEspera[], int passMai) {
		int telefone = Integer.parseInt(JOptionPane.showInputDialog("Digite um telefone para cancelar"));
		for (int x = 0; x < passMai; x++) {
			if (passageiro.length > x) {
				if (passageiro[x] != null && passageiro[x].getTelefone() == telefone) {
					JOptionPane.showMessageDialog(null,
							"Assento " + (x+1) + ", do telefone " + telefone + " foi cancelado");
					if (listaDeEspera[0] != null) {
						passageiro[x] = listaDeEspera[0];
						for (int y = 0; y < listaDeEspera.length; y++) {
							if (y == 0 || y != listaDeEspera.length - 1) {
								listaDeEspera[y] = listaDeEspera[y + 1];
							} else if (y == listaDeEspera.length - 1) {
								listaDeEspera[y] = null;
							}
						}
					} else {
						passageiro[x] = null;

					}
					x = passMai;
				}
			}
			if (listaDeEspera.length > x) {
				if (listaDeEspera[x] != null && listaDeEspera[x].getTelefone() == telefone) {
					JOptionPane.showMessageDialog(null, "Passageiro no " + (x + 1)
							+ "� lugar na lista de espera, do telefone " + telefone + " foi cancelado");
					for (int y = x; y < listaDeEspera.length; y++) {
						if (y == 0 || y != listaDeEspera.length - 1) {
							listaDeEspera[y] = listaDeEspera[y + 1];
						} else if (y == listaDeEspera.length - 1) {
							listaDeEspera[y] = null;
						}
					}
					x = passMai;
				}
			}
			if (x == passMai - 1) {
				telefone = Integer.parseInt(JOptionPane
						.showInputDialog("<html><b style='color:red'>N�mero n�o encontrado, digite novamente:</b>"));
				x = -1;
			}
		}
	}

	public void visualizarVagas(Passageiro passageiro[], Passageiro listaDeEspera[]) {
		for (int x = 0; x < passageiro.length; x++) {
			if (passageiro[x] != null) {
				JOptionPane.showMessageDialog(null,
						"Passageiro no " + (x + 1) + "� assento:\n" + passageiro[x]);
			}

		}
		for (int x = 0; x < listaDeEspera.length; x++) {
			if (listaDeEspera[x] != null) {
				JOptionPane.showMessageDialog(null,
						(x + 1) + "� passageiro da lista de espera:\n" + listaDeEspera[x]);
			}
		}
	}
}
