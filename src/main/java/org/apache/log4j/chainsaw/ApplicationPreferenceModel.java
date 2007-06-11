/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.log4j.chainsaw;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * Encapsulates the Chainsaw Application wide properties
 *
 * @author Paul Smith <psmith@apache.org>
 *
 */
public class ApplicationPreferenceModel {

    private boolean showNoReceiverWarning = true ;
    private boolean statusBar = true;
    private boolean toolbar = true;
    private boolean receivers = true;
    private boolean confirmExit = true;
    private boolean showSplash = true;
    private String lookAndFeelClassName = "";
    private int toolTipDisplayMillis = 4000;
    private int cyclicBufferSize = 5000;
    private String lastUsedVersion = "";
    private int responsiveness = 3;
    
    private String identifierExpression = "PROP.hostname - PROP.application"; 

    private transient final PropertyChangeSupport propertySupport =
        new PropertyChangeSupport(this);
    
    private int tabPlacement = 3;
    
    /**
     * If not 'empty', this property will be used as the URL to load log4j configuration at startup
     */
    private String configurationURL="";
	  
    /**
     *    this means for Receivers that require optional jars that can't be delivered
     *    by the Web start classloader, we need to be able to remove the SecurityManager in place
     */
    private boolean okToRemoveSecurityManager = false;

