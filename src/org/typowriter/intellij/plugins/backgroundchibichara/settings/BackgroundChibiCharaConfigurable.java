package org.typowriter.intellij.plugins.backgroundchibichara.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import org.typowriter.intellij.plugins.backgroundchibichara.BackgroundChibiCharaApplicationSettings;

import javax.swing.*;

public class BackgroundChibiCharaConfigurable implements Configurable {// todo searchableconfigurable
    private BackgroundChibiCharaApplicationSettings myBackgroundChibiApplicationSeetings;
    @Nullable
    private BackgroundChibiCharaSettingsForm form = null;

    BackgroundChibiCharaConfigurable(BackgroundChibiCharaApplicationSettings settings) {
        myBackgroundChibiApplicationSeetings = settings;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Settings.BackgroundChibiChara";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return getForm().getComponent();
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        myBackgroundChibiApplicationSeetings.setSettings(getForm().getSettings());
    }

    @Override
    public void reset() {
        getForm().setSettings(myBackgroundChibiApplicationSeetings.getSettings());
    }

    @Override
    public void disposeUIResources() {
        form = null;
    }

    @Nullable
    public BackgroundChibiCharaSettingsForm getForm() {
        if (form == null) {
            form = new BackgroundChibiCharaSettingsForm();
        }
        return form;
    }
}
