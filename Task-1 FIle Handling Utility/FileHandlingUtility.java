/*
 * We have the interface of File Handling Utility
 * Also have a logo below this we have the label of "FILE HANDLING UTILITY"
 * Interface with 4 buttons Create , Read , Write , Update (all happens in a single file)
 * 
 * if we click on Create then the "Input Dialog will be shown and take the "File Name"
 *  We will store the file name and send it to the file object it will create the file with that name
 * Similar kind of approach will be apply on different operation.. 
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FileHandlingUtility implements ActionListener {

    private JButton createButton, updateButton, writeButton, readButton;

    public FileHandlingUtility() {
        // Creating frame
        JFrame frame = new JFrame("File Handling with EasyCode");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setLayout(new BorderLayout(20, 20));

        // Adding icon
        ImageIcon image = new ImageIcon("fileLogo.png");
        JLabel label = new JLabel("FILE HANDLING UTILITY", image, JLabel.CENTER);
        label.setForeground(Color.WHITE);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);

        // Panel for title
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(label);
        panel.setBackground(Color.DARK_GRAY);

        // Initializing buttons
        createButton = new JButton("Create File");
        updateButton = new JButton("Update File");
        writeButton = new JButton("New Write to File");
        readButton = new JButton("Read File");

        // Setting button properties
        JButton[] buttons = {createButton, updateButton, writeButton, readButton};

        for (JButton btn : buttons) {
            btn.setFocusable(false);
            btn.setBackground(Color.GRAY);
            btn.setForeground(Color.WHITE);
            btn.addActionListener(this);
        }

        // Panel for buttons
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(4, 1, 10, 10));
        panel2.setBackground(Color.DARK_GRAY);
        for (JButton btn : buttons) {
            panel2.add(btn);
        }

        // Adding components to frame
        frame.add(panel, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String fileName = JOptionPane.showInputDialog("Enter file name:");

        if (fileName == null || fileName.trim().isEmpty()) { // if input is Empty for this condition
            JOptionPane.showMessageDialog(null, "No file name entered!");
            return;
        }

        File file = new File(fileName);

        // Create File
        if (e.getSource() == createButton) {
            try {
                if (file.createNewFile()) { // create the file if done succesfully it will return true 
                    JOptionPane.showMessageDialog(null, "File created: " + file.getAbsolutePath()); // show the dialog box File Created
                } else {
                    JOptionPane.showMessageDialog(null, "File already exists: " + file.getAbsolutePath());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error creating file: " + ex.getMessage());
            }
        }

        // Read File
        if (e.getSource() == readButton) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder(); // StringBuilder type Object
                String line; // simple String Object set to be null by default 
                while ((line = reader.readLine()) != null) { // read each word and return String word to the line  
                    content.append(line).append("\n"); //in content we are storing each word getting from the line 
                }
                JOptionPane.showMessageDialog(null, content.toString().isEmpty() /*here if String content is empty then */ ? "File is empty!" : content.toString()/*otherwise show the content via .toString() method */,
                        fileName + " File Content", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception eread) {
                JOptionPane.showMessageDialog(null, "Error reading file! Make sure the file exists.");
            }
        }

        // Write to File from 0th line 
        if (e.getSource() == writeButton) {
            String content = JOptionPane.showInputDialog("Enter content to write:"); // store in content 
            if (content != null) {
                try (FileWriter writer = new FileWriter(file)) { // passes the file in writer object 
                    writer.write(content + "\n"); // write the content with write function 
                } catch (Exception ewrite) {
                    JOptionPane.showMessageDialog(null, "Error writing to file."); // message box show if any error occur 
                }
            }
        }

        // update the file 
        if(e.getSource() == updateButton){
          String content = JOptionPane.showInputDialog("Enter content to write:"); // any content it will store in String Object 
            if (content != null) {
                try (FileWriter writer = new FileWriter(file, true)) { // Append mode if it is true it means add your content after existing content feature is enabled, u can add after existing content (true)
                    writer.write(content + "\n");
                    JOptionPane.showMessageDialog(null, "Content written to file successfully!");
                } catch (Exception ewrite) {
                    JOptionPane.showMessageDialog(null, "Error writing to file.");
                }
            }
          } // update button end
    }

    public static void main(String[] args) {
        new FileHandlingUtility(); // just Creating a new Object of a FIleHandlingUtility Class for calling the Constructor 
    }
}