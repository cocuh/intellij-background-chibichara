package org.typowriter.intellij.plugins.backgroundchibichara;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackgroundChibiCharaSettingsForm {
    private JList jList;
    private JRadioButton alignLeft;
    private JRadioButton alignCenter;
    private JRadioButton alignRight;
    private JTextField fieldSpacing;
    private JTextField fieldMargin;
    private JButton addButton;
    private JPanel mainPanel;
    private JTextField fieldAlpha;
    private JButton removeButton;
    private ButtonGroup alignGroup;

    public BackgroundChibiCharaSettingsForm() {
        fieldSpacing.setInputVerifier(new IntegerInputVerifier());
        fieldMargin.setInputVerifier(new IntegerInputVerifier());
        fieldAlpha.setInputVerifier(new DoubleInputVerifier());
        initAlignRadioButtons();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = intiFileChooser();
                int returnVal = fileChooser.showDialog(getComponent(), null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File[] files = fileChooser.getSelectedFiles();
                    List<String> filepathList = new ArrayList<String>();
                    for (File file : files) {
                        filepathList.add(file.getAbsolutePath());
                        Notifications.Bus.notify(new Notification("", "", file.getAbsolutePath(), NotificationType.ERROR));
                    }
                    addFilepathList(filepathList);
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                removeFilePathIndexes(jList.getSelectedIndices());
            }
        });
        addButton.setBorder(null);
        removeButton.setBorder(null);
    }

    private JFileChooser intiFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        FileFilter filter = new FileNameExtensionFilter("image files", ImageIO.getReaderFileSuffixes());
        fileChooser.setFileFilter(filter);
        return fileChooser;
    }

    private void addFilepathList(List<String> filepathList) {
        for (String filepath : filepathList) {
            ((DefaultListModel) jList.getModel()).addElement(filepath);
        }
    }

    private void removeFilePathIndexes(int[] indexes) {
        Arrays.sort(indexes);
        for (int idx = indexes.length - 1; idx >= 0; idx--) {
            if (idx >= 0) {
                ((DefaultListModel) jList.getModel()).remove(indexes[idx]);
            }
        }
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

    public void setSettings(BackgroundChibiCharaSettings settings) {
        DefaultListModel listModel = new DefaultListModel();
        for (String path : settings.filepathList) {
            listModel.addElement(path);
        }
        jList.setModel(listModel);

        getAlignButton(settings.align).setSelected(true);
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
