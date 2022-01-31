package com.example.admin.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.admin.beans.AgentBean;


import java.util.List;

@FeignClient(name = "agent-service",url = "localhost:8091/agent")
public interface MicroserviceAgentProxy{
    @GetMapping("/all")
    ResponseEntity<List<AgentBean>> getAllAgent ();
    
    @GetMapping("/agent/{email}")
    ResponseEntity<AgentBean> getAgentByEmail (@PathVariable("email") String email);
    
    @GetMapping("/agentid/{id}")
    ResponseEntity<AgentBean> getAgentById (@PathVariable("id") Long id);
    
    
    @PutMapping("/update/{id}")
    ResponseEntity<AgentBean> updateAgent(@PathVariable("id") Long id,@RequestBody AgentBean agent);
    
    //add compte from agent
    @PostMapping("/add")
    ResponseEntity<AgentBean> addAgent(@RequestBody AgentBean agent);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<AgentBean> deleteAgent(@PathVariable("id") Long id);
    
    
}
