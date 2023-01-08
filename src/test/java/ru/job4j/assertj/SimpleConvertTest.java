package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three");
        assertThat(list).hasSize(3)
                .containsOnly("second", "first", "three")
                .containsExactly("first", "second", "three")
                .containsAnyOf("zero", "second", "six")
                .startsWith("first");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("one", "two", "ten");
        assertThat(set).first()
                .isEqualTo("one");
        assertThat(set).element(2).isNotNull()
                .isEqualTo("two");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("1", "2", "4", "7");
        assertThat(map)
                .hasSize(4)
                .containsKeys("1", "4", "7")
                .doesNotContainKey("10")
                .containsEntry("7", 3);
    }
}