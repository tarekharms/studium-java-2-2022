package vorlesung_04_29;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextfileLines implements Iterable<String> {
    private List<String> fileLines;

    public TextfileLines(String pfad) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(pfad));
        this.fileLines = new ArrayList<>();

        for(String line = reader.readLine(); line != null; line=reader.readLine())
        {
            fileLines.add(line);
        }
    }

    @Override
    public Iterator iterator() {
        return this.fileLines.iterator();
    }
}
