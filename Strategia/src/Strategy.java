import java.util.Scanner;

public class Strategy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Sort bubble = new BubbleSort();
        Sort merge = new MergeSort();
        Sort insertion = new InsertionSort();

        int size;
        int[] array;
        int choice;

        System.out.print("Enter array size: ");
        size = scanner.nextInt();

        array = new int[size];

        System.out.print("\nEnter values: ");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
            if (i < size - 1) {
                System.out.print("\t\t\t  ");
            }
        }

        System.out.println("\nSelect sort method: ");
        System.out.println("1. Bubble sort");
        System.out.println("2. Merge sort");
        System.out.println("3. Insertion sort");

        System.out.print("\nYour choice: ");
        choice = scanner.nextInt();

        if (choice == 1 || choice == 2 || choice == 3){
            System.out.print("\nSorted array: ");
        }

        switch (choice) {
            case 1:
                bubble.display(bubble.sort(array));
                break;
            case 2:
                merge.display(merge.sort(array, 0, array.length - 1));
                break;
            case 3:
                insertion.display(insertion.sort(array));
                break;
            default:
                System.err.println("Incorrect choice.");
        }
        System.out.println();
    }
}
