/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youngfriend.moduletree;

import com.google.common.base.Strings;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youngfriend.bean.BeanDto;
import youngfriend.common.util.StringUtils;
import youngfriend.common.util.net.exception.ServiceInvokerException;
import youngfriend.main_pnl.MainPnlFactory;
import youngfriend.moduletree.menus.ModuleTreePopup;
import youngfriend.service.CatalogServiceUtil;
import youngfriend.service.ModuleServiceUtil;
import youngfriend.service.ServiceInvoker;
import youngfriend.utils.Do4objs;
import youngfriend.utils.ModuleType;
import youngfriend.utils.PubUtil;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static youngfriend.App.busyDoing;
import static youngfriend.utils.PubUtil.FALSESTR;
import static youngfriend.utils.PubUtil.TRUESTR;

/**
 * @author xiong
 */
public class ModuleTreePnl extends javax.swing.JPanel {


    /**
     * Creates new form ModuleTreePnl
     */
    public ModuleTreePnl() {
        initComponents();
        after();
    }

    /**
     * This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        projectCombo = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        moduleTree = new javax.swing.JTree();
        jPanel3 = new javax.swing.JPanel();
        moduleSearchTf = new javax.swing.JTextField();
        moduleSearchBtn = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        add(projectCombo, java.awt.BorderLayout.NORTH);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new java.awt.BorderLayout());

        moduleTree.setModel(moduleTreeModel);
        moduleTree.setScrollsOnExpand(true);
        jScrollPane3.setViewportView(moduleTree);

        jPanel1.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel3.setPreferredSize(new java.awt.Dimension(298, 28));

        moduleSearchBtn.setText("定位");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(239, Short.MAX_VALUE)
                                .addComponent(moduleSearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(moduleSearchTf)
                                        .addGap(56, 56, 56)))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(moduleSearchBtn))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(moduleSearchTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 1, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.NORTH);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton moduleSearchBtn;
    private javax.swing.JTextField moduleSearchTf;
    private javax.swing.JTree moduleTree;
    private javax.swing.JComboBox projectCombo;
    // End of variables declaration//GEN-END:variables


    private static final Logger logger = LoggerFactory.getLogger(ModuleTreePnl.class);
    public static final String[] CATALOG_TOSTRING = {"name", "code"};
    public static final String[] MODULE_TOSTRING = {"name", "code", "module_type"};
    public static final String ISMODULE = "ismodule";
    public static final String MODULE_TYPE_PROP = "typee";
    private DefaultMutableTreeNode moduleTreeRoot = new DefaultMutableTreeNode("组件树");
    private DefaultTreeModel moduleTreeModel = new DefaultTreeModel(moduleTreeRoot, true);
    private String beforeSelectNodeId;
    private DefaultMutableTreeNode selectNode;
    private BeanDto projectBean;

    private boolean rebuild = false;
    private BeanDto moduleInfoBean;

    public BeanDto getModuleCatalogBean() {
        return moduleCatalogBean;
    }

    public BeanDto getModuleInfoBean() {
        return moduleInfoBean;
    }

    private BeanDto moduleCatalogBean;

    public void setMainPnlFactory(MainPnlFactory mainPnlFactory) {
        this.mainPnlFactory = mainPnlFactory;
    }

    private MainPnlFactory mainPnlFactory;

    /**
     * typed mean v6version 2 or 3
     *
     * @return
     */
    public boolean isVersion2() {
        if (moduleInfoBean == null) {
            return false;

        }
        return "2".equals(moduleInfoBean.getValue("typed"));
    }

    private void after() {
        moduleTree.setScrollsOnExpand(true);
        moduleTree.setRootVisible(false);
        moduleTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        addEvents();
        loadProjectData();
        new ModuleTreePopup(this);
        new SearchNodeDelegate(moduleTree, moduleTreeRoot, moduleSearchTf, moduleSearchBtn);
        new KeyEventDelegate(this);
    }

    public String getProjectId() {
        return projectBean == null ? null : projectBean.getValue("id");
    }

    private void loadProjectData() {
        String msg = null;
        try {
            msg = ServiceInvoker.designproject_project_get();
        } catch (ServiceInvokerException e) {
            PubUtil.showMsg("获取项目信息失败");
            logger.error(e.getMessage());
        }
        JsonElement json = PubUtil.parseJson(msg);
        if (json != null) {
            JsonArray asJsonArray = json.getAsJsonArray();
            for (JsonElement obj : asJsonArray) {
                BeanDto dto = new BeanDto(obj.getAsJsonObject(), "name");
                if (dto.getValue("id").equals("6e37e3385fab44baa654f386b2b2030f")) {
                    projectCombo.addItem(dto);
                }
            }
        }
    }

