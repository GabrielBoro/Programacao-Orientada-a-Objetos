package br.main;

import br.menus.Menus;
import br.passageiro.Passageiro;

public class Main {
	public static void main(String[] args) {

		Passageiro passageiro[] = new Passageiro[7];
		Passageiro listaDeEspera[] = new Passageiro[15];
		Menus r = new Menus();
		int resp = 0;
		int ind = 0;
		int indEspera = 0;
		int passMai = 0;

		do {
			if (passageiro.length > listaDeEspera.length) {
				passMai = passageiro.length;
			} else {
				passMai = listaDeEspera.length;
			}

			resp = r.telaPrincipal(ind);

			switch (resp) {
			case 1:
				r.tela1(ind, passageiro, listaDeEspera, indEspera);
				if (ind < passageiro.length) {
					ind++;
				} else if (listaDeEspera != null) {
					indEspera++;
				}

				break;

			case 2:
				r.pesquisarVaga(passageiro, listaDeEspera, passMai);
				break;

			case 3:
				r.cancelarVaga(passageiro, listaDeEspera, passMai);
				if (indEspera > 0) {
					if (listaDeEspera[indEspera - 1] == null) {
						indEspera--;
					}
				} else {
					ind--;
				}
				break;

			case 4:
				r.visualizarVagas(passageiro, listaDeEspera);
				break;

			}
		} while (resp != 5);
	}

}
