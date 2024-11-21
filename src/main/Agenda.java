package main;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Agenda {

    private ArrayList<Agendamento> agendamentos;

    public Agenda() {
        this.agendamentos = new ArrayList<Agendamento>();
    }

    public void adicionarAgendamento() {
        int hora = Integer.parseInt(JOptionPane.showInputDialog(null, "Horário atendimento:", "Agenda", JOptionPane.QUESTION_MESSAGE));
        if(this.verificarHorario(hora)) {
            JOptionPane.showMessageDialog(null, "Já possui um agendamento nesse horário, favor verificar os horários disponiveis.", "Agenda", JOptionPane.ERROR_MESSAGE);
        } else {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o código da pessoa:", "Agenda", JOptionPane.QUESTION_MESSAGE));
            String nome = JOptionPane.showInputDialog(null, "Digite o nome da pessoa:", "Agenda", JOptionPane.QUESTION_MESSAGE);
            String telefone = JOptionPane.showInputDialog(null, "Digite o telefone da pessoa", "Agenda", JOptionPane.QUESTION_MESSAGE);

            Pessoa p = new Pessoa(codigo, nome, telefone);

            this.agendamentos.add(new Agendamento(hora, p));

            JOptionPane.showMessageDialog(null, "Agendamento marcado com sucesso.\n\nHorario: " + hora + "\nNome cliente: " + nome, "Agenda", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean verificarHorario(int hora) {
        return this.agendamentos.stream().anyMatch(agendamento -> agendamento.getHora() == hora);
    }

    public void buscarHorario() {
        int hora = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o horário de atendimento para busca:", "Agenda", JOptionPane.QUESTION_MESSAGE));
        Agendamento agendamento = this.agendamentos.stream().filter(ag -> ag.getHora() == hora).findFirst().orElse(null);
        if(agendamento == null) {
            JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum agendamento nesse horário.", "Agenda", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Atendimento encontrado!\n\nHorário: " + hora + "\nCódigo: " + agendamento.getP().getCodigo() + "\nNome: " + agendamento.getP().getNome() + "\nTelefone: " + agendamento.getP().getTelefone(), "Agenda", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void buscarHorarioPorNome() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da pessoa:", "Agenda", JOptionPane.QUESTION_MESSAGE);
        Agendamento agendamento = this.agendamentos.stream().filter(ag -> Objects.equals(ag.getP().getNome().toLowerCase(), nome.toLowerCase())).findFirst().orElse(null);
        if(agendamento == null) {
            JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum agendamento nesse horário.", "Agenda", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Atendimento encontrado!\n\nHorário: " + agendamento.getHora() + "\nCódigo: " + agendamento.getP().getCodigo() + "\nNome: " + agendamento.getP().getNome() + "\nTelefone: " + agendamento.getP().getTelefone(), "Agenda", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void cancelarAgendamento() {
        int hora = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o horário de atendimento para cancelamento:", "Agenda", JOptionPane.QUESTION_MESSAGE));
        Agendamento agendamento = this.agendamentos.stream().filter(ag -> ag.getHora() == hora).findFirst().orElse(null);
        if(agendamento == null) {
            JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum agendamento nesse horário para cancelamento.", "Agenda", JOptionPane.ERROR_MESSAGE);
        } else {
            this.removerAgendamento(agendamento);
            JOptionPane.showMessageDialog(null, "Agendamento das " + hora +  " hora(s) cancelado com sucesso.", "Agenda", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void removerAgendamento(Agendamento agendamento) {
        this.agendamentos.remove(agendamento);
    }

    public void mostrarHorarios() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Lista de Horarios:\n\n");
        if(this.agendamentos.isEmpty()) {
            stringBuilder.append("Nenhum horário marcado.");
            JOptionPane.showMessageDialog(null, stringBuilder, "Agenda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            this.agendamentos.sort(new Comparator<Agendamento>() {
                @Override
                public int compare(Agendamento o1, Agendamento o2) {
                    return Integer.compare(o1.getHora(), o2.getHora());
                }
            });
            for (Agendamento agendamento : this.agendamentos) {
                stringBuilder.append(agendamento.getHora()).append(":00 - ").append(agendamento.getP().getNome()).append("\n");
            }
            JOptionPane.showMessageDialog(null, stringBuilder);
        }
    }

}
