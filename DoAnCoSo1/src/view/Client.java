package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import model.ModelClothes;
import model.ModelHandBag;
import model.ModelInvoice;
import model.ModelShoe;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.CardLayout;
import java.io.*;
import java.net.*;
import java.util.List;

public class Client extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
	
	 private volatile boolean keepRunning = true; 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Client frame = new Client();
                    frame.setVisible(true);
                    frame.startClientInBackground();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Client() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1281, 664);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel_top = new JPanel();
        panel_top.setBackground(new Color(175, 238, 238));
        panel_top.setBounds(0, 0, 1267, 71);
        contentPane.add(panel_top);
        panel_top.setLayout(null);

        JLabel lblNewLabel = new JLabel("THÔNG TIN SẢN PHẨM");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        lblNewLabel.setBounds(34, 10, 241, 51);
        panel_top.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0, 255, 255));
        panel_1.setBounds(0, 70, 281, 557);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JButton btnHome = new JButton("HOME");
        btnHome.setBorder(new LineBorder(Color.white, 2)); // trắng, độ rộng 2 pixel
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHome.setForeground(Color.white); // Đổi màu chữ khi di chuột qua
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHome.setForeground(Color.BLACK); // Khôi phục màu chữ khi di chuột ra khỏi nhãn
            }
        });
        btnHome.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnHome.setBackground(Color.CYAN);
        btnHome.setBounds(24, 32, 211, 41);
        panel_1.add(btnHome);

        JButton btnNewButton = new JButton("CLOTHES");
        btnNewButton.setBackground(new Color(0, 255, 255));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton.setBorder(new LineBorder(Color.white, 2)); // trắng, độ rộng 2 pixel
        btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButton.setForeground(Color.white); // Đổi màu chữ khi di chuột qua
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButton.setForeground(Color.BLACK); // Khôi phục màu chữ khi di chuột ra khỏi nhãn
            }
        });
        btnNewButton.setBounds(24, 97, 211, 41);
        panel_1.add(btnNewButton);

        JButton btnSearch = new JButton("SEARCH");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSearch.setForeground(Color.white); // Đổi màu chữ khi di chuột qua
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSearch.setForeground(Color.BLACK); // Khôi phục màu chữ khi di chuột ra khỏi nhãn
            }
        });
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnSearch.setBorder(new LineBorder(Color.white, 2)); // trắng, độ rộng 2 pixel
        btnSearch.setBackground(Color.CYAN);
        btnSearch.setBounds(24, 301, 211, 41);
        panel_1.add(btnSearch);

        JButton btnHandBag = new JButton("HAND BAG");
        btnHandBag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHandBag.setForeground(Color.white); // Đổi màu chữ khi di chuột qua
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHandBag.setForeground(Color.BLACK); // Khôi phục màu chữ khi di chuột ra khỏi nhãn
            }
        });
        btnHandBag.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnHandBag.setBackground(Color.CYAN);
        btnHandBag.setBounds(24, 164, 211, 41);
        btnHandBag.setBorder(new LineBorder(Color.white, 2)); // trắng, độ rộng 2 pixel
        panel_1.add(btnHandBag);

        JButton btnShoe = new JButton("SHOE");
        btnShoe.setBorder(new LineBorder(Color.white, 2)); // trắng, độ rộng 2 pixel
        btnShoe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnShoe.setForeground(Color.white); // Đổi màu chữ khi di chuột qua
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnShoe.setForeground(Color.BLACK); // Khôi phục màu chữ khi di chuột ra khỏi nhãn
            }
        });
        btnShoe.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnShoe.setBackground(Color.CYAN);
        btnShoe.setBounds(24, 233, 211, 41);
        panel_1.add(btnShoe);

        // cardlayout
        JPanel panel_cardclient = new JPanel();
        panel_cardclient.setLayout(new CardLayout());
        panel_cardclient.setBounds(281, 70, 1002, 557);

        Home home = new Home(); // Tạo instance của Home
        panel_cardclient.add(home.getContentPane(), "Home"); // Thêm Home vào cardPanel với tên "Home"

        Clothes clothes = new Clothes();
        panel_cardclient.add(clothes.getContentPane(), "Clothes");
        HandBag handbag = new HandBag(); // Tạo instance của Clothes
        panel_cardclient.add(handbag.getContentPane(), "HandBag"); // Thêm Clothes vào cardPanel với tên "Clothes"

        Shoe shoe = new Shoe(); // Tạo instance của Shoe
        panel_cardclient.add(shoe.getContentPane(), "Shoe"); // Thêm Shoe vào cardPanel với tên "Shoe"

        Search search = new Search(	); // Tạo instance của Search
        panel_cardclient.add(search.getContentPane(), "Search"); // Thêm Search vào cardPanel với tên "Search"

        // các nút chuyển cardlayout
        btnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                CardLayout cl = (CardLayout) panel_cardclient.getLayout();
                cl.show(panel_cardclient, "Home"); // Chuyển đến Home khi nhấn button "Home chủ"
            }
        });
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                CardLayout cl = (CardLayout) panel_cardclient.getLayout();
                cl.show(panel_cardclient, "Clothes");
            }
        });
        btnHandBag.addActionListener(new ActionListener() {
            @Override	
            public void actionPerformed(ActionEvent arg0) {
                CardLayout cl = (CardLayout) panel_cardclient.getLayout();
                cl.show(panel_cardclient, "HandBag");
            }
        });
        btnShoe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                CardLayout cl = (CardLayout) panel_cardclient.getLayout();
                cl.show(panel_cardclient, "Shoe");
            }
        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                CardLayout cl = (CardLayout) panel_cardclient.getLayout();
                cl.show(panel_cardclient, "Search");
            }
        });
        contentPane.add(panel_cardclient);

        // Khởi tạo kết nối đến server
        initializeConnection();
        startUpdateThread();
    }

    private void startUpdateThread() {
        // Định nghĩa một luồng mới để gửi yêu cầu cập nhật đến server định kỳ
        Thread updateThread = new Thread(() -> {
            while (keepRunning) {
                try {
                    // Gửi yêu cầu cập nhật đến server
                    sendUpdateRequestToServer();
                    // Dừng luồng trong một khoảng thời gian cố định trước khi gửi yêu cầu tiếp theo
                    Thread.sleep(20000); // 60,000 milliseconds = 1 phút
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        

        // Khởi động luồng để gửi yêu cầu cập nhật đến server định kỳ
        updateThread.start();
    }
    private void sendUpdateRequestToServer() {
        try {
            if (socket == null || socket.isClosed()) {
                initializeConnection(); // Khởi tạo lại kết nối nếu kết nối đã đóng
            }
            
            // Gửi một yêu cầu cập nhật đến server, chẳng hạn bằng cách gửi một đối tượng đặc biệt
            out.writeObject("UPDATE_REQUEST");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error sending update request to server: " + e.getMessage());
        }
    }
    private void initializeConnection() {
        try {
            socket = new Socket("localhost", 1234);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Unable to connect to server. Please try again later.", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startClientInBackground() {
        new Thread(this::receiveProductDataFromServer).start();
    }

    private void receiveProductDataFromServer() {
        try {
            while (keepRunning) {
                // Đọc dữ liệu từ server
                try {
                    List<ModelClothes> clothesList = (List<ModelClothes>) in.readObject();
                    List<ModelShoe> shoeList = (List<ModelShoe>) in.readObject();
                    List<ModelHandBag> handbagList = (List<ModelHandBag>) in.readObject();

                    // Cập nhật dữ liệu lên giao diện
                    updateUI(clothesList, shoeList, handbagList);
                } catch (SocketException e) {
                    System.out.println("Server has been shut down.");
                    handleServerShutdown();
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            handleServerShutdown(); // Xử lý khi có lỗi xảy ra
        }
    }

    private void updateUI(List<ModelClothes> clothesList, List<ModelShoe> shoeList, List<ModelHandBag> handbagList) {
        // Cập nhật dữ liệu lên các bảng hoặc thành phần UI khác
        Clothes.updateTable(clothesList);
        Shoe.updateTable(shoeList);
        HandBag.updateTable(handbagList);
        Search.updateTablecl(clothesList);
        Search.updateTablesh(shoeList);
        Search.updateTablehb(handbagList);
    }

    private void handleServerShutdown() {
        // Xóa dữ liệu trên client
        Clothes.clearTablec();
        Shoe.clearTables();
        HandBag.clearTableh();
        Search.clearTableclo();
        Search.clearTableshoe();
        Search.clearTablehbag();

        // Hiển thị thông báo cho người dùng
        JOptionPane.showMessageDialog(this, "The server has been shut down. Data has been cleared.", "Server Shutdown", JOptionPane.WARNING_MESSAGE);

        // Đóng kết nối
        try {
        	keepRunning = false;
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDataToServer(ModelInvoice dataclo) {
        try {
            if (socket == null || socket.isClosed()) {
                initializeConnection(); // Khởi tạo lại kết nối nếu kết nối đã đóng
            }
            // Ghi đối tượng ModelInvoice vào luồng đầu ra
            out.writeObject(dataclo);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error sending data to server: " + e.getMessage());
        }
    }
}
