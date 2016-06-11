package lion.controller;

import lion.config.MongoConfig;
import lion.model.Lion;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LionController {
    private MongoOperations mongoOperations;

    public LionController() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
        mongoOperations = (MongoOperations) ctx.getBean("mongoTemplate");
    }

    @RequestMapping(value = "/lion", method = RequestMethod.GET)
    public List<Lion> getLions() {
        return mongoOperations.findAll(Lion.class);
    }

    @RequestMapping(value = "/lion", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Lion> createLion(@RequestBody Lion lion) {
        mongoOperations.save(lion);

        return new ResponseEntity<>(lion, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/lion/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getLion(@PathVariable("id") String id) {
        Lion lion = findLionById(id);

        if (lion == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Lion>(lion, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/lion/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteLion(@PathVariable("id") String id) {
        Lion lion = findLionById(id);

        if (lion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            mongoOperations.remove(lion);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value="/lion/{id}", method=RequestMethod.PUT)
    public ResponseEntity<?> updateLion(@PathVariable("id") String id, @RequestBody Lion lion)
    {
        Lion foundLion = findLionById(id);


        if (foundLion == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            lion.setId(id);

            mongoOperations.save(lion);

            return new ResponseEntity<Lion>(lion, HttpStatus.OK);
        }
    }

    protected Lion findLionById(String id) {
        Query query = new Query(Criteria.where("id").is(id));

        Lion lion = mongoOperations.findOne(query, Lion.class);

        return lion;
    }
}
