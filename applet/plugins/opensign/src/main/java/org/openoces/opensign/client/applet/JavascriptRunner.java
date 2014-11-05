package org.openoces.opensign.client.applet;

import netscape.javascript.JSObject;

public final class JavascriptRunner
{
    private AbstractApplet applet;

    JavascriptRunner(AbstractApplet applet)
    {
        this.applet = applet;
    }

    public final void callFunction(String function, String[] args)
    {
        JavascriptServiceFactory factory = new DefaultJavascriptServiceFactory(applet);
        JavascriptService service = factory.create();
        service.callFunction(function, args);
    }

    public final String eval(String function) {
        JSObject window = JSObject.getWindow(applet);

        return (String)window.eval(function);
    }

    public final void call(String function, Object[] data) {
        JSObject window = JSObject.getWindow(applet);

        window.call(function, data);
    }

    public final void setMember(String member, String data) {
        JSObject window = JSObject.getWindow(applet);

        window.setMember(member, data);
    }
}