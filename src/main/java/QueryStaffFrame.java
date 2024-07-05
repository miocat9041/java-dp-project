import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class QueryStaffFrame extends JFrame{
    //用來取得資料庫連線資源
    private Connection conn;
    //宣告表單元件
    private JTextField tftid;//輸入學號用
    private JButton tbSearch;//搜尋按鈕
    private JButton addButton;//新增資料按鈕
    private JButton updateButton;//更新資料按鈕
    private JButton submitButton;//提交按鈕
    private JTextField tidField,nameField, ageField, tsubjectField, salaryField, mdeptField, typeField;
    private JTable tb01;//顯示成績的表格
    private DefaultTableModel dtm01;//用於管理呈現表格數據
    private JLabel lbl01;
    
    //設定建構子
    public QueryStaffFrame(){
        //1.取得資料庫連線
        this.conn = DatabaseConnection.getConnection();
        //實作 視窗的初始化
        setTitle("員工資料查詢視窗");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //設定輸入區域中的元件排版
        JPanel iJPanel  = new JPanel();
        iJPanel.setBorder(new EmptyBorder(10, 10, 10, 10));//設定出血線(內距)
        iJPanel.add(new JLabel("請輸入教職編號："));
        tbSearch = new JButton("查詢");//初始化按鈕並設定按鈕文字
        tftid = new JTextField(10);//初始化文字輸入框

        tidField = new JTextField(10);
        nameField = new JTextField(10);
        ageField = new JTextField(10);
        tsubjectField = new JTextField(10);
        salaryField = new JTextField(10);
        mdeptField = new JTextField(10);
        typeField = new JTextField(10);
        addButton = new JButton("新增資料");
        updateButton = new JButton("修改資料");
        submitButton = new JButton("提交資料");
       

        iJPanel.add(tftid);//把元件加入面板
        iJPanel.add(tbSearch);
        iJPanel.add(addButton);
        iJPanel.add(updateButton);

        add(iJPanel,BorderLayout.NORTH);
        //新增資料
       
        //表格區
        //設定表格欄位標題
        String[] colTitles = {"流水號", "年齡", "指導科目", "薪水", "管理職", "職稱"};

        dtm01 = new DefaultTableModel(colTitles, 0);//初始化表格模型
        tb01 = new JTable(dtm01);//初始化表格物件

        JScrollPane sp01 = new JScrollPane(tb01);//用卷軸面板收納表格
        sp01.setBorder(new EmptyBorder(10,10,10,10));

        //設定顯示姓名的標籤
        lbl01 = new JLabel("教師姓名");
        //設定一個panel來收納表格跟標籤的容器
        JPanel displayJPanel = new JPanel(new BorderLayout());
        displayJPanel.setBorder(new EmptyBorder(10,10,10,10));

        displayJPanel.add(lbl01,BorderLayout.NORTH);//把標籤擺放在面板上方(有東、南、西、北、中，可選)
        displayJPanel.add(sp01,BorderLayout.CENTER);//把表格擺放在面板上方(有東、南、西、北、中，可選)

        add(displayJPanel,BorderLayout.CENTER);//把表格區面板擺在視窗裡的中間(有東、南、西、北、中，可選)

        //事件綁定
        tbSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent event){
                try{
                    searchStaff();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                openAddStaffDialog();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUpdateStaffDialog();
                } 
            });
    }


private void searchStaff()throws SQLException {
        String tid = tftid.getText();

        String sqlStr = "select * from staff where tid = ?";
        try(PreparedStatement st = conn.prepareStatement(sqlStr)){
            st.setString(1, tid);
            ResultSet rs =st.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");
                lbl01.setText("職員姓名："+name);

            }else{
                JOptionPane.showMessageDialog(this,"查無此職員");
                return; 
            }
        }
        String sqlStr2 = "select * from staff where tid = ?";
        dtm01.setRowCount(0);//每次查詢清空表格
        try(PreparedStatement st  = conn.prepareStatement(sqlStr2)){
            st.setString(1, tid);
            ResultSet rs2 = st.executeQuery();
            boolean flag = false;
            while (rs2.next()) {
                flag = true;
                dtm01.addRow(new Object[]{
                    rs2.getInt("no"),
                    rs2.getInt("age"),
                    rs2.getString("tsubject"),
                    rs2.getInt("salary"),
                    rs2.getString("mdept"),
                    rs2.getString("type")
                });
            if(flag) {
                tb01.setRowHeight(30);
            } else {
                JOptionPane.showMessageDialog(this, "查無此職員資料");
                return;
            }
            }
        }
    }
