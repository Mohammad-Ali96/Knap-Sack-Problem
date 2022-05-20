package knapsack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import java.io.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KnapSack_GUI extends JFrame {

    JLabel NumberOfItemsLable;
    JLabel capacityLable;

    JTextField NumberOfItemsTextField;
    JTextField capacityTextField;

    JButton exitBtn;
    JButton clearBtn;

    JButton randomBtn;
    JButton fileBtn;
    JButton fractionalBtn;
    JButton dynamicBtn;
    JButton recursiveBtn;

    JSeparator divider;

    JTextArea fractionalArea;
    JTextArea dynamicArea;
    JTextArea recursiveArea;

    Fractional_KnapSack algo1;
    Zer_One_KnapSack algo2;
    Recursive_KnapSack algo3;

    public KnapSack_GUI() {

        this.setVisible(true);
        this.setSize(1200, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("KnapSack Problem");
        this.setResizable(false);
        this.setLayout(null);

        // create components
        NumberOfItemsLable = new JLabel("Number of items");
        capacityLable = new JLabel("Capacity of bag");

        NumberOfItemsTextField = new JTextField();
        capacityTextField = new JTextField();

        exitBtn = new JButton("exit");
        clearBtn = new JButton("clear");

        randomBtn = new JButton("Generate Random Input");
        fileBtn = new JButton("Input Items from file");

        divider = new JSeparator();

        fractionalBtn = new JButton("Solving Fractional KnapSack using Greedy techniqe");
        dynamicBtn = new JButton("Solving 0-1 KnapSack using Dynamic techniqe");
        recursiveBtn = new JButton("Solving 0-1 KnapSack using Recursive techniqe");

        fractionalArea = new JTextArea(10, 10);
        dynamicArea = new JTextArea(10, 10);
        recursiveArea = new JTextArea(10, 10);

        // add components to GUI
        this.add(NumberOfItemsLable);
        this.add(capacityLable);
        this.add(capacityTextField);
        this.add(NumberOfItemsTextField);
        this.add(exitBtn);
        this.add(clearBtn);
        this.add(randomBtn);
        this.add(fileBtn);
        this.add(divider);
        this.add(fractionalBtn);
        this.add(dynamicBtn);
        this.add(recursiveBtn);
        this.add(recursiveArea);
        this.add(dynamicArea);
        this.add(fractionalArea);

        // set location for each components
        NumberOfItemsLable.setBounds(420, 20, 100, 28);
        capacityLable.setBounds(420, 56, 100, 32);

        NumberOfItemsTextField.setBounds(536, 24, 120, 26);
        capacityTextField.setBounds(536, 60, 120, 26);

        exitBtn.setBounds(1070, 640, 80, 30);
        clearBtn.setBounds(970, 640, 80, 30);

        randomBtn.setBounds(180, 36, 200, 30);
        fileBtn.setBounds(720, 36, 200, 30);
        divider.setBounds(0, 160, 1200, 10);

        fractionalBtn.setBounds(30, 180, 340, 32);
        dynamicBtn.setBounds(420, 180, 340, 32);
        recursiveBtn.setBounds(820, 180, 340, 32);

        fractionalArea.setBounds(25, 230, 320, 400);
        dynamicArea.setBounds(425, 230, 320, 400);
        recursiveArea.setBounds(825, 230, 320, 400);

        fileBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String path = JOptionPane.showInputDialog(null, "input the url of file", "");

                int num = Integer.parseInt(NumberOfItemsTextField.getText());
                int capacity = Integer.parseInt(capacityTextField.getText());

                algo1 = new Fractional_KnapSack(num, capacity);
                algo2 = new Zer_One_KnapSack(num, capacity);
                algo3 = new Recursive_KnapSack(num, capacity);

                try {
                    FileReader file = new FileReader(path);
                    BufferedReader br = new BufferedReader(file);

                    String line = null;

                    while ((line = br.readLine()) != null) {
                        String ss[] = line.split(" ");
                        algo1.items.add(new Item(Integer.parseInt(ss[0]), Integer.parseInt(ss[1])));
                        algo2.items.add(new Item(Integer.parseInt(ss[0]), Integer.parseInt(ss[1])));
                        algo3.items.add(new Item(Integer.parseInt(ss[0]), Integer.parseInt(ss[1])));

                    }

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(KnapSack_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(KnapSack_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        randomBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int num = Integer.parseInt(NumberOfItemsTextField.getText());
                int capacity = Integer.parseInt(capacityTextField.getText());

                algo1 = new Fractional_KnapSack(num, capacity);
                algo2 = new Zer_One_KnapSack(num, capacity);
                algo3 = new Recursive_KnapSack(num, capacity);
                String input = "";
                input = "#Number of Items : " + num + "\n";
                input += "Capacity of bag : " + capacity + "\n";
                Random r = new Random();

                for (int i = 0; i < num; i++) {
                    int w = r.nextInt(capacity) + 2;
                    int v = r.nextInt(1000) + 100;
                    input += i+1 + "- " + "("+w+", " + v+")"+"\n";
                    algo1.items.add(new Item(w, v));
                    algo2.items.add(new Item(w, v));
                    algo3.items.add(new Item(w, v));

                }
                
                JOptionPane.showMessageDialog(null, input);
                

            }
        });

        fractionalBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                algo1.findOptimalSolution();
                String msg = algo1.printSolution();
                fractionalArea.setText(msg);

            }
        });

        exitBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        clearBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                algo1 = null;
                algo2 = null;
                algo3 = null;
                NumberOfItemsTextField.setText("");
                capacityTextField.setText("");
                fractionalArea.setText("");
                dynamicArea.setText("");
                recursiveArea.setText("");

            }
        });

        dynamicBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                algo2.findOptimalSolution();
                String msg = algo2.printSolution();
                dynamicArea.setText(msg);
            }
        });

        recursiveBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                algo3.findOptimalSolution_Recursive();
                String msg = algo3.printSolution();
                recursiveArea.setText(msg);
            }
        });

    }

}
