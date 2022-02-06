package com.example.admin.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.admin.beans.ClientBean;

import java.util.List;

@FeignClient(name = "client-service")
public interface MicroserviceClientProxy{
	 @GetMapping("/client/all")
	    public ResponseEntity<List<ClientBean>> getAllClients () ;
    
	 @PutMapping("/client/updateClient/{clientId}")
	    public ResponseEntity<ClientBean> updateClient(@PathVariable("clientId")Long clientId,@RequestBody ClientBean client);
    
	 @GetMapping("/client/findid/{id}")
	    public ResponseEntity<ClientBean> findClientById(@PathVariable("id") Long id);
    
	 @GetMapping("/client/findgsm/{gsm}")
	    public ResponseEntity<ClientBean> findClientByGSM(@PathVariable("gsm") String gsm);
    
	 @GetMapping("/client/findcin/{cin}")
	    public ResponseEntity<ClientBean> findClientByCin(@PathVariable("cin") String cin);
}