    private void addEvents() {

        projectCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                final BeanDto selectBean = (BeanDto) itemEvent.getItem();
                buildTreeByProject(selectBean);
            }
        });


        moduleTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
                if (rebuild) {
                    return;
                }

                selectNode = (DefaultMutableTreeNode) treeSelectionEvent.getPath().getLastPathComponent();
                reLoadData();
            }
        });


    }


    /**
     * @param selectBean projectBeanTemp
     */
    private void buildTreeByProject(final BeanDto selectBean) {
        if (selectBean == null) {
            clearTreeNodes();
            projectBean = null;
        } else {
            busyDoing(new Do4objs() {
                @Override
                public void do4ojbs(Object... objs) {
                    try {
                        String projectId = selectBean.getValue("id");
                        String jsonStr = CatalogServiceUtil.form_catalog_get(projectId);
                        addNodesLeftTree(jsonStr);
                        jsonStr = CatalogServiceUtil.btnmodule_catalog_get(projectId);
                        addNodesLeftTree(jsonStr);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        throw new RuntimeException("建组件树失败");
                    }

                }

            }, new Do4objs() {

                @Override
                public void do4ojbs(Object... ojbs) {
                    moduleTreeModel.reload(moduleTreeRoot);
                    if (moduleTreeRoot.getChildCount() > 0) {
                        moduleTree.expandRow(0);
                    }
                    projectBean = selectBean;
                }
            });
        }
    }

    private void clearTreeNodes() {
        moduleTreeRoot.removeAllChildren();
        moduleTreeModel.nodeStructureChanged(moduleTreeRoot);
    }

    /**
     * add tree node to tree
     *
     * @param jsonStr
     * @throws Exception
     */

    private void addNodesLeftTree(String jsonStr) throws Exception {
        JsonElement jsonElement = PubUtil.parseJson(jsonStr);
        if (jsonElement != null) {
            JsonArray asJsonArray = jsonElement.getAsJsonArray();
            PubUtil.addTreeNode(moduleTreeRoot, asJsonArray, "code", new Do4objs() {
                @Override
                public void do4ojbs(Object... objs) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) objs[0];
                    BeanDto dto = (BeanDto) objs[1];
                    String moduleid = dto.getValue("moduleid");
                    if (Strings.isNullOrEmpty(moduleid) || "#".equals(moduleid)) {
                        node.setAllowsChildren(true);
                        dto.setItem(ISMODULE, FALSESTR);
                    } else {
                        dto.setItem(ISMODULE, TRUESTR);
                        dto.setToString(MODULE_TOSTRING);
                    }
                    if (rebuild && beforeSelectNodeId != null) {
                        if (beforeSelectNodeId.equals(dto.getValue("id"))) {
                            selectNode = node;
                        }
                    }
                }
            }, CATALOG_TOSTRING);
        }
    }


    public boolean isModule() {
        if (moduleCatalogBean == null) {
            return false;
        }
        boolean ismodule = TRUESTR.equals(moduleCatalogBean.getValue(ISMODULE));
        return ismodule;
    }

    public void reBuildTree() {
        rebuild = true;
        if (this.moduleCatalogBean != null) {
            beforeSelectNodeId = moduleCatalogBean.getValue("id");
        }
        clearTreeNodes();
        buildTreeByProject(projectBean);
        if (beforeSelectNodeId != null) {
            TreeUtil.selectNode(moduleTree, selectNode);
            beforeSelectNodeId = null;
        }
        rebuild = false;
    }

    /**
     * change select node text string
     */
    public void selectNodeChange() {
        moduleTreeModel.nodeChanged(selectNode);
    }


    public ModuleType getModuleType() {
        String typee = moduleInfoBean.getValue(MODULE_TYPE_PROP);
        if (StringUtils.nullOrBlank(typee)) {
            //default type is common  which value is empty
            return ModuleType.COMMON;
        }
        ModuleType[] values = ModuleType.values();
        for (ModuleType value : values) {
            if (value.getValue().equals(typee)) {
                return value;
            }
        }
        return ModuleType.COMMON;
    }

    public JTree getModuleTree() {
        return moduleTree;
    }

    public DefaultMutableTreeNode getSelectNode() {
        return selectNode;
    }

    /**
     * 保存过后,重新取数据
     */
    public void reLoadData() {
        if (selectNode != null && !selectNode.isRoot()) {
            moduleCatalogBean = (BeanDto) selectNode.getUserObject();
            String moudleid = moduleCatalogBean.getValue("moduleid");
            try {
                String moduleString = ModuleServiceUtil.getModule(moudleid);
                JsonElement moduleJsonElement = PubUtil.parseJson(moduleString);
                moduleInfoBean = null;
                if (moduleJsonElement != null) {
                    JsonArray moduleJsonArray = moduleJsonElement.getAsJsonArray();
                    if (moduleJsonArray.size() > 0) {
                        JsonObject moduleObj = moduleJsonArray.get(0).getAsJsonObject();
                        moduleInfoBean = new BeanDto(moduleObj, "name");
                    }
                }

            } catch (ServiceInvokerException e) {
                PubUtil.showMsg("获取组件信息失败");
                logger.error(e.getMessage());
            }
        } else {
            moduleCatalogBean = null;
            moduleInfoBean = null;
        }
        mainPnlFactory.loadData(moduleCatalogBean, moduleInfoBean, isVersion2());
    }
}
