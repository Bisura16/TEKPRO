package nomor2;

class Resto {
    private int chickenStock = 100;

    // Solusinya ialah dengan Tambahkan keyword 'synchronized' di sini
    // keyword 'synchronized' membuat method ini menjadi 'Critical Section'.
    //'Critical Section', artinya thread dapat mengakses atau mengubah data yang digunakan bersama (shared resource) dengan thread lainnya
    // Kalau Kasir-A masuk ke method ini, Kasir-B dan Kasir-C harus tunggu di luar 
    // sampai Kasir-A selesai sepenuhnya.
    public synchronized void serveCustomer(String cashierName) {
        if (chickenStock > 0) {
            try { Thread.sleep(10); } catch (InterruptedException e) {}
            
            chickenStock--; 
            System.out.println(cashierName + " berhasil menjual 1 ayam. Sisa stok: " + chickenStock);
        } else {
            System.out.println(cashierName + " gagal: Stok Habis!");
        }
    }

    public int getRemainingStock() {
        return chickenStock;
    }
}

public class RestoSimulasi {
    public static void main(String[] args) throws InterruptedException {
        Resto ayamJuicyLuicyGallagher = new Resto();

        Runnable task = () -> {
            for (int i = 0; i < 40; i++) {
                ayamJuicyLuicyGallagher.serveCustomer(Thread.currentThread().getName());
            }
        };

        Thread kasir1 = new Thread(task, "Kasir-A");
        Thread kasir2 = new Thread(task, "Kasir-B");
        Thread kasir3 = new Thread(task, "Kasir-C");

        kasir1.start();
        kasir2.start();
        kasir3.start();

        kasir1.join();
        kasir2.join();
        kasir3.join();

        System.out.println("--- HASIL AKHIR STOK: " + ayamJuicyLuicyGallagher.getRemainingStock() + " ---");
    }
}
