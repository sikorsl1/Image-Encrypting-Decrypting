package photosencryption;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class PhotosEncryption{

    private static final String ALGORITHM = "AES";
    private byte[] keyValue;
    private byte[] imageValue;
    
    public PhotosEncryption(String key){
        keyValue = key.getBytes();
    }
    
    public PhotosEncryption(String key, String path) throws IOException{
        keyValue = key.getBytes();
        imageReader(path);
    }
    
    public String encrypt(String Data) throws Exception{
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes(StandardCharsets.ISO_8859_1));
        String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        return encryptedValue;
    }
    
    public String decrypt(String encryptedData) throws Exception{
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue,StandardCharsets.ISO_8859_1);
        return decryptedValue;
    }
    
    private Key generateKey() throws Exception{
        Key key = new SecretKeySpec(keyValue,ALGORITHM);
        return key;
    }
    
    public void imageWriter(String path, String data) throws FileNotFoundException, IOException{
        OutputStream file = new FileOutputStream(path);
        DataOutputStream outputStream = new DataOutputStream(file);
        byte[] bufor = data.getBytes("ISO-8859-1");
        outputStream.write(bufor);
    }
    
    public void imageReader(String path) throws FileNotFoundException, IOException{
        InputStream file = new FileInputStream(path);
        DataInputStream inputStream = new DataInputStream(file);
        int count = file.available();
        byte[] bufor = new byte[count];
        inputStream.read(bufor);
        imageValue = bufor;
    }
    
    public static void main(String[] args) throws InterruptedException {
        PhotosEncryptionGUI dialogBox = new PhotosEncryptionGUI();
        dialogBox.run();
        int start = 0;
        String key;
        String path;
        boolean mode;
        while(true){
            start = dialogBox.start;
            TimeUnit.MILLISECONDS.sleep(3);
            path = dialogBox.path;
            if(path==null){
                start = 0;
            }
            else if(dialogBox.path!=null);
            if(start == 1){
                key = dialogBox.key;
                TimeUnit.MILLISECONDS.sleep(3);
                mode = dialogBox.rbEncrypt.isSelected();
                TimeUnit.MILLISECONDS.sleep(3);
                System.out.println(mode);
                try{
                    PhotosEncryption aes = new PhotosEncryption(key,path);
                    if(mode){
                        String encdata = aes.encrypt(new String(aes.imageValue,StandardCharsets.ISO_8859_1));
                        aes.imageWriter("D:/Lolek/Projekty/PhotosEncryption/build/classes/photosencryption/testImage2.png",
                                    encdata);
                    }else{
                        String decdata = aes.decrypt(new String(aes.imageValue,StandardCharsets.ISO_8859_1));
                        aes.imageWriter("D:/Lolek/Projekty/PhotosEncryption/build/classes/photosencryption/testImage3.png",
                                    decdata);
                    }
                    
                }catch(Exception ex){
                    Logger.getLogger(PhotosEncryption.class.getName()).log(Level.SEVERE,null,ex);
                }
            dialogBox.start = 0;
            start = 0;
            }
        }
    }
}
