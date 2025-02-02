package org.project.mcc.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class CollectionUtils {

    private CollectionUtils() {}

    public static <T> List<T> castArrayToList(T[] array) {

        if(null == array) {
            return new ArrayList<>();
        }

        return Arrays.stream(array).collect(Collectors.toList());
    }

}
