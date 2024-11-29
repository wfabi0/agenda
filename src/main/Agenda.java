package main;

import file.FileManager;

import javax.swing.*;
import java.io.IOException;

public class Agenda {

    private final FileManager fileManager;

    public Agenda(String fileName) {
        this.fileManager = new FileManager(fileName);
    }

    public void adicionarAgendamento() throws IOException {
        int hora = Integer.parseInt(JOptionPane.showInputDialog(null, "Horário atendimento:", "Agenda", JOptionPane.QUESTION_MESSAGE));
        if (this.verificarHorario(hora)) {
            JOptionPane.showMessageDialog(null, "Já possui um agendamento nesse horário, favor verificar os horários disponiveis.", "Agenda", JOptionPane.ERROR_MESSAGE);
        } else {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o código da pessoa:", "Agenda", JOptionPane.QUESTION_MESSAGE));
            String nome = JOptionPane.showInputDialog(null, "Digite o nome da pessoa:", "Agenda", JOptionPane.QUESTION_MESSAGE);
            String telefone = JOptionPane.showInputDialog(null, "Digite o telefone da pessoa", "Agenda", JOptionPane.QUESTION_MESSAGE);

            Pessoa p = new Pessoa(codigo, nome, telefone);
            Agendamento agendamento = new Agendamento(hora, p);

            fileManager.write(agendamento);

            JOptionPane.showMessageDialog(null, "Agendamento marcado com sucesso.\n\nHorario: " + hora + "\nNome cliente: " + nome, "Agenda", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean verificarHorario(int hora) throws IOException {
        Agendamento find = fileManager.find(Integer.toString(hora), 0);
        return (find != null);
    }

    public void buscarHorario() throws IOException {
        int hora = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o horário de atendimento para busca:", "Agenda", JOptionPane.QUESTION_MESSAGE));
        Agendamento agendamento = fileManager.find(Integer.toString(hora), 0);
        if (agendamento == null) {
            JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum agendamento nesse horário.", "Agenda", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Atendimento encontrado!\n\nHorário: " + hora + "\nCódigo: " + agendamento.getP().getCodigo() + "\nNome: " + agendamento.getP().getNome() + "\nTelefone: " + agendamento.getP().getTelefone(), "Agenda", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void buscarHorarioPorNome() throws IOException {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da pessoa:", "Agenda", JOptionPane.QUESTION_MESSAGE);
        Agendamento agendamento = fileManager.find(nome.toLowerCase(), 2);
        if (agendamento == null) {
            JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum agendamento nesse horário.", "Agenda", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Atendimento encontrado!\n\nHorário: " + agendamento.getHora() + "\nCódigo: " + agendamento.getP().getCodigo() + "\nNome: " + agendamento.getP().getNome() + "\nTelefone: " + agendamento.getP().getTelefone(), "Agenda", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void cancelarAgendamento() throws IOException {
        int hora = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o horário de atendimento para cancelamento:", "Agenda", JOptionPane.QUESTION_MESSAGE));
        Agendamento agendamento = fileManager.find(Integer.toString(hora), 0);
        if (agendamento == null) {
            JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum agendamento nesse horário para cancelamento.", "Agenda", JOptionPane.ERROR_MESSAGE);
        } else {
            fileManager.delete(Integer.toString(hora), 0);
            JOptionPane.showMessageDialog(null, "Agendamento das " + hora + " hora(s) cancelado com sucesso.", "Agenda", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void mostrarHorarios() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Lista de Horarios:\n\n");
        StringBuilder agendamentos = fileManager.show();
        if (agendamentos.length() == 0) {
            stringBuilder.append("Nenhum horário marcado.");
            JOptionPane.showMessageDialog(null, stringBuilder, "Agenda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, stringBuilder + agendamentos.toString());
        }
    }

}
