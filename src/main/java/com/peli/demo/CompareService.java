package com.peli.demo;

import com.peli.demo.model.Contact;
import org.apache.commons.text.similarity.JaccardSimilarity;

public class CompareService {

    private static final JaccardSimilarity jaccardSimilarity = new JaccardSimilarity();


    // This is a very simplistic comparison method, for a v2 I would implement a weighted comparison, Considering the importance of each field
    // For example if the email is the same, the similarity should be higher than if the address is the same

    public Double compareContacts(Contact c1, Contact c2) {
        String contact1Data = c1.getComparableString();
        String contact2Data = c2.getComparableString();
        return jaccardSimilarity.apply(contact1Data, contact2Data);
    }
}
