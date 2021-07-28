package modernJavaInAction;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

enum Color {RED, GREEN}


interface Predicate<T>{
    boolean test (T t);
}


class Apple{
    private final Color color;
    private final int weight;

    Apple(Color color, int weight){
        this.color = color;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color=" + color +
                ", weight=" + weight +
                '}';
    }
}

class AppleHeavyWeightPredicate implements Predicate<Apple>{
    public boolean test(Apple apple){
        return apple.getWeight() > 100;
    }
}


class AppleRedColorPredicate implements Predicate<Apple>{
    public boolean test(Apple apple){
        return Color.RED.equals(apple.getColor());
    }
}

interface AppleStringifyer{
    String stringify(Apple apple);
}

class AppleIsHeavyColorStringifyer implements AppleStringifyer{
    public String stringify(Apple apple) {
        boolean isHeavy = apple.getWeight() > 100;
        String isHeavyMessage = isHeavy ? "yes" : "no";
       return "Color = " + apple.getColor() + ", isHeavy = " + isHeavyMessage;
    }
}

@FunctionalInterface
interface Function2<One, Two, Return>{
    Return apply(One one, Two two);
}

class Person{
    String firstName;
    String lastName;

    Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    String customGreeting(Function2<String, String, String> func){
        return func.apply(firstName, lastName);
    }
}


public class Chapter2 {

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate){
        List<T> result = new ArrayList<>();
        for(T element : list){
            if(predicate.test(element)){
                result.add(element);
            }
        }
        return result;
    }

    public static void prettyPrintApples(List<Apple> inventory, AppleStringifyer appleStringifyer){
        for(Apple apple: inventory){
            String output = appleStringifyer.stringify(apple);
            System.out.println(output);
        }
    }

    public static Predicate<Apple> makeColorPredicate(Color color){
        return apple -> color.equals(apple.getColor());
    }

    public static void main(String[] args){
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(Color.GREEN, 100));
        inventory.add(new Apple(Color.RED, 150));
        inventory.add(new Apple(Color.GREEN, 200));
        inventory.add(new Apple(Color.RED, 50));


//
//        inventory.sort(new Comparator<Apple>() {
//            public int compare(Apple left, Apple right) {
//                return Integer.compare(left.getWeight(), right.getWeight());
//            }
//        });

//        inventory.sort(Comparator.comparingInt(Apple::getWeight));
//
//        System.out.println(inventory);


        Predicate<Apple> isGreen = makeColorPredicate(Color.GREEN);
        Predicate<Apple> isRed = makeColorPredicate(Color.RED);
        Predicate<Apple> isHeavy = apple -> apple.getWeight() > 100;

        List<Apple> greenApples = filter(inventory, isGreen);
        System.out.println(greenApples);

        List<Apple> redApples = filter(inventory, isRed);
        System.out.println(redApples);

        List<Apple> bigApples = filter(inventory, isHeavy);
        System.out.println(bigApples);

        Person canon = new Person("Canon", "Law");
        System.out.println(canon.customGreeting((firstName,lastName) -> "Hi, " + "I am " + firstName + " " + lastName));


    }
}
