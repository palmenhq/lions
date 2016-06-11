package lion.model;

import java.util.NoSuchElementException;
import java.util.TreeMap;

public class LionsMap extends TreeMap<Integer, Lion>
{
    public Lion addLion(Lion lion)
    {
        Integer newKey;
        try {
            newKey = this.lastKey() + 1;
        } catch (NoSuchElementException e) {
            newKey = 1;
        }

        lion.setId(newKey);

        return this.put(newKey, lion);
    }

    public Lion setLion(Integer id, Lion lion)
    {
        lion.setId(id);

        return this.put(id, lion);
    }
}
