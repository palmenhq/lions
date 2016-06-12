package lion.controller;

import lion.config.MongoConfig;
import lion.model.Lion;

import lion.model.repository.LionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LionController {
    @Autowired
    private LionRepository lionRepository;

    public LionController() {
    }

    @RequestMapping(value = "/lion", method = RequestMethod.GET)
    public List<Lion> getLions() {
        return lionRepository.findAll();
    }

    @RequestMapping(value = "/lion", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Lion> createLion(@RequestBody Lion lion) {
        lionRepository.save(lion);

        return new ResponseEntity<>(lion, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/lion/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getLion(@PathVariable("id") String id) {
        Lion lion = lionRepository.findOne(id);

        if (lion == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Lion>(lion, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/lion/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteLion(@PathVariable("id") String id) {
        Lion lion = lionRepository.findOne(id);

        if (lion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            lionRepository.delete(lion);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value="/lion/{id}", method=RequestMethod.PUT)
    public ResponseEntity<?> updateLion(@PathVariable("id") String id, @RequestBody Lion lion)
    {
        Lion foundLion = lionRepository.findOne(id);

        if (foundLion == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            lion.setId(id);

            lionRepository.save(lion);

            return new ResponseEntity<Lion>(lion, HttpStatus.OK);
        }
    }
}
