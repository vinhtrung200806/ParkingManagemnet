package parking.management.system;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingLotManagementGUI extends JFrame {
    private ParkingLot parkingLot;
    private JTextArea displayArea;

    private JTextField tfLicense, tfOwner, tfPhone, tfEntry, tfExit;
    private JComboBox<String> cbType, cbFuel;

    public ParkingLotManagementGUI() {
        parkingLot = new ParkingLot();

        setTitle("Parking Lot Management System");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ================= KHU HIỂN THỊ =================
        displayArea = new JTextArea(12, 70);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // ================= NÚT CHỨC NĂNG =================
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Thêm Xe");
        JButton deleteBtn = new JButton("Xóa Xe");
        JButton showBtn = new JButton("Hiển Thị");
        JButton feeBtn = new JButton("Tính Tổng Phí");
        JButton exportBtn = new JButton("Xuất File");
        JButton searchBtn = new JButton("Tìm Xe");
        JButton countBtn = new JButton("Hiển Thị Số Lượng");

        buttonPanel.add(addBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(showBtn);
        buttonPanel.add(feeBtn);
        buttonPanel.add(exportBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(countBtn);

        // ================= FORM NHẬP =================
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin xe"));

        formPanel.add(new JLabel("Biển số:"));
        tfLicense = new JTextField();
        formPanel.add(tfLicense);

        formPanel.add(new JLabel("Chủ xe:"));
        tfOwner = new JTextField();
        formPanel.add(tfOwner);

        formPanel.add(new JLabel("SDT:"));
        tfPhone = new JTextField();
        formPanel.add(tfPhone);

        formPanel.add(new JLabel("Thời gian vào (dd/MM/yyyy HH:mm):"));
        tfEntry = new JTextField();
        formPanel.add(tfEntry);

        formPanel.add(new JLabel("Thời gian ra (dd/MM/yyyy HH:mm hoặc để trống):"));
        tfExit = new JTextField();
        formPanel.add(tfExit);

        formPanel.add(new JLabel("Loại xe:"));
        cbType = new JComboBox<>(new String[]{"Car", "Bike", "Other"});
        formPanel.add(cbType);

        formPanel.add(new JLabel("Nhiên liệu:"));
        cbFuel = new JComboBox<>(new String[]{"Xe điện", "Xăng", "Dầu"});
        formPanel.add(cbFuel);

        // ================= BỐ CỤC CHÍNH =================
        setLayout(new BorderLayout(10, 10));
        add(scrollPane, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);

        // ================= ACTIONS =================
        addBtn.addActionListener(e -> {
            try {
                String license = tfLicense.getText();
                String owner = tfOwner.getText();
                String phone = tfPhone.getText();
                String type = (String) cbType.getSelectedItem();
                String fuel = (String) cbFuel.getSelectedItem();

                Date entry = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(tfEntry.getText());
                Vehicle v = new Vehicle(license, owner, phone, entry, type, fuel);

                if (!tfExit.getText().trim().isEmpty()) {
                    Date exit = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(tfExit.getText());
                    v.setExitTime(exit);
                }

                parkingLot.addVehicle(v);
                displayArea.append(formatVehicle(v));
                clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu: " + ex.getMessage());
            }
        });

        showBtn.addActionListener(e -> {
            displayArea.setText("");
            if (parkingLot.getAllVehicles().isEmpty()) {
                displayArea.append("Chưa có xe nào trong bãi.\n");
            } else {
                for (Vehicle v : parkingLot.getAllVehicles()) {
                    displayArea.append(formatVehicle(v));
                }
            }
        });

        deleteBtn.addActionListener(e -> {
            String license = JOptionPane.showInputDialog("Nhập biển số cần xóa:");
            boolean removed = parkingLot.removeByLicense(license);
            displayArea.setText(removed ? "Đã xóa xe: " + license + "\n" : "Không tìm thấy xe.\n");
        });

        searchBtn.addActionListener(e -> {
            String license = JOptionPane.showInputDialog("Nhập biển số cần tìm:");
            Vehicle v = parkingLot.searchByLicense(license);
            displayArea.setText(v != null ? formatVehicle(v) : "Không tìm thấy xe.\n");
        });

        exportBtn.addActionListener(e -> {
            try {
                parkingLot.exportToFile("parking_data.txt");
                JOptionPane.showMessageDialog(this, "Xuất file thành công!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi xuất file: " + ex.getMessage());
            }
        });

        feeBtn.addActionListener(e -> {
            long total = parkingLot.calculateTotalFees();
            displayArea.append("Tổng phí bãi xe: " + total + " VND\n");
        });

        countBtn.addActionListener(e -> {
            displayArea.append("Tổng số xe hiện tại: " + parkingLot.getVehicleCount() + "\n");
        });
    }

    private String formatVehicle(Vehicle v) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();

        sb.append("====================================\n");
        sb.append("Biển số: ").append(v.getLicense()).append("\n");
        sb.append("Chủ xe: ").append(v.getOwner()).append("\n");
        sb.append("SDT: ").append(v.getPhone()).append("\n");

        if (v.getEntryTime() != null) sb.append("Thời gian vào: ").append(sdf.format(v.getEntryTime())).append("\n");
        if (v.getExitTime() != null) sb.append("Thời gian ra: ").append(sdf.format(v.getExitTime())).append("\n");
        else sb.append("Thời gian ra: Chưa rời\n");

        sb.append("Loại xe: ").append(v.getType()).append("\n");
        sb.append("Nhiên liệu: ").append(v.getFuel()).append("\n");
        sb.append("Phí: ").append(v.calculateFee()).append(" VND\n\n");

        return sb.toString();
    }

    private void clearForm() {
        tfLicense.setText("");
        tfOwner.setText("");
        tfPhone.setText("");
        tfEntry.setText("");
        tfExit.setText("");
        cbType.setSelectedIndex(0);
        cbFuel.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ParkingLotManagementGUI().setVisible(true));
    }
}
