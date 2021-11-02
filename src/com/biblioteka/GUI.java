package com.biblioteka;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Vector;

public class GUI extends JFrame{
    private JPanel mainPanel = new JPanel(new CardLayout());
    private CardLayout cards;

    public GUI(String title, Library biblioteka){
        super(title);
        setSize(new Dimension(800,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //okno stałego rozmiaru
        setResizable(false);
        add(mainPanel);

        //oddzielne JPanele dla każdej metody
        JPanel  oknoL = oknoLogowania(biblioteka);
        JPanel oknoR = oknoRejestracji(biblioteka);
        JPanel menuNZ = menuNiezalogowany(biblioteka);
        JPanel menuZ = menuZalogowany(biblioteka);
        JPanel wyswietlD = wyswietlDostepne(biblioteka);
        JPanel wyswietlW = wyswietlWypozyczone(biblioteka);

        //dodanie paneli metod do mainPanel, w której można je zmieniać np. switchem
        mainPanel.add(oknoL, "OknoLogowania");
        mainPanel.add(oknoR, "OknoRejestracji");
        mainPanel.add(menuNZ, "MenuNiezalogowany");
        mainPanel.add(menuZ, "MenuZalogowany");
        mainPanel.add(wyswietlD, "WyswietlDostepne");
        mainPanel.add(wyswietlW, "WyswietlWypozyczone");

        //utworzenie kart dla mainPanel
        cards = (CardLayout) mainPanel.getLayout();

        //działa jak switch, po wpisaniu stringów wyżej pokazuje się to okno, aktualnie jest wyświetlenie dostęnych książek
        cards.show(mainPanel, "WyswietlDostepne");

        setVisible(true);
    }

    JPanel menuNiezalogowany(Library biblioteka){
        //tworzenie JPanel
        JPanel menuNiezalogowany = new JPanel();
        menuNiezalogowany.setLayout(null);
        menuNiezalogowany.setBackground(Color.white);

        //Tekst na górze aplikacji
        JLabel title = new JLabel("Biblioteka", SwingConstants.CENTER);
        title.setFont(new Font("Sans", Font.PLAIN, 20));

        //rozmiary JLabel dla tytułu
        title.setBounds(20,10,this.getWidth()-40,30);
        menuNiezalogowany.add(title);

        //wypisanie tekstu na środku
        JLabel desctription = new JLabel("Wybierz opcję z listy", SwingConstants.CENTER);
        desctription.setFont(new Font("Sans", Font.PLAIN, 16));
        desctription.setBounds(20,45,this.getWidth()-40,30);
        menuNiezalogowany.add(desctription);

        JList<String> menuNiezalogowanyLista;
        DefaultListModel<String> listModel = new DefaultListModel<>();
        String menuOpcje[] = {"Wyjdź z programu", "Zaloguj się", "Załóż konto", "Wypisz dostępne książki"};

        listModel.addAll(Arrays.stream(menuOpcje).toList());

        //stworzenie listy z tekstów w tablicy menuOpcje
        menuNiezalogowanyLista = new JList<>(listModel);

        //tekst w liście na środku
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) menuNiezalogowanyLista.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        menuNiezalogowanyLista.setBorder(new LineBorder(Color.black));
        menuNiezalogowanyLista.setBounds(40,90,300,100);
        menuNiezalogowanyLista.setFont(new Font("Sans", Font.PLAIN, 16));

        //każde kliknięcie wartości na liście zwraca w konsoli index wybranej wartości
        menuNiezalogowanyLista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting())
                {
                    System.out.println(menuNiezalogowanyLista.getSelectedIndex());
                }
            }
        });

        menuNiezalogowany.add(menuNiezalogowanyLista);

        return menuNiezalogowany;
    }

   JPanel menuZalogowany(Library biblioteka){
        //zmienna zalogowana na sztywno
        Main.zalogowana = biblioteka.osoby.firstElement();

        //Metoda podobna do menuNiezalogowany
        JPanel menuZalogowany = new JPanel();
        menuZalogowany.setLayout(null);
        menuZalogowany.setBackground(Color.white);

        JLabel title = new JLabel("Biblioteka", SwingConstants.CENTER);
        title.setFont(new Font("Sans", Font.PLAIN, 20));
        title.setBounds(20,10,300,30);
        menuZalogowany.add(title);

        //zalogowana osoba
        JLabel loggedIn = new JLabel("Zalogowany: |" + Main.zalogowana.login + "| " + Main.zalogowana.imię + " " + Main.zalogowana.nazwisko);
        loggedIn.setFont(new Font("Sans", Font.PLAIN, 16));
        loggedIn.setBounds(310,10,270,30);
        menuZalogowany.add(loggedIn);

        JLabel desctription = new JLabel("Wybierz opcję z listy", SwingConstants.CENTER);
        desctription.setFont(new Font("Sans", Font.PLAIN, 16));
        desctription.setBounds(20,45,this.getWidth()-40,30);
        menuZalogowany.add(desctription);

        JList<String> menuNiezalogowanyLista;
        DefaultListModel<String> listModel = new DefaultListModel<>();
        String menuOpcje[] = {"Wyjdź z programu", "Wyloguj się", "Wypożycz książkę", "Oddaj książkę", "Wypisz dostępne książki", "Wypisz wypożyczone przez ciebie książki"};

        listModel.addAll(Arrays.stream(menuOpcje).toList());


        menuNiezalogowanyLista = new JList<>(listModel);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) menuNiezalogowanyLista.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        menuNiezalogowanyLista.setBorder(new LineBorder(Color.black));
        menuNiezalogowanyLista.setBounds(40,90,300,141);
        menuNiezalogowanyLista.setFont(new Font("Sans", Font.PLAIN, 16));

        menuNiezalogowanyLista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting())
                {
                    System.out.println(menuNiezalogowanyLista.getSelectedIndex());
                }
            }
        });

        menuZalogowany.add(menuNiezalogowanyLista);

        return menuZalogowany;
    }
    JPanel wyswietlDostepne(Library biblioteka){
        JPanel wyswietlDostepne = new JPanel();
        wyswietlDostepne.setLayout(null);
        wyswietlDostepne.setBackground(Color.white);
        JLabel title = new JLabel("Dostępne książki", SwingConstants.CENTER);
        title.setFont(new Font("Sans", Font.PLAIN, 20));
        title.setBounds(20,10,this.getWidth()-40,30);
        wyswietlDostepne.add(title);

        //nazwy kolumn w tabeli
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Tytuł");
        tableModel.addColumn("Autor");
        tableModel.addColumn("Rok Wydania");
        tableModel.addColumn("Dostępne");
        JTable dostepne = new JTable(tableModel);
        dostepne.setFont(new Font("Sans", Font.PLAIN, 14));
        dostepne.setBorder(new LineBorder(Color.black));

        //Dodawanie wszystkich dostępnych pozycji
        for(Book b : biblioteka.ksiazki)
        {
            int i = biblioteka.ksiazki.indexOf(b);
            tableModel.addRow(new Object[]{i, b.tytuł, b.autor, b.rokWydania, b.dostępne});
        }
        dostepne.setBounds(20,50,getWidth()-60,biblioteka.ksiazki.size()*16);
        dostepne.setEnabled(false);
        wyswietlDostepne.add(dostepne);

        return wyswietlDostepne;
    }
    JPanel wyswietlWypozyczone(Library biblioteka){
        //zmienna zalogowana na sztywno
        Main.zalogowana = biblioteka.osoby.firstElement();

        JPanel wyswietlWypozyczone = new JPanel();
        wyswietlWypozyczone.setLayout(null);
        wyswietlWypozyczone.setBackground(Color.white);
        JLabel title = new JLabel("Wypożyczone przez ciebie książki", SwingConstants.CENTER);
        title.setFont(new Font("Sans", Font.PLAIN, 20));
        title.setBounds(20,10,this.getWidth()-40,30);
        wyswietlWypozyczone.add(title);

        //nazwy kolumn w tabeli
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Tytuł");
        tableModel.addColumn("Autor");
        tableModel.addColumn("Rok Wydania");
        tableModel.addColumn("Dostępne");
        JTable wypozyczone = new JTable(tableModel);
        wypozyczone.setFont(new Font("Sans", Font.PLAIN, 14));
        wypozyczone.setBorder(new LineBorder(Color.black));

        //wpisywanie do tabeli książek wypożyczonych przez zalogowana
        for (int i = 0; i < Main.zalogowana.wypozyczoneKsiazki.size(); i++) {
            Integer index[] = Main.zalogowana.wypozyczoneKsiazki.keySet().toArray(new Integer[0]);
            Book b[] = Main.zalogowana.wypozyczoneKsiazki.values().toArray(new Book[0]);
            tableModel.addRow(new Object[]{index[i], b[i].tytuł, b[i].autor, b[i].rokWydania, b[i].dostępne});
        }

        wypozyczone.setBounds(20,50,getWidth()-60,Main.zalogowana.wypozyczoneKsiazki.size()*16);
        wyswietlWypozyczone.add(wypozyczone);

        return wyswietlWypozyczone;
    }
    JPanel oknoLogowania(Library biblioteka){
        JPanel oknoLogowania = new JPanel();
        oknoLogowania.setLayout(null);
        oknoLogowania.setBackground(Color.white);
        //tytuł na górze aplikacji
        JLabel title = new JLabel("Okno Logowania", SwingConstants.CENTER);
        title.setFont(new Font("Sans", Font.PLAIN, 20));
        title.setBounds(20,10,this.getWidth()-40,30);
        oknoLogowania.add(title);

//Login:
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(20,50,50,25);
        oknoLogowania.add(loginLabel);

        JTextField loginText = new JTextField();
        loginText.setBounds(80,50,this.getWidth()-200,25);
        oknoLogowania.add(loginText);
//koniec loginu

//hasło wypisywane kropkami
        JLabel passwordLabel = new JLabel("Hasło");
        passwordLabel.setBounds(20,80,50,25);
        oknoLogowania.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(80,80,this.getWidth()-200,25);
        oknoLogowania.add(passwordText);
//koniec wypisywania hasła

        //informacja czy użytkownik jest zalogowany
        JLabel informacja = new JLabel("");
        informacja.setBounds(20,getHeight()-100,250,25);
        oknoLogowania.add(informacja);

        //przycisk do logowania użytkownika
        JButton logowanieButton = new JButton("Zaloguj");
        logowanieButton.setBounds(20,150,100,25);
        logowanieButton.addActionListener(e -> {
            String myPass=String.valueOf(passwordText.getPassword());
            if (Main.logowanie(loginText.getText(), myPass, biblioteka)) {
                informacja.setText("Udana próba Logowania");
            }
            else informacja.setText("Nie udana próba logowania");
        });
        oknoLogowania.add(logowanieButton);

        return oknoLogowania;
    }

    JPanel oknoRejestracji(Library biblioteka){
        //Metoda podobna do oknoLogownia, do tego wpisanie imienia i nazwiska
        JPanel oknoRejestracji = new JPanel();
        oknoRejestracji.setLayout(null);
        oknoRejestracji.setBackground(Color.white);
        JLabel title = new JLabel("Okno Rejestracji", SwingConstants.CENTER);
        title.setFont(new Font("Sans", Font.PLAIN, 20));
        title.setBounds(20,10,getWidth()-40,30);
        oknoRejestracji.add(title);

        JLabel nameLabel = new JLabel("Imię");
        nameLabel.setBounds(20,50,70,25);
        oknoRejestracji.add(nameLabel);

        JTextField nameText = new JTextField();
        nameText.setBounds(80,50,getWidth()-200,25);
        oknoRejestracji.add(nameText);

        JLabel surnameLabel = new JLabel("Nazwisko");
        surnameLabel.setBounds(20,80,100,25);
        oknoRejestracji.add(surnameLabel);

        JTextField surnameText = new JTextField();
        surnameText.setBounds(80,80,getWidth()-200,25);
        oknoRejestracji.add(surnameText);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(20,110,50,25);
        oknoRejestracji.add(loginLabel);

        JTextField loginText = new JTextField();
        loginText.setBounds(80,110,getWidth()-200,25);
        oknoRejestracji.add(loginText);

        JLabel passwordLabel = new JLabel("Hasło");
        passwordLabel.setBounds(20,140,50,25);
        oknoRejestracji.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(80,140,getWidth()-200,25);
        oknoRejestracji.add(passwordText);

        JLabel informacja = new JLabel("");
        informacja.setBounds(20,getHeight()-100,getWidth()-200,25);
        oknoRejestracji.add(informacja);

        JButton logowanieButton = new JButton("Zaloguj");
        logowanieButton.setBounds(20,170,100,25);
        logowanieButton.addActionListener(e -> {
            String myPass=String.valueOf(passwordText.getPassword());

            //tworzenie tymczasowej osoby, jeśli nie ma jej w bibliotece to jest zapisywana
            Person tmp = new Person(nameText.getText(), surnameText.getText(), loginText.getText(), myPass);

            if (biblioteka.dodajOsobe(tmp)) {
                informacja.setText("Udane utworzenie konta");
                if (Main.logowanie(tmp.login, tmp.hasło, biblioteka)) informacja.setText(informacja.getText()+";    Udana próba logowania");
                else informacja.setText(informacja.getText()+";    Nieudana próba logowania");
            }
            else informacja.setText("Nie udało się utworzyć konta");
        });

        oknoRejestracji.add(logowanieButton);

        return oknoRejestracji;
    }
}
