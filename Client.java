import java.io.*;
import java.net.*;

public class Client {
    public static void main (String args[]) throws IOException{
        String hostName = "127.0.0.1";
        int portNumber = 8888;

        // Parse command line arguments for custom host and port configuration
        if (args.length == 0) {
            System.out.println("usage -> <./executable> <port number> <ip adress>");
            System.out.println("Now using default port number <8888> and ip adress <127.0.0.1>");
        }
        else if (args.length == 2) {
            hostName = args[1];
            portNumber = Integer.parseInt(args[0]);
        }
        else {
            System.out.println("usage -> <./executable> <port number> <ip adress>");
        }

        // Establish connection to the server and handle standard input/output streams
        try ( Socket calcSocket = new Socket(hostName, portNumber);
              PrintWriter out = new PrintWriter(calcSocket.getOutputStream(), true);
              BufferedReader in = new BufferedReader(new InputStreamReader(calcSocket.getInputStream()));
              BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String inputLine;
            System.out.println("Welcome to server calculator");
            System.out.println("Type any mathematical operation");
            
            // Loop to send user input expressions and print server responses
            while ((inputLine = stdin.readLine()) != null) { 
                out.println(inputLine);
                System.out.println(in.readLine());
            }

        }catch (UnknownHostException e) {  
            System.err.println("Don't know about host " + hostName);  
            System.out.println(e.getMessage());
            System.exit(1);  
        } catch (IOException e) {
            System.out.println("Exception occured on port" + portNumber);
            System.out.println(e.getMessage());
            System.exit(1); 
        }
    }
}