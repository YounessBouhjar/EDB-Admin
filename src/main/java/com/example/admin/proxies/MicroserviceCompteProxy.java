package com.example.admin.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.admin.beans.CompteBean;

@FeignClient(name = "compte-service")

public interface MicroserviceCompteProxy {
	
	@PostMapping("/compte/add")
    public ResponseEntity<CompteBean> addCompte(@RequestBody CompteBean compte);
	
    @GetMapping("/compte/find/{numCompte}")
    public ResponseEntity<CompteBean> findCompteBynumCompte(@PathVariable("numCompte") String numCompte);
    @GetMapping("/compte/find/client/{nomClient}")
    public ResponseEntity<CompteBean> findCompteByNomClient(@PathVariable("nomClient") String nomClient);

    //debiter le compte
    @PutMapping("/compte/debiter/{id}/{solde}")
    public ResponseEntity<CompteBean> updateCompte(@PathVariable("id") Long id,@PathVariable("solde") float solde);
    //crediter le compte
    @PutMapping("/compte/crediter/{id}/{solde}")
    public ResponseEntity<CompteBean> crediterCompte(@PathVariable("id") Long id,@PathVariable("solde") float solde);
    
    @GetMapping("/compte/getBackoffice")
    public ResponseEntity<CompteBean> findCompteByNom();
    
    @PutMapping("/compte/updateSolde/{nomClient}")
	public ResponseEntity<CompteBean> updateSolde(@PathVariable("nomClient") String nomClient,@RequestParam(required = true) float solde);
}

