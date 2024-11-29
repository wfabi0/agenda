package file;

import main.Agendamento;
import main.Pessoa;

import java.io.*;
import java.util.*;

public class FileManager {

    private String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public StringBuilder show() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        StringBuilder stringBuilder = new StringBuilder();
        List<String> agendamentosList = new ArrayList<>();
        while (line != null) {
            String[] lineStrings = line.split(";");
            agendamentosList.add(lineStrings[0] + ":00 - " + lineStrings[2]);
//            stringBuilder.append(lineStrings[0]).append(":00 - ").append(lineStrings[2]).append("\n");
            line = reader.readLine();
        }
        reader.close();
        Collections.sort(agendamentosList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int hour1 = Integer.parseInt(o1.split(":")[0]);
                int hour2 = Integer.parseInt(o2.split(":")[0]);
                return Integer.compare(hour1, hour2);
            }
        });
        StringBuilder sorted = new StringBuilder();
        for (String agendamento : agendamentosList) {
            sorted.append(agendamento).append("\n");
        }
        return sorted;
    }

    public void write(Agendamento agendamento) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.write(agendamento.toString() + System.lineSeparator());
        writer.close();
    }

    public Agendamento find(String word, int position) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        while (line != null) {
            String[] lineStrings = line.split(";");
            if (Objects.equals(lineStrings[position], word)) {
                Pessoa pessoa = new Pessoa(Integer.parseInt(lineStrings[1]), lineStrings[2], lineStrings[3]);
                reader.close();
                return new Agendamento(Integer.parseInt(lineStrings[0]), pessoa);
            }
            line = reader.readLine();
        }
        reader.close();
        return null;
    }

    public boolean delete(String word, int position) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        BufferedWriter writer = new BufferedWriter(new FileWriter("temp-file.txt"));
        String line = reader.readLine();
        boolean removed = false;
        while (line != null) {
            String[] lineStrings = line.split(";");
            if (!Objects.equals(lineStrings[position], word)) {
                writer.write(line);
                writer.newLine();
            } else {
                removed = true;
            }
            line = reader.readLine();
        }
        reader.close();
        writer.close();

        if (removed) {
            File originalFile = new File(fileName);
            File tempFile = new File("temp-file.txt");
            if (originalFile.delete()) {
                tempFile.renameTo(originalFile);
            } else {
                throw new IOException("Erro ao substituir o arquivo original.");
            }
        } else {
            new File("tempFile.txt").delete();
        }

        return removed;
    }

}
