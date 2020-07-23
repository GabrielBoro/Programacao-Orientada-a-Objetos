package br.pacientes;

import java.util.ArrayList;

public abstract class Paciente {
    protected String nome;
    protected String cpf;
    protected String status;
    protected ArrayList<String> sintomas;

    public Paciente(String nome, String cpf, ArrayList<String> sintomas) {
        this.nome = nome;
        this.cpf = cpf;
        this.sintomas = sintomas;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<String> getSintomas() {
        return sintomas;
    }

    @Override
    public String toString() {
        return  "Nome= " + nome + '\n' +
                "Cpf= " + cpf + '\n' +
                "Status= " + status + '\n';
    }
}
