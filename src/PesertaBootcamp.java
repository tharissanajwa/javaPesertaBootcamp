public class PesertaBootcamp {
    // Inilisiasi variabel atribut peserta
    private String nama;
    private String alamat;
    private String noTelp;
    private String statusPeserta; // Status disini berfungsi untuk metode soft delete, bila status nya tidak aktif maka peserta tersebut tidak ada dalam data, namun history nya masih tersimpan

    // Konstruktor untuk inisialisasi objek PesertaBootcamp
    public PesertaBootcamp(String nama, String alamat, String noTelp, String statusPeserta) {
        this.nama = nama;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.statusPeserta = statusPeserta;
    }

    // Metode getter untuk mendapatkan nama peserta
    public String getNama() {
        return nama;
    }

    // Metode setter untuk mengubah nama peserta
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Metode getter untuk mendapatkan alamat peserta
    public String getAlamat() {
        return alamat;
    }

    // Metode setter untuk mengubah alamat peserta
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    // Metode getter untuk mendapatkan nomor telepon peserta
    public String getNoTelp() {
        return noTelp;
    }

    // Metode setter untuk mengubah nomor telepon peserta
    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    // Metode getter untuk mendapatkan status peserta
    public String getStatusPeserta() {
        return statusPeserta;
    }

    // Metode setter untuk mengubah status peserta
    public void setStatusPeserta(String statusPeserta) {
        this.statusPeserta = statusPeserta;
    }
}
