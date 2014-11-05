package org.openoces.opensign.client.applet.dialogs.listeners;

import org.openoces.opensign.client.applet.GuiCallback;
import org.openoces.opensign.client.applet.dialogs.GuiUtil;
import org.openoces.opensign.utils.FileLog;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

public class PrintActionListener implements ActionListener {
    private JTextComponent jTextComponent;

    public PrintActionListener(JTextComponent jTextComponent) {
        this.jTextComponent = jTextComponent;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        GuiUtil.doInGui(new GuiCallback() {
            @Override
            public void doInGui() {
                try {
                    String cn = UIManager.getSystemLookAndFeelClassName();
                    UIManager.setLookAndFeel(cn); // Use the native L&F

                    jTextComponent.print();
                } catch (ClassNotFoundException e) {
                    FileLog.error(e.getMessage(), e);
                } catch (InstantiationException e) {
                    FileLog.error(e.getMessage(), e);
                } catch (IllegalAccessException e) {
                    FileLog.error(e.getMessage(), e);
                } catch (UnsupportedLookAndFeelException e) {
                    FileLog.error(e.getMessage(), e);
                } catch (PrinterException e) {
                    FileLog.error(e.getMessage(), e);
                }

            }
        });
    }
}
