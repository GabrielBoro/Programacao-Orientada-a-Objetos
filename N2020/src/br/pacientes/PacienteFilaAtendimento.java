package br.pacientes;

import java.util.ArrayList;

public class PacienteFilaAtendimento extends Paciente {


    public PacienteFilaAtendimento(String nome, String cpf, ArrayList<String> sintomas) {
        super(nome, cpf, sintomas);
        this.status = "Fila de atendimento";
    }
}
