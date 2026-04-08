package nomor3;

import java.util.Arrays;

public class ProblemThree {
    public static void main(String[] args) {
        int arr[] = {12, 4, 3, 1, 9, 657};
        int n = 3; // Mencari elemen terbesar ke-3

        int ans = Arrays.stream(arr)
                .boxed() // [2] Konversi ke Stream<Integer>
                .sorted((a, b) -> Integer.compare(b, a)) // descending
                .skip(n - 1) // [3] Lewati elemen sebelumnya
                .findFirst() // [4] Ambil elemen berikutnya
                .orElse(0); // [5] default jika kosong

        System.out.println("The 3rd largest element is: " + ans);
    }
}