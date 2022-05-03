package vorlesung_04_29;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class TextfileLinesLazy implements Iterable<String> {
    private BufferedReader reader;

    public TextfileLinesLazy(String pfad) throws IOException
    {
        this.reader = new BufferedReader(new FileReader(pfad));
    }

    @Override
    public Iterator<String> iterator()
    {
        return new TextfileLinesIterator(this.reader);
    }

    private class TextfileLinesIterator implements Iterator<String>
    {
        private BufferedReader reader;
        private String lookahead;

        public TextfileLinesIterator(BufferedReader reader)
        {
            this.reader = reader;

            try
            {
                lookahead = this.reader.readLine();
            }
            catch (IOException e)
            {
                lookahead = null;
            }
        }

        @Override
        public boolean hasNext() {
            boolean more = this.lookahead != null;

            try {
                if(!more) this.reader.close();
            }
            catch (Exception e)
            {
                throw new IllegalStateException();
            }

            return more;
        }

        @Override
        public String next() {
            try
            {
                String string = this.lookahead;
                this.lookahead = reader.readLine();
                return string;
            }
            catch (IOException e)
            {
                return null;
            }
        }
    }


}
