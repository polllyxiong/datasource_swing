/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youngfriend.main_pnl;

import com.google.gson.JsonObject;
import org.dom4j.Element;
import youngfriend.bean.BeanDto;
import youngfriend.service.ServiceInvoker;
import youngfriend.utils.ModuleType;
import youngfriend.utils.PubUtil;
import youngfriend.utils.ServiceType;
import youngfriend.utils.fileupload.DocDelegate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static youngfriend.main_pnl.MainPnlFactory.TABLE_NAME;

/**
 * @author xiong
 */
public class MainHeaderPnl extends javax.swing.JPanel {


    /**
     * Creates new form MainHeaderPnl
     */
    public MainHeaderPnl() {
        initComponents();
        docDelegate = new DocDelegate(upLoadDocBtn, downLoadDocBtn);
    }

    /**
     * This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabel3 = new javax.swing.JLabel();
                moduleTypeLb = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                moduleAliasTf = new javax.swing.JTextField();
                jLabel4 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                descTa = new javax.swing.JTextArea();
                upLoadDocBtn = new javax.swing.JButton();
                downLoadDocBtn = new javax.swing.JButton();
                jLabel2 = new javax.swing.JLabel();
                serviceNameLb = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                datasourceLb = new javax.swing.JLabel();
                datasourceReadAbleCb = new javax.swing.JCheckBox();

                setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                jLabel3.setText("组件类型:");

                moduleTypeLb.setText("类型值");

                jLabel5.setText("组件别名:");

                jLabel4.setText("组件描述:");

                descTa.setColumns(20);
                descTa.setRows(5);
                jScrollPane1.setViewportView(descTa);

                upLoadDocBtn.setText("上传组件文档");

                downLoadDocBtn.setText("下载组件文档");

                jLabel2.setText("服务名:");

                serviceNameLb.setText("服务名值");

                jLabel7.setText("数据源:");

                datasourceLb.setText("数据源值");

                datasourceReadAbleCb.setText("数据源只读");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel7))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(datasourceLb, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(serviceNameLb, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel5))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(moduleTypeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(moduleAliasTf, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(27, 27, 27)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(upLoadDocBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(datasourceReadAbleCb)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(downLoadDocBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                                .addGap(24, 24, 24))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(28, 28, 28)
                                                                .addComponent(downLoadDocBtn))
                                                        .addComponent(upLoadDocBtn)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                                        .addComponent(jLabel2)
                                                        .addComponent(serviceNameLb)
                                                        .addComponent(jLabel3)
                                                        .addComponent(moduleTypeLb))
                                                .addGap(12, 12, 12)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                                        .addComponent(jLabel7)
                                                        .addComponent(datasourceLb)
                                                        .addComponent(jLabel5)
                                                        .addComponent(moduleAliasTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(datasourceReadAbleCb)))
                                .addContainerGap(11, Short.MAX_VALUE))
                );
        }// </editor-fold>//GEN-END:initComponents


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel datasourceLb;
        private javax.swing.JCheckBox datasourceReadAbleCb;
        private javax.swing.JTextArea descTa;
        private javax.swing.JButton downLoadDocBtn;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTextField moduleAliasTf;
        private javax.swing.JLabel moduleTypeLb;
        private javax.swing.JLabel serviceNameLb;
        private javax.swing.JButton upLoadDocBtn;
        // End of variables declaration//GEN-END:variables

    private DocDelegate docDelegate;
    private ServiceType servicetype;
    private BeanDto tableBean;

    public String getTablename() {
        return tablename;
    }

    public BeanDto getTableBean() {
        return tableBean;
    }

    private String tablename;

    public Map<BeanDto, List<Element>> getTablesMap() {
        return tablesMap;
    }

    public ServiceType getServicetype() {
        return servicetype;
    }

    private Map<BeanDto, List<Element>> tablesMap = null;
    //数据源 数据表
    public static String TABLEBEANPRONAME_COMMON = "table_name";
    public static String TABLEBEANPRONAME_OTHER = "value";

    public void load(List<BeanDto> servicebeans, JsonObject jsonData, ModuleType moduleType, BeanDto moduleInfoBean, boolean commonModule, boolean isVersion2) throws Exception {
        clear();
        //load servicename
        String servicename = PubUtil.getProp(jsonData, "servicename");
        BeanDto serviceBean = PubUtil.getDto(servicebeans, "name", servicename);
        if (serviceBean == null) {
            throw new RuntimeException("无此服务:" + servicename);
        }
        List<BeanDto> tableList;
        if (commonModule) {
            String tableXML = null;
            tablesMap = new HashMap<BeanDto, List<Element>>();
            if (isVersion2) {
                tableXML = ServiceInvoker.getTables(servicename, PubUtil.getService2Url());
                if ("codecenter".equals(servicename)) {
                    servicetype = ServiceType.SERVICE2_CODECENTER;
                    tableList = ServiceInvoker.parseTable(servicename, tableXML, null);
                } else {
                    servicetype = ServiceType.SERVICE2;
                    tableList = ServiceInvoker.parseTable(servicename, tableXML, tablesMap);
                }
            } else {
                servicetype = ServiceType.SERVICE3;
                tableXML = ServiceInvoker.getTables(serviceBean.getValue("name"));
                tableList = ServiceInvoker.parseTable(servicename, tableXML, tablesMap);
            }
            //通用的 设置 表格
        } else {
            tableList = ServiceInvoker.getDataSource(servicename, !isVersion2, moduleType);
        }

        //load tableBean
        tablename = PubUtil.getProp(jsonData, TABLE_NAME);
        BeanDto tableBeanTemp = PubUtil.getDto(tableList, commonModule ? TABLEBEANPRONAME_COMMON : TABLEBEANPRONAME_OTHER, tablename);
        if (tableBeanTemp == null) {
            throw new IllegalArgumentException("没找到已设置表格:" + tablename);
        }
        tableBean = tableBeanTemp;
        datasourceLb.setText(tableBean.toString());
        descTa.setText(moduleInfoBean.getValue("description"));
        moduleAliasTf.setText(moduleInfoBean.getValue("modulealias"));
        moduleTypeLb.setText(moduleType.getName());
        serviceNameLb.setText(serviceBean == null ? "" : serviceBean.toString());
        docDelegate.load(jsonData);
        datasourceReadAbleCb.setSelected("true".equals(PubUtil.getProp(jsonData, "readOnly")));
    }


    public String getModuleAlias() {
        return moduleAliasTf.getText();
    }

    public String getDesc() {
        return descTa.getText();
    }

    public void save(JsonObject jsonData) throws IOException {
        docDelegate.save(jsonData);
        jsonData.addProperty("readOnly", datasourceReadAbleCb.isSelected() ? "true" : "false");
    }


    private void clear() {
        moduleAliasTf.setText("");
        descTa.setText("");
    }

}
