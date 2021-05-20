import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Window implements ActionListener {
    JTextArea textArea;
    JFrame frame = new JFrame("McNote");
    public void createWindow(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            //UIManager.put("nimbusBase", new Color(000000));
            //UIManager.put("nimbusBlueGrey", new Color(000000));

            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e){
           // UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //javax.swing.plaf.metal.MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }

        textArea = new JTextArea(500,600);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(true);

        JMenuBar menuBar = new JMenuBar();

        JMenu subMenu1 = new JMenu("File");
        JMenuItem item1 = new JMenuItem("New");
        JMenuItem item2 = new JMenuItem("Open");
        JMenuItem item3 = new JMenuItem("Save");
        JMenuItem item9 = new JMenuItem("Print");
        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item9.addActionListener(this);
        subMenu1.add(item1);
        subMenu1.add(item2);
        subMenu1.add(item3);
        subMenu1.add(item9);

        JMenu subMenu2 = new JMenu("Edit");
        JMenuItem item4 = new JMenuItem("Cut");
        JMenuItem item5 = new JMenuItem("Copy");
        JMenuItem item6 = new JMenuItem("Paste");
        subMenu2.add(item4);
        subMenu2.add(item5);
        subMenu2.add(item6);

        JMenuItem closeMenu = new JMenuItem("Close");
        closeMenu.addActionListener(this);

        menuBar.add(item1);
        menuBar.add(item2);
        menuBar.add(closeMenu);

        frame.setJMenuBar(menuBar);
        frame.add(textArea);
        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(600, 600));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        String actionString = event.getActionCommand();

        if (actionString.equals("Cut")) {
            textArea.cut();
        }
        else if (actionString.equals("Copy")){
            textArea.copy();
        }
        else if (actionString.equals("Paste")){
            textArea.paste();
        }
        else if (actionString.equals("Save")){
            JFileChooser chsr = new JFileChooser("f:");
            int sDialog = chsr.showSaveDialog(null);

            if (sDialog == JFileChooser.APPROVE_OPTION) {
                File file = new File(chsr.getSelectedFile().getAbsolutePath());

                try {
                    FileWriter wr = new FileWriter(file, false);

                    BufferedWriter bw = new BufferedWriter(wr);

                    bw.write(textArea.getText());
                    bw.flush();
                    bw.close();
                }catch (Exception evnt){
                    JOptionPane.showMessageDialog(frame, evnt.getMessage());
                }

            }
            else {
                JOptionPane.showMessageDialog(frame, "User cancelled operation");
            }
        }
        else if (actionString.equals("Print")){
            try{
                textArea.print();
            }catch (Exception evnt){
                JOptionPane.showMessageDialog(frame, evnt.getMessage());
            }
        }
        else if (actionString.equals("Open")){
            JFileChooser iChooser = new JFileChooser("f:");

            int oDialog = iChooser.showOpenDialog(null);

            if (oDialog == JFileChooser.APPROVE_OPTION){
                File iFile = new File(iChooser.getSelectedFile().getAbsolutePath());

                try{
                    String sb = "", se = "";
                    FileReader fr = new FileReader(iFile);
                    BufferedReader br = new BufferedReader(fr);
                    se = br.readLine();

                    while ((sb = br.readLine()) != null){
                        se = se + "\n" + sb;
                    }

                    textArea.setText(se);
                }catch (Exception evnt){
                    JOptionPane.showMessageDialog(frame, evnt.getMessage());
                }
            }
            else{
                JOptionPane.showMessageDialog(frame, "User cancelled operation");
            }
        }
        else if (actionString.equals("New")){
            textArea.setText("");
        }
        else if (actionString.equals("Close")){
            frame.setVisible(false);
            frame.dispose();
        }
    }
}
