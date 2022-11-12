import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //1 task
        Stream<Person> personStream = persons.stream();
        long countPersonsLess18 = personStream
                .filter(person -> person.getAge() > 18)
                .count();
//        System.out.println(countPersonsLess18);

        //2 task
        Stream<Person> listStream18to27 = persons.stream();
        List<String> arr18to27 = listStream18to27
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() > 17 && person.getAge() < 28)
                .map(Person::getFamily)
                .collect(Collectors.toList());
//        System.out.println(arr18to27);

        //3 task
        Stream<Person> listStream18to65 = persons.stream();
        List<String> arr18to65 = listStream18to65
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> (person.getSex() == Sex.MAN) ?  person.getAge() < 65 : person.getAge() <60)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily)
                .collect(Collectors.toList());
//        System.out.println(arr18to65);

    }
}
