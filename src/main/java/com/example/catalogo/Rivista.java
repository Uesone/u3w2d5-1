package com.example.catalogo;

public class Rivista extends ElementoCatalogo {
    private Periodicita periodicita;

    public Rivista(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "periodicita=" + periodicita +
                "} " + super.toString();
    }
}
