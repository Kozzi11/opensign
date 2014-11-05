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

/* $Id: FileLog.java,v 1.2 2012/02/28 08:21:01 pakj Exp $ */

package org.openoces.opensign.client.applet.bootstrap;

/**
 * This class implements logging
 *
 * @author Jens Bo Friis  <jbf@it-practice.dk>
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 * @author Paw F. Kjeldgaard <pakj@danid.dk>
 */

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;

class FileLog {
    public enum Level {
        FATAL(1), ERROR(2), WARN(3), INFO(4), DEBUG(5);

        private int level;

        private Level(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }


    private static Level verbose = Level.INFO;

    protected FileLog() {
    }


    static void setVerbose(Level verbose) {
        FileLog.verbose = verbose;
    }

    static Level getVerbose() {
        return verbose;
    }

    static void info(String s) {
		log(s, null, Level.INFO);
	}

	static void warn(String s) {
		log(s, null, Level.WARN);
	}

    static void warn(String s, Throwable t) {
		log(s, t, Level.WARN);
	}

	static void fatal(String s) {
		log(s, null, Level.FATAL);
	}

    static void fatal(String s, Throwable t) {
		log(s, t, Level.FATAL);
	}

	static void error(String s) {
		log(s, null, Level.ERROR);
	}

	static void error(String s, Throwable t) {
		log(s, t, Level.ERROR);
	}

	static void debug(String s, Throwable t) {
		log(s, t, Level.DEBUG);
	}

    static void debug(String s) {
		log(s, null, Level.DEBUG);
	}

	private synchronized static void logToConsole(String s) {
		System.out.println(s);
	}

	static void log(String s, Throwable t, Level type) {
		if (verbose.getLevel() < type.getLevel())
			return;

		PrintWriter pw = null;
		synchronized (FileLog.class) {
			try {
				StringWriter sw = new StringWriter();
				pw = new PrintWriter(sw);
				String date = DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis()));
				pw.print(date);
				switch (type) {
					case DEBUG :
						pw.print(" <DEBUG> ");
						break;
					case ERROR :
						pw.print(" <ERROR> ");
						break;
					case FATAL :
						pw.print(" <FATAL> ");
						break;
					case INFO :
						pw.print(" <INFO>  ");
						break;
					case WARN :
						pw.print(" <WARN>  ");
						break;
				}
				pw.print(Thread.currentThread().getName());
				pw.print(" - ");
				if (t != null) {
					pw.println(s);
					t.printStackTrace(pw);
				} else {
					pw.println(s);
				}
				String out = sw.getBuffer().toString();
   			    logToConsole(out);
			} catch (Throwable t1) {
				t1.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
			}
		}
	}
}