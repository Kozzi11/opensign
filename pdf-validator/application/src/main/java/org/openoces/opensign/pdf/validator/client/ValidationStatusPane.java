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

import javax.swing.*;
import java.awt.*;

/**
 * @author: Michael Martinsen <mma@openminds.dk>
 */
public class ValidationStatusPane extends JPanel implements ApplicationLabels {
    private PdfValidatorApplication parent;
    private JLabel labelTotalNumberOfFiles;
    private JLabel labelNumberOfFilesPassed;
    private JLabel labelNumberOfFilesFailed;

    public ValidationStatusPane(PdfValidatorApplication parent) {
        super();
        this.parent = parent;
        initUI();
    }

    /**
     * Set the number of total files.
     * Notice this will also reset the count of passed and failed to
     * let the UI show that not all files have been validated.
     * @param numberOfFiles
     */
    void setTotalNumberOfFiles(int numberOfFiles) {
        labelTotalNumberOfFiles.setText(String.valueOf(numberOfFiles));
        setNumberOfFilesPassed(0);
        setNumberOfFilesFailed(0);
    }

    void setNumberOfFilesPassed(int numberOfFiles) {
        labelNumberOfFilesPassed.setText(String.valueOf(numberOfFiles));
    }

    void setNumberOfFilesFailed(int numberOfFiles) {
        labelNumberOfFilesFailed.setText(String.valueOf(numberOfFiles));
    }

    private void initUI() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder());

        JLabel labelFiles = new JLabel(parent.resolveLabel(LABEL_KEY_TEXT_STATUS_FILES));
        add(Box.createHorizontalStrut(5));
        add(labelFiles);

        labelTotalNumberOfFiles = new JLabel("0");
        add(labelTotalNumberOfFiles);

        add(Box.createHorizontalStrut(10));
        add(getSeparator());
        add(Box.createHorizontalStrut(10));

        JLabel labelPassedFiles = new JLabel(parent.resolveLabel(LABEL_KEY_TEXT_STATUS_PASSED_FILES));
        labelPassedFiles.setHorizontalAlignment(SwingConstants.LEFT);
        add(labelPassedFiles);

        labelNumberOfFilesPassed = new JLabel("0");
        add(labelNumberOfFilesPassed);

        add(Box.createHorizontalStrut(10));
        add(getSeparator());
        add(Box.createHorizontalStrut(10));

        JLabel labelFailedFiles = new JLabel(parent.resolveLabel(LABEL_KEY_TEXT_STATUS_FAILED_FILES));
        labelFailedFiles.setHorizontalAlignment(SwingConstants.LEFT);
        add(labelFailedFiles);

        labelNumberOfFilesFailed = new JLabel("0");
        labelNumberOfFilesFailed.setHorizontalAlignment(SwingConstants.LEFT);
        add(labelNumberOfFilesFailed);
    }

    private Component getSeparator() {
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setPreferredSize(new Dimension(separator.getPreferredSize().width, 15));
        separator.setMaximumSize(new Dimension(separator.getPreferredSize().width, 15));

        return separator;
    }
}
