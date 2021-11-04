package br.ufsm.leagueoflanguages;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.FlowLayout;
import java.util.Comparator;
import java.util.List;

public class AppGUI {
    public static void main(String[] args) throws Exception  {
		ArrayList<Language> languages = new ArrayList<Language>();

        // Criação do JFrame
        JFrame f = new JFrame("League of Languages");
        f.setLayout(null);

        // lista de linguagens
        JPanel panel = new JPanel();
        LanguageRepository repository = new LanguageRepository();
        List<Language> languagesList = repository.findAll();
        languagesList.sort(Comparator.comparing(Language::getFirstAppeared));
        List<String> languagesListStrings = new ArrayList<String>();
        for (Language lang: languagesList) {
             languagesListStrings.add(lang.getLanguageId());
        }
        String[] languagesStrings = languagesListStrings.toArray(new String[0]);
        JList listaLinguages = new JList(languagesStrings);
        JScrollPane listScroller = new JScrollPane(listaLinguages);
        panel.add(listScroller);
        panel.setBounds(340, 60, 100, 300);
        f.add(panel);


        // Text fild language id
        JLabel id = new JLabel("languageId");
        id.setBounds(80, 30, 250, 40);
        f.add(id);
        JTextField language = new JTextField();
        language.setBounds(80, 60, 250, 40);
        f.add(language);

        // Text fild first Appeared
        JLabel year = new JLabel("firstAppeared");
        year.setBounds(80, 90, 250, 40);
        f.add(year);
        JTextField yearValue = new JTextField();
        yearValue.setBounds(80, 120, 250, 40);
        f.add(yearValue);

        // Text fild paradigm
        JLabel paradigm = new JLabel("paradigm");
        paradigm.setBounds(80, 150, 250, 40);
        f.add(paradigm);
        JTextField paradigmValue = new JTextField();
        paradigmValue.setBounds(80, 180, 250, 40);
        f.add(paradigmValue);

        // Text fild userId
        JLabel userId = new JLabel("userId");
        userId.setBounds(80, 210, 250, 40);
        f.add(userId);
        JTextField userIdValue = new JTextField();
        userIdValue.setBounds(80, 240, 250, 40);
        f.add(userIdValue);

        // botão adicionar nova linguagem
        JButton addButton = new JButton("Adicionar Linguagem");
        // evento do botão
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dataNewLanguage[] = {"saveLanguage", language.getText(), yearValue.getText(), paradigmValue.getText(), userIdValue.getText()}; //, 
                try{
                    AppCLI.saveLanguage(dataNewLanguage);
                    List<Language> languagesList = repository.findAll();
                    List<String> languagesListStrings = new ArrayList<String>();
                    languagesList.sort(Comparator.comparing(Language::getFirstAppeared));
                    for (Language lang: languagesList) {
                        languagesListStrings.add(lang.getLanguageId());
                    }
                    String[] languagesStrings = languagesListStrings.toArray(new String[0]);
                    listaLinguages.setListData(languagesStrings);
                } catch (Exception error) {
                    error.printStackTrace();
                }
                
            }
        });
        // x axis, y axis, width, height
        addButton.setBounds(80, 300, 250, 40);
        f.add(addButton);

        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
