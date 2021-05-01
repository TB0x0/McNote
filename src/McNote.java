import javax.swing.JFrame;

public class main {
    public static voic main(String args[]){
        // Create application window
        JFrame frame = new Grid();
        frame.setTitle("McNote");
        frame.setVisible(true);
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);

        int fileToOpen;
        int fileToSave;
        JFileChooser fileOpen;
        JFileChooser fileSave;

        Grid(){
            MenuBar menuBar = new MenuBar();
            MenuItem menuitem = new MenuItem();
            final JTextArea textArea = new JTextArea();
            setMenuBar(menuBar);
            Menu file = new Menu("File");
            menuBar.add(file);

            MenuItem newOption = new MenuItem("New");
            MenuItem open = new MenuItem("Open");
            MenuItem save = new MenuItem("Save");
            MenuItem close = new MenuItem("Exit");

            file.add(newOption);
            file.add(open);
            file.add(save);
            file.add(close);
            getContentPane().add(textArea);

            newOption.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    textArea.setText("");
                }
            });

            open.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    openFile();
                    if (fileToOpen == JFileChooser.APPROVE_OPTION){
                        textArea.setText("");
                        try{
                            Scanner scan = new Scanner(new FileReader(fileOpen.getSelectedFile().getPath()));

                            while (scan.hasNext()){
                                textArea.append(scan.nextLine());
                            }
                            catch (Exception ex){
                                System.out.println(ex.getMessage());
                            }
                        }
                    }
                }
            })

        }
    }

}