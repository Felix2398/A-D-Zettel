public class LinkedList<T> {
    ListElement<T> head;

    public static class ListElement<T> {
        T key;
        ListElement<T> next;

        ListElement(T key) {
            this.key = key;
            this.next = null;
        }
    }

    public void add(T key) {
        ListElement<T> current = head;

        if (head == null) {
            head = new ListElement<>(key);
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListElement<>(key);
        }
    }

    public void newHead(T key) {
        ListElement<T> temp = head;
        head = new ListElement<>(key);
        head.next = temp;
    }

    public void delete(T key) {
        ListElement<T> prev = head;
        ListElement<T> current = head.next;

        if (head.key.equals(key)) {
            head = head.next;
        } else {
            while (current != null && !current.key.equals(key)) {
                prev = current;
                current = current.next;
            }
            if (current != null) {
                prev.next = current.next;
            }
        }
    }

    public boolean search(T key) {
        ListElement<T> current = head;

        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int getLength() {
        if (head == null) {
            return 0;
        } else {
            int sum = 0;
            ListElement<T> current = head;
            while (current != null) {
                sum++;
                current = current.next;
            }
            return sum;
        }
    }

    @Override
    public String toString() {
        if (head == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        ListElement<T> current = head.next;

        sb.append(head.key.toString());
        while (current != null) {
            sb.append(", ").append(current.key.toString());
            current = current.next;
        }
        return sb.toString();
    }
}
