package com.example.demowithfirebase.repository;

import com.example.demowithfirebase.model.CustomerExample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class CustomerExampleRepository {

    private static final String COLLECTION_NAME = "customers"; // Firestore collection

    private final Firestore firestore;

    public CustomerExampleRepository() {
        this.firestore = FirestoreClient.getFirestore();
    }

    /**
     * Hämta en kund baserat på e-post
     */
    public Optional<CustomerExample> getCustomerByEmail(String email) {
        CollectionReference customers = firestore.collection(COLLECTION_NAME);
        Query query = customers.whereEqualTo("email", email).limit(1);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        try {
            for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
                return Optional.of(document.toObject(CustomerExample.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
