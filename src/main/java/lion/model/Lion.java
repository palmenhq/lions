package lion.model;

public class Lion
{
    public Integer id;
    public String name;
    public String sex;

    public Lion(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public Lion() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
