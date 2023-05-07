package klausurenserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Klausurenserver {
    private ServerSocket socket;
    private int port;

    private Map<String, Set<Integer>> teilnahmen;

    public Klausurenserver(int port)
    {
        this.port = port;
        this.teilnahmen = new HashMap<>();
    }

    public void start()
    {
        try
        {
            this.socket = new ServerSocket(this.port);
        }
        catch (IOException e)
        {
            System.out.println("Server konnte nicht gestartet werden (" + e.getMessage() + ")");
            return;
        }

        TeilnahmeDatenCLI cli = new TeilnahmeDatenCLI();

        while(cli.isRunning())
        {
            try {
                System.out.println("Wart auf Client...");
                Socket clientSocket = socket.accept();
                System.out.println("Client verbunden...");
                new KlausurenserverThread(clientSocket, cli).start();
            }
            catch (IOException e)
            {
                System.out.println("OHNOO");
                return;
            }
        }

        try {
            this.socket.close();
        }
        catch (IOException e)
        {
            System.out.println("Socket konnte nicht geschlossen werden...");
        }
    }
}
