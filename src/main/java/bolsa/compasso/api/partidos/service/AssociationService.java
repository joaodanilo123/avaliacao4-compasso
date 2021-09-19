package bolsa.compasso.api.partidos.service;

import bolsa.compasso.api.partidos.model.Associate;
import bolsa.compasso.api.partidos.model.Party;
import bolsa.compasso.api.partidos.model.dto.AssociateDTO;
import bolsa.compasso.api.partidos.repository.AssociateRepository;
import bolsa.compasso.api.partidos.repository.PartyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssociationService {

    @Autowired
    AssociateRepository associateRepository;

    @Autowired
    PartyRepository partyRepository;

    public List<AssociateDTO> listPartyAssociates(Party party, ModelMapper modelMapper){
        List<Associate> associates = party.getAssociates();
        return associates.stream().map(associate -> modelMapper.map(associate, AssociateDTO.class)).collect(Collectors.toList());
    }

    public void createAffiliation(Long associateId, Long partyId){

        Optional<Associate> findAssociate = associateRepository.findById(associateId);
        Optional<Party> findParty = partyRepository.findById(partyId);

        if(findAssociate.isPresent() && findParty.isPresent()){
            Associate associate = findAssociate.get();
            Party party = findParty.get();

            associate.setParty(party);
        }
    }

    public void deleteAffiliation(Associate associate){
        associate.setParty(null);
    }

}
