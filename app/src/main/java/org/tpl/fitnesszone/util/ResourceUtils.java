package org.tpl.fitnesszone.util;

import java.lang.reflect.Field;

public class ResourceUtils {

    public static int getResourceId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "+ resourceName + " / " + c, e);
        }
    }
}
