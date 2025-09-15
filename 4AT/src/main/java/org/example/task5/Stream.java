package org.example.task5;

//Stream:
//a) Generate 10 random objects using a class from a previous task
//b) Sort it using any two fields using stream.
//c) Filter it by any two fields custom filter.
//d) Collect it to List with *main field(s).


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stream {
    public static void main(String[] args) {
        List<Meeting> meets = new ArrayList<>();


        IntStream.range(0, 10).forEach(e -> {
            Meeting elem = new Meeting();
            elem.setDate("2023-01-" + e);
            elem.setDescription(UUID.randomUUID().toString() + e);
            elem.setTitle( UUID.randomUUID().toString().substring(0, 5));

            meets.add(elem);
        });

        meets.forEach(System.out::println);
        System.out.println("____");

        meets.stream().sorted(
                Comparator.comparing(Meeting::getTitle)
                    .thenComparing(Meeting::getDate, Comparator.reverseOrder())
                ).forEach(System.out::println);

        System.out.println("____");

        List<Meeting> resList =  meets.stream().filter(e -> e.getDate().equals("2023-01-1")).collect(Collectors.toList());
        System.out.println(resList);


        if(args.length > 0){
            System.out.println("Args 0: " + args[0]);
        }
    }
}
