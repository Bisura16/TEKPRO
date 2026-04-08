package nomor1;
class Account {
    int balance = 150; //menyimpan saldo awal sebesar 150
}

public class TransferFulus {
    public static void main(String[] args) throws InterruptedException {

        // Dua objek Account dibuat secara terpisah di heap memory.
        // Keduanya akan menjadi KUNCI (lock) dalam synchronized block.
        // Inilah yang akan diperebutkan oleh kedua thread dan ini adalah akar masalah deadlock.
        Account acc1 = new Account();
        Account acc2 = new Account();

        // Thread 1: Menjumlahkan/ transfer fulus dari acc1 ke acc2
        Thread t1 = new Thread(() -> {
            synchronized (acc1) { //t1 MENGUNCI acc1 terlebih dahulu. Selama berada di sini, tidak ada thread lain yang bisa menyentuh acc1.
                System.out.println("t1: Mengunci acc1, menunggu acc2 ");
                try { Thread.sleep(100); } catch (Exception e) {} 
                //Simulasi dengan memberikan jeda. Mengapa diperlukan Exception? karena Method Thread.sleep() menjeda thread. 
                //Saat jeda, thread bisa diinterupsi oleh thread lain sehingga memicu InterruptedException.
                //Karena ini checked exception, ia wajib ditangani menggunakan try-catch (atau throws) 
                //agar program bisa dikompilasi dan terhindar dari crash.

                synchronized (acc2) { // t1 berusaha mendapatkan lock dari objek acc2 agar bisa menyelesaikan operasi penjumlahannya.
                    System.out.println("Mengunci acc2, melakukan transfer dari acc1 ke acc2");
                    acc2.balance += acc1.balance;
                }
            }
        });

        // Thread 2: Transfer dari acc2 ke acc1
        Thread t2 = new Thread(() -> {
            // SOLUSI: dengan mengubah urutan, harus Selalu kunci acc1 untuk pertama kali, baru selanjutnya mengunci acc2, 
            //  harus sama seperti Thread 1.untuk menghindari Circular Wait yang menyebabkan deadlock.
            synchronized (acc1) { 
                System.out.println("t2: Mengunci acc1, menunggu acc2");
                try { Thread.sleep(100); } catch (Exception e) {}

                // Mengunci acc2 setelah berhasil mengunci acc1
                synchronized (acc2) { 
                    System.out.println("t2: Mengunci acc2, mentransfer saldo acc2 ke acc1");
                    acc1.balance += acc2.balance;
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("--- HASIL AKHIR ---");
        System.out.println("Saldo Akhir acc1: " + acc1.balance);
		System.out.println("Saldo Akhir acc2: " + acc2.balance);
    }
}
