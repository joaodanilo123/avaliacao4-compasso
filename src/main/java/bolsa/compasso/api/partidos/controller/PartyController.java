package bolsa.compasso.api.partidos.controller;

import bolsa.compasso.api.partidos.model.Party;
import bolsa.compasso.api.partidos.model.dto.AssociateDTO;
import bolsa.compasso.api.partidos.model.dto.PartyDTO;
import bolsa.compasso.api.partidos.model.Ideology;
import bolsa.compasso.api.partidos.model.form.PartyForm;
import bolsa.compasso.api.partidos.model.form.PartyUpdateForm;
import bolsa.compasso.api.partidos.repository.PartyRepository;
import bolsa.compasso.api.partidos.service.AssociationService;
import bolsa.compasso.api.partidos.service.PartyService;
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
@RequestMapping("/partidos")
public class PartyController {

    @Autowired
    private PartyRepository repository;

    @Autowired
    private PartyService service;

    @Autowired
    private AssociationService associationService;

    @Autowired
    private ModelMapper modelMapper;

    private PartyDTO toPartyDTO(Party party){
        return modelMapper.map(party, PartyDTO.class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartyDTO> show(@PathVariable Long id) {

        Optional<Party> party = repository.findById(id);
        if (party.isPresent()) {
            return ResponseEntity.ok(toPartyDTO(party.get()));
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/{id}/associados")
    public ResponseEntity<List<AssociateDTO>> showAffiliations(@PathVariable Long id) {

        Optional<Party> party = repository.findById(id);
        if (party.isPresent()) {
            return ResponseEntity.ok(associationService.listPartyAssociates(party.get(), modelMapper));
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping
    public ResponseEntity<List<PartyDTO>> showAll(@RequestParam(name = "ideologia",required = false) String ideology) {

        List<Party> partys;

        if(ideology != null && service.validateIdeologyString(ideology)){
            partys = repository.findAllByIdeology(Ideology.valueOf(ideology));
        } else {
            partys = repository.findAll();
        }

        if (!partys.isEmpty()) {
            return ResponseEntity.ok(partys.stream().map(p -> modelMapper.map(p, PartyDTO.class)).collect(Collectors.toList()));
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid PartyForm form, UriComponentsBuilder uriBuilder){

        Party party = modelMapper.map(form, Party.class);
        repository.save(party);

        URI uri = uriBuilder.path("/partidos/{id}").buildAndExpand(party.getId()).toUri();
        return ResponseEntity.created(uri).body(toPartyDTO(party));
    }



    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid PartyUpdateForm form, @PathVariable Long id){

        Optional<Party> party = repository.findById(id);
        if(party.isPresent()){
            form.update(party.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id){

        Optional<Party> party = repository.findById(id);
        if(party.isPresent()){
            repository.delete(party.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }
}
