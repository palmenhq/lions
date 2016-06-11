package lion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lion")
public class Lion
{
    @Id
    public String id;

    public String name;
    public String sex;

    @PersistenceConstructor
    public Lion(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public Lion() {}

    public String getId() {
        return id;
    }

    public Lion setId(String id) {
        this.id = id;

        return this;
    }

    public String getName() {
        return name;
    }

    public Lion setName(String name) {
        this.name = name;

        return this;
    }

    public String getSex() {
        return sex;
    }

    public Lion setSex(String sex) {
        this.sex = sex;

        return this;
    }
}
