import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ListPesertaBootcamp extends Component {
    private static List<PesertaBootcamp> dataPeserta = new ArrayList<>();

    public static List<PesertaBootcamp> getDataPeserta() {
        return dataPeserta;
    }

    public static List<PesertaBootcamp> tambahPeserta(List<PesertaBootcamp> peserta, String nama, String alamat, String noTelp, String statusPeserta) {
        peserta.add(new PesertaBootcamp(nama, alamat, noTelp, statusPeserta));
        return peserta;
    }

    public static void base() {
        // Membuat data set
        tambahPeserta(getDataPeserta(), "Tharissa Najwa", "Jl. Kenangan 8", "089578656789", "aktif");
        tambahPeserta(getDataPeserta(), "Putri Budiman", "Jl. Melati 2", "085378786543", "aktif");

        boolean menuLooping = true;
        while (menuLooping) {
            String menuMessage = menuMessage();
            String input = JOptionPane.showInputDialog(null, menuMessage + "\n\nMasukkan pilihan menu Anda (0-4) :");
            if (input != null) {
                switch (input) {
                    case "1":
                        JOptionPane.showMessageDialog(null, "Anda memilih menu untuk melihat Peserta Bootcamp.");
                        lihatDataPeserta();
                        break;
                    case "2":
                        daftarPeserta();
                        break;
                    case "3" :
                        hapusPeserta();
                        break;
                    case "4" :
                        updatePeserta();
                        break;
                    case "0":
                        JOptionPane.showMessageDialog(null, "Anda memilih untuk keluar dari aplikasi. \nTerimakasih atas waktunya.");
                        menuLooping = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Mohon maaf! Pilihan yang anda inputkan tidak valid. \nSilahkan coba inputkan kembali.");
                        break;
                }
            }
        }
    }

    public static String menuMessage() {
        String menuMessage;
        menuMessage = "Halo, selamat datang di Manajemen Peserta Bootcamp! \n" +
                "Berikut menu - menu yang tersedia : \n" +
                "1. Lihat Peserta Bootcamp\n" +
                "2. Mendaftarkan Peserta Baru\n" +
                "3. Menghapus Peserta\n" +
                "4. Update Data Peserta\n" +
                "0. Keluar dari Aplikasi";
        return menuMessage;
    }
    public static void lihatDataPeserta() {
        String dataMessage = "Berikut Data Peserta Bootcamp yang tersedia :";

        int number = 0;
        for (PesertaBootcamp peserta: getDataPeserta()) {
            String namaPeserta = peserta.getNama();
            String alamatPeserta = peserta.getAlamat();
            String noTelpPeserta = peserta.getNoTelp();
            String statusPeserta = peserta.getStatusPeserta();

            if (statusPeserta.equalsIgnoreCase("aktif")) {
                number++;
                dataMessage += "\n\nNama: " + namaPeserta + "\nAlamat: " + alamatPeserta + "\nNo Telepon: " + noTelpPeserta + "\n";
            }
        }

        if (number > 0) {
            JOptionPane.showMessageDialog(null, dataMessage, "Data Peserta", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Saat ini belum ada data peserta yang tersedia.", "Data Peserta", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void daftarPeserta() {
        JOptionPane.showMessageDialog(null, "Anda memilih menu untuk Mendaftarkan Peserta Baru.");
        lihatDataPeserta();
        boolean loopInputNama = true;
        while (loopInputNama) {
            String inputNama = JOptionPane.showInputDialog(null, "Silahkan inputkan nama peserta : ");

            if (inputNama != null && inputNama.equalsIgnoreCase("keluar")) {
                loopInputNama = false;
            } else {
                boolean namaExists = false;
                for (PesertaBootcamp peserta : getDataPeserta()) {
                    if (peserta.getStatusPeserta().equalsIgnoreCase("aktif")) {
                        if (inputNama != null && inputNama.equalsIgnoreCase(peserta.getNama())) {
                            namaExists = true;
                        }
                    }
                }

                if (!namaExists) {
                    String inputAlamat = JOptionPane.showInputDialog(null, "Silahkan inputkan alamat peserta : ");
                    boolean validasiNoTelepon = false;
                    while (!validasiNoTelepon) {
                        String inputNoTelp = JOptionPane.showInputDialog(null, "Silahkan inputkan no telepon peserta : ");
                        if (inputNoTelp != null && inputNoTelp.equalsIgnoreCase("keluar")) {
                            validasiNoTelepon = true;
                            loopInputNama = false;
                        } else {
                            if (inputNoTelp != null && inputNoTelp.matches("^[0-9]{10,13}$")) {
                                if (inputNama != null && inputAlamat != null && inputNoTelp != null) {
                                    tambahPeserta(getDataPeserta(), inputNama, inputAlamat, inputNoTelp, "aktif");
                                    JOptionPane.showMessageDialog(null, "Selamat! Anda berhasil menambahkan peserta baru.");
                                    validasiNoTelepon = true;
                                    loopInputNama = false;
                                } else {
                                    validasiNoTelepon = true;
                                    loopInputNama = false;
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Mohon maaf no telepon harus berupa 10-13 digit angka. Silahkan buat baru. \nUntuk kembali ke menu utama, silahkan ketik `keluar`.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Mohon maaf nama " + inputNama + " sudah ada dalam data. Silahkan buat nama baru. \nUntuk kembali ke menu utama, silahkan ketik `keluar`.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void hapusPeserta() {
        JOptionPane.showMessageDialog(null, "Anda memilih menu untuk menghapus peserta.");
        lihatDataPeserta();
        String inputNama = JOptionPane.showInputDialog(null, "Silahkan inputkan nama peserta yang ingin dihapus : ");

        int number = 0;
        for (PesertaBootcamp peserta : getDataPeserta()) {
            if (peserta.getStatusPeserta().equalsIgnoreCase("aktif")) {
                if (inputNama != null && inputNama.equalsIgnoreCase(peserta.getNama())) {
                    number++;
                    peserta.setStatusPeserta("tidak aktif");
                }
            }
        }

        if (number > 0) {
            JOptionPane.showMessageDialog(null, "Selamat, Anda berhasil menghapus " + inputNama + " dari peserta bootcamp.");
        } else {
            JOptionPane.showMessageDialog(null, "Mohon maaf, data peserta tidak ada.");
        }
    }

    public static void updatePeserta() {
        JOptionPane.showMessageDialog(null, "Anda memilih menu untuk update peserta.");
        lihatDataPeserta();
        boolean loopingInputNama = false;
        while (!loopingInputNama) {
            String inputNama = JOptionPane.showInputDialog(null, "Silahkan inputkan nama peserta yang ingin dirubah : ");

            if (inputNama != null && inputNama.equalsIgnoreCase("keluar")) {
                loopingInputNama = true;
            } else {
                int number = 0;
                for (PesertaBootcamp peserta : getDataPeserta()) {
                    if (peserta.getStatusPeserta().equalsIgnoreCase("aktif")) {
                        if (inputNama != null && inputNama.equalsIgnoreCase(peserta.getNama())) {
                            number++;
                        }
                    }
                }

                if (number > 0) {
                    loopingInputNama = true;
                    boolean loopInputPilihan = false;
                    while (!loopInputPilihan) {
                        String inputPilihan = JOptionPane.showInputDialog(null, "Silahkan inputkan data yang ingin dirubah (Nama, Alamat, No Telepon): ");

                        if (inputPilihan != null && inputPilihan.equalsIgnoreCase("keluar")) {
                            loopInputPilihan = true;
                            loopingInputNama = true;
                        } else {
                            switch (inputPilihan) {
                                case "Nama" :
                                    updateNama(inputNama);
                                    loopInputPilihan = true;
                                    break;
                                case "Alamat" :
                                    updateAlamat(inputNama);
                                    loopInputPilihan = true;
                                    break;
                                case "No Telepon" :
                                    updateNoTelp(inputNama);
                                    loopInputPilihan = true;
                                    break;
                                default :
                                    JOptionPane.showMessageDialog(null, "Maaf! Inputan harus berupa Nama, Alamat, atau No Telepon. Silahkan coba kembali. \nUntuk kembali ke menu, silahkan ketikkan `keluar`.", "Error", JOptionPane.ERROR_MESSAGE);
                                    break;
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, peserta tidak ada dalam data. Silahkan coba kembali. \nUntuk kembali ke menu, silahkan ketikkan `keluar`.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    public static void updateNama(String namaLama) {
        boolean loopInputNamaBaru = false;
        while (!loopInputNamaBaru) {
            String inputNamaBaru = JOptionPane.showInputDialog(null, "Silahkan inputkan nama peserta yang baru : ");

            if (inputNamaBaru != null && inputNamaBaru.equalsIgnoreCase("keluar")) {
                loopInputNamaBaru = true;
            } else {
                for (PesertaBootcamp peserta : getDataPeserta()) {
                    if (peserta.getStatusPeserta().equalsIgnoreCase("aktif")) {
                        if (inputNamaBaru != null && !inputNamaBaru.equalsIgnoreCase(peserta.getNama())) {
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
            }
        }
    }

    public static void updateAlamat(String namaLama) {
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

    public static void updateNoTelp(String namaLama) {
        boolean validasiNoTelepon = false;
        while (!validasiNoTelepon) {
            String inputNoTelpBaru = JOptionPane.showInputDialog(null, "Silahkan inputkan no telepon peserta yang baru : ");

            if (inputNoTelpBaru != null && inputNoTelpBaru.equalsIgnoreCase("keluar")) {
                validasiNoTelepon = true;
            } else {
                if (inputNoTelpBaru != null && inputNoTelpBaru.matches("^[0-9]{10,13}$")) {
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
            }
        }

    }
}