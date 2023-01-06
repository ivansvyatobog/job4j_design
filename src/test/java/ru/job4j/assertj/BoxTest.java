package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(0);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
        double area = box.getArea();
        assertThat(area).isEqualTo(1256.63D, withPrecision(0.01D));
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 8);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(4);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
        double area = box.getArea();
        assertThat(area).isEqualTo(110.85D, withPrecision(0.01D));
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 6);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(8);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
        double area = box.getArea();
        assertThat(area).isEqualTo(216D, withPrecision(0.01D));
    }

    @Test
    void unknownFigure() {
        Box box = new Box(0, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(-1);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
        double area = box.getArea();
        assertThat(area).isEqualTo(0);
    }
}