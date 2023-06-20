
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/*
 * Nama : Felix Windriyareksa Hardyan
 * NPM : 50421506
 * Kelas : 2IA12
 * 
 * Program absen mahasiswa
 */


 //class data untuk mendeklarasikan data
class Data {
    protected String[][] absen; // array yg dipakai untuk menyimpan data absen mahasiswa
    protected int jumlahMhs; // jumlah mhs dalam kelas
    protected int jumlahHadir; // jumlah siswa yang hadir 

    // konstruktor class Data
    public Data(int jumlahMhs) {
        this.absen = new String[jumlahMhs][4]; // mendeklarasikan array absen dengan ukuran yang diberikan
        this.jumlahMhs = jumlahMhs; // mendekalarasikan jumlah siswa
        this.jumlahHadir = 0; // jumlah siswa yang hadir di awal di-set ke 0
    }
}

/**
 * class Kehadiran adalah subclass dari class Data
 */
class Kehadiran extends Data{
    Kehadiran(int jumlahMhs){
        super(jumlahMhs);
    }

    // function yang mengambil input dari pengguna
    public void inputAbsen() {
        Scanner scanner = new Scanner(System.in);
    
        // loop sebanyak jumlah mhs untuk mengambil input nama dan status kehadiran
        for (int i = 0; i < super.jumlahMhs; i++) {
            System.out.print("Masukkan Nama Mahasiswa Ke-" + (i+1) + ": ");
            String nama = scanner.nextLine();
            super.absen[i][0] = nama; // simpan nama mhs ke array absen pada kolom 0

            //Input jenis kelamin
            System.out.print("Jenis Kelamin? (L/P):");
            String jkel = scanner.nextLine();
            super.absen[i][3] = jkel; // simpan jenis kelamin ke array absen pada kolom 3
            if (jkel.equalsIgnoreCase("L")){ //jika input L maka akan keluar output laki-laki
            super.absen[i][3] = "Laki-laki";
            } else if (jkel.equalsIgnoreCase("P")) { //jika input P maka akan keluar output perempuan
                super.absen[i][3] = "Perempuan";
            } else {
                System.out.println("Jenis Kelamin Tidak Valid!!!");
            }

            //Proses input kehadiran
            System.out.print("Apakah Mahasiswa Hadir Hari ini? (Y/N): ");
            String hadir = scanner.nextLine();
    
            // jika mhs hadir, simpan "Hadir" ke array absen pada kolom 1 dan tambahkan 1 ke jumlahHadir
            if (hadir.equalsIgnoreCase("Y")) { //jika input y akan menyimpan status hadir ke array absen pada kolom 1
                super.absen[i][1] = "Hadir";
                super.absen[i][2] = ""; //pada array absen kolom 2 (keterangan) akan dikosongkan
                super.jumlahHadir++;
            } else { // jika mhs tidak hadir, simpan "Tidak hadir" ke array absen pada kolom 1
                super.absen[i][1] = "Tidak Hadir";
                System.out.print("Keterangan? (S/I/A): "); //jika mhs tidak hadir maka akan keluar kolom input keterangan
                String ket = scanner.nextLine();
                if (ket.equalsIgnoreCase("S")) {
                    super.absen[i][1] = "Tidak Hadir"; //jika input S maka akan menyimpan Sakit di array absen kolom 2
                    super.absen[i][2] = "Sakit";
                } else if (ket.equalsIgnoreCase("I")) {
                    super.absen[i][1] = "Tidak Hadir"; //jika input I maka akan menyimpan Izin di array absen kolom 2
                    super.absen[i][2] = "Izin";
                } else if (ket.equalsIgnoreCase("A")){
                    super.absen[i][1] = "Tidak Hadir"; //jika input A maka akan menyimpan Alfa di array absen kolom 2
                    super.absen[i][2] = "Alfa";
                } else {
                    System.out.println("Keterangan Tidak Valid!!!");
                }
            }
        }
        scanner.close(); //Untuk menutup scanner
    }

    // metode untuk menampilkan data hasil absen ke layar
    public void tampilkanAbsen() {
        
        //untuk menampilkan tanggal dan waktu pada saat program berjalan
        DateTimeFormatter tanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
        LocalDateTime tglSkrg = LocalDateTime.now(); 

        //print out tabel absen
        System.out.println("\nABSEN KELAS 2IA12 - " + tanggal.format(tglSkrg));// tanggal dan jam saat program di run
        System.out.println("=====================================================================================");
        System.out.println("No.\tNama\t\tJenis Kelamin\t\tHadir/Tidak Hadir\tKeterangan");
        System.out.println("=====================================================================================");
    
        // loop sebanyak jumlah mhs untuk menampilkan data absen
        for (int i = 0; i < super.jumlahMhs; i++) {
            System.out.println((i+1) + "\t" + super.absen[i][0] + "\t\t" + super.absen[i][3] +"\t\t" + super.absen[i][1] + "\t\t" + super.absen[i][2]);
        }
        //print out jumlah mahasiswa yang hadir setelah proses penginputan absen selesai
        System.out.println("=====================================================================================");
        System.out.println("Jumlah Mahasiswa Yang Hadir: " + super.jumlahHadir);
    }
}

//class main untuk menjalankan seluruh program
public class AbsenKelas {
    public static void main(String[] args) {

        //scanner
        Scanner scanner = new Scanner(System.in);

        //Print out judul program
        System.out.println("PROGRAM ABSEN KELAS 2IA12");
        System.out.println("=========================");

        System.out.print("Masukkan Jumlah Mahasiswa: "); // input untuk memasukkan jumlah total mahasiswa
        int jumlahMhs = scanner.nextInt();

        Kehadiran absenKelas = new Kehadiran(jumlahMhs); // membuat objek AbsenKelas dengan jumlah siswa yang diberikan

        absenKelas.inputAbsen(); // mengambil input absen dari pengguna
        absenKelas.tampilkanAbsen(); // menampilkan data absen ke layar
        scanner.close();
    } 
}
