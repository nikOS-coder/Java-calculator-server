import java.io.*;
import java.net.*;

public class ClientThread extends Thread {
    Socket clientSocket;
    Calculator calculator;

    // Constructor to initialize socket and shared calculator instance
    public ClientThread(Socket clientSocket, Calculator calculator) {
        this.clientSocket = clientSocket;
        this.calculator = calculator;
    }

    @Override
    public void run() { 
        try ( 
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            // Read incoming expressions, evaluate them, and send back the results
            while ((inputLine = in.readLine()) != null) { 
                try {
                    double result = calculator.evaluate(inputLine);
                    out.println(inputLine + " = " + result);
                } catch (Exception e) {
                    out.println("Error in expression: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error communicating with the client: " + e.getMessage());
        }
    }
}