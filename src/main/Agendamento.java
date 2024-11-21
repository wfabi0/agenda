package main;

public class Agendamento {

    private int hora;
    private Pessoa p;

    public Agendamento(int hora, Pessoa p) {
        this.hora = hora;
        this.p = p;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public Pessoa getP() {
        return p;
    }

    public void setP(Pessoa p) {
        this.p = p;
    }
}
