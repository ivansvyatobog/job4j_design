package ru.job4j.generics;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleNameIsBanana() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Carrot", 15));
        store.add(new Role("2", "Banana", 6));
        Role result = store.findById("2");
        assertThat(result.getRolename()).isEqualTo("Banana");
        assertThat(result.getValue()).isEqualTo(6);
    }

    @Test
    void whenAddDuplicateAndFindRoleNameOrange() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Orange", 10));
        store.add(new Role("1", "Peach", 1));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Orange");
        assertThat(result.getValue()).isEqualTo(10);
    }

    @Test
    void whenAddAndFindNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Peach", 1));
        Role result = store.findById("4");
        assertThat(result).isNull();
    }

    @Test
    void whenReplaceThenRoleNameIsPepper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Orange", 10));
        store.replace("1", new Role("1", "Pepper", 30));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Pepper");
    }

    @Test
    void whenReplaceThenNoChangesRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Banana", 10));
        store.replace("9", new Role("9", "Meat", 4));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Banana");
    }
}