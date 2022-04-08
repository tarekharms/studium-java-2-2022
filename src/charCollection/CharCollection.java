package charCollection;

import java.util.*;

public class CharCollection {
    List<Character> characters;

    public CharCollection(char... characters)
    {
        this.characters = new ArrayList<>(characters.length);

        for(char character : characters) {
            this.characters.add(character);
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

    private CharCollection(List<Character> characters)
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

    private Set<Character> getDifferentCharacters()
    {
        Set<Character> vorhanden = new HashSet<>();

        for(int i = 0; i < this.size(); i++)
        {
            vorhanden.add(this.characters.get(i));
        }

        return vorhanden;
    }

    public char top()
    {
        Set<Character> verschiedene = this.getDifferentCharacters();

        char top = 0;
        int countTop = 0;

        for(Character candidate : verschiedene)
        {
            if(this.count(candidate) > countTop)
            {
                top = candidate;
                countTop = this.count(candidate);
            }
        }

        return top;
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

        Set<Character> verschiedeneThis = this.getDifferentCharacters();

        for(Character character : verschiedeneThis)
        {
            if(
                    this.count(character) != that.count(character)
            )
            {
                return false;
            }
        }

        return true;
    }

    public CharCollection except(CharCollection cc)
    {
        List<Character> ccList = new ArrayList<>(cc.characters);
        List<Character> newCharList = new ArrayList<>();

        for(Character character : this.characters)
        {
            if(ccList.contains(character))
            {
                ccList.remove(character);
            }
            else
            {
                newCharList.add(character);
            }
        }

        return new CharCollection(newCharList);
    }

    public boolean isSubset(CharCollection cc)
    {
        CharCollection vergleich = cc.except(this);

        return vergleich.size() == 0;
    }

}
