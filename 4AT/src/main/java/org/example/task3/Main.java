//V6.
//        Array Intersection: Write a Java program that takes two arrays of integers and finds their intersection. Your program should prompt the user to enter the size and the elements of both arrays and then output the common elements between them.
//        Make HashSet of doubles from the result array and perform the following operations: a)Add an element to the end of the list; b)Remove an element from the list; c)Replace an element in the list; d)Sort the list in alphabetical order; e)Print the elements of the list;
//        Make up the situation for NumberFormatException. Catch it and display the explanation for your custom case.

package org.example.task3;

import java.util.*;

class ArrayIntersection {
    List<Integer> arr1;
    List<Integer> arr2;

    public ArrayIntersection( List<Integer> arr1, List<Integer> arr2){
        this.arr1 = arr1;
        this.arr2 = arr2;
    }
//
//    public List<Integer> findIntersection(){
//        List<Integer> res = new ArrayList<>();
//
//        for(int i = 0; i < arr1.size(); i++){
//            for(int j = 0; j < arr2.size(); j++){
//                if(arr1.get(i) == arr2.get(j)){
//                    res.add(arr1.get(i));
//                }
//            }
//        }
//
//        return res;
//    }

    public List<Integer> findIntersection(){
        List<Integer> res = new ArrayList<>(arr1);
        res.retainAll(arr2);

        return res;
    }

    public void operationsWithHashSet(List<Integer> array){
        HashSet<Double> newHash = new HashSet<>(
                array.stream()
                        .map(Integer::doubleValue)
                        .toList()
        );

        // a)Add an element to the end of the list;
        newHash.add(3.2);

        // b)Remove an element from the list;
        if (!newHash.isEmpty()) {
            newHash.remove(3.2);
        }

        // c)Replace an element in the list;
        if (!newHash.isEmpty()) {
            Double first = newHash.iterator().next(); // отримуємо будь-який елемент
            newHash.remove(first);
            newHash.add(7.7);
        }

        // d)Sort the list in alphabetical order;
        TreeSet<Double> sortedSet = new TreeSet<>(Comparator.comparing(String::valueOf));
        sortedSet.addAll(newHash);

        //e)Print the elements of the list;
        System.out.println(newHash);
    }
}

public class Main {
    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        Scanner s = new Scanner(System.in);
        try {
            System.out.println("Enter size of your 1 Array: ");
            int sizeOfList1 = s.nextInt();

            System.out.println("Enter elements of your 1 Array ");
            for( int i = 0; i < sizeOfList1; i++){
                System.out.println("Enter element on index " + i + ": ");
                int elem = s.nextInt();
                list1.add(elem);
            }

            System.out.println("Entered Array: " + list1);

            System.out.println("Enter size of your 2 Array: ");
            int sizeOfList2 = s.nextInt();

            System.out.println("Enter elements of your 2 Array ");
            for( int i = 0; i < sizeOfList2; i++){
                System.out.println("Enter element on index " + i + ": ");
                int elem = s.nextInt();
                list2.add(elem);
            }

            System.out.println("Entered Array: " + list2);

        } catch (InputMismatchException e) {
            System.out.println("Помилка! вводьте тільки цифри: " + e.getMessage());
        }

        s.close();

        ArrayIntersection arr = new ArrayIntersection(list1, list2);
        List<Integer> resultArr = arr.findIntersection();

        arr.operationsWithHashSet(resultArr);
    }
}