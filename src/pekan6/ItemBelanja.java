package pekan6;

public class ItemBelanja {
	String nama;
    int kuantitas;
    String kategori;

    public ItemBelanja(String nama, int kuantitas, String kategori) {
        this.nama = nama;
        this.kuantitas = kuantitas;
        this.kategori = kategori;
    }

    @Override
    public String toString() {
        return "- " + nama + " (" + kuantitas + ") [" + kategori + "]";
    }
}