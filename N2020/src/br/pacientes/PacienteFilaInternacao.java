package br.pacientes;

import java.util.ArrayList;

public class PacienteFilaInternacao extends Paciente {

    public PacienteFilaInternacao(String nome, String cpf, ArrayList<String> sintomas) {
        super(nome, cpf, sintomas);
        this.status = "Internado";
    }
}