private void addStaff()throws SQLException{
        // 檢查所有必填字段是否已填寫
        if (tidField.getText().isEmpty() ||nameField.getText().isEmpty() || ageField.getText().isEmpty() || tsubjectField.getText().isEmpty() ||
            salaryField.getText().isEmpty() || mdeptField.getText().isEmpty() || typeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "請填寫完所有表格");
            return;
        }
        // 檢查數字字段是否為有效數字
        int age, salary;
        try {
                age = Integer.parseInt(ageField.getText());
                salary = Integer.parseInt(salaryField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "請輸入正確數值.");
                return;
            }
        String sqlStr ="insert into staff( name, tid, age, tsubject, salary, mdept, type) values (?,?,?,?,?,?,?);";
        try(PreparedStatement st = conn.prepareStatement(sqlStr)){
            st.setString(1, nameField.getText());
            st.setString(2, tidField.getText());
            st.setInt(3,Integer.parseInt( ageField.getText()));
            st.setString(4, tsubjectField.getText());
            st.setInt(5, Integer.parseInt(salaryField.getText()));
            st.setString(6, mdeptField.getText());
            st.setString(7, typeField.getText());

            int addnew =st.executeUpdate();
            if(addnew>0){
                JOptionPane.showMessageDialog(this, "職員新增成功"); 
            }
        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "新增失敗，請重新嘗試");
            return;
        }

    }
private void openAddStaffDialog() {
        JDialog dialog = new JDialog(this, "新增職員", true);
        dialog.setSize(400, 350);
        JPanel DLpanel = new JPanel(new GridLayout(8, 2, 2, 2));
        DLpanel.setBorder(new EmptyBorder(15,15,15,15));
        
        DLpanel.add(new JLabel("職員編號:"));
        DLpanel.add(tidField);
        DLpanel.add(new JLabel("姓名:"));
        DLpanel.add(nameField);
        DLpanel.add(new JLabel("年齡:"));
        DLpanel.add(ageField);
        DLpanel.add(new JLabel("指導科目:"));
        DLpanel.add(tsubjectField);
        DLpanel.add(new JLabel("薪水:"));
        DLpanel.add(salaryField);
        DLpanel.add(new JLabel("管理部門:"));
        DLpanel.add(mdeptField);
        DLpanel.add(new JLabel("職稱:"));
        DLpanel.add(typeField);
        DLpanel.add(new JLabel(""));
        DLpanel.add(submitButton);

        dialog.add(DLpanel,BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addStaff();
                } catch (SQLException e1) {
                    
                    e1.printStackTrace();
                }
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
}
private void updateStaff()throws SQLException{
    // 檢查所有必填字段是否已填寫
    if (tidField.getText().isEmpty() ||nameField.getText().isEmpty() || ageField.getText().isEmpty() || tsubjectField.getText().isEmpty() ||
        salaryField.getText().isEmpty() || mdeptField.getText().isEmpty() || typeField.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "請填寫完所有表格");
        return;
    }
    // 檢查數字字段是否為有效數字
    int age, salary;
    try {
            age = Integer.parseInt(ageField.getText());
            salary = Integer.parseInt(salaryField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "請輸入正確數值.");
            return;
        }
    String sqlStr ="UPDATE staff SET name =?, age=?, tsubject=?, salary=?, mdept=?, type=? where tid=?;";
    try(PreparedStatement st = conn.prepareStatement(sqlStr)){
        st.setString(1, nameField.getText());
        st.setInt(2,Integer.parseInt( ageField.getText()));
        st.setString(3, tsubjectField.getText());
        st.setInt(4, Integer.parseInt(salaryField.getText()));
        st.setString(5, mdeptField.getText());
        st.setString(6, typeField.getText());
        st.setString(7, tidField.getText());

        int updateStaff = st.executeUpdate();
        if (updateStaff > 0) {
            JOptionPane.showMessageDialog(this, "職員更新成功");
        } else {
            JOptionPane.showMessageDialog(this, "更新失敗，無匹配的職員ID");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "更新失敗，請重新嘗試");
    }
}
private void openUpdateStaffDialog() {
    JDialog Updialog = new JDialog(this, "修改職員資料", true);
    Updialog.setSize(400, 350);
    JPanel UPDLpanel = new JPanel(new GridLayout(8, 2, 2, 2));
    UPDLpanel.setBorder(new EmptyBorder(15,15,15,15));
    
    UPDLpanel.add(new JLabel("輸入欲更新的員工的職員編號:"));
    UPDLpanel.add(tidField);
    UPDLpanel.add(new JLabel("輸入欲更新姓名:"));
    UPDLpanel.add(nameField);
    UPDLpanel.add(new JLabel("輸入欲更新年齡:"));
    UPDLpanel.add(ageField);
    UPDLpanel.add(new JLabel("輸入欲更新指導科目:"));
    UPDLpanel.add(tsubjectField);
    UPDLpanel.add(new JLabel("輸入欲更新薪水:"));
    UPDLpanel.add(salaryField);
    UPDLpanel.add(new JLabel("輸入欲更新管理部門:"));
    UPDLpanel.add(mdeptField);
    UPDLpanel.add(new JLabel("輸入欲更新職稱:"));
    UPDLpanel.add(typeField);
    UPDLpanel.add(new JLabel(""));
    UPDLpanel.add(submitButton);

    Updialog.add(UPDLpanel,BorderLayout.CENTER);

    submitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                updateStaff();
            } catch (SQLException e2) {
                
                e2.printStackTrace();
            }
            Updialog.dispose();
        }
    });

    Updialog.setVisible(true);
}
}

