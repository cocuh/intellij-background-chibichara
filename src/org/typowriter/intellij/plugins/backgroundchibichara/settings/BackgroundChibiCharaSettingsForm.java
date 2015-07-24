package org.typowriter.intellij.plugins.backgroundchibichara.settings;

import org.typowriter.intellij.plugins.backgroundchibichara.BackgroundImageBorder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BackgroundChibiCharaSettingsForm implements BackgroundChibiCharaSettings.Holder {
    private JList jList;
    private JRadioButton alignLeft;
    private JRadioButton alignCenter;
    private JRadioButton alignRight;
    private JTextField fieldSpacing;
    private JTextField fieldMargin;
    private JButton addButton;
    private JPanel mainPanel;
    private JTextField fieldAlpha;
    private ButtonGroup alignGroup;

    public BackgroundChibiCharaSettingsForm() {
        fieldSpacing.setInputVerifier(new IntegerInputVerifier());
        fieldMargin.setInputVerifier(new IntegerInputVerifier());
        fieldAlpha.setInputVerifier(new DoubleInputVerifier());
        initAlignRadioButtons();
    }

    private void createUIComponents() {
    }

    private void initAlignRadioButtons() {
    }

    public JRadioButton getAlignButton(BackgroundImageBorder.Align align) {
        switch (align) {
            case LEFT:
                return alignLeft;
            case CENTER:
                return alignCenter;
            case RIGHT:
                return alignRight;
            default:
                return alignLeft;
        }
    }

    public BackgroundImageBorder.Align getAlign() {
        if (alignLeft.isSelected()) {
            return BackgroundImageBorder.Align.LEFT;
        } else if (alignCenter.isSelected()) {
            return BackgroundImageBorder.Align.CENTER;
        } else if (alignRight.isSelected()) {
            return BackgroundImageBorder.Align.RIGHT;
        } else {
            return BackgroundImageBorder.Align.LEFT;
        }
    }

    @Override
    public BackgroundChibiCharaSettings getSettings() {
        List<String> filepathList = getFilepathList();
        return new BackgroundChibiCharaSettings(
                filepathList,
                getAlign(),
                Integer.parseInt(fieldMargin.getText()),
                Integer.parseInt(fieldSpacing.getText()),
                Double.parseDouble(fieldAlpha.getText())
        );
    }

    @Override
    public void setSettings(BackgroundChibiCharaSettings settings) {
        getAlignButton(settings.getAlign()).setSelected(true);
        fieldMargin.setText(String.valueOf(settings.margin));
        fieldSpacing.setText(String.valueOf(settings.spacing));
        fieldAlpha.setText(String.valueOf(settings.alpha));
    }

    private List<String> getFilepathList() {
        List<String> filepathList = new ArrayList<String>();
        ListModel model = jList.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            filepathList.add((String) model.getElementAt(i));
        }
        return filepathList;
    }

    public JComponent getComponent() {
        return mainPanel;
    }


    private class IntegerInputVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent jComponent) {
            JTextField field = (JTextField) jComponent;
            boolean verified = false;
            try {
                Integer.parseInt(field.getText());
                verified = true;
            } catch (NumberFormatException e) {
                UIManager.getLookAndFeel().provideErrorFeedback(field);
            }
            return verified;
        }
    }

    private class DoubleInputVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent jComponent) {
            JTextField field = (JTextField) jComponent;
            boolean verified = false;
            try {
                Double.parseDouble(field.getText());
                verified = true;
            } catch (NumberFormatException e) {
                UIManager.getLookAndFeel().provideErrorFeedback(field);
            }
            return verified;
        }
    }
}
