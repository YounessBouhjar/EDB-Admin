package com.example.admin.controller;

import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.admin.beans.AgentBean;
import com.example.admin.beans.ClientBean;
import com.example.admin.beans.TransfertBean;
import com.example.admin.model.Admin;
import com.example.admin.service.AdminService;


import com.example.admin.proxies.MicroserviceAgentProxy;
import com.example.admin.proxies.MicroserviceClientProxy;
import com.example.admin.proxies.MicroserviceTransfertProxy;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class AdminController {
	@Autowired
	MicroserviceAgentProxy microserviceAgentProxy;
	@Autowired
	MicroserviceTransfertProxy microserviceTransfertProxy;
	@Autowired
	MicroserviceClientProxy microserviceClientProxy;
	
	AdminService service;
	
	@Autowired
	public AdminController(AdminService service) {
		
		this.service=service;
	}
	
			//GET
			@GetMapping("/")
			@ResponseStatus(HttpStatus.OK)
			public String log()
			{	
				return "Welcome";
			
			}
			
			@GetMapping("/admins")
			@ResponseStatus(HttpStatus.OK)
			public List<Admin> getAdmins()
			{	
				return service.getAdmins();
			}
			
			
			@GetMapping("/admin/username/{username}")
			@ResponseStatus(HttpStatus.OK)
			public Admin getByUsername(@PathVariable(name="username") String username)
			{
				return service.getByEmail(username);
			}
		//POST
			
			@PostMapping("/admins")
			@ResponseStatus(HttpStatus.CREATED)
			public void addAdmin(@RequestBody Admin admin)
			{
				service.addAdmin(admin);
			}
			
	
			@GetMapping("/agent/all")
		    ResponseEntity<List<AgentBean>> getAllAgent() {
		        return microserviceAgentProxy.getAllAgent();
		    }
			@GetMapping("/agent/{email}")
		    ResponseEntity<AgentBean> getAgentByEmail (@PathVariable("email") String email){
		        return microserviceAgentProxy.getAgentByEmail(email);
		    }
			@GetMapping("/agentid/{id}")
		    ResponseEntity<AgentBean> getAgentById (@PathVariable("id") Long id){
		        return microserviceAgentProxy.getAgentById(id);
		    }
			@PutMapping("/agent/update/{id}")
		    ResponseEntity<AgentBean> updateAgent(@PathVariable("id") Long id,@RequestBody AgentBean agent){
		        return microserviceAgentProxy.updateAgent(id,agent);
		    }
			@PostMapping("/agent/add")
		    ResponseEntity<AgentBean> addAgent(@RequestBody AgentBean agent){
		        return microserviceAgentProxy.addAgent(agent);
		    }
			@DeleteMapping("agent/delete/{id}")
		    public ResponseEntity<AgentBean> deleteAgent(@PathVariable("id") Long id){
		        return microserviceAgentProxy.deleteAgent(id);
		    }
			
	

			@GetMapping("/alltransferts")
		     ResponseEntity<List<TransfertBean>> getAllTransferts () {
		        return microserviceTransfertProxy.getAllTransferts();
		    }
			@GetMapping("/all/client/{idClient}")
		     ResponseEntity<List<TransfertBean>> getTransfertByIdClient(@PathVariable("idClient") Long idClient){
		        return microserviceTransfertProxy.getTransfertByIdClient(idClient);
		    }
			@GetMapping("/all/beneficiaire/{idBeneficiaire}")
		      ResponseEntity<List<TransfertBean>> getTransfertByIdBeneficiaire(@PathVariable("idBeneficiaire") Long idBeneficiaire){
		        return microserviceTransfertProxy.getTransfertByIdBeneficiaire(idBeneficiaire);
		    }
			@GetMapping("/all/beneficiaire/{idClient}/{idBeneficiaire}")
		     ResponseEntity<List<TransfertBean>> getTransfertByIdClientAndIdBeneficiaire(@PathVariable("idClient") Long idClient,@PathVariable("idBeneficiaire") Long idBeneficiaire){
		        return microserviceTransfertProxy.getTransfertByIdClientAndIdBeneficiaire(idClient, idBeneficiaire);
		    }

			
			@PatchMapping("/update/{id}/{nombreJours}")
		     ResponseEntity<TransfertBean> updateNombreJours(@PathVariable("id") Long id, @PathVariable("nombreJours") int nombreJours){
		     return microserviceTransfertProxy.updateNombreJours(id, nombreJours);
			}
			
			 @PatchMapping("/update/status/{id}/{status}")
		     ResponseEntity<TransfertBean> updateStatus(@PathVariable("id") Long id, @PathVariable("status") String status){
				 return microserviceTransfertProxy.updateStatus(id, status);
			 }
			 
			 @GetMapping("client/all")
			    public ResponseEntity<List<ClientBean>> getAllClients (){
				  	return microserviceClientProxy.getAllClients();

			 } 
		    
			 @PutMapping("client/updateClient/{clientId}")
			    public ResponseEntity<ClientBean> updateClient(@PathVariable("clientId")Long clientId,@RequestBody ClientBean client){
				 return microserviceClientProxy.updateClient(clientId, client);
			 }
			 
//			 @GetMapping("/transCrit/{idAgent}/{idClient}/{pi}/{numGsm}/{codeTransfert}/{status}")
//			    public ResponseEntity<List<TransfertBean>> getTransCrit (@PathVariable("idAgent") Long idAgent,@PathVariable("idClient") Long idClient,@PathVariable("pi") String pi,
//			    		@PathVariable("numGsm") String numGsm,@PathVariable("codeTransfert") String codeTransfert,@PathVariable("status") String status){
//				 return microserviceTransfertProxy.getTransCrit(idAgent, idClient, pi, numGsm, codeTransfert, status);
//			 }
			 @PostMapping("/TranSearch")
			    public ResponseEntity<List<TransfertBean>> getTransCrit (    		
			    		@RequestParam(required = false) Long idAgent,
			    		@RequestParam(required = false) Long idClient,
			    		@RequestParam(required = false) String pi,
			    		@RequestParam(required = false) String numGsm,
			    		@RequestParam(required = false) String codeTransfert,
			    		@RequestParam(required = false) String status){
			    return microserviceTransfertProxy.getTransCrit(idAgent, idClient, pi, numGsm, codeTransfert, status);
			 }
			 @GetMapping("/transferts/export/excel")
			    public ResponseEntity<List<TransfertBean>> exportToExcel(HttpServletResponse response,    		
			    		@RequestParam(required = false) Long idAgent,
			    		@RequestParam(required = false) Long idClient,
			    		@RequestParam(required = false) String pi,
			    		@RequestParam(required = false) String numGsm,
			    		@RequestParam(required = false) String codeTransfert,
			    		@RequestParam(required = false) String status) {
			    return microserviceTransfertProxy.exportToExcel(response, idAgent, idClient, pi, numGsm, codeTransfert, status);
			    }
}