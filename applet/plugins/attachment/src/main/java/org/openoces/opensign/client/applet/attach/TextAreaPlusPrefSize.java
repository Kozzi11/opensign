/*
    Copyright 2006 IT Practice A/S
    Copyright 2006 TDC Totalløsninger A/S
    Copyright 2006 Jens Bo Friis
    Copyright 2006 Preben Rosendal Valeur
    Copyright 2006 Carsten Raskgaard


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

/* $Id: TextAreaPlusPrefSize.java,v 1.2 2012/02/28 08:21:09 pakj Exp $ */

package org.openoces.opensign.client.applet.attach;

/**
 * This class extends a textarea with a default preferred size
 * (as setPreferredSize() is jdk 1.5+ we implement what we need here)
 *
 * @author Preben Rosendal Valeur  <prv@tdc.dk>
 */

import java.awt.*;

class TextAreaPlusPrefSize extends TextArea {
	private static final long serialVersionUID = -7849779169768719468L;

	protected Dimension preferredSize = null;
    TextAreaPlusPrefSize(){
        super(null, 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
    }
    public Dimension getPreferredSize(){
        if (preferredSize == null){
            return super.getPreferredSize();
        } else {
            return preferredSize;
        }
    }
    public void setPreferredSize(Dimension preferredSize) {
        this.preferredSize = preferredSize;
    }
}
