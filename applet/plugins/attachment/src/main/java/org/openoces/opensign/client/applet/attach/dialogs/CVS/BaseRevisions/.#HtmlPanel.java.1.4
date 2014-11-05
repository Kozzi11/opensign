/*
	Copyright 2011 Paw F. Kjeldgaard

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

/* $Id: HtmlPanel.java,v 1.4 2012/09/27 11:03:49 pakj Exp $ */

package org.openoces.opensign.client.applet.attach.dialogs;

import org.openoces.opensign.client.applet.CallBackHandler;
import org.openoces.opensign.client.applet.dialogs.OpenSignDialog;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.ChangedCharSetException;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import java.awt.*;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

public class HtmlPanel extends AbstractViewPanel {

    public HtmlPanel(CallBackHandler callbackHandler, OpenSignDialog dialog, String title, byte[] data) {
        super(callbackHandler, dialog, title, data);
    }

    @Override
    protected JComponent getContentPanel() {
        JPanel panel = componentFactory.createPanel(new BorderLayout());
        panel.add(title, BorderLayout.NORTH);


		JEditorPane display;
		// ...
		display = new JEditorPane();
		display.setContentType(getContentType());
		display.setEditable(false);
		/*
		 * display.setBackground( Color.LIGHT_GRAY ); display.setFont(new Font(
		 * "Serif", Font.PLAIN, 12 )); display.setForeground( Color.BLACK );
		 */
		/*
		 * GridBagConstraints gbc = new GridBagConstraints(); gbc.gridx = 0;
		 * gbc.gridy = 4;
		 */
		// ...
		displayScroller = new JScrollPane(display);
		displayScroller.getViewport().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				whenStateChanged();
			}
		});
		try {
			// determine charset
			String t = new String(data, "UTF8");
			// check for a BOM (Byte Order
			// Mark)(http://www.unicode.org/faq/utf_bom.html#22)
			// and remove if present
			if (t.charAt(0) == 0xFEFF) {
				t = t.substring(1);
			}
			Document doc = display.getDocument();
			doc.remove(0, doc.getLength());
			if (t == null || t.equals("")) {
				display.setText(getErrorText("EMPTY"));
			}
			Reader r = new StringReader(t);
			EditorKit kit = display.getEditorKit();
			kit.read(r, doc, 0);
		} catch (ChangedCharSetException e) {
			// try new encoding
			String charset = getNewCharSet(e);
			try {
				String t = new String(data, charset);
				Reader r = new StringReader(t);
				EditorKit kit = display.getEditorKit();
				Document doc = display.getDocument();
				doc.remove(0, doc.getLength());
				doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
				kit.read(r, doc, 0);
			} catch (IOException ee) {
				display.setText(getErrorText(e));
			} catch (BadLocationException ee) {
				display.setText(getErrorText(e));
			}
		} catch (BadLocationException e) {
			display.setText(getErrorText(e));
		} catch (UnsupportedEncodingException e) {
			display.setText(getErrorText(e));
		} catch (IOException e) {
			display.setText(getErrorText(e));
		}

        panel.add(displayScroller, BorderLayout.CENTER);

		return panel;
    }

    // from:
	// http://www.java2s.com/ExampleCode/Swing-JFC/JEditorPaneExample.htm
	protected String getNewCharSet(ChangedCharSetException e) {
		String spec = e.getCharSetSpec();
		if (e.keyEqualsCharSet()) {
			// The event contains the new CharSet
			return spec;
		}

		// The event contains the content type
		// plus ";" plus qualifiers which may
		// contain a "charset" directive. First
		// remove the content type.
		int index = spec.indexOf(';');
		if (index != -1) {
			spec = spec.substring(index + 1);
		}

		// Force the string to lower case
		spec = spec.toLowerCase();

		StringTokenizer st = new StringTokenizer(spec, " \t=", true);
		boolean foundCharSet = false;
		boolean foundEquals = false;
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (token.equals(" ") || token.equals("\t")) {
				continue;
			}
			if (!foundCharSet && foundEquals == false
					&& token.equals("charset")) {
				foundCharSet = true;
				continue;
			} else if (!foundEquals && token.equals("=")) {
				foundEquals = true;
				continue;
			} else if (foundEquals && foundCharSet == true) {
				return token;
			}

			// Not recognized
			foundCharSet = false;
			foundEquals = false;
		}

		// No charset found - return a guess
		return "8859_1";
	}

    protected String getContentType() {
		return "text/html";
	}

	protected String getErrorText() {
		return getErrorText("UNSPECIFIED_ERROR");
	}

	protected String getErrorText(Exception e) {
		return getErrorText(e.getMessage());
	}

	protected String getErrorText(String s) {
		return "<html><body><h1>" + s + "</h1></body></html>";
	}

	// make it scroll up again - here could be a nicer way!
	private JScrollPane displayScroller;
	private boolean doneUpscrolling;

	void whenStateChanged() {
		if (doneUpscrolling)
			return;
		if (!displayScroller.isValid())
			return;
		Point before = displayScroller.getViewport().getViewPosition();
		if (before.y == 0)
			return;
		doneUpscrolling = true;
		displayScroller.getViewport().setViewPosition(new Point());
	}

}