package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Agenda agenda = new Agenda();

        String menu = "\n" +
                "1. Marcar horario\n" +
                "2. Desmarcar horario\n" +
                "3. Mostrar horarios\n" +
                "4. Buscar horario por hora\n" +
                "5. Buscar horario por nome\n" +
                "0. Sair\n\n";

        String opcao = "";
        while (true) {
            opcao = JOptionPane.showInputDialog(null, menu, "Agenda", JOptionPane.QUESTION_MESSAGE);
            switch (opcao) {
                case "1": {
                    try {
                        agenda.adicionarAgendamento();
                    } catch (Exception e) {
                        continue;
                    }
                    break;
                }
                case "2": {
                    try {
                        agenda.cancelarAgendamento();
                    } catch (Exception e) {
                        continue;
                    }
                    break;
                }
                case "3": {
                    try {
                        agenda.mostrarHorarios();
                    }  catch (Exception e) {
                        continue;
                    }
                    break;
                }
                case "4": {
                    try {
                        agenda.buscarHorario();
                    }  catch (Exception e) {
                        continue;
                    }
                    break;
                }
                case "5": {
                    try {
                        agenda.buscarHorarioPorNome();
                    }  catch (Exception e) {
                        continue;
                    }
                    break;
                }
                case "0": {
                    return;
                }
                default: {
                    JOptionPane.showMessageDialog(null, "Opção inválida.", "Agenda", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

}