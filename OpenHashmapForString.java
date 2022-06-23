public class OpenHashmapForString extends OpenHashmap<String>{

    public OpenHashmapForString(int m) {
        super(m);
    }

    public OpenHashmapForString(int m, Probing probing) {
        super(m, probing);
    }

    @Override
    public int hashFunction(String value) {
        return (int)(mapStringToNumber(value) % m);
    }

    private int hashFunction2(String value) {
        return 1 + (int)(mapStringToNumber(value) % (m - 1));
    }

    @Override
    public int hashFunctionLinear(String value, int i) {
        int h = hashFunction(value);
        return (h + i) % m;
    }

    @Override
    public int hashFunctionQuadratic(String value, int i) {
        int h = hashFunction(value);
        double c1 = 0.5;
        double c2 = 0.5;
        return (int) (h + c1 * i + c2 * Math.pow(i, 2)) % m;
    }

    @Override
    public int hashFunctionDouble(String value, int i) {
        int h1 = hashFunction(value);
        int h2 = hashFunction2(value);
        int v =  (h1 + i * h2);

        //Integer Overflow Protection
        if (v >= 0) {
            return (v % m) ;
        } else {
            return ((v + Integer.MAX_VALUE) % m);
        }
    }

    public long mapStringToNumber(String key) {
        long sum = 0;
        char[] array = key.toCharArray();
        for (int i = 0; i < array.length; i++) {
            char c = array[array.length - i - 1];
            int number = switch (c) {
                case 'Ä' -> 27;
                case 'Ö' -> 28;
                case 'Ü' -> 29;
                default -> c - 64;
            };
            sum += number * Math.pow(31, i);
        }
        return sum;
    }
}
