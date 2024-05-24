package com.peli.demo;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.exceptions.CsvException;
import com.peli.demo.model.Comparison;
import com.peli.demo.model.Contact;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOService {

    // This is a simple CSV reader, it reads the file and maps the rows to a list of Contact

    public List<Contact> readContacts(String fileLocation) {
        List<Contact> data = new ArrayList<>();

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(fileLocation))
              .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
              .build()
        ) {
            String[] row;
            reader.skip(1); // Skip header row

            while ((row = reader.readNext()) != null) {
                Contact contact = new Contact(row);
                data.add(contact);
            }
        } catch (IOException | CsvException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return data;
    }

    public void writeResults(String fileLocation, List<Comparison> comparisons) {
        try (CSVWriter writer = (CSVWriter) new CSVWriterBuilder(new FileWriter(fileLocation))
              .withSeparator(';')
              .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
              .build()
        ) {
            // Write data
            for (Comparison comparison : comparisons) {
                writer.writeNext(toCSV(comparison));
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private String[] toCSV(Comparison comparison) {
        return new String[] {
              comparison.getAccuracy().toString(),
              String.format(("%.2f"), comparison.getSimilarity()),
              comparison.getContact1().getComparableString(),
              comparison.getContact2().getComparableString()
        };
    }
}
