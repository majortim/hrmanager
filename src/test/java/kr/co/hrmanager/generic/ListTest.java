package kr.co.hrmanager.generic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ListTest {

    @Test
    void removeAll() {
        List<LocalDate> listSource = new ArrayList<>(List.of(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 2)));
        List<LocalDate> listTarget = List.of(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 3));

        listSource.removeAll(listTarget);
        log.debug("listSource: {}", listSource);

        assertEquals(listSource.size(), 1);
    }

    @Test
    void listToSet() {
        List<LocalDate> list = List.of(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 1));

        Set<LocalDate> set = new HashSet<>(list);

        assertEquals(set.size(), 1);
    }
}
