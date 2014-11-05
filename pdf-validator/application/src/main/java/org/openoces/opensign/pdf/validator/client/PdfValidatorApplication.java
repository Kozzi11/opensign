/*
    This file is part of OpenSign.

    OpenSign is free software; you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation; either version 2.1 of the License, or
    (at your option) any later version.

    OpenSign is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with OpenOcesAPI; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA


    Note to developers:
    If you add code to this file, please take a minute to add an additional
    copyright statement above and an additional
    @author statement below.
*/

package org.openoces.opensign.pdf.validator.client;

import org.openoces.opensign.pdf.validator.PDFException;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * This is a simple validation application for validating PDF files.
 * The validation is the same validation the OpenOces OpenSign applet will perform
 * before allowing a PDF file to be signed.
 *
 * @author: Michael Martinsen <mma@openminds.dk>
 */
public class PdfValidatorApplication extends JFrame implements ApplicationLabels {
    public static final Dimension DEFAULT_DIMENSION = new Dimension(940, 800);

    private ResourceBundle labels;

    private Component topComponent;
    private JComboBox openSignVersions;
    private ValidationStatusPane validationStatusPane;
    private JTable selectedFilesTable;
    private NonEditableTableModel selectedFilesTableModel;
    private PDFFilesValidator validator;
    private IconManager iconManager;
    private UserPreferences userPreferences;
    private JLabel buildTimestampLabel;
    private BuildTimestamp buildTimestamp;

    public PdfValidatorApplication() throws HeadlessException {
        topComponent = this;
        iconManager = new IconManager();
        userPreferences = new UserPreferences();
        try {
            userPreferences.load();
        } catch (Exception e) {
            // Ignore
        }

        loadBuildTimestamp();
    }

    public void init() {
        initResourceBundle();

        String title = resolveLabel(LABEL_KEY_TITLE) + " [Build " + buildTimestamp.getTimestamp() + "]";
        setTitle(title);
        setSize(DEFAULT_DIMENSION);
        setLocationRelativeTo(null);    //centre on screen

        initValidator();

        JPanel layoutPanel = new JPanel();
        layoutPanel.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(getNorth());
        mainPanel.add(getCenter());

        layoutPanel.add(mainPanel, BorderLayout.CENTER);

        add(layoutPanel);

        initAppCloseHandling();
        initTableModelListeners();
    }

    private void initResourceBundle() {
        labels = ResourceBundle.getBundle("LabelsBundle");
    }

