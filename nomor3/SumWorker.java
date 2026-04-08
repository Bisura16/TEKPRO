package nomor3;
// Kelas WorkerThread yang bertugas melakukan penjumlahan parsial
public class SumWorker extends Thread {
    private int threadId;
    private long startNumber;
    private long endNumber;
    private ResultTracker tracker;

    public SumWorker(int threadId, long startNumber, long endNumber, ResultTracker tracker) {
        this.threadId = threadId;
        this.startNumber = startNumber;
        this.endNumber = endNumber;
        this.tracker = tracker;
    }

    @Override
    public void run() {
        long partialSum = 0;
        
        System.out.println("Thread-" + threadId + ": Menjumlahkan angka dari " + startNumber + " sampai " + endNumber);

        // Melakukan penjumlahan parsial
        for (long i = startNumber; i <= endNumber; i++) {
            partialSum += i;
        }

        System.out.println("Thread-" + threadId + ": Selesai. Hasil parsial = " + partialSum);
        
        // Menambahkan hasil parsial ke total akhir secara aman (Thread-Safe)
        tracker.addPartialSum(partialSum);
    }
}