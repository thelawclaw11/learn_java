import org.jetbrains.annotations.NotNull;

import java.awt.font.NumericShaper;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

class Name implements Comparable<Name>{
    private final String firstName, lastName;

    public Name(String firstName, String lastName){
        if(firstName == null || lastName == null){
            throw new NullPointerException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public boolean equals(Object o){
        if(!(o instanceof  Name)){
            return false;
        }
        Name n = (Name)o;
        return n.firstName.equals(firstName) && n.lastName.equals(lastName);
    }

    public int hashCode(){
        return 31 * firstName.hashCode() + lastName.hashCode();
    }

    public String toString(){
        return firstName + " " + lastName;
    }

    public int compareTo(Name n){
        int lastCmp = lastName.compareTo(n.lastName);
        return lastCmp != 0 ? lastCmp : firstName.compareTo(n.firstName);
    }
}

class Sort{
    static final Comparator<Name> ByFirstName = new Comparator<Name>() {
        @Override
        public int compare(Name o1, Name o2) {
            return o1.getFirstName().compareTo(o2.getFirstName());
        }
    };
}

public class CollectionsDemo {

    public static void main(String[] args){
        Name nameArray[] = {
                new Name("Canon", "Law"),
                new Name("Belinda", "Blumenthal"),
                new Name("James", "Godwin")
        };

        List<Name> names = Arrays.asList(nameArray);

        Collections.sort(names, Sort.ByFirstName);
        System.out.println(names);

    }
}
