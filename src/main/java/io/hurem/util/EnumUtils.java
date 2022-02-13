package io.hurem.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EnumUtils {
    @SafeVarargs
    public static <E extends Enum<E>> List<E> getEnumList(final Class<E> enumClass, E ...args) {
        List<E> list =  new ArrayList<>(Arrays.asList(enumClass.getEnumConstants()));
        Optional.of(Arrays.asList(args)).ifPresent(list::removeAll);

        return list;
    }
}
