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

/* $Id: ResourcesChecker.java,v 1.2 2012/02/28 08:21:07 pakj Exp $ */

package org.openoces.opensign.client.applet.resources;

/**
 * Use this class to check missing in locales
 *
 *
 * @author Carsten Raskgaard  <carsten@raskgaard.dk>
 */

import java.util.*;

public class ResourcesChecker {
    private final static class ResourceBundleStats {
        String id;
        //int noKeys;
        String[] missingKeys;
        String[] unexpectedKeys;
    }

    public static void main(String[] args) {
        ResourcesChecker checker = new ResourcesChecker();
        ResourceBundleStats[] s = checker.analyze();
        for (int i = 0; i < s.length; i++) {
            ResourceBundleStats resourceBundleStats = s[i];
            System.out.println("Bundle:"+resourceBundleStats.id+":");
            System.out.println(" Missing keys:");
            for (int j = 0; j < resourceBundleStats.missingKeys.length; j++) {
                String missingKey = resourceBundleStats.missingKeys[j];
                System.out.println("  "+missingKey);
            }
            System.out.println(" Unexpected keys:");
            for (int j = 0; j < resourceBundleStats.unexpectedKeys.length; j++) {
                String unexpectedKey = resourceBundleStats.unexpectedKeys[j];
                System.out.println("  "+unexpectedKey);
            }

        }
    }

    protected Set getAuthoritativeSet() {
        return loadKeys(new Resources_da_DK());
    }

    protected ResourceBundleStats[] analyze() {
        ListResourceBundle[] rs = {
            new Resources_ca_ES(),
            new Resources_da_DK(),
            new Resources_en_US(),
            new Resources_cs_CZ(),
            new Resources_es_ES(),
            new Resources_nl_NL(),

        };

        ResourceBundleStats[] stats = new ResourceBundleStats[rs.length];

        Set[] resSets = new Set[rs.length];
        for (int i = 0; i < rs.length; i++) {
            resSets[i] = loadKeys(rs[i]);
        }
        for (int i = 0; i < resSets.length; i++) {
            ResourceBundleStats resStats = new ResourceBundleStats();
            Set resSet = resSets[i];

            resStats.id = rs[i].getClass().getName();
            //resStats.noKeys = resSet.size();

            /* calculate missing keys */
            Set s0 = getAuthoritativeSet();
            s0.removeAll(resSet);

            resStats.missingKeys = new String[s0.size()];
            int j = 0;
            for (Iterator iterator = s0.iterator(); iterator.hasNext();) {
                String s = (String) iterator.next();
                resStats.missingKeys[j++] = s;
            }

            /* calculate unexpected keys */
            resSet.removeAll(getAuthoritativeSet());
            resStats.unexpectedKeys = new String[resSet.size()];
            j = 0;
            for (Iterator iterator = resSet.iterator(); iterator.hasNext();) {
                String s = (String) iterator.next();
                resStats.unexpectedKeys[j++] = s;
            }

            stats[i] = resStats;
        }
        return stats;
    }

    protected static Set loadKeys(ListResourceBundle r) {
        Enumeration enumeration = r.getKeys();
        Set set = new TreeSet();
        while (enumeration.hasMoreElements()) {
            String s = (String) enumeration.nextElement();
            set.add(s);
        }
        return set;
    }
}
