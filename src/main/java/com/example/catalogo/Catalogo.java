package com.example.catalogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Catalogo {
    private List<ElementoCatalogo> catalogo;

    public Catalogo() {
        this.catalogo = new ArrayList<>();
    }

    public void aggiungiElemento(ElementoCatalogo elemento) {
        catalogo.add(elemento);
    }

    public void rimuoviElemento(String codiceISBN) {
        catalogo.removeIf(elemento -> elemento.getCodiceISBN().equals(codiceISBN));
    }

    public Optional<ElementoCatalogo> ricercaPerISBN(String codiceISBN) {
        return catalogo.stream()
                .filter(elemento -> elemento.getCodiceISBN().equals(codiceISBN))
                .findFirst();
    }

    public List<ElementoCatalogo> ricercaPerAnnoPubblicazione(int annoPubblicazione) {
        return catalogo.stream()
                .filter(elemento -> elemento.getAnnoPubblicazione() == annoPubblicazione)
                .collect(Collectors.toList());
    }

    public List<Libro> ricercaPerAutore(String autore) {
        return catalogo.stream()
                .filter(elemento -> elemento instanceof Libro)
                .map(elemento -> (Libro) elemento)
                .filter(libro -> libro.getAutore().equals(autore))
                .collect(Collectors.toList());
    }

    public void stampaCatalogo() {
        catalogo.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Scegli un'operazione:");
            System.out.println("1. Aggiungi un elemento");
            System.out.println("2. Rimuovi un elemento");
            System.out.println("3. Ricerca per ISBN");
            System.out.println("4. Ricerca per anno pubblicazione");
            System.out.println("5. Ricerca per autore");
            System.out.println("6. Stampa catalogo");
            System.out.println("7. Esci");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1 -> {
                    System.out.println("Scegli il tipo di elemento:");
                    System.out.println("1. Libro");
                    System.out.println("2. Rivista");

                    int tipoElemento = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Inserisci il codice ISBN: ");
                    String codiceISBN = scanner.nextLine();

                    System.out.print("Inserisci il titolo: ");
                    String titolo = scanner.nextLine();

                    System.out.print("Inserisci l'anno di pubblicazione: ");
                    int annoPubblicazione = scanner.nextInt();

                    System.out.print("Inserisci il numero di pagine: ");
                    int numeroPagine = scanner.nextInt();
                    scanner.nextLine();

                    if (tipoElemento == 1) {
                        System.out.print("Inserisci l'autore: ");
                        String autore = scanner.nextLine();

                        System.out.print("Inserisci il genere: ");
                        String genere = scanner.nextLine();

                        catalogo.aggiungiElemento(new Libro(codiceISBN, titolo, annoPubblicazione, numeroPagine, autore, genere));
                    } else if (tipoElemento == 2) {
                        System.out.println("Scegli la periodicità:");
                        System.out.println("1. Settimanale");
                        System.out.println("2. Mensile");
                        System.out.println("3. Semestrale");

                        int periodicitaScelta = scanner.nextInt();
                        scanner.nextLine();

                        Periodicita periodicita = switch (periodicitaScelta) {
                            case 1 -> Periodicita.SETTIMANALE;
                            case 2 -> Periodicita.MENSILE;
                            case 3 -> Periodicita.SEMESTRALE;
                            default -> throw new IllegalArgumentException("Scelta non valida");
                        };

                        catalogo.aggiungiElemento(new Rivista(codiceISBN, titolo, annoPubblicazione, numeroPagine, periodicita));
                    }
                }
                case 2 -> {
                    System.out.print("Inserisci il codice ISBN dell'elemento da rimuovere: ");
                    String codiceISBN = scanner.nextLine();
                    catalogo.rimuoviElemento(codiceISBN);
                }
                case 3 -> {
                    System.out.print("Inserisci il codice ISBN: ");
                    String codiceISBN = scanner.nextLine();
                    Optional<ElementoCatalogo> elemento = catalogo.ricercaPerISBN(codiceISBN);
                    elemento.ifPresent(System.out::println);
                }
                case 4 -> {
                    System.out.print("Inserisci l'anno di pubblicazione: ");
                    int annoPubblicazione = scanner.nextInt();
                    scanner.nextLine();
                    List<ElementoCatalogo> risultati = catalogo.ricercaPerAnnoPubblicazione(annoPubblicazione);
                    risultati.forEach(System.out::println);
                }
                case 5 -> {
                    System.out.print("Inserisci l'autore: ");
                    String autore = scanner.nextLine();
                    List<Libro> risultati = catalogo.ricercaPerAutore(autore);
                    risultati.forEach(System.out::println);
                }
                case 6 -> catalogo.stampaCatalogo();
                case 7 -> {
                    System.out.println("Uscita...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Scelta non valida. Riprova.");
            }
        }
    }
}
