package klausurenserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class KlausurenserverThread extends Thread {
    private Socket clientSocket;
    private TeilnahmeDatenCLI cli;

    public KlausurenserverThread(Socket clientSocket, TeilnahmeDatenCLI cli)
    {
        this.clientSocket = clientSocket;
        this.cli = cli;
    }

    public void run()
    {
        try {
            BufferedReader eingabeSocket = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            PrintWriter ausgabeSocket = new PrintWriter(this.clientSocket.getOutputStream(), true);

            String command;

            System.out.println("Warte auf Command...");

            if((command = eingabeSocket.readLine()) != null && this.cli.isRunning())
            {
                System.out.println("Command: " + command);
                String ausgabe = this.cli.command(command);
                ausgabeSocket.println(ausgabe);
            }

            this.clientSocket.close();
        }
        catch (IOException e)
        {

        }
    }
}
