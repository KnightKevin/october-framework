package com.simon.october.common.util;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

public class ObjectUtil {
    /**
     * convert from String to a target type
     *
     * @param targetType
     * @param s
     */
    public static Object convert(Class<?> targetType, String s) {
        PropertyEditor editor = PropertyEditorManager.findEditor(targetType);
        editor.setAsText(s);
        return editor.getValue();
    }
}
