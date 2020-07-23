package br.pacientes;

import java.util.ArrayList;

public class PacienteLiberado extends Paciente {


    public PacienteLiberado(String nome, String cpf, ArrayList<String> sintomas, String status) {
        super(nome, cpf, sintomas);
        this.status = status;
    }
}
