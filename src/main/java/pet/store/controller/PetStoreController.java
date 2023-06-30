package pet.store.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	@Autowired
	private PetStoreService petStoreService;
	 
	@PostMapping("/pet_store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData makeStoreData
		(@RequestBody PetStoreData petStoreData) {
		log.info("Creating PetStore {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	@PutMapping("/pet_store/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId,
			@RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(petStoreId);
		log.info("Updating pet-store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PostMapping("/pet_store/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreEmployee createStoreEmployee
    (@PathVariable Long petStoreId, @RequestBody 
    		PetStoreEmployee petStoreEmployee) {
		log.info("Creating employee {} for pet store with ID={}");
		return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
}
	@GetMapping("/pet_store")
	public List <PetStoreData> retrieveAllPetStores(){
		log.info("Retrieving all pet stores");
		return petStoreService.retrieveAllPetStores();
	}
	@GetMapping("/pet_store/{petStoreId}")
	public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
		log.info("Retrieving pet store with ID= ", petStoreId);
		return petStoreService.retrievePetStoreById(petStoreId);
	}
//	@DeleteMapping("/pet_store/{petStoreId}")
//	public PetStoreData deletePetStoreById(@PathVariable Long petStoreId) {
//		log.info("Deleting pet store with ID= ", petStoreId);
//		return PetStoreService.deletePetStoreById(petStoreId);
//	}
}
