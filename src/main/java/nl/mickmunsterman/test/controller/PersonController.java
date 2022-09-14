package nl.mickmunsterman.test.controller;

import nl.mickmunsterman.test.model.Person;
import nl.mickmunsterman.test.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;

// Je moet de controller laag dom houden ( alle logica komt in de service )

@RestController
public class PersonController {

    private final PersonService service;

    PersonController( PersonService service ){
        this.service = service;
    }

    private ArrayList<Person> persons = new ArrayList<>();

    @GetMapping("/persons")
    public ResponseEntity<Object> getPersons() {

        return new ResponseEntity<>( this.persons, HttpStatus.OK);
    }

    @PostMapping("/persons")
    public ResponseEntity<Object> createPerson(@RequestBody Person p){
        System.out.println("post");
        this.persons.add(p);
//        return ResponseEntity.noContent();
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }


    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person p){
        this.persons.set(id, p);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    /*
    @PatchMapping("/persons/{id}")
    public ResponseEntity<Object> modifyPerson(@PathVariable int id, @RequestBody String name) {

        persons.get(id).setName(name);

        return new ResponseEntity<>("Renamed", HttpStatus.OK);
    }
    */
    /*
    @PatchMapping("/persons/{id}")
    public ResponseEntity<Object> modifyPerson2(@PathVariable int id,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(defaultValue = ".") char gender) {
        if( name != null ) {
            persons.get(id).setName(name);
        }
        if( !Character.toString(gender).equals(".") ){
            persons.get(id).setGender(gender);
        }

        return new ResponseEntity<>("Renamed", HttpStatus.OK);
    }
    */

    @PatchMapping("/persons/{id}")
    public ResponseEntity<Person> modifyPerson2(@PathVariable int id, @RequestBody Person p ) {

        if( p.getName() != null){
            persons.get(id).setName( p.getName() );
        }

        if (p.getDob() != null){
            persons.get(id).setDob( p.getDob() );
        }

        System.out.println( p.getGender() == ' ');
//        if( p.getGender() != null )
//        if( !Character.toString(gender).equals("x") ){
//            persons.get(id).setGender(gender);
//        }
//        System.out.println( dob.toString() );

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable int id){
        this.persons.remove( id );
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }


}
