/*
    Copyright 2006 Paw F. Kjeldgaard


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

/* $Id: SwingFileDialogStrategy.java,v 1.4 2012/09/27 11:03:46 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs;

import org.openoces.opensign.client.applet.OS;
import org.openoces.opensign.utils.ArrayUtil;
import org.openoces.opensign.utils.FileLog;
import org.openoces.opensign.utils.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;

/**
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 */
public class SwingFileDialogStrategy implements FileDialogStrategy {
    private Container owner;
    private JComponent oldFocusComponent;

    public SwingFileDialogStrategy(Container owner, JComponent oldFocusComponent) {
        super();
        this.owner = owner;
        this.oldFocusComponent = oldFocusComponent;
    }

    public SwingFileDialogStrategy(AbstractDialog owner, JComponent oldFocusComponent) {
        super();
        this.owner = owner.dialog;
        this.oldFocusComponent = oldFocusComponent;
    }


    public File getLoadFile(String title, FilenameFilter filter) {
        JFileChooser chooser = create();
        chooser.setDialogTitle(title);
        chooser.setFileFilter(new FileFilterWrapper(filter));

        int value = chooser.showOpenDialog(owner);

        if (oldFocusComponent != null) {
            GuiUtil.requestFocus(oldFocusComponent);
        }

        if (value == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            return null;
        }
    }

    public File getSaveFile(String defaultFolder, String defaultFileName, String[] allowedFileExtensions, String title) {
        JFileChooser chooser = create();
        chooser.setDialogTitle(title);
        chooser.setCurrentDirectory(new File(defaultFolder));
        chooser.setSelectedFile(new File(defaultFolder + OS.getFileSeperator() + defaultFileName));

        if (allowedFileExtensions != null) {
            chooser.setFileFilter(new FileExtensionFilter(allowedFileExtensions));
        }

        int value = chooser.showSaveDialog(owner);

        GuiUtil.requestFocus(oldFocusComponent);

        if (value == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            return null;
        }
    }

    public File getSaveFile(String defaultFolder, String defaultFileName, String title) {
        JFileChooser chooser = create();
        chooser.setDialogTitle(title);
        chooser.setCurrentDirectory(new File(defaultFolder));
        chooser.setSelectedFile(new File(defaultFolder + OS.getFileSeperator() + defaultFileName));

        int value = chooser.showSaveDialog(owner);

        GuiUtil.requestFocus(oldFocusComponent);

        if (value == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            return null;
        }
    }

    private final static class FileFilterWrapper extends FileFilter {
        private FilenameFilter filenameFilter;

        public FileFilterWrapper(FilenameFilter filenameFilter) {
            super();
            this.filenameFilter = filenameFilter;
        }

        public boolean accept(File pathname) {
            if (pathname.isDirectory()) {
                return true;
            } else {
                String[] folderAndFileName = FileUtils.getFolderAndFileName(pathname);
                return filenameFilter.accept(new File(folderAndFileName[0]), folderAndFileName[1]);
            }
        }

        public String getDescription() {
            return "";
        }
    }

    private final static class FileExtensionFilter extends FileFilter {
        private String[] allowedFileExtensions;

        private FileExtensionFilter(String[] allowedFileExtensions) {
            this.allowedFileExtensions = allowedFileExtensions != null ? ArrayUtil.copyOf(allowedFileExtensions) : null;
        }

        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            for (String allowedFileExtension : allowedFileExtensions) {
                if (f.getAbsolutePath().endsWith(allowedFileExtension)) {
                    return true;
                }
            }
            return false;
        }

        public String getDescription() {
            return "";
        }
    }

    private JFileChooser create() {

        try {
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        } catch (Exception e) {
            FileLog.debug("Unable to set native look and feel!!!");
        }

        return new JFileChooser();

    }


}