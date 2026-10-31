import java.io.*;
import java.net.*;

public class Server {
    public static void main (String args[]) throws IOException {
        Calculator calculator = new Calculator();

        int portNumber = 8888;
        if (args.length == 0) {
            System.out.println("usage -> <./executable> <port number>");
            System.out.println("Now using default port number <8888>");
        }
        else if (args.length == 1) {
            portNumber = Integer.parseInt(args[0]);
        }        
        else {
            System.out.println("usage -> <./executable> <port number>");
            System.exit(1);
        }
        System.out.println("Calculator server is running ...");
        try {
            ServerSocket serversocket = new ServerSocket(portNumber);
            while (true) { 
                Socket clientSocket = serversocket.accept();
                
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
           
                String inputLine;
                while ((inputLine = in.readLine()) != null) { 
                    try {
                        double result = calculator.evaluate(inputLine);
                        out.println(inputLine + " = " + result);
                    } catch (Exception e) {
                        out.println("Error in expression: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Exception occured on port" + portNumber);
            System.out.println(e.getMessage());
            System.exit(1); 
        }

    }
}