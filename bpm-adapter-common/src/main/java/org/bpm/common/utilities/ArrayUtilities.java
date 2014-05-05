package org.bpm.common.utilities;

import java.lang.reflect.Array;

public abstract class ArrayUtilities {

    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    @SuppressWarnings("rawtypes")
    public static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];

    public static final int INDEX_NOT_FOUND = -1;

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(short[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(char[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(boolean[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(Object[] array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(long[] array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(int[] array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(short[] array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(char[] array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(byte[] array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(double[] array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(float[] array) {
        return (array != null && array.length != 0);
    }

    public static boolean isNotEmpty(boolean[] array) {
        return (array != null && array.length != 0);
    }

    // IndexOf search
    public static int indexOf(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind, 0);
    }

    public static int indexOf(Object[] array, Object objectToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (objectToFind == null) {
            for (int i = startIndex; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
            for (int i = startIndex; i < array.length; i++) {
                if (objectToFind.equals(array[i])) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int lastIndexOf(Object[] array, Object objectToFind) {
        return lastIndexOf(array, objectToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(Object[] array, Object objectToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        if (objectToFind == null) {
            for (int i = startIndex; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
            for (int i = startIndex; i >= 0; i--) {
                if (objectToFind.equals(array[i])) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static boolean contains(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind) != INDEX_NOT_FOUND;
    }

    public static int indexOf(long[] array, long valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(long[] array, long valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int lastIndexOf(long[] array, long valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(long[] array, long valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static boolean contains(long[] array, long valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    public static int indexOf(int[] array, int valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(int[] array, int valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int lastIndexOf(int[] array, int valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(int[] array, int valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static boolean contains(int[] array, int valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    public static int indexOf(short[] array, short valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(short[] array, short valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int lastIndexOf(short[] array, short valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(short[] array, short valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static boolean contains(short[] array, short valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    public static int indexOf(char[] array, char valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(char[] array, char valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int lastIndexOf(char[] array, char valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(char[] array, char valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static boolean contains(char[] array, char valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    public static int indexOf(byte[] array, byte valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(byte[] array, byte valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int lastIndexOf(byte[] array, byte valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(byte[] array, byte valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static boolean contains(byte[] array, byte valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    public static int indexOf(double[] array, double valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(double[] array, double valueToFind, double tolerance) {
        return indexOf(array, valueToFind, 0, tolerance);
    }

    public static int indexOf(double[] array, double valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int indexOf(double[] array, double valueToFind, int startIndex, double tolerance) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        double min = valueToFind - tolerance;
        double max = valueToFind + tolerance;
        for (int i = startIndex; i < array.length; i++) {
            if (array[i] >= min && array[i] <= max) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int lastIndexOf(double[] array, double valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(double[] array, double valueToFind, double tolerance) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE, tolerance);
    }

    public static int lastIndexOf(double[] array, double valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int lastIndexOf(double[] array, double valueToFind, int startIndex, double tolerance) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        double min = valueToFind - tolerance;
        double max = valueToFind + tolerance;
        for (int i = startIndex; i >= 0; i--) {
            if (array[i] >= min && array[i] <= max) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static boolean contains(double[] array, double valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    public static boolean contains(double[] array, double valueToFind, double tolerance) {
        return indexOf(array, valueToFind, 0, tolerance) != INDEX_NOT_FOUND;
    }

    public static int indexOf(float[] array, float valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(float[] array, float valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int lastIndexOf(float[] array, float valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(float[] array, float valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static boolean contains(float[] array, float valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    public static int indexOf(boolean[] array, boolean valueToFind) {
        return indexOf(array, valueToFind, 0);
    }

    public static int indexOf(boolean[] array, boolean valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int lastIndexOf(boolean[] array, boolean valueToFind) {
        return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(boolean[] array, boolean valueToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static boolean contains(boolean[] array, boolean valueToFind) {
        return indexOf(array, valueToFind) != INDEX_NOT_FOUND;
    }

    /**
     * <pre>
     * ArrayUtilities.getLength(null)            = 0
     * ArrayUtilities.getLength([])              = 0
     * ArrayUtilities.getLength([null])          = 1
     * ArrayUtilities.getLength([true, false])   = 2
     * ArrayUtilities.getLength([1, 2, 3])       = 3
     * ArrayUtilities.getLength(["a", "b", "c"]) = 3
     * </pre>
     */
    public static int getLength(Object array) {
        if (array == null) {
            return 0;
        }
        return Array.getLength(array);
    }

    /**
     * <pre>
     * ArrayUtilities.addAll(null, null)     = null
     * ArrayUtilities.addAll(array1, null)   = cloned copy of array1
     * ArrayUtilities.addAll(null, array2)   = cloned copy of array2
     * ArrayUtilities.addAll([], [])         = []
     * ArrayUtilities.addAll([null], [null]) = [null, null]
     * ArrayUtilities.addAll(["a", "b", "c"], ["1", "2", "3"]) = ["a", "b", "c", "1", "2", "3"]
     * </pre>
     */
    public static Object[] addAll(Object[] array1, Object[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        Object[] joinedArray = (Object[]) Array.newInstance(array1.getClass().getComponentType(), array1.length
                + array2.length);
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    public static byte[] addAll(byte[] array1, byte[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        byte[] joinedArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    // Clone
    public static Object[] clone(Object[] array) {
        if (array == null) {
            return null;
        }
        return (Object[]) array.clone();
    }

    public static long[] clone(long[] array) {
        if (array == null) {
            return null;
        }
        return (long[]) array.clone();
    }

    public static int[] clone(int[] array) {
        if (array == null) {
            return null;
        }
        return (int[]) array.clone();
    }

    public static short[] clone(short[] array) {
        if (array == null) {
            return null;
        }
        return (short[]) array.clone();
    }

    public static char[] clone(char[] array) {
        if (array == null) {
            return null;
        }
        return (char[]) array.clone();
    }

    public static byte[] clone(byte[] array) {
        if (array == null) {
            return null;
        }
        return (byte[]) array.clone();
    }

    public static double[] clone(double[] array) {
        if (array == null) {
            return null;
        }
        return (double[]) array.clone();
    }

    public static float[] clone(float[] array) {
        if (array == null) {
            return null;
        }
        return (float[]) array.clone();
    }

    public static boolean[] clone(boolean[] array) {
        if (array == null) {
            return null;
        }
        return (boolean[]) array.clone();
    }

    /**
     * <pre>
     * ArrayUtilities.remove(["a"], 0)           = []
     * ArrayUtilities.remove(["a", "b"], 0)      = ["b"]
     * ArrayUtilities.remove(["a", "b"], 1)      = ["a"]
     * ArrayUtilities.remove(["a", "b", "c"], 1) = ["a", "c"]
     * </pre>
     */
    public static Object[] remove(Object[] array, int index) {
        return (Object[]) remove((Object) array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.removeElement(null, "a")            = null
     * ArrayUtilities.removeElement([], "a")              = []
     * ArrayUtilities.removeElement(["a"], "b")           = ["a"]
     * ArrayUtilities.removeElement(["a", "b"], "a")      = ["b"]
     * ArrayUtilities.removeElement(["a", "b", "a"], "a") = ["b", "a"]
     * </pre>
     */
    public static Object[] removeElement(Object[] array, Object element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.remove([true], 0)              = []
     * ArrayUtilities.remove([true, false], 0)       = [false]
     * ArrayUtilities.remove([true, false], 1)       = [true]
     * ArrayUtilities.remove([true, true, false], 1) = [true, false]
     * </pre>
     */
    public static boolean[] remove(boolean[] array, int index) {
        return (boolean[]) remove((Object) array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.removeElement(null, true)                = null
     * ArrayUtilities.removeElement([], true)                  = []
     * ArrayUtilities.removeElement([true], false)             = [true]
     * ArrayUtilities.removeElement([true, false], false)      = [true]
     * ArrayUtilities.removeElement([true, false, true], true) = [false, true]
     * </pre>
     */
    public static boolean[] removeElement(boolean[] array, boolean element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.remove([1], 0)          = []
     * ArrayUtilities.remove([1, 0], 0)       = [0]
     * ArrayUtilities.remove([1, 0], 1)       = [1]
     * ArrayUtilities.remove([1, 0, 1], 1)    = [1, 1]
     * </pre>
     */
    public static byte[] remove(byte[] array, int index) {
        return (byte[]) remove((Object) array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.removeElement(null, 1)        = null
     * ArrayUtilities.removeElement([], 1)          = []
     * ArrayUtilities.removeElement([1], 0)         = [1]
     * ArrayUtilities.removeElement([1, 0], 0)      = [1]
     * ArrayUtilities.removeElement([1, 0, 1], 1)   = [0, 1]
     * </pre>
     */
    public static byte[] removeElement(byte[] array, byte element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.remove(['a'], 0)           = []
     * ArrayUtilities.remove(['a', 'b'], 0)      = ['b']
     * ArrayUtilities.remove(['a', 'b'], 1)      = ['a']
     * ArrayUtilities.remove(['a', 'b', 'c'], 1) = ['a', 'c']
     * </pre>
     */
    public static char[] remove(char[] array, int index) {
        return (char[]) remove((Object) array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.removeElement(null, 'a')            = null
     * ArrayUtilities.removeElement([], 'a')              = []
     * ArrayUtilities.removeElement(['a'], 'b')           = ['a']
     * ArrayUtilities.removeElement(['a', 'b'], 'a')      = ['b']
     * ArrayUtilities.removeElement(['a', 'b', 'a'], 'a') = ['b', 'a']
     * </pre>
     */
    public static char[] removeElement(char[] array, char element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.remove([1.1], 0)           = []
     * ArrayUtilities.remove([2.5, 6.0], 0)      = [6.0]
     * ArrayUtilities.remove([2.5, 6.0], 1)      = [2.5]
     * ArrayUtilities.remove([2.5, 6.0, 3.8], 1) = [2.5, 3.8]
     * </pre>
     */
    public static double[] remove(double[] array, int index) {
        return (double[]) remove((Object) array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.removeElement(null, 1.1)            = null
     * ArrayUtilities.removeElement([], 1.1)              = []
     * ArrayUtilities.removeElement([1.1], 1.2)           = [1.1]
     * ArrayUtilities.removeElement([1.1, 2.3], 1.1)      = [2.3]
     * ArrayUtilities.removeElement([1.1, 2.3, 1.1], 1.1) = [2.3, 1.1]
     * </pre>
     */
    public static double[] removeElement(double[] array, double element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.remove([1.1], 0)           = []
     * ArrayUtilities.remove([2.5, 6.0], 0)      = [6.0]
     * ArrayUtilities.remove([2.5, 6.0], 1)      = [2.5]
     * ArrayUtilities.remove([2.5, 6.0, 3.8], 1) = [2.5, 3.8]
     * </pre>
     */
    public static float[] remove(float[] array, int index) {
        return (float[]) remove((Object) array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.removeElement(null, 1.1)            = null
     * ArrayUtilities.removeElement([], 1.1)              = []
     * ArrayUtilities.removeElement([1.1], 1.2)           = [1.1]
     * ArrayUtilities.removeElement([1.1, 2.3], 1.1)      = [2.3]
     * ArrayUtilities.removeElement([1.1, 2.3, 1.1], 1.1) = [2.3, 1.1]
     * </pre>
     */
    public static float[] removeElement(float[] array, float element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.remove([1], 0)         = []
     * ArrayUtilities.remove([2, 6], 0)      = [6]
     * ArrayUtilities.remove([2, 6], 1)      = [2]
     * ArrayUtilities.remove([2, 6, 3], 1)   = [2, 3]
     * </pre>
     */
    public static int[] remove(int[] array, int index) {
        return (int[]) remove((Object) array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.removeElement(null, 1)      = null
     * ArrayUtilities.removeElement([], 1)        = []
     * ArrayUtilities.removeElement([1], 2)       = [1]
     * ArrayUtilities.removeElement([1, 3], 1)    = [3]
     * ArrayUtilities.removeElement([1, 3, 1], 1) = [3, 1]
     * </pre>
     */
    public static int[] removeElement(int[] array, int element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.remove([1], 0)         = []
     * ArrayUtilities.remove([2, 6], 0)      = [6]
     * ArrayUtilities.remove([2, 6], 1)      = [2]
     * ArrayUtilities.remove([2, 6, 3], 1)   = [2, 3]
     * </pre>
     */
    public static long[] remove(long[] array, int index) {
        return (long[]) remove((Object) array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.removeElement(null, 1)      = null
     * ArrayUtilities.removeElement([], 1)        = []
     * ArrayUtilities.removeElement([1], 2)       = [1]
     * ArrayUtilities.removeElement([1, 3], 1)    = [3]
     * ArrayUtilities.removeElement([1, 3, 1], 1) = [3, 1]
     * </pre>
     */
    public static long[] removeElement(long[] array, long element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.remove([1], 0)         = []
     * ArrayUtilities.remove([2, 6], 0)      = [6]
     * ArrayUtilities.remove([2, 6], 1)      = [2]
     * ArrayUtilities.remove([2, 6, 3], 1)   = [2, 3]
     * </pre>
     */
    public static short[] remove(short[] array, int index) {
        return (short[]) remove((Object) array, index);
    }

    /**
     * <pre>
     * ArrayUtilities.removeElement(null, 1)      = null
     * ArrayUtilities.removeElement([], 1)        = []
     * ArrayUtilities.removeElement([1], 2)       = [1]
     * ArrayUtilities.removeElement([1, 3], 1)    = [3]
     * ArrayUtilities.removeElement([1, 3, 1], 1) = [3, 1]
     * </pre>
     */
    public static short[] removeElement(short[] array, short element) {
        int index = indexOf(array, element);
        if (index == INDEX_NOT_FOUND) {
            return clone(array);
        }
        return remove(array, index);
    }

    private static Object remove(Object array, int index) {
        int length = getLength(array);
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }

        Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
        System.arraycopy(array, 0, result, 0, index);
        if (index < length - 1) {
            System.arraycopy(array, index + 1, result, index, length - index - 1);
        }

        return result;
    }

    // Subarrays
    public static Object[] subArray(Object[] array, int startIndex, int count) {
        if (array == null) {
            return null;
        }
        Class<?> type = array.getClass().getComponentType();
        int length = array.length;
        if (isEmpty(array) || count <= 0 || length < startIndex) {
            return (Object[]) Array.newInstance(type, 0);
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (length < count) {
            count = length;
        }
        Object[] subArray = (Object[]) Array.newInstance(type, count);
        System.arraycopy(array, startIndex, subArray, 0, count);
        return subArray;
    }

    public static long[] subArray(long[] array, int startIndex, int count) {
        if (array == null) {
            return null;
        }
        int length = array.length;
        if (isEmpty(array) || count <= 0 || length < startIndex) {
            return EMPTY_LONG_ARRAY;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (length < count) {
            count = length;
        }
        long[] subArray = new long[count];
        System.arraycopy(array, startIndex, subArray, 0, count);
        return subArray;
    }

    public static int[] subArray(int[] array, int startIndex, int count) {
        if (array == null) {
            return null;
        }
        int length = array.length;
        if (isEmpty(array) || count <= 0 || length < startIndex) {
            return EMPTY_INT_ARRAY;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (length < count) {
            count = length;
        }
        int[] subArray = new int[count];
        System.arraycopy(array, startIndex, subArray, 0, count);
        return subArray;
    }

    public static short[] subArray(short[] array, int startIndex, int count) {
        if (array == null) {
            return null;
        }
        int length = array.length;
        if (isEmpty(array) || count <= 0 || length < startIndex) {
            return EMPTY_SHORT_ARRAY;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (length < count) {
            count = length;
        }
        short[] subArray = new short[count];
        System.arraycopy(array, startIndex, subArray, 0, count);
        return subArray;
    }

    public static char[] subArray(char[] array, int startIndex, int count) {
        if (array == null) {
            return null;
        }
        int length = array.length;
        if (isEmpty(array) || count <= 0 || length < startIndex) {
            return EMPTY_CHAR_ARRAY;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (length < count) {
            count = length;
        }
        char[] subArray = new char[count];
        System.arraycopy(array, startIndex, subArray, 0, count);
        return subArray;
    }

    public static byte[] subArray(byte[] array, int startIndex, int count) {
        if (array == null) {
            return null;
        }
        int length = array.length;
        if (isEmpty(array) || count <= 0 || length < startIndex) {
            return EMPTY_BYTE_ARRAY;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (length < count) {
            count = length;
        }
        byte[] subArray = new byte[count];
        System.arraycopy(array, startIndex, subArray, 0, count);
        return subArray;
    }

    public static double[] subArray(double[] array, int startIndex, int count) {
        if (array == null) {
            return null;
        }
        int length = array.length;
        if (isEmpty(array) || count <= 0 || length < startIndex) {
            return EMPTY_DOUBLE_ARRAY;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (length < count) {
            count = length;
        }
        double[] subArray = new double[count];
        System.arraycopy(array, startIndex, subArray, 0, count);
        return subArray;
    }

    public static float[] subArray(float[] array, int startIndex, int count) {
        if (array == null) {
            return null;
        }
        int length = array.length;
        if (isEmpty(array) || count <= 0 || length < startIndex) {
            return EMPTY_FLOAT_ARRAY;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (length < count) {
            count = length;
        }
        float[] subArray = new float[count];
        System.arraycopy(array, startIndex, subArray, 0, count);
        return subArray;
    }

    public static boolean[] subArray(boolean[] array, int startIndex, int count) {
        if (array == null) {
            return null;
        }
        int length = array.length;
        if (isEmpty(array) || count <= 0 || length < startIndex) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (length < count) {
            count = length;
        }
        boolean[] subArray = new boolean[count];
        System.arraycopy(array, startIndex, subArray, 0, count);
        return subArray;
    }

}
