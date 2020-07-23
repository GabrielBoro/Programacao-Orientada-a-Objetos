package br.metodos;

import br.pacientes.Paciente;
import br.pacientes.PacienteFilaAtendimento;
import br.pacientes.PacienteFilaInternacao;
import br.pacientes.PacienteLiberado;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Metodos {

    public int setMaxInternamentos() {
        int qtdMaxInternamento = 0;
        while (qtdMaxInternamento == 0) {
            try {
                qtdMaxInternamento = parseInt(showInputDialog("Quantidade máxima de Internamentos"));
            } catch (NumberFormatException e) {
                showMessageDialog(null, "Digite a quantidade máxima de internamentos");
            }
        }

        return qtdMaxInternamento;
    }

    public int telaPrincipal() {
        int resp = 0;

        try {
            resp = parseInt(showInputDialog("1. Registrar paciente.\n2. Pesquisar paciente.\n3. Liberar paciente.\n4. Listar pacientes.\n5. Sair."));
            if (resp < 1 || resp > 5) {
                throw new OpcaoException();
            }
        } catch (OpcaoException e) {
            showMessageDialog(null, "Digite algum número de 1 a 5");
        }

        return resp;
    }

    public void registrarPaciente(String[] sintomas, List<Paciente> filaPaciente, List<Paciente> filaRegistro, int qtdMaxInternamento) {

        JCheckBox checaSintomas[] = new JCheckBox[sintomas.length];

        for (int x = 0; x < sintomas.length; x++) {
            checaSintomas[x] = new JCheckBox(sintomas[x]);
        }

        String nome = showInputDialog("Nome:");
        while (nome.equalsIgnoreCase("")) {
            nome = showInputDialog("Digite novamente seu Nome:");
        }

        String cpf = showInputDialog("Cpf:");
        while (cpf.equalsIgnoreCase("")) {
            cpf = showInputDialog("Digite novamente seu Cpf:");
        }

        for (int x = 0; x < filaPaciente.size(); x++) {
            if (filaPaciente.get(x).getCpf().equalsIgnoreCase(cpf)) {
                cpf = showInputDialog("<html> <b style='color: red;'>Cpf já existente.</b>\nCpf:");
                x = -1;
            }

        }

        for (int x = 0; x < filaRegistro.size(); x++) {
            if (filaRegistro.get(x).getCpf().equalsIgnoreCase(cpf) && filaRegistro.get(x).getStatus().equalsIgnoreCase("Óbito")) {
                cpf = showInputDialog("<html> <b style='color: red;'>Paciente relacionado ao Cpf está em óbito.</b>\nCpf:");
                x = -1;
            }

        }

        Object[] mensagem = {"Selecione sintomas do paciente", checaSintomas};
        ArrayList<String> sintomasPaciente = new ArrayList<String>();
        int n = 0;

        do {
            n = JOptionPane.showConfirmDialog(null, mensagem, null, JOptionPane.YES_NO_OPTION);

            for (JCheckBox checkBox : checaSintomas) {
                if (checkBox.isSelected()) {
                    sintomasPaciente.add(checkBox.getText());
                }
            }
        } while (sintomasPaciente.isEmpty());

        filaPaciente.add(adicionarPaciente(nome, cpf, sintomasPaciente, filaPaciente, qtdMaxInternamento));
        filaRegistro.add(adicionarPaciente(nome, cpf, sintomasPaciente, filaPaciente, qtdMaxInternamento));
    }

    public Paciente adicionarPaciente(String nome, String cpf, ArrayList<String> sintomasPaciente, List<Paciente> filaPaciente, int qtdMaxInternamento) {
        int checaMax = 0;

        for (Paciente p : filaPaciente) {
            if (p instanceof PacienteFilaInternacao) {
                checaMax++;
            }
        }

        if (checaMax <= qtdMaxInternamento) {
            return new PacienteFilaInternacao(nome, cpf, sintomasPaciente);
        }
        return new PacienteFilaAtendimento(nome, cpf, sintomasPaciente);
    }

    public void pesquisarPaciente(List<Paciente> filaRegistro) {
        String cpf = showInputDialog("Digite o Cpf para pesquisar:");
        for (int x = 0; x < filaRegistro.size(); x++) {
            if (filaRegistro.get(x).getCpf().equalsIgnoreCase(cpf)) {
                showMessageDialog(null, filaRegistro.get(x) + "\nSintomas\n" + filaRegistro.get(x).getSintomas());
                for (int y = x + 1; y < filaRegistro.size(); y++) {
                    if (filaRegistro.get(y).getCpf().equalsIgnoreCase(cpf)) {
                        showMessageDialog(null, filaRegistro.get(y) + "\nSintomas\n" + filaRegistro.get(y).getSintomas());
                    }
                }
                return;
            }
        }
        showMessageDialog(null, "Cpf não encontrado");
    }

    public void liberarPaciente(List<Paciente> filaPaciente, List<Paciente> filaRegistro) {
        String statusLib = "";
        String cpf = showInputDialog("Digite o Cpf para liberar:");
        int resp = parseInt(showInputDialog("Motivo da liberação:\n1. Alta\n2. Óbito"));
        while (resp != 1 && resp != 2) {
            resp = parseInt(showInputDialog("<html><b style='color:red;'>Digite novamente</b>\nMotivo da liberação:\n1. Alta\n2. Óbito"));
        }

        if (resp == 1) {
            statusLib = "Alta";
        } else {
            statusLib = "Óbito";
        }

        int checaLib = 0;
        int checaReg = 0;

        for (Paciente p : filaPaciente) {
            if (p.getCpf().equalsIgnoreCase(cpf)) {

                for (int x = 0; x < filaRegistro.size(); x++) {
                    if (p.getCpf().equalsIgnoreCase(filaRegistro.get(x).getCpf())) {
                        filaRegistro.set(x, new PacienteLiberado(p.getNome(), p.getCpf(), p.getSintomas(), statusLib));
                    }
                }

                if (p instanceof PacienteFilaInternacao) {
                    for (Paciente pInt : filaRegistro) {
                        if (pInt instanceof PacienteFilaAtendimento) {
                            filaPaciente.set(checaLib, new PacienteFilaInternacao(pInt.getNome(), pInt.getCpf(), pInt.getSintomas()));
                            filaRegistro.set(checaReg, filaPaciente.get(checaLib));
                            break;
                        }
                        checaReg++;
                    }
                }

                filaPaciente.remove(p);
                showMessageDialog(null, "Paciente com o Cpf: " + cpf + " foi liberado");
                return;
            }

            checaLib++;
        }
        showMessageDialog(null, "Cpf não encontrado");
    }

    public void listarPacientes(List<Paciente> filaPaciente, List<Paciente> filaRegistro) {
        int resp = parseInt(showInputDialog("1. Pacientes na fila de atendimento\n2. Pacientes internados\n3. Pacientes liberados\n4. Todos pacientes"));
        int con = 0;

        switch (resp) {
            case 1:
                for (Paciente p : filaPaciente) {
                    if (p instanceof PacienteFilaAtendimento) {
                        showMessageDialog(null, p);
                        con++;
                    }
                }
                if (con == 0) {
                    showMessageDialog(null, "Nenhum paciente na fila de atendimento");
                }
                break;

            case 2:
                for (Paciente p : filaPaciente) {
                    if (p instanceof PacienteFilaInternacao) {
                        showMessageDialog(null, p);
                        con++;
                    }
                }
                if (con == 0) {
                    showMessageDialog(null, "Nenhum paciente internado");
                }
                break;

            case 3:
                for (Paciente p : filaRegistro) {
                    if (p instanceof PacienteLiberado) {
                        showMessageDialog(null, p);
                        con++;
                    }
                }
                if (con == 0) {
                    showMessageDialog(null, "Nenhum paciente liberado");
                }
                break;

            default:
                for (Paciente p : filaRegistro) {
                    showMessageDialog(null, p);
                }
                break;
        }
    }

}
