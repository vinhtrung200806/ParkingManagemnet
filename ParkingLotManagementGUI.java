package parking.management.system;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingLotManagementGUI extends JFrame {
    private ParkingLot parkingLot;
    private JTextField licenseField, ownerField, phoneField, entryField, exitField;
    private JComboBox<String> typeBox, fuelBox;
    private JTextArea displayArea;

    public ParkingLotManagementGUI() {
        parkingLot = new ParkingLot();

        setTitle("Parking Lot Management");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        displayArea = new JTextArea(10, 50);
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.NORTH);

     
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10)); 
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin xe"));

        inputPanel.add(new JLabel("Biển số:"));
        licenseField = new JTextField(20);
        inputPanel.add(licenseField);

        inputPanel.add(new JLabel("Chủ xe:"));
        ownerField = new JTextField(20);
        inputPanel.add(ownerField);

        inputPanel.add(new JLabel("SDT:"));
        phoneField = new JTextField(20);
        inputPanel.add(phoneField);

        inputPanel.add(new JLabel("Thời gian vào (dd/MM/yyyy HH:mm):"));
        entryField = new JTextField(20);
        inputPanel.add(entryField);

        inputPanel.add(new JLabel("Thời gian ra (dd/MM/yyyy HH:mm hoặc để trống):"));
        exitField = new JTextField(20);
        inputPanel.add(exitField);

        inputPanel.add(new JLabel("Loại xe (Car/Bike/Other):"));
        typeBox = new JComboBox<>(new String[]{"Car", "Bike", "Other"});
        inputPanel.add(typeBox);

        inputPanel.add(new JLabel("Nhiên liệu:"));
        fuelBox = new JComboBox<>(new String[]{"Xe điện", "Xăng", "Dầu"});
        inputPanel.add(fuelBox);

        add(inputPanel, BorderLayout.CENTER); 

        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Thêm Xe");
        JButton removeBtn = new JButton("Xóa Xe");
        JButton showBtn = new JButton("Hiển Thị");
        JButton totalBtn = new JButton("Tính Tổng Phí");
        JButton exportBtn = new JButton("Xuất File");
        JButton searchBtn = new JButton("Tìm Xe");
        JButton countBtn = new JButton("Hiển Thị Số Lượng");

        buttonPanel.add(addBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(showBtn);
        buttonPanel.add(totalBtn);
        buttonPanel.add(exportBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(countBtn);

        add(buttonPanel, BorderLayout.SOUTH);

      
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ParkingLotManagementGUI().setVisible(true);
        });
    }
}

