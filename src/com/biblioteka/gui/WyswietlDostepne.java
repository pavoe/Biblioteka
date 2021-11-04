package com.biblioteka.gui;

import com.biblioteka.Book;
import com.biblioteka.GUI;
import com.biblioteka.Library;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class WyswietlDostepne extends PanelBazowy {

    public WyswietlDostepne(Library biblioteka, JPanel mainPanel, CardLayout cards, GUI gui) {
        //JPanel wyswietlDostepne = new JPanel();
        setLayout(null);
        //wyswietlDostepne.setBackground(Color.white);
        JLabel title = new JLabel("Dostępne książki", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 20));
        title.setForeground(new Color(0x5C3D2E));
        add(title);

        //nazwy kolumn w tabeli
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Tytuł");
        tableModel.addColumn("Autor");
        tableModel.addColumn("Rok Wydania");
        tableModel.addColumn("Dostępne");
        JTable dostepne = new JTable(tableModel);
        dostepne.setFont(new Font("Sans", Font.PLAIN, 14));
        dostepne.setBackground(new Color(0xE5DCC3));
        dostepne.setBorder(new LineBorder(Color.black));

        //Dodawanie wszystkich dostępnych pozycji

        for (int i = 0; i < biblioteka.size(); i++) {
            Book b = biblioteka.getKsiazka(i);
            tableModel.addRow(new Object[]{i, b.getTytuł(), b.getAutor(), b.getRokWydania(), b.getDostępne()});
        }

        dostepne.setBounds(20, 50, gui.getWidth() - 60, biblioteka.size() * 16);
        dostepne.setEnabled(false);
        add(dostepne);

        //
        JButton backButton = new JButton("Powrót");
        backButton.setBounds(20, 10, 100, 25);
        backButton.setBackground(new Color(0xB85C38));
        backButton.setForeground(Color.white);
        backButton.addActionListener(e -> {
            if (gui.getZalogowanyUżytkownik() == null)
                cards.show(mainPanel, "MenuNiezalogowany");
            else
                cards.show(mainPanel, "MenuZalogowany");
        });

        add(backButton);
    }
}
