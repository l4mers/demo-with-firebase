package com.example.demowithfirebase.auth;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class FirebaseInitialization {

    @PostConstruct
    public void initialization() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder() // Path till din secret key som du laddad ner från Firebase
                    // Denna kan du ta med på olika sett. i projektet (inte rekommenderat), vid deploy eller global variabel
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream("app/imports/key.json")))
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
