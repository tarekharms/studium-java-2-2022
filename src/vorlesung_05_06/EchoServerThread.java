package vorlesung_05_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoServerThread extends Thread {
    private Socket clientSocket;
    private int clientNummer;

    public EchoServerThread(Socket clientSocket, int clientNummer)
    {
        this.clientSocket = clientSocket;
        this.clientNummer = clientNummer;
    }

    public void run()
    {
        try
        {
            BufferedReader eingabeSocket = new BufferedReader(
                    new InputStreamReader(
                            this.clientSocket.getInputStream()
                    )
            );

            PrintWriter ausgabeSocket = new PrintWriter(this.clientSocket.getOutputStream(), true);

            String zeile;

            while ((zeile = eingabeSocket.readLine()) != null)
            {
                if(zeile.toLowerCase().equals(zeile))
                {
                    break;
                }
                ausgabeSocket.println("Client " + this.clientNummer + ": " + zeile);
            }
            this.clientSocket.close();

            System.out.println("Client Nummer " + this.clientNummer + " beendet");
        }
        catch (IOException e)
        {
            System.out.println("Client Nummer " + this.clientNummer + " abgestuerzt");
        }
    }
}
