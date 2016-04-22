import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;


/**
 * Created by Manu on 19-Apr-16.
 */
public class GUI extends JFrame {

    // GUI
    public static int readIntGUI(String label) {
        String input = JOptionPane.showInputDialog(null,
                label);
        int returnValue = Integer.parseInt(input);
        return returnValue;
    }

    public static double readDoubleGUI(String label) {
        String input = JOptionPane.showInputDialog(null,
                label);
        double returnValue = Double.parseDouble(input);
        return returnValue;
    }

    public static String readStringGUI(String label) {
        String input = JOptionPane.showInputDialog(null,
                label);
        return input;
    }

    public static void printGUI(String text) {
        JOptionPane.showMessageDialog(null, text);
    }

    public static void printGUI(int text) {
        JOptionPane.showMessageDialog(null, text);
    }

    public static void printGUI(double text) {
        JOptionPane.showMessageDialog(null, text);
    }

    // CONSOLE
    public static String readStringConsole(String label) {
        System.out.print(label);
        String input = new Scanner(System.in).nextLine();
        return input;
    }

    public static int readIntConsole(String label) {
        System.out.print(label);
        int input = new Scanner(System.in).nextInt();
        return input;
    }

    public static double readDoubleConsole(String label) {
        System.out.print(label);
        double input = new Scanner(System.in).nextDouble();
        return input;
    }

    public static void printConsole(String text) {
        System.out.println(text);
    }

    public static void printConsole(int text) {
        System.out.println(text);
    }

    public static void printConsole(double text) {
        System.out.println(text);
    }

    /* end of utility methods*/


    //******************************************************************************************************************
        //**************************************************************************************************************
             //**********************************************************************************************************

    //Start of actual code

    private JPanel p = new JPanel();
    //center
    private JTextArea mytext=new JTextArea();
    private JScrollPane mytext_scroll=new JScrollPane(
            mytext,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );
    private JPanel east = new JPanel();
    private JButton afisare = new JButton("Afisare agenda");
    private JButton adaugare = new JButton("Contact nou");
    private JButton cautare = new JButton("Cautare contact");
    private JButton stergere= new JButton("Stergere contact");
    private JButton modificare= new JButton("Modificare contact");

    private Contact [] agenda = new Contact[10];
    private int index=0;

    public static void main(String[] args){
        new GUI();
    }
    public GUI(){
        super("Agenda telefonica");

        setSize(500, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        p.setLayout(new BorderLayout());
        //OUTPUT
        mytext.setEditable(false);
        mytext.setLineWrap(true);
        mytext.setWrapStyleWord(true);
        p.add(mytext_scroll, BorderLayout.CENTER);

        //Butoane
        east.setLayout(new GridLayout(5,1));

        east.add(afisare);
        afisare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mytext.setText("");
                boolean flag=false;
                for (int i = 0; i < agenda.length; i++) {
                    if (agenda[i] != null) {  // => la inceput nu afiseaza nimic, pentru ca nu am introdus nume
                        Contact p = agenda[i];
                        mytext.setText(mytext.getText()+ "\n" +  p.getNume());
                        mytext.setText(mytext.getText() + "\n"  + p.getTelefon());
                        flag = true;
                    }
                }

                if(flag==false) {
                    printGUI("Inca nu exista contacte!");
                }
            }
        });


        east.add(adaugare);
        adaugare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nume=readStringGUI("nume:");
                String telefon=readStringGUI("telefon:");
                Contact p = new Contact(nume, telefon);
                if (index < agenda.length) {
                    agenda[index] = p;
                    index++;
                } else {
                    printGUI("Ne pare rau, dar agenda este plina deja!");
                }
            }
        });

        east.add(cautare);
        cautare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nume=readStringGUI("Introduceti numele cautat: ");
                int r = -1;
                for (int i = 0; i < agenda.length; i++) {
                    if (agenda[i]!=null && nume.equals(agenda[i].getNume())) {
                        r = i+1;
                        printGUI("Numele " + nume + " a fost gasit pe pozitia " + r);
                    }
                }
                if (r==-1){printGUI("Numele " + nume + " nu a fost gasit in agenda!");}
            }

        });

        east.add(stergere);
        stergere.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nume=readStringGUI("Introduceti numele de sters:");
                int r = -1;
                for (int i = 0; i < agenda.length; i++) {
                    if (agenda[i]!=null && nume.equals(agenda[i].getNume())) {
                        r = i;
                        break;}
                }

                if (r == -1) {
                    System.out.println();
                    printConsole("Numele " + nume + " nu exista in agenda!");

                }
                else {
                    agenda[r] = null;
                    printGUI("Nume sters cu succes. Verificati prin afisare.");
                }

            }
        });

        east.add(modificare);
        modificare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nume=readStringGUI("Introduceti numele de modificat:");
                int r = -1;
                for (int i = 0; i < agenda.length; i++) {
                    if (agenda[i]!=null && nume.equals(agenda[i].getNume())) {
                        r = i;
                        break;}
                }
                if (r == -1) {
                    printGUI("Numele " + nume + " nu exista in agenda!");
                } else {
                    String numeModif = readStringGUI("Introduceti numele dorit: ");
                    agenda[r].setNume(numeModif);
                    printGUI("Nume modificat cu succes. Verificati prin afisare.");
                }
            }
        });

        p.add(east, BorderLayout.EAST);
        add(p);
        setVisible(true);

    }

}




