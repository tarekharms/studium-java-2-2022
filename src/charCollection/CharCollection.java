package charCollection;

import grosseZahl.GrosseZahl;

import java.util.ArrayList;

public class CharCollection {
    ArrayList<Character> characters;


    public CharCollection(char... characters)
    {
        this.characters = new ArrayList<>(characters.length);

        for(int i = 0; i < characters.length; i++) {
            this.characters.add(characters[i]);
        }
    }

    public CharCollection(String characters)
    {
        this.characters = new ArrayList<>(characters.length());

        for(int i = 0; i < characters.length(); i++)
        {
            this.characters.add(characters.charAt(i));
        }
    }

    private  CharCollection(ArrayList<Character> characters)
    {
        this.characters = characters;
    }

    public int size()
    {
        return this.characters.size();
    }

    public int count(char character)
    {
        int count = 0;

        for(int i = 0; i < this.size(); i++)
        {
            if(this.characters.get(i) == character)
                count++;
        }

        return count;
    }

    public int different()
    {
        return this.getDifferentCharacters().size();
    }

    private ArrayList<Character> getDifferentCharacters()
    {
        ArrayList<Character> vorhanden = new ArrayList<>();

        for(int i = 0; i < this.size(); i++)
        {
            char character = this.characters.get(i);
            if(!vorhanden.contains(character))
            {
                vorhanden.add(character);
            }
        }

        return vorhanden;
    }

    public char top()
    {
        ArrayList<Character> verschiedene = this.getDifferentCharacters();

        int indexTop = 0;
        int countTop = 0;

        for(int i = 0; i < verschiedene.size(); i++)
        {
            if(this.count(verschiedene.get(i)) > countTop)
            {
                indexTop = i;
                countTop = this.count(verschiedene.get(i));
            }
        }

        return verschiedene.get(indexTop);
    }

    public String toString()
    {
        String string = "(";

        if(this.size() == 0)
        {
            string += ")";
            return string;
        }

        string += this.characters.get(0);

        for(int i = 1; i < this.size(); i++)
        {
            string += ", " + this.characters.get(i);
        }

        string += ")";

        return string;
    }

    public CharCollection moreThan(int m)
    {
        ArrayList<Character> thisMehrAlsM = new ArrayList<>();

        for(int i = 0; i < this.size(); i++)
        {
            if(this.count(this.characters.get(i)) > m)
            {
                thisMehrAlsM.add(this.characters.get(i));
            }
        }


        return new CharCollection(thisMehrAlsM);
    }

    public boolean equals(Object object)
    {
        if(this == object) return true;

        if(!(object instanceof CharCollection)) return false;

        CharCollection that = (CharCollection) object;

        ArrayList<Character> verschiedeneThis = this.getDifferentCharacters();

        for(int i = 0; i < verschiedeneThis.size(); i++)
        {
            if(
                    this.count(verschiedeneThis.get(i)) != that.count(verschiedeneThis.get(i))
            )
            {
                return false;
            }
        }

        return true;
    }



}
