import javax.swing.*;
import java.awt.*;

public class Window {
    protected static void createWindow(){
        JFrame frame = new JFrame("McNote");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            UIManager.put("nimbusBase", new Color(000000));
            UIManager.put("nimbusBlueGrey", new Color(000000));

            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e){
            // Set backup look and feel
        }

        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(600, 600));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
}
