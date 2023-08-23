public class PesertaBootcamp {

    private String nama;
    private String alamat;
    private String noTelp;
    private String statusPeserta;

    public PesertaBootcamp(String nama, String alamat, String noTelp, String statusPeserta) {
        this.nama = nama;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.statusPeserta = statusPeserta;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getStatusPeserta() {
        return statusPeserta;
    }

    public void setStatusPeserta(String statusPeserta) {
        this.statusPeserta = statusPeserta;
    }
}