    private void initTableModelListeners() {
        selectedFilesTable.getModel().addTableModelListener(new TableModelListener() {
            /**
             * Notice this method will be called every time the table model is changed.
             * We could have updated the number of passed / failed files from here in a clean way,
             * but when a validation is triggered, it means this method is called 3 times. So
             * for performance reasons we won't do that.
             * @param e
             */
            @Override
            public void tableChanged(TableModelEvent e) {
                int totalNumberOfFilesCount = selectedFilesTableModel.getRowCount();
                validationStatusPane.setTotalNumberOfFiles(totalNumberOfFilesCount);
            }
        });

        selectedFilesTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable source = (JTable) e.getSource();
                    int row = source.getSelectedRow();
                    int column = source.getSelectedColumn();

                    if (column == 2) {
                        Object cellValue = selectedFilesTableModel.getValueAt(row, column);
                        if (cellValue instanceof List && ((List<PDFException>) cellValue).size() > 0 ) {
                            ErrorDetailsDialog dialog = new ErrorDetailsDialog(PdfValidatorApplication.this);
                            dialog.show((List<PDFException>) cellValue);
                        }
                    }
                }
            }
        });
    }

    private void initAppCloseHandling() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();

                int result = JOptionPane.showConfirmDialog(
                        frame,
                        resolveLabel(LABEL_KEY_CONFIRM_EXIT_APP_MESSAGE),
                        resolveLabel(LABEL_KEY_CONFIRM_EXIT_APP_TITLE),
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION)

                    if ( ! userPreferences.isEmpty()) {
                        saveUserPreferences();
                    }

                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }

    private void initValidator() {
        validator = new PDFFilesValidatorImpl();
    }

    private void loadBuildTimestamp() {
        buildTimestamp = new BuildTimestamp();
    }

    private Component getNorth() {
        JPanel versionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        openSignVersions = new JComboBox();
        List<String> versions = validator.getSupportedValidatorVersions();
        for (int i = 0; i < versions.size(); i++) {
            String supportedVersion = versions.get(i);
            PDFValidatorVersion version = new PDFValidatorVersion(supportedVersion);
            openSignVersions.addItem(version);
        }

        openSignVersions.setPreferredSize(new Dimension(60, openSignVersions.getPreferredSize().height));

        versionPanel.add(new JLabel(resolveLabel(LABEL_KEY_SELECT_OPEN_SIGN_VERSION)));
        versionPanel.add(openSignVersions);

        return versionPanel;
    }

    private JPanel getCenter() {
        JPanel panel = new JPanel(new BorderLayout());

        // Force some space around the entire main content
        panel.setBorder(BorderFactory.createLineBorder(panel.getBackground(), 5));

        panel.add(new JLabel(resolveLabel(LABEL_KEY_PDF_FILES_LIST_CAPTION)), BorderLayout.NORTH);

        selectedFilesTableModel = new NonEditableTableModel();
        selectedFilesTableModel.addColumn(resolveLabel(LABEL_KEY_PDF_FILES_LIST_COLUMN_1));
        selectedFilesTableModel.addColumn(resolveLabel(LABEL_KEY_PDF_FILES_LIST_COLUMN_2));
        selectedFilesTableModel.addColumn(resolveLabel(LABEL_KEY_PDF_FILES_LIST_COLUMN_3));
        selectedFilesTable = new JTable(selectedFilesTableModel);
        selectedFilesTable.setRowHeight(27);

        selectedFilesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(selectedFilesTable);
        selectedFilesTable.getColumnModel().getColumn(1).setCellRenderer(new IconTableCellRenderer());
        selectedFilesTable.getColumnModel().getColumn(2).setCellRenderer(new PDFExceptionListCellRenderer(this));
        selectedFilesTable.getColumnModel().getColumn(1).setMaxWidth(50);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(getControlPanel(), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel getControlPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(getSouth(), BorderLayout.WEST);
        panel.add(getActionButtonPanel(), BorderLayout.EAST);

        return panel;
    }

    private JPanel getActionButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBorder(BorderFactory.createEmptyBorder());

        JButton buttonFileSelectionButton = new JButton(resolveLabel(LABEL_KEY_BUTTON_ADD_FILES));
        buttonFileSelectionButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        JFileChooser fileChooser = new JFileChooser();  // Defaults to user home

                        String pathOfLatestFileAdded =
                                userPreferences.getPreference(UserPreferences.PREFERENCE_PATH_OF_LATEST_FILE_ADDED);
                        if (pathOfLatestFileAdded != null) {
                            fileChooser.setCurrentDirectory(new File(pathOfLatestFileAdded));
                        }
                        fileChooser.setMultiSelectionEnabled(true);
                        int returnVal = fileChooser.showOpenDialog(PdfValidatorApplication.this);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            addSelectedFilesToTable(fileChooser.getSelectedFiles());
                            userPreferences.setPreference(UserPreferences.PREFERENCE_PATH_OF_LATEST_FILE_ADDED,
                                    fileChooser.getCurrentDirectory().getAbsolutePath());
                        }
                    }
                });
        panel.add(buttonFileSelectionButton);

        JButton buttonValidate = new JButton(resolveLabel(LABEL_KEY_BUTTON_VALIDATE_FILES));
        panel.add(buttonValidate);
        buttonValidate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File[] currentFiles = getCurrentFiles();
                if (currentFiles.length > 0) {
                    PDFFilesValidationResult validationResult = validator.validateFiles(currentFiles, getPDFValidatorVersion());
                    Vector<Vector<String>> rows = selectedFilesTableModel.getDataVector();

                    int passedFilesCount = 0;
                    int failedFilesCount = 0;

                    for (int i = 0; i < rows.size(); i++) {
                        Vector<String> columnValues = rows.elementAt(i);
                        String fileName = columnValues.get(0);
                        File file = new File(fileName);
                        PDFSingleFileValidationResult singleFileValidationResult = validationResult.getSingleValidationResult(file);
                        selectedFilesTableModel.setValueAt(createValidationStatus(singleFileValidationResult.hasPassed()), i, 1);
                        selectedFilesTableModel.setValueAt(singleFileValidationResult.getErrors(), i, 2);
                        selectedFilesTableModel.fireTableDataChanged();

                        ValidationStatus validationStatus = (ValidationStatus) selectedFilesTableModel.getValueAt(i, 1);
                        if (validationStatus.isPassed()) {
                            passedFilesCount++;
                        } else {
                            failedFilesCount++;
                        }

                        validationStatusPane.setNumberOfFilesPassed(passedFilesCount);
                        validationStatusPane.setNumberOfFilesFailed(failedFilesCount);
                    }
                } else {
                    JOptionPane.showConfirmDialog(topComponent,
                            resolveLabel(LABEL_KEY_VALIDATE_NO_FILES_MESSAGE),
                            resolveLabel(LABEL_KEY_VALIDATE_NO_FILES_TITLE),
                            JOptionPane.DEFAULT_OPTION);
                }
            }
        });

        JButton buttonClearSelectedFiles = new JButton(resolveLabel(LABEL_KEY_BUTTON_CLEAR_FILES));
        panel.add(buttonClearSelectedFiles);
        buttonClearSelectedFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedFilesTableModel.getDataVector().removeAllElements();
                selectedFilesTableModel.fireTableDataChanged();
            }
        });

        JButton buttonExit = new JButton(resolveLabel(LABEL_KEY_BUTTON_EXIT));
        panel.add(buttonExit);
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fire a "real" close window event (which will be handle by the window listener...
                Window topContainer = (Window) ((JButton) e.getSource()).getTopLevelAncestor();
                getToolkit().getSystemEventQueue().postEvent(new WindowEvent(topContainer, WindowEvent.WINDOW_CLOSING));
            }
        });

        return panel;
    }

    private void saveUserPreferences() {
        try {
            userPreferences.save();
        } catch (Exception e) {
            // ignore
            // TODO: We should have a log file...
        }
    }

    private JPanel getSouth() {
        validationStatusPane = new ValidationStatusPane(this);
        return validationStatusPane;
    }

    private void addSelectedFilesToTable(File[] selectedFiles) {
        for (File selectedFile : selectedFiles) {
            if (! filesAlreadyAdded(selectedFilesTableModel, selectedFile)) {
                selectedFilesTableModel.addRow(createDefaultTableRow(selectedFile));
            }
        }
    }

    private boolean filesAlreadyAdded(DefaultTableModel selectedFilesTableModel, File selectedFile) {
        Vector<Vector<String>> selectedFiles = selectedFilesTableModel.getDataVector();
        for (int i = 0; i < selectedFiles.size(); i++) {
            Vector<String> columns = selectedFiles.elementAt(i);
            File existingFile = new File(columns.get(0));
            if (existingFile.equals(selectedFile)) {
                return true;
            }
        }

        return false;
    }

    private File[] getCurrentFiles() {
        Vector<Vector<String>> selectedFiles = selectedFilesTableModel.getDataVector();
        File[] currentFiles = new File[selectedFiles.size()];

        for (int i = 0; i < selectedFiles.size(); i++) {
            Vector<String> columns = selectedFiles.elementAt(i);
            File file = new File(columns.get(0));
            currentFiles[i] = file;
        }

        return currentFiles;
    }

    private Vector<String> createDefaultTableRow(File file) {
        Vector<String> row = new Vector<String>();
        row.add(file.getAbsolutePath());
        row.add("");
        row.add("");
        return row;
    }

    String resolveLabel(String bundleKey) {
        return labels.getString(bundleKey);
    }

    private ValidationStatus createValidationStatus(boolean status) {
        ValidationStatus validationStatus =
                new ValidationStatus(status, iconManager.getIconBasedOnState(status));

        return validationStatus;
    }

    private PDFValidatorVersion getPDFValidatorVersion() {
        return (PDFValidatorVersion) openSignVersions.getSelectedItem();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PdfValidatorApplication validator = new PdfValidatorApplication();
                validator.init();
                validator.setVisible(true);
            }
        });
    }

}

