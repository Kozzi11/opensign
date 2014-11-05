package org.openoces.opensign.client.applet.attach.dialogs;

import org.openoces.opensign.client.applet.attach.resources.AttachmentResourceManager;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

class AttachmentTableModel extends AbstractTableModel {
    private final String[] COLUMN_NAMES = new String[]{AttachmentResourceManager.getString("TITLE"), AttachmentResourceManager.getString("SIZE"), "", "", ""};
    private final Class<?>[] COLUMN_TYPES = new Class<?>[]{JLabel.class, JLabel.class, JButton.class, JButton.class, JLabel.class};

    private Object[][] data;

    AttachmentTableModel(Object[][] data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_TYPES[columnIndex];
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        return data[rowIndex][columnIndex];
    }
}