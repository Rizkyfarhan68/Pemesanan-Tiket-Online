import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketBookingApp extends JFrame {
    private JComboBox<Route> routeComboBox;
    private JComboBox<String> ticketTypeComboBox;
    private JSpinner quantitySpinner; // Spinner untuk memilih jumlah tiket
    private JButton bookButton;
    private JLabel totalLabel;

    private Map<String, Integer> ticketPrices;
    private List<String> orders; // Menyimpan semua pesanan

    public TicketBookingApp() {
        ticketPrices = new HashMap<>();
        ticketPrices.put("Ekonomi", 50000);
        ticketPrices.put("Bisnis", 100000);
        ticketPrices.put("Eksekutif", 150000);
        ticketPrices.put("Luxury", 250000);

        Route[] routes = {
                new Route("Jakarta", "Bandung", 100000),
                new Route("Jakarta", "Semarang", 150000),
                new Route("Jakarta", "Bumiayu", 200000),
                new Route("Jakarta", "Yogyakarta", 250000),
                new Route("Jakarta", "Surabaya", 300000)
        };

        orders = new ArrayList<>(); // Inisialisasi daftar pesanan

        setTitle("Ticket Booking System");
        setSize(400, 300); // Mengurangi ukuran jendela
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Welcome to the Ticket Booking System", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBackground(new Color(255, 140, 0)); // Warna oranye
        headerLabel.setOpaque(true);
        add(headerLabel, BorderLayout.NORTH);

        // Panel utama
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2, 10, 10)); // Mengurangi jumlah baris
        mainPanel.setBackground(Color.ORANGE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Komponen
        routeComboBox = new JComboBox<>(routes);
        ticketTypeComboBox = new JComboBox<>(ticketPrices.keySet().toArray(new String[0]));
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1)); // Spinner untuk jumlah tiket
        bookButton = new JButton("Book Ticket");
        totalLabel = new JLabel("Total: ");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Menambahkan tooltips
        routeComboBox.setToolTipText("Pilih rute perjalanan");
        ticketTypeComboBox.setToolTipText("Pilih jenis tiket");
        quantitySpinner.setToolTipText("Pilih jumlah tiket");
        bookButton.setToolTipText("Klik untuk memesan tiket");

        // Menambahkan ActionListener untuk tombol
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Route selectedRoute = (Route) routeComboBox.getSelectedItem();
                String selectedTicketType = (String) ticketTypeComboBox.getSelectedItem();
                int ticketPrice = ticketPrices.get(selectedTicketType);
                int quantity = (Integer) quantitySpinner.getValue(); // Mengambil jumlah tiket
                
                Ticket ticket = new Ticket(selectedTicketType, ticketPrice);
                TicketBooking booking = new TicketBooking(selectedRoute, ticket);
                
                int total = booking.calculateTotal() * quantity; // Menghitung total berdasarkan jumlah
                
                // Menyimpan deskripsi pemesanan
                String orderSummary = String.format("Jurusan: %s to %s\nJenis Tiket: %s\nJumlah: %d\nTotal Harga: %d\n\n",
                        selectedRoute.getDeparture(), selectedRoute.getArrival(), selectedTicketType, quantity, total);
                orders.add(orderSummary); // Menambahkan pesanan ke daftar

                // Menampilkan informasi lengkap
                totalLabel.setText(String.format("<html><body>Total: %d<br/>Jenis Tiket: %s<br/>Jurusan: %s to %s<br/>Jumlah: %d</body></html>",
                        total, selectedTicketType, selectedRoute.getDeparture(), selectedRoute.getArrival(), quantity));
                
                // Menampilkan semua pesanan di console (opsional)
                System.out.println(orderSummary);
            }
        });

        // Menambahkan komponen ke panel
        mainPanel.add(new JLabel("Select Route :"));
        mainPanel.add(routeComboBox);
        mainPanel.add(new JLabel("Select Ticket Type:"));
        mainPanel.add(ticketTypeComboBox);
        mainPanel.add(new JLabel("Quantity:")); // Label untuk jumlah tiket
        mainPanel.add(quantitySpinner); // Menambahkan spinner untuk jumlah tiket
        mainPanel.add(bookButton);
        mainPanel.add(totalLabel);

        // Menambahkan panel utama ke frame
        add(mainPanel, BorderLayout.CENTER);

        // Menambahkan border dengan rounded corners
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Menambahkan efek hover pada tombol
        bookButton.setBackground(Color.WHITE);
        bookButton.setForeground(Color.ORANGE);
        bookButton.setFocusPainted(false);
        bookButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        bookButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookButton.setBackground(Color.ORANGE);
                bookButton.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookButton.setBackground(Color.WHITE);
                bookButton.setForeground(Color.ORANGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicketBookingApp app = new TicketBookingApp();
            app.setVisible(true);
        });
    }
}