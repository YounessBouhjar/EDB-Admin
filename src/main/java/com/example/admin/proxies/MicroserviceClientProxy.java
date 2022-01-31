package com.example.admin.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.admin.beans.ClientBean;

import java.util.List;

@FeignClient(name = "client-service",url = "localhost:8083/")
public interface MicroserviceClientProxy{
	 @GetMapping("client/all")
	    public ResponseEntity<List<ClientBean>> getAllClients () ;
    
	 @PutMapping("client/updateClient/{clientId}")
	    public ResponseEntity<ClientBean> updateClient(@PathVariable("clientId")Long clientId,@RequestBody ClientBean client);
    

    

    
    
}
