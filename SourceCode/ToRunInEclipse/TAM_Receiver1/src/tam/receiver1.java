/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * receiver1.java
 *
 * Created on Aug 8, 2012, 9:26:10 AM
 */

package tam;
import java.io.*;
import java.net.Socket;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author Administrator
 */
public class receiver1 extends javax.swing.JFrame {
    public static String filepath1,fp2="c:\\decrypt.txt";
    /** Creates new form receiver1 */
    public receiver1() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 600));
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Select a file:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(60, 180, 77, 17);
        getContentPane().add(jTextField1);
        jTextField1.setBounds(220, 180, 219, 20);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/browse.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(280, 240, 90, 30);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Enter the 16 (byte) key:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 320, 148, 17);
        getContentPane().add(jTextField2);
        jTextField2.setBounds(220, 320, 219, 20);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cipher.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(280, 380, 100, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fileretrieve.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 720, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
            JFileChooser fc=new JFileChooser();
            int a=fc.showOpenDialog(null);
            if(a==JFileChooser.APPROVE_OPTION){
                File fileToOpen=fc.getSelectedFile();
                jTextField1.setText(filepath1=fileToOpen.toString());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{
            File desFile=new File(fp2);
            FileInputStream fis=null;
            FileOutputStream fos;
            CipherInputStream cis;
            byte key[]=(jTextField2.getText()).getBytes();
            SecretKeySpec secretKey=new SecretKeySpec(key,"AES");
            Cipher encrypt=Cipher.getInstance("AES");
            encrypt.init(Cipher.DECRYPT_MODE, secretKey);
            try{
                fis=new FileInputStream(filepath1);
            }
            catch(IOException e){
                System.out.println("Cannot open file");
                System.exit(-1);
            }
            cis=new CipherInputStream(fis, encrypt);
            fos=new FileOutputStream(desFile);
            byte[] b=new byte[8];
            int i=cis.read(b);
            while(i!=-1){
                fos.write(b, 0, i);
                i=cis.read(b);
            }
            fos.flush();
            fos.close();
            cis.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "File is Decrypted");
            r1intchek r1ic=new r1intchek();
            r1ic.setVisible(true);
            this.dispose();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new receiver1().setVisible(true);
            }
        });
        try{
            Socket clientSocket=new Socket("127.0.0.1",6789);
            byte buffer[]=new byte[1024];
            File f=new File("C:\\file.txt");
            FileOutputStream fos=new FileOutputStream(f);
            BufferedInputStream bis=new BufferedInputStream(clientSocket .getInputStream());
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(f));
            int read;
            while((read=bis.read(buffer))!=-1){
                bos.write(buffer, 0, read);
            }
            bos.close();
            bis.close();
            fos.close();
            clientSocket.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}
