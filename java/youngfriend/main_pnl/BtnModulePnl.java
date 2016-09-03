/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youngfriend.main_pnl;

import com.google.gson.JsonObject;
import youngfriend.main_pnl.deleagte.InparamTableDelegateButtonAbs;

import java.util.Map;

/**
 * @author xiong
 */
public class BtnModulePnl extends AbstractMainPnl {

    public void clear() {
        super.clear();
        inparamTableDeletage.clear();
        outParamTableDeletate.clear();
    }

    @Override
    void saveInparamLevel2Custom(JsonObject inparamLevel1) {
    }

    @Override
    public void saveParam(String modulelabel, JsonObject jsonData) {
        super.saveParam(modulelabel, jsonData);
        inparamTableDeletage.save(jsonData);

    }

    @Override
    public void loadData(JsonObject jsonData) throws Exception {
        try {
            init = true;
            JsonObject inparamLevel1 = commomLoadData(jsonData, readOnlyCb);
            Map<String, JsonObject> inParamFieldMap = getInParamFieldMap(inparamLevel1);
            inparamTableDeletage.loadInTableDatas(jsonData, inParamFieldMap);
        } finally {
            init = false;
        }
    }


    /**
     * Creates new form CommonPnl
     */
    public BtnModulePnl() {
        initComponents();
        commonModule = false;
        inparamTableDeletage = new InparamTableDelegateButtonAbs();
        afterUi(table_combo, outParams_table, outParamsAdd_btn, outParamsDel_btn, readOnlyCb);
    }


    /**
     * This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        table_combo = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        readOnlyCb = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        fieldtable = new javax.swing.JTable();
        outParams_pnl = new javax.swing.JPanel();
        outParams_spnl = new javax.swing.JScrollPane();
        outParams_table = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        outParamsAdd_btn = new javax.swing.JButton();
        outParamsDel_btn = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jLabel8.setText("数据源");

        readOnlyCb.setText("是否只读");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(table_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(readOnlyCb)
                                .addContainerGap(476, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(table_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8)
                                        .addComponent(readOnlyCb))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        fieldtable.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        fieldtable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "字段", "显示名称", "作为入口参数", "入口参数默认值"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        fieldtable.setRowHeight(25);
        fieldtable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(fieldtable);
        if (fieldtable.getColumnModel().getColumnCount() > 0) {
            fieldtable.getColumnModel().getColumn(2).setMinWidth(100);
            fieldtable.getColumnModel().getColumn(2).setPreferredWidth(100);
            fieldtable.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 993, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(jScrollPane2)
                                        .addGap(0, 0, 0)))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 373, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                                        .addGap(0, 0, 0)))
        );

        add(jPanel3, java.awt.BorderLayout.CENTER);

        outParams_pnl.setBorder(javax.swing.BorderFactory.createTitledBorder("出口参数设置"));
        outParams_pnl.setPreferredSize(new java.awt.Dimension(177, 200));
        outParams_pnl.setLayout(new java.awt.BorderLayout());

        outParams_spnl.setPreferredSize(new java.awt.Dimension(100, 50));

        outParams_table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "字段名", "显示名称"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        outParams_table.setRowHeight(25);
        outParams_spnl.setViewportView(outParams_table);

        outParams_pnl.add(outParams_spnl, java.awt.BorderLayout.CENTER);

        outParamsAdd_btn.setText("增加");
        jPanel5.add(outParamsAdd_btn);

        outParamsDel_btn.setText("删除");
        jPanel5.add(outParamsDel_btn);

        outParams_pnl.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        add(outParams_pnl, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable fieldtable;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton outParamsAdd_btn;
    private javax.swing.JButton outParamsDel_btn;
    private javax.swing.JPanel outParams_pnl;
    private javax.swing.JScrollPane outParams_spnl;
    private javax.swing.JTable outParams_table;
    private javax.swing.JCheckBox readOnlyCb;
    private javax.swing.JComboBox table_combo;
    // End of variables declaration//GEN-END:variables
}
