import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Menggunakan Event Listener Interface (ActionListener)
public class MainGUI extends JFrame implements ActionListener {

    // Atomic Components
    private JLabel labelJudul, labelInput, labelStatus;
    private JTextField fieldTugas;
    private JButton btnTambah, btnHapus;
    private JTextArea areaDaftar;

    // Intermediate Containers
    private JPanel panelAtas, panelInput, panelBawah;

    public MainGUI() {
        // 1. TOP-LEVEL CONTAINER (JFrame)
        setTitle("Catatan Tugas Kuliah - PBO");
        setSize(500, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Posisi tengah layar

        // 4. LAYOUT CLASS 1: BorderLayout pada JFrame
        setLayout(new BorderLayout(10, 10));

        // 2. INTERMEDIATE CONTAINER 1 (JPanel bagian atas)
        // Menggunakan LAYOUT CLASS 2: GridLayout
        panelAtas = new JPanel(new GridLayout(2, 1, 5, 5));
        panelAtas.setBackground(Color.WHITE);
        panelAtas.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));

        // 3. ATOMIC COMPONENTS (JLabel)
        labelJudul = new JLabel("DAFTAR TUGAS MAHASISWA", SwingConstants.CENTER);
        labelJudul.setFont(new Font("SansSerif", Font.BOLD, 16));
        panelAtas.add(labelJudul);

        // INTERMEDIATE CONTAINER 2 (JPanel untuk form input)
        // Menggunakan LAYOUT CLASS 3: FlowLayout
        panelInput = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        panelInput.setBackground(Color.WHITE);

        labelInput = new JLabel("Nama Tugas:");
        fieldTugas = new JTextField(18);
        btnTambah = new JButton("Tambah");
        btnHapus = new JButton("Bersihkan");

        // Mendaftarkan ActionListener ke tombol
        btnTambah.addActionListener(this);
        btnHapus.addActionListener(this);

        // 5. EVENT ADAPTER CLASS (KeyAdapter untuk tombol Enter pada JTextField)
        fieldTugas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tambahTugas();
                }
            }
        });

        panelInput.add(labelInput);
        panelInput.add(fieldTugas);
        panelInput.add(btnTambah);
        panelInput.add(btnHapus);
        panelAtas.add(panelInput);

        // Bagian Tengah: JTextArea dibungkus JScrollPane
        areaDaftar = new JTextArea();
        areaDaftar.setEditable(false);
        areaDaftar.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaDaftar.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(areaDaftar);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Tugas Tersimpan"));

        // INTERMEDIATE CONTAINER 3 (JPanel bagian bawah untuk status)
        panelBawah = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBawah.setBackground(Color.WHITE);
        labelStatus = new JLabel("Status: Siap menerima input.");
        panelBawah.add(labelStatus);

        // Menambahkan seluruh panel ke Top-Level Container (JFrame)
        add(panelAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);

        // Mengatur warna latar belakang utama menjadi putih bersih
        getContentPane().setBackground(Color.WHITE);
    }

    private void tambahTugas() {
        String teks = fieldTugas.getText().trim();
        if (!teks.isEmpty()) {
            areaDaftar.append("- " + teks + "\n");
            fieldTugas.setText("");
            labelStatus.setText("Status: Tugas '" + teks + "' berhasil ditambahkan.");
        } else {
            labelStatus.setText("Status: Kolom tugas tidak boleh kosong!");
        }
    }

    // 5. EVENT METHOD OVERRIDE dari Interface ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTambah) {
            tambahTugas();
        } else if (e.getSource() == btnHapus) {
            areaDaftar.setText("");
            labelStatus.setText("Status: Semua daftar tugas dibersihkan.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainGUI().setVisible(true);
        });
    }
}