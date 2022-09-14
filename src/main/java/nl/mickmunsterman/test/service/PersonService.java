package nl.mickmunsterman.test.service;

import nl.mickmunsterman.test.model.Person;
import nl.mickmunsterman.test.exception.RecordNotFoundException;
import nl.mickmunsterman.test.repository.IPersonRepository;
import org.springframework.stereotype.Service;


// Hier maken we geen gebruik van Entity's maar van Data Transfer Objects (DTO)
@Service
public class PersonService {

    public final IPersonRepository repo;

    PersonService( IPersonRepository repo ){
        this.repo = repo;
    }

    public Person getPerson(Long id ){
        if(repo.findById(id).isPresent()){
            return repo.findById(id).get();
        }else{
            throw new RecordNotFoundException("ID not found");
        }
    }


}
