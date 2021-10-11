/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author az540
 */
public class Encryption extends JFrame {

    String _startTimeGeneration, _endTimeGeneration, _startTimeAttacking, _endTimeAttacking, _attackingResult;
    static FileWriter fileWriter;
    static char[] set = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static int _digitNumber = 6;

    /**
     * @param args the command line arguments
     */
    public Encryption() throws IOException {
        super("Informations Security HW1");
        setLayout(null);

        /* Personal Infos. */
        JLabel _nameText = new JLabel("STU. Name: ");
        _nameText.setBounds(100, 20, 80, 30);

        JLabel _stuName = new JLabel("Ahmad Ziad Abdullah Almabhoh");
        _stuName.setBounds(180, 20, 180, 30);

        JLabel _idText = new JLabel("STU. ID: ");
        _idText.setBounds(400, 20, 80, 30);

        JLabel _stuID = new JLabel("20182776");
        _stuID.setBounds(460, 20, 100, 30);

        _nameText.setForeground(Color.red);
        _idText.setForeground(Color.red);
        _stuName.setForeground(Color.blue);
        _stuID.setForeground(Color.blue);

        add(_nameText);
        add(_stuName);
        add(_idText);
        add(_stuID);

        JButton _generationButton = new JButton("Generation");
        _generationButton.setBounds(100, 100, 200, 30);

        JLabel _generationStart = new JLabel(_startTimeGeneration);
        _generationStart.setBounds(480, 100, 60, 30);

        JLabel _generationTo = new JLabel("to");
        _generationTo.setBounds(550, 105, 20, 20);
        _generationTo.setForeground(Color.green);

        JLabel _generationEnd = new JLabel(_endTimeGeneration);
        _generationEnd.setBounds(600, 100, 60, 30);

        JLabel _generationLabel = new JLabel("Generation Time :");
        _generationLabel.setBounds(350, 100, 100, 30);
        _generationLabel.setForeground(Color.BLUE);

        JTextField _jTextFieldAttack = new JTextField();
        _jTextFieldAttack.setBounds(100, 200, 200, 30);

        JButton _attackButton = new JButton("Attack");
        _attackButton.setBounds(100, 250, 200, 30);

        JLabel _attackingLabel = new JLabel("Attacking Time :");
        _attackingLabel.setBounds(350, 200, 100, 30);
        _attackingLabel.setForeground(Color.BLUE);

        JLabel _attackingTo = new JLabel("to");
        _attackingTo.setBounds(550, 205, 20, 20);
        _attackingTo.setForeground(Color.green);

        JLabel _attackingStart = new JLabel(_startTimeAttacking);
        _attackingStart.setBounds(480, 200, 60, 30);

        JLabel _attackingEnd = new JLabel(_endTimeAttacking);
        _attackingEnd.setBounds(600, 200, 60, 30);

        JLabel _labelAttackingResult = new JLabel(_attackingResult);
        _labelAttackingResult.setBounds(500, 250, 150, 30);

        JLabel _labelAttackingResultState = new JLabel("State :");
        _labelAttackingResultState.setBounds(450, 250, 60, 30);
        _labelAttackingResultState.setForeground(Color.DARK_GRAY);
        
        JTextField _digitNum = new JTextField();
        _digitNum.setBounds(170, 150, 130, 30);
        
        JLabel _digitLabel = new JLabel("Digits No.");
        _digitLabel.setBounds(100, 150, 100,30);

        _generationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                _digitNumber = Integer.parseInt(_digitNum.getText());
                try {
                    fileWriter = new FileWriter("file.txt");
                } catch (IOException ex) {
                    Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
                }
                _startTimeGeneration = elapsedTime();
                _generationStart.setText(_startTimeGeneration);
                createFile();
                _endTimeGeneration = elapsedTime();
                _generationEnd.setText(_endTimeGeneration);
                revalidate();
                try {
                    fileWriter.close();
                } catch (IOException ex) {
                    Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        _attackButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                _startTimeAttacking = elapsedTime();
                _attackingStart.setText(_startTimeAttacking);
                Color r;
                try {
                    if (attackFile("file.txt", _jTextFieldAttack.getText()).equals("Attack successful")) {
                        _labelAttackingResult.setForeground(Color.blue);
                    } else {
                        _labelAttackingResult.setForeground(Color.red);
                    }
                    _labelAttackingResult.setText(attackFile("file.txt", _jTextFieldAttack.getText()));
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Generate a file before traying attack.");
                }
                _endTimeAttacking = elapsedTime();
                _attackingEnd.setText(_endTimeAttacking);
            }

        });

        add(_generationButton);
        add(_generationLabel);
        add(_generationStart);
        add(_generationTo);
        add(_generationEnd);
        add(_jTextFieldAttack);
        add(_attackButton);
        add(_attackingLabel);
        add(_attackingTo);
        add(_attackingStart);
        add(_attackingEnd);
        add(_labelAttackingResult);
        add(_labelAttackingResultState);
        add(_digitNum);
        add(_digitLabel);

        setSize(700, 400);
        setResizable(true);
        setVisible(true);
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - getWidth()) / 2;
        int iCoordY = (objDimension.height - getHeight()) / 2;
        setLocation(iCoordX, iCoordY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        new Encryption();
    }

    public static void createFile() {
        File file = new File("file.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File Created Successfully.");
                printAllKLength(set, _digitNumber);
            } else {
                System.out.println("File Already Exist.");
                printAllKLength(set, _digitNumber);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "An Error Occured!");
            ex.printStackTrace();
        }
    }

    static void printAllKLength(char[] set, int k) throws IOException {
        int n = set.length;
        printAllKLengthRec(set, "", n, k);
    }

    static void printAllKLengthRec(char[] set, String prefix, int n, int k) throws IOException {
        if (k == 0) {
            fileWriter.write(prefix + "\n");
            return;
        }

        for (int i = 0; i < n; ++i) {
            String newPrefix = prefix + set[i];
            printAllKLengthRec(set, newPrefix, n, k - 1);
        }
    }

    public static String elapsedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public static String attackFile(String fileName, String attackPoint) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        String result = "a";
        while (scan.hasNext()) {
            /*
             int point = Integer.parseInt(scan.nextLine().toString());
             if (point == Integer.parseInt(attackPoint)){
             result = "Approved";
             }else{
             result = "Failed Attack";
             }
             */
            if (scan.nextLine().contains(attackPoint)) {
                result = "Attack successful";
            }
            if (!result.equals("a")) {
                return result;
            }
            /*            if (result.equals("Attack successful")) {
             } else {
             result = "Attack failed";
             }*/
        }
        if (result.equals("a")) {
            result = "Attack failed";
        }
        return result;
    }
}
