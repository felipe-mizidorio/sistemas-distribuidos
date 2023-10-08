package client;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("IP: ");
        String IPServer = stdIn.readLine();
        System.out.print("Port: ");
        int port = Integer.parseInt(stdIn.readLine());

        String serverHostName = "localHost";

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket(serverHostName, port);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: " + serverHostName);
            System.exit(1);
        }

        String userInput;

        System.out.println ("Type Message (\"Bye.\" to quit)");
        while ((userInput = stdIn.readLine()) != null)
        {
            out.println(userInput);

            // end loop
            if (userInput.equals("Bye."))
                break;

            System.out.println("echo: " + in.readLine());
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}
