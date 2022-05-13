package vorlesung_05_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public void start(int port)
    {
        ServerSocket socket = null;

        try
        {
            socket = new ServerSocket(port);
        }
        catch(IOException e)
        {
            System.out.println("WHOOOPSIEDAYSI");
            return;
        }

        int clientZaehler = 0;

        while(true)
        {
            try {
                Socket clientSocket = socket.accept();
                clientZaehler++;

                BufferedReader eingabeSocket = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()
                        )
                );

                PrintWriter ausgabeSocket = new PrintWriter(clientSocket.getOutputStream(), true);

                String zeile;

                if((zeile = eingabeSocket.readLine()) != null)
                {
                    zeile = zeile.toLowerCase();

                }

                while ((zeile = eingabeSocket.readLine()) != null)
                {
                    if(zeile.toLowerCase().equals(zeile))
                    {
                        break;
                    }
                    ausgabeSocket.println("Client " + clientZaehler + ": " + zeile);
                }
                clientSocket.close();
            }
            catch (IOException e)
            {
                System.out.println("OHNOO");
                return;
            }
        }
    }
}
