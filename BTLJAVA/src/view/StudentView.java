package view;

import dao.StudentDao;
import entity.Student;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;



public class StudentView extends JFrame implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    private JButton addStudentBtn;
    private JButton editStudentBtn;
    private JButton thongkeBtn;

    private JButton SearchnameStudentBtn;
    private JButton SearchgpaStudentBtn;
    private JButton deleteStudentBtn;
    private JButton clearBtn;
    private JButton sortStudentGPABtn;
    private JButton sortStudentNameBtn;
    private JScrollPane jScrollPaneStudentTable;

    private JTable studentTable;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel diemLabel;
    private JLabel monLabel;
    private JLabel khoiLabel;

    private JTextField idField;
    private JTextField nameField;
    private JTextField diemField;
    private JTextArea monTA = new JTextArea();

    private JTextArea khoiField = new JTextArea();
    private javax.swing.JComboBox<String> jComboBox4;
    private JTextField searchname;
    private JTextField  searchgpa;

    private javax.swing.JComboBox<String> jComboBox1;


    // định nghĩa các cột của bảng student
    private String [] columnNames = new String [] {
            "ID", "Tên", "Phí", "Khen thưởng", "Thông tin"};
    // định nghĩa dữ liệu mặc định của bẳng student là rỗng
    private Object data = new Object [][] {};
    ;

    public StudentView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "","Đoàn viên","Đảng viên","Chưa kết nạp" }));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "","Có", "Không" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        addStudentBtn = new JButton("Thêm");
        SearchnameStudentBtn = new JButton("Tìm tên");
        SearchgpaStudentBtn = new JButton("Tìm chức vụ");
        editStudentBtn = new JButton("Sửa");
        deleteStudentBtn = new JButton("Xóa");
        clearBtn = new JButton("Làm mới");
        sortStudentGPABtn = new JButton("Xếp Phí");
        sortStudentNameBtn = new JButton("Xếp Tên");
        // khởi tạo bảng student
        jScrollPaneStudentTable = new JScrollPane();
        studentTable = new JTable();
        thongkeBtn = new JButton("Thống kê");

        // khởi tạo các label
        idLabel = new JLabel("Id");
        nameLabel = new JLabel("Tên");
        diemLabel = new JLabel("Phí");
        monLabel = new JLabel("Khen thưởng");
        khoiLabel = new JLabel("Thông tin");

        // khởi tạo các trường nhập dữ liệu cho student
        idField = new JTextField(6);
        idField.setEditable(false);
        nameField = new JTextField(15);
        diemField = new JTextField(6);



        searchgpa = new JTextField(6);
        searchname = new JTextField(10);

        // cài đặt các cột và data cho bảng student
        studentTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneStudentTable.setViewportView(studentTable);
        jScrollPaneStudentTable.setPreferredSize(new Dimension (480, 300));

        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Student
        JPanel panel = new JPanel();
        panel.setSize(800, 420);
        panel.setLayout(layout);
        panel.add(jScrollPaneStudentTable);
        panel.add(thongkeBtn);
        panel.add(addStudentBtn);
        panel.add(SearchnameStudentBtn);
        panel.add(SearchgpaStudentBtn);
        panel.add(editStudentBtn);
        panel.add(deleteStudentBtn);
        panel.add(clearBtn);
        panel.add(sortStudentGPABtn);
        panel.add(sortStudentNameBtn);

        panel.add(idLabel);
        panel.add(nameLabel);
        panel.add(diemLabel);
        panel.add(monLabel);
        panel.add(khoiLabel);
        panel.add(jComboBox1);
        panel.add(idField);
        panel.add(nameField);
        panel.add(diemField);
        panel.add(searchname);
        panel.add(searchgpa);
        panel.add(jComboBox4);
        this.setTitle("Chọn môn học");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setVisible(true);

        // cài đặt vị trí các thành phần trên màn hình login
        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, diemLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, diemLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, monLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, monLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, khoiLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, khoiLabel, 200, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, idField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, diemField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, diemField, 70, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jComboBox1, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jComboBox1, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jComboBox4, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jComboBox4, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchname, 110, SpringLayout.WEST, SearchnameStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, searchname, 275, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, SearchnameStudentBtn, 0, SpringLayout.WEST, addStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, SearchnameStudentBtn, 270, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchgpa, 110, SpringLayout.WEST, SearchgpaStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, searchgpa, 305, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, SearchgpaStudentBtn, 0, SpringLayout.WEST, addStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, SearchgpaStudentBtn, 300, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneStudentTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneStudentTable, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addStudentBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addStudentBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editStudentBtn, 60, SpringLayout.WEST, addStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, editStudentBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteStudentBtn, 60, SpringLayout.WEST, editStudentBtn);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 60, SpringLayout.WEST, deleteStudentBtn);

        layout.putConstraint(SpringLayout.NORTH, deleteStudentBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortStudentGPABtn, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortStudentGPABtn, 330, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortStudentNameBtn, 115, SpringLayout.WEST, sortStudentGPABtn);
        layout.putConstraint(SpringLayout.NORTH, sortStudentNameBtn, 330, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, thongkeBtn, 115, SpringLayout.WEST, sortStudentNameBtn);
        layout.putConstraint(SpringLayout.NORTH, thongkeBtn, 330, SpringLayout.NORTH, panel);

        this.add(panel);
        this.pack();
        this.setTitle("Student Information");
        this.setSize(800, 420);
        // disable Edit and Delete buttons
        editStudentBtn.setEnabled(false);
        deleteStudentBtn.setEnabled(false);
        // enable Add button
        addStudentBtn.setEnabled(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * hiển thị list student vào bảng studentTable
     *
     * @param list
     */
    public void showListStudents(List<Student> list) {
        int size = list.size();
        // với bảng studentTable có 5 cột,
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student
        // số cột: là 5
        Object [][] students = new Object[size][5];
        for (int i = 0; i < size; i++) {
            students[i][0] = list.get(i).getId();
            students[i][1] = list.get(i).getName();
            students[i][2] = list.get(i).getDiem();
            students[i][3] = list.get(i).getMon();
            students[i][4] = list.get(i).getKhoi();
        }
        studentTable.setModel(new DefaultTableModel(students, columnNames));
    }

    /**
     * điền thông tin của hàng được chọn từ bảng student
     * vào các trường tương ứng của student.
     */
    public void fillStudentFromSelectedRow() {
        // Lấy chỉ số của hàng được chọn
        int row = studentTable.getSelectedRow();
        if (row >= 0) {
            // Lấy thông tin sinh viên từ bảng
            String id = studentTable.getModel().getValueAt(row, 0).toString();
            String name = studentTable.getModel().getValueAt(row, 1).toString();
            String diem = studentTable.getModel().getValueAt(row, 2).toString();
            String mon = studentTable.getModel().getValueAt(row, 3).toString();
            String khoi = studentTable.getModel().getValueAt(row, 4).toString();

            // Hiển thị thông tin lên các trường nhập liệu
            idField.setText(id);
            nameField.setText(name);
            diemField.setText(diem);
            khoiField.setText(khoi);
            monTA.setText(mon);

            // Chọn môn của sinh viên trong ComboBox
            int index = -1;
            for (int i = 0; i < jComboBox4.getItemCount(); i++) {
                if (jComboBox4.getItemAt(i).equals(khoi)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                jComboBox4.setSelectedIndex(index);
            }
            for (int i = 0; i < jComboBox1.getItemCount(); i++) {
                if (jComboBox1.getItemAt(i).equals(mon)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                jComboBox1.setSelectedIndex(index);
            }
            // Kích hoạt các nút Edit và Delete
            editStudentBtn.setEnabled(true);
            deleteStudentBtn.setEnabled(true);

            // Vô hiệu hóa nút Add
            addStudentBtn.setEnabled(false);
        }
    }


    /**
     * xóa thông tin student
     */
    public void clearStudentInfo() {
        idField.setText("");
        nameField.setText("");
        diemField.setText("");
        jComboBox1.setSelectedItem(null);
        monTA.setText("");
        jComboBox4.setSelectedItem(null);
        khoiField.setText("");
        searchname.setText("");
        searchgpa.setText("");
        // disable Edit and Delete buttons
        editStudentBtn.setEnabled(false);
        deleteStudentBtn.setEnabled(false);
        // enable Add button
        addStudentBtn.setEnabled(true);

    }



    /**
     * hiện thị thông tin student
     *
     * @param student
     */
    public void showStudent(Student student) {
        idField.setText("" + student.getId());
        nameField.setText(student.getName());
        diemField.setText("" + student.getDiem());
        String selectedMon1 = jComboBox1.getSelectedItem().toString();
        String allSelectedMons = selectedMon1 ;
        monTA.setText(allSelectedMons);


        String selectedkhoi4 = jComboBox4.getSelectedItem().toString();
        khoiField.setText(selectedkhoi4);
        // enable Edit and Delete buttons
        editStudentBtn.setEnabled(true);
        deleteStudentBtn.setEnabled(true);
        // disable Add button
        addStudentBtn.setEnabled(false);
        addStudentBtn.setEnabled(false);
    }

    /**
     * lấy thông tin student
     *
     * @return
     */
    public Student getStudentInfo() {
        // validate student
        if (!validateName() ||  !validateMon() || !validateKhoi()) {
            return null;
        }
        try {
            Student student = new Student();
            if (idField.getText() != null && !"".equals(idField.getText())) {
                student.setId(Integer.parseInt(idField.getText()));
            }
            student.setName(nameField.getText().trim());
            student.setDiem(Float.parseFloat(diemField.getText().trim()));
            String selectedMon1 = jComboBox1.getSelectedItem().toString().trim();
            String mon = selectedMon1 ;
            student.setMon(mon);
            String selectedkhoi4 = jComboBox4.getSelectedItem().toString().trim();
            student.setKhoi(selectedkhoi4);
            return student;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }


    private boolean validateName() {
        String name = nameField.getText();
        if (name == null || "".equals(name.trim())) {
            nameField.requestFocus();
            showMessage("Tên không được trống.");
            return false;
        }
        return true;
    }

    private boolean validateMon() {
        String mon1 = jComboBox1.getSelectedItem().toString();

        if (mon1 == null || mon1.trim().equals("") ) {
            showMessage("Vui lòng chọn đầy đủ thông tin môn học.");
            return false;
        }
        return true;
    }





    private boolean validateKhoi() {
        String khoi4 = jComboBox4.getSelectedItem().toString();
        if ( khoi4 == null || khoi4.trim().equals("")) {
            showMessage("Vui lòng chọn đầy đủ thông tin học.");
            return false;
        }
        return true;
    }
    public void addthongkeBtn(ActionListener listener){
        thongkeBtn.addActionListener(listener);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void valueChanged(ListSelectionEvent e) {
    }

    public void addAddStudentListener(ActionListener listener) {
        addStudentBtn.addActionListener(listener);
    }
    public void addSearchnameStudentListener(ActionListener listener){
        SearchnameStudentBtn.addActionListener(listener);
    }
    public void addSearchgpaStudentListener(ActionListener listener){
        SearchgpaStudentBtn.addActionListener(listener);
    }
    public void addEdiStudentListener(ActionListener listener) {
        editStudentBtn.addActionListener(listener);
    }

    public void addDeleteStudentListener(ActionListener listener) {
        deleteStudentBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addSortStudentGPAListener(ActionListener listener) {
        sortStudentGPABtn.addActionListener(listener);
    }

    public void addSortStudentNameListener(ActionListener listener) {
        sortStudentNameBtn.addActionListener(listener);
    }


    public void addListStudentSelectionListener(ListSelectionListener listener) {
        studentTable.getSelectionModel().addListSelectionListener(listener);
    }

    public String getSearchnameValue() {
        return searchname.getText();
    }


    public String getSearchKhoiValue() {
        String khoi = searchgpa.getText();  // Assuming searchgpa is the component for khoi input
        return khoi;
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

}

