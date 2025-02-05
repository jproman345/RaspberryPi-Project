import java.io.*;
import java.net.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;


public class finalClient extends javax.swing.JFrame {

  public finalClient() {
      initComponents();
  }

  private void initComponents() {

   connectedLabel = new javax.swing.JLabel();
      messageBox = new javax.swing.JTextField();
      sendButton = new javax.swing.JButton();
      disconnectButton = new javax.swing.JButton();
      chatBox = new javax.swing.JScrollPane();
      chatBox1 = new javax.swing.JTextArea();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setTitle("ChatBot Version 0.6.9");

      connectedLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
      connectedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      connectedLabel.setText("Worlds best messenger!");

      messageBox.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              messageBoxActionPerformed(evt);
          }
      });

      sendButton.setBackground(new java.awt.Color(102, 255, 102));
      sendButton.setText("Send");
      sendButton.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              sendButtonActionPerformed(evt);
          }
      });

      disconnectButton.setBackground(new java.awt.Color(255, 102, 102));
      disconnectButton.setText("Disconnect");
      disconnectButton.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              disconnectButtonActionPerformed(evt);
          }
      });

      chatBox.setBackground(new java.awt.Color(204, 255, 255));

      chatBox1.setEditable(false);
      chatBox1.setBackground(new java.awt.Color(204, 204, 255));
      chatBox1.setColumns(20);
      chatBox1.setRows(5);
     // chatBox1.setText("New Chat!");
      chatBox.setViewportView(chatBox1);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(connectedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
              .addContainerGap()
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(chatBox)
                  .addGroup(layout.createSequentialGroup()
                      .addComponent(messageBox, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addGap(18, 18, 18)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addComponent(sendButton)
                          .addComponent(disconnectButton))
                      .addGap(0, 8, Short.MAX_VALUE)))
              .addContainerGap())
      );

      layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {disconnectButton, sendButton});

      layout.setVerticalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
              .addComponent(connectedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(chatBox, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                      .addComponent(sendButton)
                      .addGap(18, 18, 18)
                      .addComponent(disconnectButton)
                      .addGap(0, 3, Short.MAX_VALUE))
                  .addComponent(messageBox))
              .addContainerGap())
      );

      pack();
  }

  static DataInputStream din;
  static DataOutputStream dout;
  static Scanner userInput = new Scanner(System.in);
  static Socket clientSocket;
  static boolean breakPoint = true;

  public static int recNum(DataInputStream din){
      try {
          int response = din.readInt(); // Reads an int from the input stream
          return response;
      } catch (Exception e) {
          e.printStackTrace();
          System.err.println(e.getMessage());
          chatBox1.append("\nYour freind disconnected :(");
          try{
            Thread.sleep(10000);
    }
    catch(Exception b){
    System.err.println(b.getMessage());
    }
          System.exit(0);
      }
      return -1; // if an incorrect value is read, the EXIT_NUM will be returned
  }

  public static String recMes(DataInputStream din){
      try {
          String response = din.readUTF(); // Reads an int from the input stream
          return response;
      } catch (Exception e) {
          e.printStackTrace();
          System.err.println(e.getMessage());
          chatBox1.append("\nYour freind disconnected :(");
          try{
            Thread.sleep(10000);
    }
    catch(Exception b){
    System.err.println(b.getMessage());
    }
          System.exit(0);
      }
      return ""; // if an incorrect value is read, the EXIT_NUM will be returned
  }

  public static void sendNumber(String stringToSend){
      try {
          dout.writeUTF(stringToSend);
          dout.flush();
   
      }
      catch(Exception e){
          System.err.println(e.getMessage());
          chatBox1.append("\nYour freind disconnected :(");
          try{
            Thread.sleep(10000);
    }
    catch(Exception b){
    System.err.println(b.getMessage());
    }
          System.exit(0);
      }

  }

  public static void cleanUp(){
      try {
          clientSocket.close();
          dout.close();
          din.close();

          System.out.println("Connections Closed");
          System.exit(0);

      } catch (IOException e) {
          System.err.println(e.getMessage());
          chatBox1.append("\nYour freind disconnected :(");
          try{
            Thread.sleep(10000);
    }
    catch(Exception b){
    System.err.println(b.getMessage());
    }
          
          System.exit(0);
      }
  }

  private void messageBoxActionPerformed(java.awt.event.ActionEvent evt) {                                        
      // TODO add your handling code here:
  }                                        

  private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {                                        
      // TODO add your handling code here:
      String message = messageBox.getText();
      chatBox1.append("\nYou: " + message);
      sendNumber(message);
      messageBox.setText("");
  }

  private void disconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
      // TODO add your handling code here:
      breakPoint = false;
      cleanUp();
      System.exit(0);
     
  }
  public static void main(String args[]){

  final int port =  1345;
      final String server_ip = args[0];
      int tmp = 0;
      int count = 0;
      String con = "";
      try {
          for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
              if ("Nimbus".equals(info.getName())) {
                  javax.swing.UIManager.setLookAndFeel(info.getClassName());
                  break;
              }
          }
      } catch (ClassNotFoundException ex) {
          java.util.logging.Logger.getLogger(finalClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
          java.util.logging.Logger.getLogger(finalClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
          java.util.logging.Logger.getLogger(finalClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
          java.util.logging.Logger.getLogger(finalClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>
      /* Create and display the form */
      java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
              new finalClient().setVisible(true);
          }
      });

      try{
          // Initialize Necessary Objects
          clientSocket = new Socket(server_ip, port); // Establishes a connection to the server
         dout = new DataOutputStream(clientSocket.getOutputStream()); // Instantiates out so we can then use it t$            din = new DataInputStream(clientSocket.getInputStream());
          din = new DataInputStream(clientSocket.getInputStream());
          // FIX ME: Create the while loop that sends and receives data
      try{
              Thread.sleep(1000);
      }
      catch(Exception e){
      System.err.println(e.getMessage());
      }
      System.out.println("Connected to User...");
        int order = recNum(din);
        con = recMes(din);
        chatBox1.append( con +"\n");
        if(order != 1){
            chatBox1.append("wait for your freind\n");
        }
          while(breakPoint == true){
               String responseM = recMes(din);
               chatBox1.append("\nFriend : " + responseM);
              
           }
      }
      catch(IOException e){
          System.err.println(e.getMessage());
      }
  }
      private javax.swing.JScrollPane chatBox;
      private static javax.swing.JTextArea chatBox1;
      private javax.swing.JLabel connectedLabel;
      private javax.swing.JButton disconnectButton;
      private javax.swing.JTextField messageBox;
      private javax.swing.JButton sendButton;
}
