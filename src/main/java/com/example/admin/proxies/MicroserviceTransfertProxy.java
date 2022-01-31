package com.example.admin.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.admin.beans.TransfertBean;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

@FeignClient(name = "transfert-service",url = "localhost:8084/transfert")
public interface MicroserviceTransfertProxy{
	@GetMapping("/alltransferts")
     ResponseEntity<List<TransfertBean>> getAllTransferts ();
    
	@GetMapping("/all/client/{idClient}")
     ResponseEntity<List<TransfertBean>> getTransfertByIdClient(@PathVariable("idClient") Long idClient);
    
     @GetMapping("/all/beneficiaire/{idBeneficiaire}")
      ResponseEntity<List<TransfertBean>> getTransfertByIdBeneficiaire(@PathVariable("idBeneficiaire") Long idBeneficiaire);
    
	@GetMapping("/all/beneficiaire/{idClient}/{idBeneficiaire}")
     ResponseEntity<List<TransfertBean>> getTransfertByIdClientAndIdBeneficiaire(@PathVariable("idClient") Long idClient,@PathVariable("idBeneficiaire") Long idBeneficiaire);

	    @PatchMapping("/update/{id}/{nombreJours}")
     ResponseEntity<TransfertBean> updateNombreJours(@PathVariable("id") Long id, @PathVariable("nombreJours") int nombreJours);
    
    @PatchMapping("/update/status/{id}/{status}")
     ResponseEntity<TransfertBean> updateStatus(@PathVariable("id") Long id, @PathVariable("status") String status);
    
//    @GetMapping("/transCrit/{idAgent}/{idClient}/{pi}/{numGsm}/{codeTransfert}/{status}")
//     ResponseEntity<List<TransfertBean>> getTransCrit (@PathVariable("idAgent") Long idAgent,@PathVariable("idClient") Long idClient,@PathVariable("pi") String pi,
//    		@PathVariable("numGsm") String numGsm,@PathVariable("codeTransfert") String codeTransfert,@PathVariable("status") String status);
//    
    @PostMapping("/TranSearch")
    public ResponseEntity<List<TransfertBean>> getTransCrit (    		
    		@RequestParam(required = false) Long idAgent,
    		@RequestParam(required = false) Long idClient,
    		@RequestParam(required = false) String pi,
    		@RequestParam(required = false) String numGsm,
    		@RequestParam(required = false) String codeTransfert,
    		@RequestParam(required = false) String status);

    @GetMapping("/transferts/export/excel")
    public ResponseEntity<List<TransfertBean>> exportToExcel(HttpServletResponse response,    		
    		@RequestParam(required = false) Long idAgent,
    		@RequestParam(required = false) Long idClient,
    		@RequestParam(required = false) String pi,
    		@RequestParam(required = false) String numGsm,
    		@RequestParam(required = false) String codeTransfert,
    		@RequestParam(required = false) String status);
}
