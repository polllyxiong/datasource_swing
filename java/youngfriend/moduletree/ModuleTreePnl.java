/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youngfriend.moduletree;

import com.google.common.base.Strings;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youngfriend.bean.BeanDto;
import youngfriend.common.util.StringUtils;
import youngfriend.common.util.net.exception.ServiceInvokerException;
import youngfriend.utils.Do4objs;
import youngfriend.utils.PubUtil;
import youngfriend.utils.ServiceInvoker;

import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import static youngfriend.App.busyDoing;
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
        moduleTree.setRootVisible(false);
        moduleTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        addEvents();
        loadProjectData();
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
    private DefaultMutableTreeNode moduleTreeRoot = new DefaultMutableTreeNode("组件树");
    private DefaultTreeModel moduleTreeModel = new DefaultTreeModel(moduleTreeRoot);

    private DefaultMutableTreeNode selectNode;
    private BeanDto moduleCatalogBean = null;
    private BeanDto projectBean;
    private BeanDto moduleInfoBean;

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
        moduleSearchBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchModule();
            }
        });
        moduleSearchTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchModule();
                }
            }

        });
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
                DefaultMutableTreeNode tempNode = (DefaultMutableTreeNode) treeSelectionEvent.getPath().getLastPathComponent();

//                if (selectNode != null && !selectNode.isRoot()) {
//                    moduleCatalogBean = (BeanDto) selectNode.getUserObject();
//                } else {
//                    return;
//                }
//                if (moduleCatalogBean != null && !TRUESTR.equals(moduleCatalogBean.getValue(ISMODULE))) {
//                    PubUtil.enableComponents(oper_pnl, false);
//                    main_pnl.setVisible(false);
//                    return;
//                }
//                loadData();
            }
        });


        moduleTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                    if (moduleInfoBean == null) {
                        return;
                    }
                    boolean ismodule = TRUESTR.equals(moduleInfoBean.getValue(ISMODULE));
//
//                    saveItem.setEnabled(!ismodule);
//                    addCatalogItem.setEnabled(!ismodule);
//                    removeItem.setEnabled(ismodule);
//                    removeCatalogItem.setEnabled(!ismodule);
//                    editModuleItem.setEnabled(ismodule);
//                    editCatalogItem.setEnabled(!ismodule);
//                    copyModuleItem.setEnabled(ismodule);
//                    menu.show(moduleTree, mouseEvent.getX(), mouseEvent.getY());
                }
            }
        });

    }


    /**
     * @param selectBean projectBeanTemp
     */
    private void buildTreeByProject(final BeanDto selectBean) {
        if (selectBean == null) {
            moduleTreeRoot.removeAllChildren();
            moduleTreeModel.nodeStructureChanged(moduleTreeRoot);
            projectBean = null;
        } else {
            busyDoing(new Do4objs() {
                @Override
                public void do4ojbs(Object... objs) {
                    try {
                        String projectId = selectBean.getValue("id");
                        String jsonStr = ServiceInvoker.form_catalog_get(projectId);
                        addNodesLeftTree(jsonStr);
                        jsonStr = ServiceInvoker.btnmodule_catalog_get(projectId);
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
                        dto.setItem(ISMODULE, "false");
                    } else {
                        dto.setItem(ISMODULE, TRUESTR);
                        dto.setToString(MODULE_TOSTRING);
                    }
                }
            }, CATALOG_TOSTRING);
        }
    }

    private void searchModule() {
        String text = moduleSearchTf.getText().trim();
        if (StringUtils.nullOrBlank(text)) {
            return;
        }
        Enumeration<DefaultMutableTreeNode> enumeration = moduleTreeRoot.breadthFirstEnumeration();
        if (selectNode != null) {
            while (enumeration.hasMoreElements()) {
                if (enumeration.nextElement().equals(selectNode)) {
                    break;
                }
            }
        }
        while (enumeration.hasMoreElements() || (enumeration = moduleTreeRoot.breadthFirstEnumeration()) != null) {
            DefaultMutableTreeNode current = enumeration.nextElement();
            if (current.toString().contains(text)) {
                TreePath path = new TreePath(current.getPath());
                moduleTree.setSelectionPath(path);
                moduleTree.scrollPathToVisible(path);
                moduleTree.repaint();
                break;
            }
        }

    }

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

    public BeanDto getModuleInfoBean() {
        return moduleInfoBean;
    }


    public void reBuildTree() {
        DefaultMutableTreeNode beforeSelectNode = this.selectNode;
        buildTreeByProject(projectBean);
        TreePath treePath = new TreePath(moduleTreeModel.getPathToRoot(beforeSelectNode));
        moduleTree.setSelectionPath(treePath);
    }
}