package org.idempiere.zk.goldenbee.theme.panel;

import java.util.Properties;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.panel.RolePanel;
import org.adempiere.webui.theme.ITheme;
import org.adempiere.webui.theme.ThemeManager;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.adempiere.webui.window.LoginWindow;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.adempiere.webui.component.Label;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zul.Combobox;

public class GoldenBeeRolePanel extends RolePanel {

    public GoldenBeeRolePanel(Properties ctx, LoginWindow loginWindow, String userName, boolean show,
            KeyNamePair[] clientsKNPairs, boolean isClientDefined) {
        super(ctx, loginWindow, userName, show, clientsKNPairs, isClientDefined);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void createUI() {

        // Main container với background gradient
        Div mainContainer = new Div();
        mainContainer.setSclass("goldenbee-role-main");

        // Card container
        Div cardContainer = new Div();
        cardContainer.setSclass("goldenbee-role-card-container");

        // Card chính - Split layout
        Div card = new Div();
        card.setSclass("goldenbee-role-card");

        // Left Section - Welcome (màu vàng đen)
        Div leftSection = createLeftSection();
        card.appendChild(leftSection);

        // Right Section - Form (màu trắng) - Giữ nguyên các field hiện có
        Div rightSection = createRightSection();
        card.appendChild(rightSection);

        cardContainer.appendChild(card);
        mainContainer.appendChild(cardContainer);
        this.appendChild(mainContainer);

        // Gọi languageChanged
        callLanguageChanged();
    }

    private Div createLeftSection() {
        Div leftSection = new Div();
        leftSection.setSclass("goldenbee-role-left-section");

        // Logo
        Image logo = new Image();
        logo.setSrc(ThemeManager.getLargeLogo());
        logo.setSclass("goldenbee-role-left-logo");
        leftSection.appendChild(logo);

        // Welcome title
        Label welcomeTitle = new Label("Chào mừng trở lại!");
        welcomeTitle.setSclass("goldenbee-role-welcome-title");
        leftSection.appendChild(welcomeTitle);

        // Welcome message
        Label welcomeMsg = new Label("Chọn vai trò để tiếp tục công việc");
        welcomeMsg.setSclass("goldenbee-role-welcome-msg");
        leftSection.appendChild(welcomeMsg);

        return leftSection;
    }

    private Div createRightSection() {
        Div rightSection = new Div();
        rightSection.setSclass("goldenbee-role-right-section");

        // Form container - Giữ nguyên cấu trúc field như hiện tại
        Div formContainer = new Div();
        formContainer.setSclass("goldenbee-role-form-container");

        // Client field
        createFormField(formContainer, lblClient, lstClient, "client");

        // Role field
        createFormField(formContainer, lblRole, lstRole, "role");

        // Organisation field
        createFormField(formContainer, lblOrganisation, lstOrganisation, "org");

        // Warehouse field
        createFormField(formContainer, lblWarehouse, lstWarehouse, "warehouse");

        // Language field
        createFormField(formContainer, lblLanguage, lstLanguage, "language");

        // Date field
        createDateField(formContainer);

        // Default label
        createDefaultLabel(formContainer);

        // Buttons
        Div buttonsDiv = createButtons();
        formContainer.appendChild(buttonsDiv);

        rightSection.appendChild(formContainer);
        return rightSection;
    }

    private void createFormField(Div container, Label label, Component field, String fieldType) {
        Div fieldContainer = new Div();
        fieldContainer.setSclass("goldenbee-role-field-container");

        // Label
        if (label != null) {
            label.setSclass("goldenbee-role-field-label");
            fieldContainer.appendChild(label);
        }

        // Input
        Div inputContainer = new Div();
        inputContainer.setSclass("goldenbee-role-input-container");

        if (field instanceof Combobox) {
            ((Combobox) field).setSclass("goldenbee-role-select");
        } else if (field instanceof org.adempiere.webui.component.Listbox) {
            ((org.adempiere.webui.component.Listbox) field).setSclass("goldenbee-role-select");
        }

        if (field instanceof HtmlBasedComponent) {
            ZKUpdateUtil.setWidth((HtmlBasedComponent) field, "100%");
        }

        inputContainer.appendChild(field);
        fieldContainer.appendChild(inputContainer);
        container.appendChild(fieldContainer);
    }

    private void createDateField(Div container) {
        Div fieldContainer = new Div();
        fieldContainer.setSclass("goldenbee-role-field-container");

        // Label
        lblDate.setSclass("goldenbee-role-field-label");
        fieldContainer.appendChild(lblDate);

        // Input
        Div inputContainer = new Div();
        inputContainer.setSclass("goldenbee-role-input-container");

        Component dateComponent = lstDate.getComponent();
        if (dateComponent instanceof HtmlBasedComponent) {
            ((HtmlBasedComponent) dateComponent).setSclass("goldenbee-role-date");
            ZKUpdateUtil.setWidth((HtmlBasedComponent) dateComponent, "100%");
        }

        inputContainer.appendChild(dateComponent);
        fieldContainer.appendChild(inputContainer);
        container.appendChild(fieldContainer);
    }

    private void createDefaultLabel(Div container) {
        Div defaultContainer = new Div();
        defaultContainer.setSclass("goldenbee-role-default-container");

        Div linkContainer = new Div();
        linkContainer.setSclass("goldenbee-role-default-link");
        linkContainer.appendChild(lblDef);

        defaultContainer.appendChild(linkContainer);
        container.appendChild(defaultContainer);
    }

    private Div createButtons() {
        Div buttonContainer = new Div();
        buttonContainer.setSclass("goldenbee-role-button-container");

        pnlButtons = new ConfirmPanel(true, false, false, false, false, false, true);
        pnlButtons.addActionListener(this);

        // OK button
        Button okBtn = pnlButtons.getButton(ConfirmPanel.A_OK);
        okBtn.setSclass("goldenbee-role-btn primary");
        okBtn.setWidgetListener("onClick", "zAu.cmd0.showBusy(null)");
        okBtn.addCallback(AbstractComponent.AFTER_PAGE_DETACHED,
                t -> ((AbstractComponent) t).setWidgetListener("onClick", null));

        // Help button
        Button helpButton = pnlButtons.createButton(ConfirmPanel.A_HELP);
        helpButton.addEventListener(Events.ON_CLICK, this);
        helpButton.setSclass("goldenbee-role-btn secondary");
        pnlButtons.addComponentsRight((org.adempiere.webui.component.Button) helpButton);

        // Cancel button
        Button cancelBtn = pnlButtons.getButton(ConfirmPanel.A_CANCEL);
        if (cancelBtn != null) {
            cancelBtn.setSclass("goldenbee-role-btn secondary");
        }

        buttonContainer.appendChild(pnlButtons);
        return buttonContainer;
    }

    private void callLanguageChanged() {
        try {
            if (validLstLanguage != null) {
                java.lang.reflect.Method method = getClass().getSuperclass().getDeclaredMethod("languageChanged",
                        String.class);
                method.setAccessible(true);
                method.invoke(this, validLstLanguage);
            }
        } catch (Exception e) {
            // Fallback
            System.out.println("Could not call languageChanged: " + e.getMessage());
        }
    }
}