package org.openoces.opensign.client.applet.bootstrap;

final class InstallerService implements Runnable {
    private BootApplet bootApplet;

    InstallerService(BootApplet bootApplet) {
        this.bootApplet = bootApplet;
    }

    public void run() {
        try {
            bootApplet.loadItems();
            bootApplet.whenDone();
        } catch (Exception e) {
            FileLog.fatal("An exception occured " + e, e);
            bootApplet.whenFailed(e);
        }
    }
}
