package br.main;


import br.metodos.Metodos;
import br.metodos.OpcaoException;
import br.pacientes.Paciente;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        List<Paciente> filaPaciente = new LinkedList<>();
        List<Paciente> filaRegistro = new LinkedList<>();
        String sintomas[] = new String[]{"Febre", "Tosse seca", "Cansaço", "Falta de ar"};
        Metodos m = new Metodos();
        int resp = 0;
        int qtdMaxInternamentos = 0;

        qtdMaxInternamentos = m.setMaxInternamentos();

        do {
            try {
                resp = m.telaPrincipal();
                if (resp != 1 && resp != 5 && filaRegistro.isEmpty()) {
                    throw new OpcaoException();
                }

                switch (resp) {
                    case 1:
                        m.registrarPaciente(sintomas, filaPaciente, filaRegistro, qtdMaxInternamentos);
                        break;

                    case 2:
                        m.pesquisarPaciente(filaRegistro);
                        break;

                    case 3:
                        m.liberarPaciente(filaPaciente, filaRegistro);
                        break;

                    case 4:
                        m.listarPacientes(filaPaciente, filaRegistro);
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "A opção deve ser um número");
            } catch (OpcaoException e){
                JOptionPane.showMessageDialog(null, "Nenhum paciente registrado");
            }
        } while (resp != 5);
    }

}
