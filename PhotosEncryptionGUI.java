package photosencryption;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PhotosEncryptionGUI extends JFrame implements ActionListener {
    
    JButton bOpenFile,bKey,bStart;
    JTextField keyToLoad;
    JRadioButton rbEncrypt,rbDecrypt;
    JLabel lPath;
    String key;
    int start = 0;
    String path;
    
    public PhotosEncryptionGUI(){
        
        setSize(300,200);
        setTitle("PhotosEncryption");
        setLayout(null);
        
        rbEncrypt= new JRadioButton("Szyfruj");
        rbDecrypt = new JRadioButton("Deszyfruj");
        rbEncrypt.setSelected(true);
        rbEncrypt.setBounds(10,10,100,20);
        rbDecrypt.setBounds(10,30,100,20);
        rbEncrypt.addActionListener(this);
        rbDecrypt.addActionListener(this);
        add(rbEncrypt);
        add(rbDecrypt);
        ButtonGroup group = new ButtonGroup();
        group.add(rbEncrypt);
        group.add(rbDecrypt);
        
        lPath = new JLabel();
        lPath.setBounds(10,140,260,20);
        add(lPath);
        
        keyToLoad = new JTextField("");
        keyToLoad.setBounds(40,120,200,20);
        add(keyToLoad);
        
        bOpenFile = new JButton("Załącz plik");
        bOpenFile.setBounds(140, 100, 100, 20);
        add(bOpenFile);
        bOpenFile.addActionListener(this);
        
        bKey = new JButton("Podaj klucz");
        bKey.setBounds(40, 100, 100, 20);
        add(bKey);
        bKey.addActionListener(this);
        
        bStart = new JButton("Start");
        bStart.setBounds(90, 80, 100, 20);
        add(bStart);
        bStart.addActionListener(this);
    }
    
    public void run(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if(source == bOpenFile){
            JFileChooser fileChooser = new JFileChooser();
            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                path = fileChooser.getSelectedFile().getAbsolutePath();
                lPath.setText(path);
            }
        }else if(source == bStart){
            start = 1;
        }else if(source == bKey){
            if(keyToLoad.getText().length()==16){
                key = keyToLoad.getText();
                System.out.println(key);
            }else{
                key = "basicEasyKey1234";
                System.out.println(key);
            }
        }else System.out.println(rbEncrypt.isSelected());
    }
}
