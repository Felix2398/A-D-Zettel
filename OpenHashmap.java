import java.util.ArrayList;

public abstract class OpenHashmap<T> {
    int m;
    ArrayList<Element<T>> table;
    Probing probing;

    public OpenHashmap(int m) {
        this.m  = m;
        this.table = new ArrayList<>(m);
        this.probing = Probing.LINEAR;

        for (int i = 0; i < m; i++) {
            table.add(new Element<>());
        }
    }

    public OpenHashmap(int m, Probing probing) {
        this(m);
        this.probing = probing;
    }

    public enum State {
        NIL, DELETED
    }

    public enum Probing {
        LINEAR, QUADRATIC, DOUBLE
    }

    private static class Element<T> {
        private T value;
        private State state;

        Element() {
            this.value = null;
            this.state = State.NIL;
        }

        private T getValue() {
            return value;
        }

        private boolean isNIL() {
            return state == State.NIL;
        }

        private boolean isDELETED() {
            return state == State.DELETED;
        }

        private void setValue(T value) {
            this.value = value;
            this.state = null;
        }

        private void setNIL() {
            this.state = State.NIL;
            this.value = null;
        }

        private void setDELETED() {
            this.state = State.DELETED;
            this.value = null;
        }

        @Override
        public String toString() {
            if (value != null) {
                return value.toString();
            } else if (state == State.DELETED) {
                return "DEL";
            } else {
                return "NIL";
            }
        }
    }

    public void insert(T value) {
        int i = 0;
        while (i < this.m) {
            int j = hashFunction(value, i);
            if (table.get(j).isNIL() || table.get(j).isDELETED()) {
                this.table.get(j).setValue(value);
                return;
            } else {
                i++;
            }
        }
        System.out.println("Overflow for: " + value.toString());
    }

    public void insertArrayList(ArrayList<T> arrayList) {
        for (T value : arrayList) {
            insert(value);
        }
    }

    public Integer search(T value) {
        int i = 0;
        while (i < m) {
            int j = hashFunction(value, i);
            if (table.get(j).isNIL()) {
                return null;
            }
            if (table.get(j).getValue().equals(value)) {
                return j;
            }
            i++;
        }
        return null;
    }

    public int numberElementsNotFound(ArrayList<T> arrayList) {
        int sum = 0;
        for (T element : arrayList) {
            if (search(element) == null) {
                sum++;
            }
        }
        return sum;
    }

    public void delete(T value) {
        Integer j = search(value);
        if (j != null) {
            table.get(j).setDELETED();
        }
    }

    private int hashFunction(T value, int i) {
        return switch (probing) {
            case LINEAR -> hashFunctionLinear(value, i);
            case QUADRATIC -> hashFunctionQuadratic(value, i);
            case DOUBLE -> hashFunctionDouble(value, i);
        };
    }

    public abstract int hashFunction(T value);
    public abstract int hashFunctionLinear(T value, int i);
    public abstract int hashFunctionQuadratic(T value, int i);
    public abstract int hashFunctionDouble (T value, int i);

    @Override
    public String toString() {
        return table.toString();
    }
}
