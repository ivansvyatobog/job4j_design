package ru.job4j.generics;

public class Role extends Base {

    private final String rolename;

    private Integer value;


    public Role(String id, String rolename, Integer value) {
        super(id);
        this.rolename = rolename;
        this.value = value;
    }

    public String getRolename() {
        return rolename;
    }

    public Integer getValue() {
        return value;
    }
}
