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

/* $Id: OpensignFocusTraversalPolicy.java,v 1.4 2012/09/27 11:03:48 pakj Exp $ */

package org.openoces.opensign.client.applet.dialogs.panel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class OpensignFocusTraversalPolicy extends FocusTraversalPolicy {

    private static class MutableBoolean {
        boolean value = false;
    }

    private static final MutableBoolean found = new MutableBoolean();

    private AbstractPanel root;

    public OpensignFocusTraversalPolicy(AbstractPanel root) {
        super();
        this.root = root;
    }

    public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
        if (focusCycleRoot == null || aComponent == null) {
            throw new IllegalArgumentException("focusCycleRoot and aComponent cannot be null");
        }
        if (!aComponent.isFocusCycleRoot(focusCycleRoot)) {
            throw new IllegalArgumentException("focusCycleRoot is not a focus cyle root of aComponent");
        }

        synchronized (focusCycleRoot.getTreeLock()) {
            found.value = false;
            Component retval = getComponentAfter(focusCycleRoot, aComponent, found);
                if (retval != null) {
                return retval;
            } else if (found.value) {
                return getFirstComponent(focusCycleRoot);
            } else {
                return null;
            }
        }
    }

    private Component getComponentAfter(Container aContainer, Component aComponent, MutableBoolean found) {
        if (!(aContainer.isVisible() && aContainer.isDisplayable())) {
            return null;
        }

        if (found.value) {
            if (accept(aContainer)) {
                return aContainer;
            }
        } else if (aContainer.equals(aComponent)) {
            found.value = true;
        }

        Component[] componentArray = getSortedContainerComponents(aContainer);

        for (Component comp : componentArray) {
            if ((comp instanceof Container) && !((Container) comp).isFocusCycleRoot() && !(comp instanceof JComponent)) {
                Component retval = getComponentAfter((Container) comp, aComponent, found);
                if (retval != null) {
                    return retval;
                }
            } else if (found.value) {
                if (accept(comp)) {
                    return comp;
                }
            } else if (comp == aComponent) {
                found.value = true;
            }

            if (found.value && getImplicitDownCycleTraversal() && (comp instanceof Container) && ((Container) comp).isFocusCycleRoot()) {
                Container cont = (Container) comp;
                Component retval = cont.getFocusTraversalPolicy().getDefaultComponent(cont);
                if (retval != null) {
                    return retval;
                }
            }
        }

        return null;
    }

    public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
        if (focusCycleRoot == null || aComponent == null) {
            throw new IllegalArgumentException("focusCycleRoot and aComponent cannot be null");
        }
        if (!aComponent.isFocusCycleRoot(focusCycleRoot)) {
            throw new IllegalArgumentException("focusCycleRoot is not a focus cyle root of aComponent");
        }

        synchronized (focusCycleRoot.getTreeLock()) {
            found.value = false;
            Component retval = getComponentBefore(focusCycleRoot, aComponent, found);
            if (retval != null) {
                return retval;
            } else if (found.value) {
                return getLastComponent(focusCycleRoot);
            } else {
                return null;
            }
        }
    }

    private Component getComponentBefore(Container aContainer, Component aComponent, MutableBoolean found) {
        if (!(aContainer.isVisible() && aContainer.isDisplayable())) {
            return null;
        }

        Component[] componentArray = getSortedContainerComponents(aContainer);

        for (int i = componentArray.length - 1; i >= 0; i--) {
            Component comp = componentArray[i];
            if (comp.equals(aComponent)) {
                found.value = true;
            } else if ((comp instanceof Container) && !((Container) comp).isFocusCycleRoot() && !(comp instanceof JComponent)) {
                Component retval = getComponentBefore((Container) comp, aComponent, found);
                if (retval != null) {
                    return retval;
                }
            } else if (found.value) {
                if (accept(comp)) {
                    return comp;
                }
            }
        }

        if (found.value) {
            if (accept(aContainer)) {
                return aContainer;
            }
        } else if (aContainer.equals(aComponent)) {
            found.value = true;
        }

        return null;
    }

    public Component getFirstComponent(Container focusCycleRoot) {
        if (focusCycleRoot == null) {
            throw new IllegalArgumentException("focusCycleRoot cannot be null");
        }

        synchronized (focusCycleRoot.getTreeLock()) {
            if (!(focusCycleRoot.isVisible() && focusCycleRoot.isDisplayable())) {
                return null;
            }

            if (accept(focusCycleRoot)) {
                return focusCycleRoot;
            }

            Component[] componentArray = getSortedContainerComponents(focusCycleRoot);

            for (Component comp : componentArray) {
                if ((comp instanceof Container) && !((Container) comp).isFocusCycleRoot() && !(comp instanceof JComponent)) {
                    Component retval = getFirstComponent((Container) comp);
                    if (retval != null) {
                        return retval;
                    }
                } else if (accept(comp)) {
                    return comp;
                }
            }
        }

        return null;
    }

    public Component getLastComponent(Container focusCycleRoot) {
        if (focusCycleRoot == null) {
            throw new IllegalArgumentException("focusCycleRoot cannot be null");
        }

        synchronized (focusCycleRoot.getTreeLock()) {
            if (!(focusCycleRoot.isVisible() && focusCycleRoot.isDisplayable())) {
                return null;
            }

            Component[] componentArray = getSortedContainerComponents(focusCycleRoot);

            for (Component comp : componentArray) {
                if ((comp instanceof Container) && !((Container) comp).isFocusCycleRoot() && !(comp instanceof JComponent)) {
                    Component retval = getLastComponent((Container) comp);
                    if (retval != null) {
                        return retval;
                    }
                } else if (accept(comp)) {
                    return comp;
                }
            }

            if (accept(focusCycleRoot)) {
                return focusCycleRoot;
            }
        }

        return null;
    }

    public Component getDefaultComponent(Container focusCycleRoot) {
        Component component = getFirstComponent(focusCycleRoot);
        if (!component.hasFocus()) component.requestFocus();
        return component;
    }

    public boolean getImplicitDownCycleTraversal() {
        return true;
    }

    protected boolean accept(Component aComponent) {
        if (!(aComponent.isVisible() && aComponent.isDisplayable() && aComponent.isEnabled())) {
            return false;
        }

        if (aComponent instanceof JPanel || aComponent instanceof JLabel) {
            return false;
        }

        // Verify that the Component is recursively enabled. Disabling a
        // heavyweight Container disables its children, whereas disabling
        // a lightweight Container does not.
        if (!(aComponent instanceof Window)) {
            for (Container enableTest = aComponent.getParent(); enableTest != null; enableTest = enableTest.getParent()) {
                if (!(enableTest.isEnabled() || enableTest.isLightweight())) {
                    return false;
                }
                if (enableTest instanceof Window) {
                    break;
                }
            }
        }

        return aComponent.isFocusable();
    }


    // VERY simple sort that puts help buttons last in line, based on their name
    private Component[] getSortedContainerComponents(Container container) {
        java.util.List<Component> v = new ArrayList<Component>(container.getComponentCount() * 2);

        populateVectors(container, v);

        // Remove all action components and put them at the end
        if (root instanceof AbstractActionsPanel) {
            JComponent[] actionComponents = ((AbstractActionsPanel) root).getActionButtons();
            if (actionComponents != null) {
                for (JComponent actionComponent : actionComponents) {
                    v.remove(actionComponent);
                }
                v.addAll(Arrays.asList(actionComponents));
            }
        }

        // Remove close button and add it to the end
        if (root.closeButton != null) {
            v.remove(root.closeButton);
            v.add(root.closeButton);
        }

        return v.toArray(new Component[v.size()]);
    }

    private void populateVectors(Container container, java.util.List<Component> components) {
        int count = container.getComponentCount();
        for (int i = 0; i < count; i++) {
            Component c = container.getComponent(i);

            if (!(c.isVisible() && c.isDisplayable() && c.isEnabled())) {
                continue;
            }

            if (c instanceof JPanel && !((Container) c).isFocusCycleRoot()) {
                populateVectors((JPanel) c, components);
            } else if (c instanceof JToolBar) {
                populateVectors((JToolBar) c, components);
            } else if (!(c instanceof JLabel)) {
                components.add(c);
            }
        }
    }

}