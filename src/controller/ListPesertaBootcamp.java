package controller;

import model.PesertaBootcamp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Kelas untuk manajemen daftar peserta bootcamp
public class ListPesertaBootcamp extends Component {
    // Daftar peserta bootcamp yang disimpan dalam sebuah List
    private static List<PesertaBootcamp> dataPeserta = new ArrayList<>();

    // Metode getter untuk mendapatkan data peserta
    private static List<PesertaBootcamp> getDataPeserta() {
        return dataPeserta;
    }

    // Metode untuk menambahkan peserta baru ke dalam daftar
    private static List<PesertaBootcamp> tambahPeserta(List<PesertaBootcamp> peserta, String nama, String alamat, String noTelp, String statusPeserta) {
        peserta.add(new PesertaBootcamp(nama, alamat, noTelp, statusPeserta));
        return peserta;
    }

    // Metode utama yang berisi loop menu aplikasi
    public static void base() {
        // Inisialisasi data peserta awal
        tambahPeserta(getDataPeserta(), "Tharissa Najwa", "Jl. Kenangan 8", "089578656789", "aktif");
        tambahPeserta(getDataPeserta(), "Putri Budiman", "Jl. Melati 2", "085378786543", "aktif");

        // Looping menu utama
        boolean menuLooping = true;
        while (menuLooping) {
            String menuMessage = menuMessage(); // Memanggil fungsi untuk menampilkan menu utama
            String input = JOptionPane.showInputDialog(null, menuMessage + "\n\nMasukkan pilihan menu Anda (0-4) :");
            if (input != null) {
                switch (input) {
                    case "1":
                        JOptionPane.showMessageDialog(null, "Anda memilih menu untuk melihat Peserta Bootcamp.");
                        lihatDataPeserta(); // Pengguna memilih untuk melihat daftar peserta, maka fungsi lihatDataPeserta dipanggil
                        break;
                    case "2":
                        daftarPeserta(); // Pengguna memilih untuk menambahkan peserta, maka fungsi daftarPeserta dipanggil
                        break;
                    case "3" :
                        hapusPeserta(); // Pengguna memilih untuk menghapus peserta, maka fungsi hapusPeserta dipanggil
                        break;
                    case "4" :
                        updatePeserta(); // Pengguna memilih untuk update peserta, maka fungsi updatePeserta dipanggil
                        break;
                    case "0":
                        JOptionPane.showMessageDialog(null, "Anda memilih untuk keluar dari aplikasi. \nTerimakasih atas waktunya.");
                        menuLooping = false; // Pengguna memilih keuar dari aplikasi, maka looping menu utama dihentikan
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Mohon maaf! Pilihan yang anda inputkan tidak valid. \nSilahkan coba inputkan kembali.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        }
    }

    // Metode untuk menampilkan pesan menu
    private static String menuMessage() {
        return "Halo, selamat datang di Manajemen Peserta Bootcamp! \n" +
                "Berikut menu - menu yang tersedia : \n" +
                "1. Lihat Peserta Bootcamp\n" +
                "2. Mendaftarkan Peserta Baru\n" +
                "3. Menghapus Peserta\n" +
                "4. Update Data Peserta\n" +
                "0. Keluar dari Aplikasi";
    }

    // Metode untuk menampilkan data peserta
    private static void lihatDataPeserta() {
        String dataMessage = "Berikut Data Peserta Bootcamp yang tersedia :";

        int number = 0; // Inisiliasi variabel number untuk pengecekan apakah data nya ada atau tidak
        for (PesertaBootcamp peserta: getDataPeserta()) { // Loop data peserta untuk menampilkan hasil
            String namaPeserta = peserta.getNama();
            String alamatPeserta = peserta.getAlamat();
            String noTelpPeserta = peserta.getNoTelp();
            String statusPeserta = peserta.getStatusPeserta();

            if (statusPeserta.equalsIgnoreCase("aktif")) { // Aktif artinya peserta masih ada dalam data, tidak aktif artinya peserta sudah dihapus
                number++; // Menambahkan nilai number untuk validasi hasil yang dimunculkan
                dataMessage += "\n\nNama: " + namaPeserta + "\nAlamat: " + alamatPeserta + "\nNo Telepon: " + noTelpPeserta + "\n";
            }
        }

        if (number > 0) { // Jika number kurang dari 0, maka akan menampilkan hasil bahwa data belum ada
            JOptionPane.showMessageDialog(null, dataMessage, "Data Peserta", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Saat ini belum ada data peserta yang tersedia.", "Data Peserta", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    // Metode untuk mendaftarkan/menambahkan peserta baru
    private static void daftarPeserta() {
        JOptionPane.showMessageDialog(null, "Anda memilih menu untuk Mendaftarkan Peserta Baru.");
        lihatDataPeserta(); // Menampilkan list peserta yang tersedia
        boolean loopInputNama = true;
        while (loopInputNama) {
            // Meminta input nama peserta baru
            String inputNama = JOptionPane.showInputDialog(null, "Silahkan inputkan nama peserta : ");

            if (inputNama != null && inputNama.equalsIgnoreCase("keluar")) { // Jika inputan pengguna adalah keluar, maka pengguna akan diarahkan kembali ke menu
                loopInputNama = false;
            } else {
                int namaExists = namaExists(inputNama);
                if (namaExists == 0) { // Jika nama tidak ada, maka pengguna akan diberikan inputan selanjutnya
                    // Meminta input alamat dan validasi nomor telepon
                    String inputAlamat = JOptionPane.showInputDialog(null, "Silahkan inputkan alamat peserta : ");
                    loopInputNama = validasiNoTelp(true, inputNama, inputAlamat);
                } else {
                    JOptionPane.showMessageDialog(null, "Mohon maaf nama " + inputNama + " sudah ada dalam data. Silahkan buat nama baru. \nUntuk kembali ke menu utama, silahkan ketik `keluar`.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Metode untuk pengecekan nama
    private static int namaExists(String inputNama) {
        int number = 0;
        // Cek apakah nama yang diinputkan pengguna sudah ada dalam data atau belum
        for (PesertaBootcamp peserta : getDataPeserta()) {
            if (peserta.getStatusPeserta().equalsIgnoreCase("aktif")) {
                if (inputNama != null && inputNama.equalsIgnoreCase(peserta.getNama())) {
                    number++; // Jika ada, maka number akan ditambahkan
                }
            }
        }
        return number;
    }

    // Metode untuk pengecekan no telepon saat daftar peserta
    private static boolean validasiNoTelp(boolean loopInputNama, String inputNama, String inputAlamat) {
        boolean validasiNoTelepon = false;
        while (!validasiNoTelepon) { // Looping untuk validasi no telepon
            String inputNoTelp = JOptionPane.showInputDialog(null, "Silahkan inputkan no telepon peserta : ");
            if (inputNoTelp != null && inputNoTelp.equalsIgnoreCase("keluar")) {
                validasiNoTelepon = true;
                loopInputNama = false;
            } else {
                if (inputNoTelp != null && inputNoTelp.matches("^[0-9+]{10,15}$")) { // Mengecek apakah inputan pengguna berupa 10-15 digit angka
                    if (inputNama != null && inputAlamat != null && inputNoTelp != null) { // Jika inputan berupa null, maka inputan tidak akan ditambahkan ke data peserta
                        tambahPeserta(getDataPeserta(), inputNama, inputAlamat, inputNoTelp, "aktif");
                        JOptionPane.showMessageDialog(null, "Selamat! Anda berhasil menambahkan peserta baru.");
                        int result = JOptionPane.showConfirmDialog( // Menanyakan pengguna apakah ingin menambahkan peserta baru lagi?
                                null,
                                "Apakah Anda ingin menambahkan peserta baru lagi?",
                                "Konfirmasi",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (result == JOptionPane.YES_OPTION) {
                            validasiNoTelepon = true;
                            loopInputNama = true;
                        } else if (result == JOptionPane.NO_OPTION) {
                            validasiNoTelepon = true;
                            loopInputNama = false;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Mohon maaf no telepon harus berupa 10-13 digit angka. Silahkan buat baru. \nUntuk kembali ke menu utama, silahkan ketik `keluar`.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return loopInputNama;
    }

    // Metode untuk menghapus peserta
    private static void hapusPeserta() {
        JOptionPane.showMessageDialog(null, "Anda memilih menu untuk menghapus peserta.");
        lihatDataPeserta();
        String inputNama = JOptionPane.showInputDialog(null, "Silahkan inputkan nama peserta yang ingin dihapus : ");

        int number = 0;
        for (PesertaBootcamp peserta : getDataPeserta()) {
            if (peserta.getStatusPeserta().equalsIgnoreCase("aktif")) {
                if (inputNama != null && inputNama.equalsIgnoreCase(peserta.getNama())) {
                    number++;
                    peserta.setStatusPeserta("tidak aktif"); // Jika nama yang diinputkan ada dalam data, maka peserta akan dihapus dengan metode soft delete
                }
            }
        }

        if (number > 0) {
            JOptionPane.showMessageDialog(null, "Selamat, Anda berhasil menghapus " + inputNama + " dari peserta bootcamp.");
        } else {
            JOptionPane.showMessageDialog(null, "Mohon maaf, data peserta tidak ada.");
        }
    }


    // Metode untuk melakukan update data peserta
    private static void updatePeserta() {
        JOptionPane.showMessageDialog(null, "Anda memilih menu untuk update peserta.");
        lihatDataPeserta();
        boolean loopingInputNama = false;
        while (!loopingInputNama) {
            String inputNama = JOptionPane.showInputDialog(null, "Silahkan inputkan nama peserta yang ingin dirubah : ");
            if (inputNama != null && inputNama.equalsIgnoreCase("keluar")) {
                loopingInputNama = true;
            } else {
                int number = namaExists(inputNama);
                if (number > 0) {
                    loopingInputNama = inputPilihanUpdate(true, inputNama);
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, peserta tidak ada dalam data. Silahkan coba kembali. \nUntuk kembali ke menu, silahkan ketikkan `keluar`.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Metode untuk validasi inputan pengguna ingin update entitas apa?
    private static boolean inputPilihanUpdate(boolean loopingInputNama, String inputNama) {
        boolean loopInputPilihan = false;
        while (!loopInputPilihan) {
            // Menanyakan pengguna ingin merubah entitas data apa?
            String inputPilihan = JOptionPane.showInputDialog(null, "Silahkan inputkan data yang ingin dirubah (Nama, Alamat, No Telepon): ");

            if (inputPilihan != null && inputPilihan.equalsIgnoreCase("keluar")) {
                loopInputPilihan = true;
                loopingInputNama = true;
            } else {
                switch (inputPilihan) {
                    case "Nama" :
                        updateNama(inputNama); // Pengguna memilih untuk mengubah entitas nama, maka fungsi updateNama akan dipanggil
                        loopInputPilihan = true;
                        break;
                    case "Alamat" :
                        updateAlamat(inputNama); // Pengguna memilih untuk mengubah entitas alamat, maka fungsi updateAlamat akan dipanggil
                        loopInputPilihan = true;
                        break;
                    case "No Telepon" :
                        updateNoTelp(inputNama); // Pengguna memilih untuk mengubah entitas no telepon, maka fungsi updateNoTelp akan dipanggil
                        loopInputPilihan = true;
                        break;
                    default :
                        JOptionPane.showMessageDialog(null, "Maaf! Inputan harus berupa Nama, Alamat, atau No Telepon. Silahkan coba kembali. \nUntuk kembali ke menu, silahkan ketikkan `keluar`.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        }
        return loopingInputNama;
    }

    // Metode untuk mengupdate nama peserta
    private static void updateNama(String namaLama) {
        boolean loopInputNamaBaru = false;
        while (!loopInputNamaBaru) {
            String inputNamaBaru = JOptionPane.showInputDialog(null, "Silahkan inputkan nama peserta yang baru : ");

            if (inputNamaBaru != null && inputNamaBaru.equalsIgnoreCase("keluar")) {
                loopInputNamaBaru = true;
            } else {
                loopInputNamaBaru = validUpdateNama(inputNamaBaru, namaLama, false);
            }
        }
    }

    // Metode untuk validasi update nama peserta
    private static boolean validUpdateNama(String inputNamaBaru, String namaLama, boolean loopInputNamaBaru) {
        for (PesertaBootcamp peserta : getDataPeserta()) {
            if (peserta.getStatusPeserta().equalsIgnoreCase("aktif")) {
                if (inputNamaBaru != null && !inputNamaBaru.contains(peserta.getNama())) {
                    if (namaLama.equalsIgnoreCase(peserta.getNama())) {
                        peserta.setNama(inputNamaBaru);
                        JOptionPane.showMessageDialog(null, "Selamat, Anda berhasil merubah nama " + namaLama + " menjadi " + inputNamaBaru + ".");
                        loopInputNamaBaru = true;
                        break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Mohon maaf nama " + inputNamaBaru + " sudah ada dalam data. Silahkan buat nama baru. \nUntuk kembali ke menu utama, silahkan ketik `keluar`.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        }
        return loopInputNamaBaru;
    }

    // Metode untuk mengupdate alamat peserta
    private static void updateAlamat(String namaLama) {
        String inputAlamatBaru = JOptionPane.showInputDialog(null, "Silahkan inputkan alamat peserta yang baru : ");

        for (PesertaBootcamp peserta : getDataPeserta()) {
            if (peserta.getStatusPeserta().equalsIgnoreCase("aktif")) {
                if (namaLama.equalsIgnoreCase(peserta.getNama())) {
                    String alamatLama = peserta.getAlamat();
                    peserta.setAlamat(inputAlamatBaru);
                    JOptionPane.showMessageDialog(null, "Selamat, Anda berhasil merubah alamat " + alamatLama + " menjadi " + inputAlamatBaru + ".");
                }
            }
        }
    }

    // Metode untuk mengupdate nomor telepon peserta
    private static void updateNoTelp(String namaLama) {
        boolean validasiNoTelepon = false;
        while (!validasiNoTelepon) {
            String inputNoTelpBaru = JOptionPane.showInputDialog(null, "Silahkan inputkan no telepon peserta yang baru : ");

            if (inputNoTelpBaru != null && inputNoTelpBaru.equalsIgnoreCase("keluar")) {
                validasiNoTelepon = true;
            } else {
                validasiNoTelepon = validUpdateNoTelp(inputNoTelpBaru, namaLama, false);
            }
        }
    }

    // Metode untuk validasi update nomor telepon peserta
    private static boolean validUpdateNoTelp(String inputNoTelpBaru, String namaLama, boolean validasiNoTelepon) {
        if (inputNoTelpBaru != null && inputNoTelpBaru.matches("^[0-9+]{10,15}$")) {
            for (PesertaBootcamp peserta : getDataPeserta()) {
                if (peserta.getStatusPeserta().equalsIgnoreCase("aktif")) {
                    if (namaLama.equalsIgnoreCase(peserta.getNama())) {
                        String noTelpLama = peserta.getNoTelp();
                        peserta.setNoTelp(inputNoTelpBaru);
                        JOptionPane.showMessageDialog(null, "Selamat, Anda berhasil merubah no telepon " + noTelpLama + " menjadi " + inputNoTelpBaru + ".");
                        validasiNoTelepon = true;
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Mohon maaf no telepon harus berupa 10-13 digit angka. Silahkan buat baru. \nUntuk kembali ke menu utama, silahkan ketik `keluar`.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return validasiNoTelepon;
    }
}