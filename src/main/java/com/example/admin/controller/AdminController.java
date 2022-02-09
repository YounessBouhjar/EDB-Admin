package com.example.admin.controller;

import java.util.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.admin.beans.AgentBean;
import com.example.admin.beans.BeneficiaireBean;
import com.example.admin.beans.ClientBean;
import com.example.admin.beans.CompteBean;
import com.example.admin.beans.NotificationBean;
import com.example.admin.beans.TransfertBean;
import com.example.admin.exceptions.DuplicatedException;
import com.example.admin.model.Admin;
import com.example.admin.service.AdminService;
import com.example.admin.proxies.MicroserviceAgentProxy;
import com.example.admin.proxies.MicroserviceBeneficiaireProxy;
import com.example.admin.proxies.MicroserviceClientProxy;
import com.example.admin.proxies.MicroserviceCompteProxy;
import com.example.admin.proxies.MicroserviceNotificationProxy;
import com.example.admin.proxies.MicroserviceTransfertProxy;


@RestController
@RequestMapping("/admin")

@CrossOrigin
public class AdminController {
	@Autowired
	MicroserviceAgentProxy microserviceAgentProxy;
	@Autowired
	MicroserviceTransfertProxy microserviceTransfertProxy;
	@Autowired
	MicroserviceClientProxy microserviceClientProxy;
	@Autowired
	MicroserviceBeneficiaireProxy microserviceBeneficiaireProxy;
	@Autowired
	MicroserviceCompteProxy microserviceCompteProxy;
	@Autowired
	MicroserviceNotificationProxy microserviceNotificationProxy;
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
			
			@GetMapping("/all")
			@ResponseStatus(HttpStatus.OK)
			public List<Admin> getAdmins()
			{	
				return service.getAdmins();
			}
			
			@GetMapping("/username/{username}")
			@ResponseStatus(HttpStatus.OK)
			public Admin getByUsername(@PathVariable(name="username") String username)
			{
				return service.getByEmail(username);
			}
		//POST
			
			@PostMapping("/add")
			@ResponseStatus(HttpStatus.CREATED)
			public void addAdmin(@RequestBody Admin admin) throws DuplicatedException
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
			@GetMapping("/agent/agentid/{id}")
		    ResponseEntity<AgentBean> getAgentById (@PathVariable("id") Long id){
		        return microserviceAgentProxy.getAgentById(id);
		    }
			 @PutMapping("/agent/update")
			    public ResponseEntity<?> update( @RequestBody AgentBean agent){
				 return microserviceAgentProxy.update(agent);
			 }
			@PostMapping("/agent/add")
		    ResponseEntity<AgentBean> addAgent(@RequestBody AgentBean agent){
		        return microserviceAgentProxy.addAgent(agent);
		    }
			@DeleteMapping("/agent/delete/{id}")
		    public ResponseEntity<AgentBean> deleteAgent(@PathVariable("id") Long id){
		        return microserviceAgentProxy.deleteAgent(id);
		    }
			
	

			@GetMapping("/transfert/alltransferts")
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
			 
			 @GetMapping("/client/all")
			    public ResponseEntity<List<ClientBean>> getAllClients (){
				  	return microserviceClientProxy.getAllClients();

			 } 
		    
			 @PutMapping("/client/updateClient/{clientId}")
			    public ResponseEntity<ClientBean> updateClient(@PathVariable("clientId")Long clientId,@RequestBody ClientBean client){
				 return microserviceClientProxy.updateClient(clientId, client);
			 }
			 
//			 @GetMapping("/transCrit/{idAgent}/{idClient}/{pi}/{numGsm}/{codeTransfert}/{status}")
//			    public ResponseEntity<List<TransfertBean>> getTransCrit (@PathVariable("idAgent") Long idAgent,@PathVariable("idClient") Long idClient,@PathVariable("pi") String pi,
//			    		@PathVariable("numGsm") String numGsm,@PathVariable("codeTransfert") String codeTransfert,@PathVariable("status") String status){
//				 return microserviceTransfertProxy.getTransCrit(idAgent, idClient, pi, numGsm, codeTransfert, status);
//			 }
			 @PostMapping("/transfert/tranSearch")
			    public ResponseEntity<List<TransfertBean>> getTransCrit (    		
			    		@RequestParam(required = false) Long idAdmin,
			    		@RequestParam(required = false) Long idClient,
			    		@RequestParam(required = false) String pi,
			    		@RequestParam(required = false) String numGsm,
			    		@RequestParam(required = false) String codeTransfert,
			    		@RequestParam(required = false) String status){
			    return microserviceTransfertProxy.getTransCrit(idAdmin, idClient, pi, numGsm, codeTransfert, status);
			 }
//			 @GetMapping("/transferts/export/excel")
//			    public ResponseEntity<List<TransfertBean>> exportToExcel(HttpServletResponse response,    		
//			    		@RequestParam(required = false) Long idAgent,
//			    		@RequestParam(required = false) Long idClient,
//			    		@RequestParam(required = false) String pi,
//			    		@RequestParam(required = false) String numGsm,
//			    		@RequestParam(required = false) String codeTransfert,
//			    		@RequestParam(required = false) String status) {
//			    return microserviceTransfertProxy.exportToExcel(response, idAgent, idClient, pi, numGsm, codeTransfert, status);
//			    }
			 
