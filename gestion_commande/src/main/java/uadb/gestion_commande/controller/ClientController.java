package uadb.gestion_commande.controller;

import uadb.gestion_commande.entity.Client;
import uadb.gestion_commande.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    // Récupérer tous les clients
    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Récupérer un client par ID
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    // Créer un nouveau client
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    // Mettre à jour un client existant
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        Client existingClient = clientRepository.findById(id).orElse(null);
        if (existingClient != null) {
            if (clientDetails.getNom() != null) existingClient.setNom(clientDetails.getNom());
            if (clientDetails.getPrenom() != null) existingClient.setPrenom(clientDetails.getPrenom());
            if (clientDetails.getEmail() != null) existingClient.setEmail(clientDetails.getEmail());
            if (clientDetails.getAdresse() != null) existingClient.setAdresse(clientDetails.getAdresse());
            if (clientDetails.getCodePostal() != null) existingClient.setCodePostal(clientDetails.getCodePostal());
            if (clientDetails.getVille() != null) existingClient.setVille(clientDetails.getVille());
            if (clientDetails.getPays() != null) existingClient.setPays(clientDetails.getPays());
            return clientRepository.save(existingClient);
        }
        return null;
    }


    // Supprimer un client
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
    }
}
