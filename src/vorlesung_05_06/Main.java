package vorlesung_05_06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args)
    {
        EchoServer server = new EchoServer();
        server.start(1337);
    }
}
