

package com.mycompany.mavenproject3;
import java.util.Arrays;
import java.util.Random;
public class Mavenproject3 {

    public static void main(String[] args) {
          // توليد مصفوفة البيانات الأصلية بحجم 100,000 عشوائياً
int[] originalRandom = generateRandomArray(N);
int[] originalReversed = generateReversedArray(originalRandom);
int[] originalPartial = generatePartiallySortedArray(originalRandom);
int[] originalSorted = generateSortedArray(originalRandom);

// طباعة رأس الجدول بالمخرجات المطلوبة
System.out.println("-----------------------------------------------------------------------------------------");
System.out.printf("%-20s | %-15s | %-15s | %-15s | %-15s%n", 
        "الخوارزمية", "غير مرتبة (Random)", "مرتبة عكسياً", "مرتبة جزئياً", "مرتبة تماماً");
System.out.println("-----------------------------------------------------------------------------------------");

// اختبار الخوارزميات الخمس
runAndPrint("Selection Sort", originalRandom, originalReversed, originalPartial, originalSorted, 1);
runAndPrint("Insertion Sort", originalRandom, originalReversed, originalPartial, originalSorted, 2);


System.out.println("-----------------------------------------------------------------------------------------");
    }
   // حجم المصفوفة المطلوب
private static final int N = 100000;

// --- دوال توليد البيانات ---
private static int[] generateRandomArray(int size) {
    Random rand = new Random();
    int[] arr = new int[size];
    for (int i = 0; i < size; i++) {
        arr[i] = rand.nextInt(1000000);
    }
    return arr;
}

private static int[] generateReversedArray(int[] base) {
    int[] arr = Arrays.copyOf(base, base.length);
    Arrays.sort(arr);
    int[] reversed = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
        reversed[i] = arr[arr.length - 1 - i];
    }
    return reversed;
}

private static int[] generatePartiallySortedArray(int[] base) {
    int[] arr = Arrays.copyOf(base, base.length);
    Arrays.sort(arr);
    Random rand = new Random();
    int swaps = (int) (arr.length * 0.2);
    for (int i = 0; i < swaps; i++) {
        int idx1 = rand.nextInt(arr.length);
        int idx2 = rand.nextInt(arr.length);
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
    return arr;
}

private static int[] generateSortedArray(int[] base) {
    int[] arr = Arrays.copyOf(base, base.length);
    Arrays.sort(arr);
    return arr;
}

private static void runAndPrint(String name, int[] rand, int[] rev, int[] part, int[] sort, int type) {
    long timeRand = measureTime(rand, type);
    long timeRev = measureTime(rev, type);
    long timePart = measureTime(part, type);
    long timeSort = measureTime(sort, type);

 System.out.printf("%-20s | %-12d us | %-12d us | %-12d us | %-12d us\n", name, timeRand, timeRev, timePart, timeSort);
}

private static long measureTime(int[] original, int type) {
    int[] testArray = Arrays.copyOf(original, original.length);
    long startTime = System.nanoTime();

    switch (type) {
        case 1: selectionSort(testArray); break;
        case 2: insertionSort(testArray); break;
    }

    long endTime = System.nanoTime();
    return (endTime - startTime) / 1000;
}

// --- خوارزميات الترتيب ---
public static void selectionSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        int minIdx = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIdx]) minIdx = j;
        }
        int temp = arr[minIdx]; arr[minIdx] = arr[i]; arr[i] = temp;
    }
}

public static void insertionSort(int[] arr) {
    int n = arr.length;
    for (int i = 1; i < n; ++i) {
        int key = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}

private static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = (low - 1);
    for (int j = low; j < high; j++) {
        if (arr[j] < pivot) {
            i++;
            int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
        }
    }
    int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
    return i + 1;
}
}