    /**
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    /**
     * @param propertyName
     * @param listener
     */
    public void addPropertyChangeListener(String propertyName,
        PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * @param propertyName
     * @param oldValue
     * @param newValue
     */
    private void firePropertyChange(String propertyName, boolean oldValue,
        boolean newValue) {
        propertySupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * @param propertyName
     * @param oldValue
     * @param newValue
     */
    private void firePropertyChange(String propertyName, int oldValue,
        int newValue) {
        propertySupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * @param propertyName
     * @param oldValue
     * @param newValue
     */
    private void firePropertyChange(String propertyName, Object oldValue,
        Object newValue) {
        propertySupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * @param propertyName
     * @return listeners flag
     */
    public boolean hasListeners(String propertyName) {
        return propertySupport.hasListeners(propertyName);
    }

    /**
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    /**
     * @return Returns the showNoReceiverWarning.
     */
    public final boolean isShowNoReceiverWarning() {

        return showNoReceiverWarning;
    }
    
    public final String getIdentifierExpression() {
        return identifierExpression;
    }

    public final void setCyclicBufferSize(int newCyclicBufferSize) {
        int oldCyclicBufferSize = cyclicBufferSize;
        cyclicBufferSize = newCyclicBufferSize;
        firePropertyChange("cyclicBufferSize", oldCyclicBufferSize, newCyclicBufferSize);
    }
    
    public final int getCyclicBufferSize() {
        return cyclicBufferSize;
    }

    public final void setToolTipDisplayMillis(int newToolTipDisplayMillis) {
        int oldToolTipDisplayMillis = toolTipDisplayMillis;
        toolTipDisplayMillis = newToolTipDisplayMillis;
        firePropertyChange("toolTipDisplayMillis", oldToolTipDisplayMillis, newToolTipDisplayMillis);
    }
    
    public final int getToolTipDisplayMillis() {
        return toolTipDisplayMillis;
    }

    public final void setIdentifierExpression(String newIdentifierExpression) {
        String oldIdentifierExpression=identifierExpression;
        this.identifierExpression = newIdentifierExpression;
        firePropertyChange("identifierExpression", oldIdentifierExpression, newIdentifierExpression);
    }

    /**
     * @param newShowNoReceiverWarning The showNoReceiverWarning to set.
     */
    public final void setShowNoReceiverWarning(boolean newShowNoReceiverWarning) {
        boolean oldShowNoReceiverWarning=showNoReceiverWarning;
        this.showNoReceiverWarning = newShowNoReceiverWarning;
        firePropertyChange("showNoReceiverWarning", oldShowNoReceiverWarning, newShowNoReceiverWarning);
    }


    /**
     * Takes another model and copies all the values into this model
     * @param model
     */
    public void apply(ApplicationPreferenceModel model)
    {
      setIdentifierExpression(model.getIdentifierExpression());
      setShowNoReceiverWarning(model.isShowNoReceiverWarning());
      setResponsiveness(model.getResponsiveness());
      setTabPlacement(model.getTabPlacement());
      setStatusBar(model.isStatusBar());
      setToolbar(model.isToolbar());
      setReceivers(model.isReceivers());
      setLookAndFeelClassName(model.getLookAndFeelClassName());
      setConfirmExit(model.isConfirmExit());
      setShowSplash(model.isShowSplash());
      setToolTipDisplayMillis(model.getToolTipDisplayMillis());
      setCyclicBufferSize(model.getCyclicBufferSize());
      setConfigurationURL(model.getConfigurationURL());
      setLastUsedVersion(model.getLastUsedVersion());
      setOkToRemoveSecurityManager(model.isOkToRemoveSecurityManager());
    }
    
    /**
     * @return Returns the responsiveness.
     */
    public final int getResponsiveness()
    {
      return responsiveness;
    }
    /**
     * @param newValue The responsiveness to set.
     */
    public final void setResponsiveness(int newValue)
    {
      int oldvalue = responsiveness;
      
      if (newValue >= 1000) {
        responsiveness = (newValue - 750) / 1000;
      } else {
        responsiveness = newValue;
      }
      firePropertyChange("responsiveness", oldvalue, responsiveness);
    }

    /**
     * @param i
     */
    public void setTabPlacement(int i) {
      int oldValue = this.tabPlacement;
       this.tabPlacement = i;
       firePropertyChange("tabPlacement",oldValue,this.tabPlacement);
    }
    /**
     * @return Returns the tabPlacement.
     */
    public final int getTabPlacement() {
      return tabPlacement;
    }

    /**
     * @return Returns the statusBar.
     */
    public final boolean isStatusBar() {
      return statusBar;
    }

    /**
     * @param statusBar The statusBar to set.
     */
    public final void setStatusBar(boolean statusBar) {
      boolean oldValue = this.statusBar;
      this.statusBar = statusBar;
      firePropertyChange("statusBar", oldValue, this.statusBar);
    }

    /**
     * @return Returns the receivers.
     */
    public final boolean isReceivers()
    {
      return receivers;
    }
    /**
     * @param receivers The receivers to set.
     */
    public final void setReceivers(boolean receivers)
    {
      boolean oldValue = this.receivers;
      this.receivers = receivers;
      firePropertyChange("receivers", oldValue, this.receivers);
    }
    /**
     * @return Returns the toolbar.
     */
    public final boolean isToolbar()
    {
      return toolbar;
    }
    /**
     * @param toolbar The toolbar to set.
     */
    public final void setToolbar(boolean toolbar)
    {
      boolean oldValue = this.toolbar;
      this.toolbar = toolbar;
      firePropertyChange("toolbar", oldValue, this.toolbar);
    }
    /**
     * @return Returns the lookAndFeelClassName.
     */
    public final String getLookAndFeelClassName() {
      return lookAndFeelClassName;
    }

    /**
     * @param lookAndFeelClassName The lookAndFeelClassName to set.
     */
    public final void setLookAndFeelClassName(String lookAndFeelClassName) {
      String oldValue = this.lookAndFeelClassName;
      this.lookAndFeelClassName = lookAndFeelClassName;
      firePropertyChange("lookAndFeelClassName", oldValue, this.lookAndFeelClassName);
    }

    /**
     * @return Returns the confirmExit.
     */
    public final boolean isConfirmExit() {
      return confirmExit;
    }

    /**
     * @param confirmExit The confirmExit to set.
     */
    public final void setConfirmExit(boolean confirmExit) {
      boolean oldValue = this.confirmExit;
      this.confirmExit = confirmExit;
      firePropertyChange("confirmExit", oldValue, this.confirmExit);
    }

    /**
     * @return Returns the showSplash.
     */
    public final boolean isShowSplash() {
      return showSplash;
    }
    /**
     * @param showSplash The showSplash to set.
     */
    public final void setShowSplash(boolean showSplash) {
      boolean oldValue = this.showSplash;
      this.showSplash = showSplash;
      firePropertyChange("showSplash", oldValue,this.showSplash);
    }
    /**
     * @return Returns the configurationURL.
     */
    public final String getConfigurationURL()
    {
        return this.configurationURL;
    }
    /**
     * @param configurationURL The configurationURL to set.
     */
    public final void setConfigurationURL(String configurationURL)
    {
        Object oldValue = this.configurationURL;
        this.configurationURL = configurationURL;
        firePropertyChange("configurationURL", oldValue, this.configurationURL);
    }
    /**
     * @return Returns the lastUsedVersion.
     */
    public final String getLastUsedVersion()
    {
        return this.lastUsedVersion;
    }
    /**
     * @param lastUsedVersion The lastUsedVersion to set.
     */
    public final void setLastUsedVersion(String lastUsedVersion)
    {
        String oldValue = this.lastUsedVersion;
        this.lastUsedVersion = lastUsedVersion;
        firePropertyChange("lastUsedVersion", oldValue, this.lastUsedVersion);
    }

	/**
	 * @return ok to remove security manager flag
	 */
	public final boolean isOkToRemoveSecurityManager() {
		return this.okToRemoveSecurityManager;
	}
	/**
	 * @param okToRemoveSecurityManager The okToRemoveSecurityManager to set.
	 */
	public final void setOkToRemoveSecurityManager(boolean okToRemoveSecurityManager) {
		boolean oldValue = this.okToRemoveSecurityManager;
        this.okToRemoveSecurityManager = okToRemoveSecurityManager;
        firePropertyChange("okToRemoveSecurityManager", oldValue, this.okToRemoveSecurityManager);
	}
}