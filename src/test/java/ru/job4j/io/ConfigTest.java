package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Ivan Svyatobog");
    }

    @Test
    void whenMultipleEqualSign() {
        String path = "data/multiple_equal_sign.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key")).isEqualTo("value=99");
    }

    @Test
    void whenPairWithComment() {
        String path = "data/with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("company")).isNull();
    }

    @Test
    void whenPairWithoutKey() {
        String path = "data/pair_without_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("there is no value before or after the equal sign");
    }

    @Test
    void whenPairWithoutValue() {
        String path = "data/pair_without_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("there is no value before or after the equal sign");
    }

    @Test
    void whenPairWithoutEqualSign() {
        String path = "data/pair_without_equal_sign.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("the string does not contain the equal sign");
    }
}