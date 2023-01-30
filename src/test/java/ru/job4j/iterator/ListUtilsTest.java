package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void removeWhenPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        Predicate<Integer> predicate = p -> p > 6;
        ListUtils.removeIf(input, predicate);
        assertThat(input).hasSize(6).containsSequence(1, 2, 3, 4, 5, 6);
    }

    @Test
    void replaceWhenPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        Predicate<Integer> predicate = p -> p == 6;
        ListUtils.replaceIf(input, predicate, 7);
        assertThat(input).hasSize(8).containsSequence(1, 2, 3, 4, 5, 7, 7, 8);
    }

    @Test
    void removeAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        List<Integer> removeList = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(input, removeList);
        assertThat(input).hasSize(5).containsSequence(4, 5, 6, 7, 8);
    }
}