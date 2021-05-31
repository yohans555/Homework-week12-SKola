package PR;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ParseCsvFromURLExample {
    public static void main(String... args) throws MalformedURLException {
        String target ="https://github.com/aswansyahputra/nusandata/blob/master/data-raw/pendidikan/ddk_tingkat_pendidikan.csv";
        URL url = new URL("target");
        

        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase();
        String query = "INSERT INTO homework2 (kode_provinsi, nama_provinsi, tingkat_pendidikan,jenis_kelamin,jenis_kelamin,jumlah_individu)VALUES(?,?,?,?,?)";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/skola", "root", "Semarang37!");
        PreparedStatement ps = con.prepareStatement(query);


        try(CSVParser csvParser = CSVParser.parse(url, StandardCharsets.UTF_8, csvFormat)) {
            for(CSVRecord csvRecord : csvParser) {
                int kode_provinsi = csvRecord.get("kode_provinsi");
                String nama_provinsi = csvRecord.get("nama_provinsi");
                String tingkat_pendidikan = csvRecord.get("tingkat_pendidikan");
                String jenis_kelamin = csvRecord.get("jenis_kelamin");
                int jumlah_individu = csvRecord.get("jumlah_individu");
                ps.setInt(1, kode_provinsi);
                ps.setString(2, nama_provinsi);
                ps.setString(3, tingkat_pendidikan);
                ps.setString(4, jenis_kelamin);
                ps.setInt(5, jumlah_individu);
                ps.executeUpdate();
            }
            ps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}