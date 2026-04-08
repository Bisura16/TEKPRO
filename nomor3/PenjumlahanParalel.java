package nomor3;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
//program utama
public class PenjumlahanParalel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Menerima Input
        System.out.print("Masukkan Jumlah Thread: ");
        int numThreads = scanner.nextInt();

        System.out.print("Masukkan Angka Akhir: ");
        long finalNumber = scanner.nextLong();

        System.out.println("\n--- Memulai Proses Paralel ---");

        ResultTracker tracker = new ResultTracker();
        List<SumWorker> threads = new ArrayList<>();

        // 2. Mekanisme Pembagian Tugas (Divide and Conquer)
        long chunkSize = finalNumber / numThreads;
        long remainder = finalNumber % numThreads; 
        long currentStart = 1;

        for (int i = 1; i <= numThreads; i++) {
            long currentEnd = currentStart + chunkSize - 1;

            if (i == numThreads) {
                currentEnd += remainder;
            }

            SumWorker worker = new SumWorker(i, currentStart, currentEnd, tracker);
            threads.add(worker);
            
            worker.start();

            currentStart = currentEnd + 1;
        }

        // 3. Menunggu semua thread selesai bekerja (Join)
        for (SumWorker worker : threads) {
            try {
                worker.join(); 
            } catch (InterruptedException e) {
                System.out.println("Thread terinterupsi: " + e.getMessage());
            }
        }

        // 4. Menampilkan Hasil Akhir
        System.out.println("\n--- HASIL AKHIR ---");
        System.out.println("Total Penjumlahan (1 sampai " + finalNumber + ") = " + tracker.getTotalSum());
        
        scanner.close();
    }
}