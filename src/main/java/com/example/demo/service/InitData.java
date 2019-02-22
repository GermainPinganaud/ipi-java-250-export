package com.example.demo.service;

import com.example.demo.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;

/**
 * Classe permettant d'insérer des données dans l'application.
 */
@Service
@Transactional
public class InitData implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private EntityManager em;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        insertTestData();
    }

    private void insertTestData() {
        Client client1 = newClient("PETRILLO", "Alexandre", LocalDate.of(1983, 12, 19));
        em.persist(client1);

        Client client2 = newClient("DUPONT", "Jérome", LocalDate.of(1957, 4, 3));
        em.persist(client2);

        Client client3 = newClient("PINGANAUD", "Germain", LocalDate.of(1984, 8, 30));
        em.persist(client3);
    }

    private Client newClient(String nom, String prenom, LocalDate birthdate) {
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setBirthdate(birthdate);
        return client;
    }
}
