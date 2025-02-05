import java.io.*;
import java.net.*;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class Server{
    static Scanner userInput = new Scanner(System.in);
    static Socket clientSocket;	
    static ServerSocket serverSocket;
    static DataInputStream din;
    static DataOutputStream dout;
    static Socket clientSocket2;
    static DataInputStream din2;
    static DataOutputStream dout2;


    public static void clean() {
        System.out.println("ENDING coms.");
        try{
            serverSocket.close();
            clientSocket.close();
            dout.close();
            System.exit(0);
        }
        catch (IOException e){
          //  System.out.println("testing 5");
            System.out.println(e.getMessage());
        }
    }
    public static void sendConfig (int message, DataOutputStream out){
        try{
            out.writeInt(message);
            out.flush();
        }
        catch(Exception e){
          //  System.out.println("testing 4");
            System.err.println(e.getMessage());
        }
    }

    public static void sendMessage (String message, DataOutputStream out){
        try{
            out.writeUTF(message);
            out.flush();
        }
        catch(Exception e){
          //  System.out.println("testing 3");
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

 public static String rec(DataInputStream din){
        try {
            String response = din.readUTF();
            return response;
        } catch (Exception e) {
           // System.out.println("testing 2");
            System.err.println(e.getMessage());
            System.exit(0);
        }

        return "";
    }
    
    public static void main(String[] args){
        // final int port =  5050;
        //final int EXIT_NUM = -1;
        final int port = 1345;
       // final int seed = Integer.parseInt(args[1]);
         // int num_msg =Integer.parseInt(args[2]);
       // final int seed = 42;
       // int num_msg = 4;
        int order = 1;
        int order2 = 2;
        String temp1 = "";
        String temp2 = "";
        boolean break1 = true;
        String con = "Connected to freind.";


 
        try{
            System.out.println("IP Address of Server: " + InetAddress.getLocalHost() + "\nPort Number " + port);

            // Initialize Necessary Objects
            serverSocket = new ServerSocket(port);
            System.out.println("waiting for clients...");
            clientSocket = serverSocket.accept(); // Blocking call --> waits here until a request comes in from a cl$ 
            clientSocket2 = serverSocket.accept();
                       
            dout = new DataOutputStream(clientSocket.getOutputStream()); // Instantiates dout so we can then use it $            // Instantiates din so we can then use it to r$
            din = new DataInputStream(clientSocket.getInputStream());
            dout2 = new DataOutputStream(clientSocket2.getOutputStream());
            din2= new DataInputStream(clientSocket2.getInputStream());
            
           
                
                sendConfig(order, dout);
                sendConfig(order2, dout2);
                System.out.println("Client 1: " +clientSocket.getInetAddress().getHostName() + " " + order);
                System.out.println("Client 2: " +clientSocket2.getInetAddress().getHostName()+ " " + order2);
                System.out.println("clients now connected");
                System.out.println("------Watching Messages------");
                //int message = Integer.parseInt(userInput.nextLine());

               // if(message == EXIT_NUM){
               //     System.out.println("Ending Communitcation");
               //     //cleanUp();
               // }

                //send(message);
                //int response = receiveNum();
                //System.out.println("Client Response: " + response);
                sendMessage(con, dout);
                sendMessage(con, dout2);
                while (break1 = true){
                    temp1 = rec(din);
                    System.out.println(clientSocket.getInetAddress().getHostName() + " Message: " + temp1);
                    sendMessage(temp1 , dout2);
                    temp2 = rec(din2);
                    System.out.println(clientSocket2.getInetAddress().getHostName() + " Message: " +temp2);
                    sendMessage(temp2, dout);
                

                }
            System.out.println("Finished listening for client messages.");
            System.out.println(clientSocket.getInetAddress().getHostName());
            System.out.println(clientSocket2.getInetAddress().getHostName());
            
        }
        catch(IOException e){
            //System.out.println("testing ");
            System.err.println(e.getMessage());
        }
    }



}
