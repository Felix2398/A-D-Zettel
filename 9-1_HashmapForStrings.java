public class HashmapForStrings extends LinkedHashmap<String> {
    HashmapForStrings(int m) {
        super(m);
    }

    @Override
    public int hashFunction(String key) {
        return mapStringToNumber(key) % m;
    }

    public int mapStringToNumber(String key) {
        int sum = 0;
        char[] array = key.toCharArray();
        for (int i = 0; i < array.length; i++) {
            sum += array[i] * (i + 1);
        }
        return sum;
    }
}
