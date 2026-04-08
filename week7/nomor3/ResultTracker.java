package nomor3;
// Kelas untuk menampung hasil akhir secara aman (Thread-Safe)
public class ResultTracker {
    private long totalSum = 0;

    // Method ini dikunci dengan keyword synchronized
    // agar hanya satu thread yang bisa menambahkan nilai pada satu waktu.
    public synchronized void addPartialSum(long partialSum) {
        totalSum += partialSum;
    }

    public long getTotalSum() {
        return totalSum;
    }
}
