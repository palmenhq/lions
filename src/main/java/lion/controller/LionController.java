package lion.controller;

import lion.model.Lion;
import lion.model.LionsMap;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class LionController
{
    public LionsMap lions;

    public LionController()
    {
        lions = new LionsMap();

        Lion leo = new Lion("Leo", "male");
        Lion leona = new Lion("Leona", "female");

        lions.addLion(leo);
        lions.addLion(leona);
    }

    @RequestMapping(value="/lion", method=RequestMethod.GET)
    public LionsMap getLions()
    {
        return lions;
    }

    @RequestMapping(value="/lion", method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Lion> createLion(@RequestBody Lion lion)
    {
        lions.addLion(lion);
        Lion newLion = lions.get(lions.lastKey());

        return new ResponseEntity<>(newLion, HttpStatus.CREATED);
    }

    @RequestMapping(value="/lion/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> getLion(@PathVariable("id") int id)
    {
        if (lions.containsKey(id)) {
            return new ResponseEntity<Lion>(lions.get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/lion/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteLion(@PathVariable("id") int id) {
        if (lions.containsKey(id)) {
            lions.remove(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/lion/{id}", method=RequestMethod.PUT)
    public ResponseEntity<?> updateLion(@PathVariable("id") int id, @RequestBody Lion lion)
    {
        if (lions.containsKey(id)) {
            lions.setLion(id, lion);

            return new ResponseEntity<Lion>(lion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