			 @GetMapping("/transfert/find/{codetransfert}")
				public ResponseEntity<TransfertBean> getTransfertByCodeTransfert(@PathVariable("codetransfert") String codeTransfert){
				 return microserviceTransfertProxy.getTransfertByCodeTransfert(codeTransfert);
			 }
			 
			 @GetMapping("/client/findid/{id}")
			    public ResponseEntity<ClientBean> findClientById(@PathVariable("id") Long id){
				 return microserviceClientProxy.findClientById(id);
			 }
			 @GetMapping("/beneficiaire/findid/{id}")
			    public ResponseEntity<BeneficiaireBean> getBeneficiaire (@PathVariable("id") Long id){
			 return microserviceBeneficiaireProxy.getBeneficiaire(id);
			 }
			    
//			 @PutMapping("/transfert/status/{codeTransfert}")
//			    public ResponseEntity<TransfertBean> restituerTransfert(@PathVariable("codeTransfert") String codeTransfert){
//					return microserviceTransfertProxy.restituerTransfert(codeTransfert);
//				}	   
			 
			 @PutMapping("/transfert/status/{codeTransfert}")
				public ResponseEntity<TransfertBean> updateTransfert(@PathVariable("codeTransfert") String codeTransfert,@RequestParam(required = true) String status,@RequestParam(required = true) String motif){
			 return  microserviceTransfertProxy.updateTransfert(codeTransfert, status, motif);
			 }
		    @PostMapping("/transfert/add")
		    public ResponseEntity<TransfertBean> addtransfert(@RequestBody TransfertBean transfert){
		    	return microserviceTransfertProxy.addtransfert(transfert);
		    }
			    
			 @GetMapping("/client/findgsm/{gsm}")
			    public ResponseEntity<ClientBean> findClientByGSM(@PathVariable("gsm") String gsm){
				 return microserviceClientProxy.findClientByGSM(gsm);
			 }
			 
			 @GetMapping("/client/findcin/{cin}")
			    public ResponseEntity<ClientBean> findClientByCin(@PathVariable("cin") String cin){
				 return microserviceClientProxy.findClientByCin(cin);
			 }
			 @PostMapping("/compte/add")
			    public ResponseEntity<CompteBean> addCompte(@RequestBody CompteBean compte){
				 return microserviceCompteProxy.addCompte(compte);
			 }
			 
			 @GetMapping("/compte/find/client/{nomClient}")
			    public ResponseEntity<CompteBean> findCompteByNomClient(@PathVariable("nomClient") String nomClient) {
				return microserviceCompteProxy.findCompteByNomClient(nomClient);
			}
			    @GetMapping("/compte/getBackoffice")
			    public ResponseEntity<CompteBean> findCompteByNom(){
			    	return microserviceCompteProxy.findCompteByNom();
			    }
			    
			    @PutMapping("/compte/debiter/{id}/{solde}")
			    public ResponseEntity<CompteBean> updateCompte(@PathVariable("id") Long id,@PathVariable("solde") float solde){
					return microserviceCompteProxy.updateCompte(id, solde);
			    	
			    }
			    @PutMapping("/compte/crediter/{id}/{solde}")
			    public ResponseEntity<CompteBean> crediterCompte(@PathVariable("id") Long id,@PathVariable("solde") float solde){
					return microserviceCompteProxy.crediterCompte(id, solde);
			    	
			    }
			    
			    @PutMapping("/compte/updateSolde/{nomClient}")
				public ResponseEntity<CompteBean> updateSolde(@PathVariable("nomClient") String nomClient,@RequestParam(required = true) float solde){
			    	return microserviceCompteProxy.updateSolde(nomClient, solde);
			    }
			    @PostMapping("/notification/send")
			    public void sendSms(@Valid @RequestBody NotificationBean smsRequest) {

			    }
				@GetMapping("/adminid/{id}")
				public ResponseEntity<Admin> getAdminByID (@PathVariable("id") Long id) {
					Admin admin=service.getAdminById(id);
				    return new ResponseEntity<Admin>(admin,HttpStatus.OK);
				}
				
				
				@PutMapping("/updateadmin/{id}")
				public void updateAdmin(@PathVariable Long id , @RequestBody(required=false) Admin admin) throws DuplicatedException
				{
					service.updateAdmin(admin);
				}
				
//				public Admin> updateAdmin(@PathVariable Long id ,@RequestBody Admin admin) throws Exception {
//					Admin updateAdmin = service.updateAdmin(id,admin);
//				    return new ResponseEntity<Admin>(updateAdmin, HttpStatus.OK);
//				}
				@PutMapping("/update")
			    public ResponseEntity<?> update(@Valid @RequestBody Admin admin) throws Exception {
			        if (admin == null)
			            return ResponseEntity.badRequest().body("The provided movie is not valid");
			        return ResponseEntity
			                .ok()
			                .body(service.updateAdmin(admin));
			    }

				
				@DeleteMapping("/delete/{id}")
				public ResponseEntity<Admin> deleteAdmin(@PathVariable("id") Long id) throws Exception{
					service.deleteAdmin(id);
				    return new ResponseEntity<Admin>(HttpStatus.OK);
				}
}