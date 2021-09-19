package bolsa.compasso.api.partidos.controller;

import bolsa.compasso.api.partidos.model.Associate;
import bolsa.compasso.api.partidos.model.Role;
import bolsa.compasso.api.partidos.model.dto.AssociateDTO;
import bolsa.compasso.api.partidos.model.form.AssociateForm;
import bolsa.compasso.api.partidos.model.form.AssociateUpdateForm;
import bolsa.compasso.api.partidos.model.form.AssociationForm;
import bolsa.compasso.api.partidos.repository.AssociateRepository;
import bolsa.compasso.api.partidos.service.AssociateService;
import bolsa.compasso.api.partidos.service.AssociationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/associados")
public class AssociateController {

    @Autowired
    AssociateRepository repository;

    @Autowired
    AssociateService service;

    @Autowired
    AssociationService associationService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<AssociateDTO>> showAll(@RequestParam(name = "cargo", required = false) String role){
        List<Associate> associates;

        if(role != null && service.validateRoleString(role)){
            associates = repository.findAllByRole(Role.valueOf(role));
        } else {
            associates = repository.findAll();
        }

        if (!associates.isEmpty()) {
            return ResponseEntity.ok(associates.stream().map(p -> modelMapper.map(p, AssociateDTO.class)).collect(Collectors.toList()));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociateDTO> show(@PathVariable Long id){
        Optional<Associate> associate = repository.findById(id);
        if (associate.isPresent()) {
            return ResponseEntity.ok(service.toAssociateDTO(associate.get(), modelMapper));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid AssociateForm form, UriComponentsBuilder uriBuilder){

        Associate associate = modelMapper.map(form, Associate.class);
        repository.save(associate);

        URI uri = uriBuilder.path("/associados/{id}").buildAndExpand(associate.getId()).toUri();
        return ResponseEntity.created(uri).body(service.toAssociateDTO(associate, modelMapper));
    }

    @PostMapping("/partidos")
    @Transactional
    public ResponseEntity<?> createAffiliation(@RequestBody @Valid AssociationForm form, UriComponentsBuilder uriBuilder){

        associationService.createAffiliation(form.getAssociateId(), form.getPartyId());

        URI uri = uriBuilder.path("/associados/{id}").buildAndExpand(form.getAssociateId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid AssociateUpdateForm form, @PathVariable Long id){

        Optional<Associate> associate = repository.findById(id);
        if(associate.isPresent()){
            form.update(associate.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id){

        Optional<Associate> associate = repository.findById(id);
        if(associate.isPresent()){
            repository.delete(associate.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}/partidos")
    @Transactional
    public ResponseEntity<?> removeAffiliation(@PathVariable Long id){

        Optional<Associate> associate = repository.findById(id);
        if(associate.isPresent()){
            associationService.deleteAffiliation(associate.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }


}
