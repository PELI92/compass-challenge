package com.peli.demo;

import com.peli.demo.model.Accuracy;
import com.peli.demo.model.Comparison;
import com.peli.demo.model.Contact;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {

    // The first thing I did was to export the .xlsx file to a .csv file, so I could read it with a CSV parser (.xlsx is really annoying to work with)
    private static final String INPUT_FILE_LOCATION = "src/main/resources/files/input/contacts.csv";
    private static final String OUTPUT_FILE_LOCATION_HIGH = "src/main/resources/files/output/results_high.csv";
    private static final String OUTPUT_FILE_LOCATION_MEDIUM = "src/main/resources/files/output/results_medium.csv";
    private static final String OUTPUT_FILE_LOCATION_LOW = "src/main/resources/files/output/results_low.csv";

    private static final CompareService compareService = new CompareService();
    private static final IOService ioService = new IOService();

    public static void main(String[] args) {
        System.out.println("INIT PROCESS");
        List<Contact> contacts = ioService.readContacts(INPUT_FILE_LOCATION);

        List<Comparison> comparisons = new ArrayList<>();

        // This is a O(n^2) operation, so it will be slow for large dataset, but for this small dataset it's fine
        // A better approach would be to use a LSH (Locality Sensitive Hashing) algorithm to reduce the number of comparisons by ignoring the ones that are very different
        for (int i = 0; i < contacts.size(); i++) {
            for (int j = i + 1; j < contacts.size(); j++) {

                Contact contact1 = contacts.get(i);
                Contact contact2 = contacts.get(j);

                Double similarity = compareService.compareContacts(contacts.get(i), contacts.get(j));
                comparisons.add(new Comparison(contact1, contact2, similarity));
            }
        }

        Map<Accuracy, List<Comparison>> groupedComparisons = comparisons.stream()
              .collect(Collectors.groupingBy(Comparison::getAccuracy));

        ioService.writeResults(OUTPUT_FILE_LOCATION_HIGH, groupedComparisons.get(Accuracy.HIGH));
        ioService.writeResults(OUTPUT_FILE_LOCATION_MEDIUM, groupedComparisons.get(Accuracy.MEDIUM));
        ioService.writeResults(OUTPUT_FILE_LOCATION_LOW, groupedComparisons.get(Accuracy.LOW));

        printResults(groupedComparisons);

        System.out.println("END PROCESS");
    }

    // This method is just to print the results
    static private void printResults(Map<Accuracy, List<Comparison>> groupedComparisons) {
        System.out.println("*********************************");
        System.out.println("Results:");
        System.out.println("    High accuracy: " + groupedComparisons.get(Accuracy.HIGH).size());
        System.out.println("    Medium accuracy: " + groupedComparisons.get(Accuracy.MEDIUM).size());
        System.out.println("    Low accuracy: " + groupedComparisons.get(Accuracy.LOW).size());
        System.out.println("*********************************");
    }
}


