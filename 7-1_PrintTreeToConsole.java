//this only works if Node<T> has this fields
//T key; for the key
//Node<T> left; for the left child
//Node<T> right; for the right child
//Node<T> parent; for the parent node
public class PrintTreeToConsole {

    public static <T extends Comparable<T>> void print(SearchTree<T> tree) {
        Node<T> root = tree.root;
        int height = getHeight(root);
        int width = (int) Math.pow(2, height - 1);
        String[][] array = new String[height][width];

        fillTreeArray(array, root, 0,0);
        int keyWidth = getGreatestStringLength(array);
        if (keyWidth % 2 == 1) {
            keyWidth++;
        }

        int spaceWidth = 2;
        int totalWidth =  (int)Math.pow(2, height - 1) * keyWidth
                + (int)Math.pow(2, height - 1) * spaceWidth
                - spaceWidth;

        for (int i = 0; i < height; i++) {
            int spaceRowStart;
            int spaceBetweenKeys;

            //Calculates the space before the first key of a row
            if (i < height - 1) {
                spaceRowStart = (int)Math.pow(2, height - 2 - i) * keyWidth
                        + ((int)Math.pow(2, height - 2 - i) - 1) * spaceWidth
                        + (spaceWidth / 2) - (keyWidth / 2);
            } else {
                spaceRowStart = 0;
            }

            //Calculates the space between keys for the current row
            if (i != 0) {
                spaceBetweenKeys = (totalWidth - (int)Math.pow(2, i) * keyWidth - 2 * spaceRowStart)
                        / ((int)Math.pow(2, i) - 1);
            } else {
                spaceBetweenKeys = 1;
            }

            //prints "---" between parent and child nodes
            if (i > 0) {
                printHorizontalLinkRow(array, i,keyWidth,spaceRowStart, spaceBetweenKeys);
            }

            //prints "|" above each element, but not in the first row
            if (i > 0) {
                printVerticalLinkRow(array, i,keyWidth,spaceRowStart, spaceBetweenKeys);
            }

            //prints the keys of the nodes
            printKeyRow(array, i, keyWidth, spaceRowStart, spaceBetweenKeys);

            //prints "|" under each element, but not in the last row
            if (i < height - 1) {
                printVerticalLinkRow(array, i,keyWidth,spaceRowStart, spaceBetweenKeys);
            }
        }
        System.out.println();
    }

    private static void printHorizontalLinkRow(String[][] array, int i, int keyWidth, int spaceRowStart, int spaceBetweenKeys) {
        System.out.print(" ".repeat(spaceRowStart + (keyWidth / 2) - 1));
        for (int j = 0; j < Math.pow(2, i); j++) {
            String str;

            if(array[i][j] != null) {
                System.out.print("-".repeat((spaceBetweenKeys / 2) + (keyWidth / 2)));
                str = "-";
            } else {
                System.out.print(" ".repeat((spaceBetweenKeys / 2) + (keyWidth / 2)));
                str = " ";
            }

            if(j % 2 == 1) {
                System.out.print(" ".repeat((spaceBetweenKeys + keyWidth - 1)));
            } else {
                System.out.print(str.repeat(1));
            }
        }
        System.out.println();
    }

    private static void printVerticalLinkRow(String[][] array, int i, int keyWidth, int spaceRowStart, int spaceBetweenKeys) {
        System.out.print(" ".repeat(spaceRowStart + (keyWidth / 2) - 1));
        for (int j = 0; j < Math.pow(2, i); j++) {
            if(array[i][j] != null) {
                System.out.print("|");
            } else {
                System.out.print(" ");
            }
            System.out.print(" ".repeat(spaceBetweenKeys + keyWidth - 1));
        }
        System.out.println();
    }

    private static void printKeyRow(String[][] array,  int i, int keyWidth, int spaceRowStart, int spaceBetweenKeys) {
        System.out.format(" ".repeat(spaceRowStart));
        for (int j = 0; j < Math.pow(2, i); j++) {
            if(array[i][j] != null) {
                System.out.print(centerString(array[i][j], keyWidth));
            } else {
                System.out.print(centerString("", keyWidth));
            }
            System.out.print(" ".repeat(spaceBetweenKeys));
        }
        System.out.println();
    }

    private static <T extends Comparable<T>> void fillTreeArray(String[][] array, Node<T> node, int i, int j) {
        if (i == array.length) {
            return;
        }
        if (node == null) {
            array[i][j] = null;
            return;
        }
        array[i][j] = node.key.toString();
        fillTreeArray(array, node.left, i + 1, j * 2);
        fillTreeArray(array, node.right, i + 1, j * 2 + 1);
    }

    public static <T extends Comparable<T>> int getHeight(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    private static int getGreatestStringLength(String[][] arrays) {
        int max = 0;
        for (String[] array : arrays) {
            for (String element : array) {
                if (element != null && element.length() > max) {
                    max = element.length();
                }
            }
        }
        return max;
    }

    private static String centerString(String str, int size) {
        int space = size - str.length();
        if (space % 2 == 0) {
            return " ".repeat(space / 2) + str + " ".repeat(space / 2);
        } else {
            return " ".repeat(space / 2) + str + " ".repeat(space / 2 + 1);
        }
    }
}
