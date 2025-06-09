import java.awt.*;
import java.io.*;
import javax.swing.*;

public class MahasiswaGUI extends JFrame {
    private static final String FILE_NAME = "mahasiswa.txt";

    private JTextField idField, namaField, jurusanField;
    private JTextArea displayArea;

    public MahasiswaGUI() {
        setTitle("Aplikasi Mahasiswa CRUD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 650);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 248, 255)); // AliceBlue

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(new Color(224, 255, 255)); // LightCyan
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel idLabel = new JLabel("ID:");
        JLabel namaLabel = new JLabel("Nama:");
        JLabel jurusanLabel = new JLabel("Jurusan:");

        idField = new JTextField();
        namaField = new JTextField();
        jurusanField = new JTextField();

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(namaLabel);
        inputPanel.add(namaField);
        inputPanel.add(jurusanLabel);
        inputPanel.add(jurusanField);

        JButton tambahButton = new JButton("Tambah");
        tambahButton.setBackground(new Color(70, 130, 180)); // SteelBlue
        tambahButton.setForeground(Color.WHITE);
        tambahButton.addActionListener(e -> tambahData());
        inputPanel.add(tambahButton);

        JButton lihatSemuaButton = new JButton("Lihat Semua");
        lihatSemuaButton.setBackground(new Color(100, 149, 237)); // CornflowerBlue
        lihatSemuaButton.setForeground(Color.WHITE);
        lihatSemuaButton.addActionListener(e -> lihatSemuaData());
        inputPanel.add(lihatSemuaButton);

        add(inputPanel, BorderLayout.NORTH);

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
        add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(new Color(240, 255, 255)); // Azure

        JButton clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(105, 105, 105)); // DimGray
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(e -> clearFields());
        buttonPanel.add(clearButton);


        JButton cariButton = new JButton("Cari ID");
        cariButton.setBackground(new Color(60, 179, 113)); // MediumSeaGreen
        cariButton.setForeground(Color.WHITE);
        cariButton.addActionListener(e -> lihatDataByID());
        buttonPanel.add(cariButton);

        JButton ubahButton = new JButton("Ubah");
        ubahButton.setBackground(new Color(255, 165, 0)); // Orange
        ubahButton.setForeground(Color.WHITE);
        ubahButton.addActionListener(e -> ubahData());
        buttonPanel.add(ubahButton);

        JButton hapusButton = new JButton("Hapus");
        hapusButton.setBackground(new Color(220, 20, 60)); // Crimson
        hapusButton.setForeground(Color.WHITE);
        hapusButton.addActionListener(e -> hapusData());
        buttonPanel.add(hapusButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void tambahData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(idField.getText() + "," + namaField.getText() + "," + jurusanField.getText());
            bw.newLine();
            displayArea.setText("Data berhasil ditambahkan.\n");
        } catch (IOException e) {
            displayArea.setText("Terjadi kesalahan: " + e.getMessage());
        }
    }
    private void clearFields() {
        idField.setText("");
        namaField.setText("");
        jurusanField.setText("");
        displayArea.setText("");
    }


    private void lihatSemuaData() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    sb.append("ID: ").append(data[0])
                      .append(", Nama: ").append(data[1])
                      .append(", Jurusan: ").append(data[2])
                      .append("\n");
                }
            }
            displayArea.setText(sb.toString());
        } catch (IOException e) {
            displayArea.setText("Gagal membaca file: " + e.getMessage());
        }
    }

    private void lihatDataByID() {
        String cariID = idField.getText();
        boolean ditemukan = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(cariID)) {
                    displayArea.setText("ID: " + data[0] + ", Nama: " + data[1] + ", Jurusan: " + data[2]);
                    ditemukan = true;
                    break;
                }
            }
            if (!ditemukan) {
                displayArea.setText("Data tidak ditemukan.\n");
            }
        } catch (IOException e) {
            displayArea.setText("Gagal membaca file: " + e.getMessage());
        }
    }

    private void ubahData() {
        String ubahID = idField.getText();
        boolean ditemukan = false;

        File file = new File(FILE_NAME);
        File tempFile = new File("temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(ubahID)) {
                    bw.write(ubahID + "," + namaField.getText() + "," + jurusanField.getText());
                    ditemukan = true;
                } else {
                    bw.write(line);
                }
                bw.newLine();
            }

        } catch (IOException e) {
            displayArea.setText("Gagal memproses file: " + e.getMessage());
            return;
        }

        file.delete();
        tempFile.renameTo(file);

        displayArea.setText(ditemukan ? "Data berhasil diubah.\n" : "Data tidak ditemukan.\n");
    }

    private void hapusData() {
        String hapusID = idField.getText();
        boolean ditemukan = false;

        File file = new File(FILE_NAME);
        File tempFile = new File("temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[0].equals(hapusID)) {
                    bw.write(line);
                    bw.newLine();
                } else {
                    ditemukan = true;
                }
            }

        } catch (IOException e) {
            displayArea.setText("Terjadi kesalahan: " + e.getMessage());
            return;
        }

        file.delete();
        tempFile.renameTo(file);

        displayArea.setText(ditemukan ? "Data berhasil dihapus.\n" : "Data tidak ditemukan.\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MahasiswaGUI::new);
    }
}
