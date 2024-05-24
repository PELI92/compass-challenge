package com.peli.demo.model;

import lombok.Value;

@Value
public class Comparison {
    Contact contact1;
    Contact contact2;

    Double similarity;
    Accuracy accuracy;

    public Comparison(Contact contact1, Contact contact2, Double similarity) {
        this.contact1 = contact1;
        this.contact2 = contact2;
        this.similarity = similarity;
        this.accuracy = Accuracy.valueOf(similarity);
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f)  -> \n %s \n %s \n", accuracy, similarity, contact1, contact2);
    }
}
