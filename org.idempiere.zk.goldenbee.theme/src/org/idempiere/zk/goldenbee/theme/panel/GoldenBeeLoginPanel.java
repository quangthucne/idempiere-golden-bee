package org.idempiere.zk.goldenbee.theme.panel;

import java.util.List;
import java.util.Properties;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.panel.LoginPanel;
import org.adempiere.webui.theme.ITheme;
import org.adempiere.webui.theme.ThemeManager;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.adempiere.webui.window.LoginWindow;
import org.compiere.model.MSSOPrincipalConfig;
import org.compiere.model.MSysConfig;
import org.compiere.model.MSystem;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.A;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.adempiere.webui.component.Label;
import org.zkoss.zul.Separator;
import org.adempiere.webui.component.Textbox;
import org.zkoss.zul.Vbox;

public class GoldenBeeLoginPanel extends LoginPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public GoldenBeeLoginPanel(Properties ctx, LoginWindow loginWindow) {
        super(ctx, loginWindow);
    }

    @Override
    protected void createUI() {

        lblLogin = new Label("Đăng nhập");
        lblLogin.setSclass("goldenbee-form-title");

        // Main container với background màu vàng chủ đạo
        Div mainContainer = new Div();
        mainContainer.setSclass("goldenbee-login-container");

        // Card login chính với layout hiện đại
        Div loginCard = new Div();
        loginCard.setSclass("goldenbee-login-card");

        // Left section - Branding
        Div leftSection = createLeftSection();
        loginCard.appendChild(leftSection);

        // Right section - Login Form
        Div rightSection = createRightSection();
        loginCard.appendChild(rightSection);

        // SSO Integration - CẦN THÊM LOGIC ẨN/HIỆN
        boolean isShowLoginPage = handleSSOIntegration(mainContainer);

        // THÊM DÒNG NÀY: Cập nhật visibility dựa trên SSO
        updateVisibilityBasedOnSSO(isShowLoginPage);

        mainContainer.appendChild(loginCard);

        this.appendChild(mainContainer);
    }

    private Div createLeftSection() {
        Div leftSection = new Div();
        leftSection.setSclass("goldenbee-left-section");

        // Logo Container
        Div logoContainer = new Div();
        logoContainer.setSclass("goldenbee-logo-container");
        Image logo = new Image();
        logo.setSrc(ThemeManager.getLargeLogo());
        logo.setSclass("goldenbee-logo");
        logoContainer.appendChild(logo);

        // Welcome Text
        Div welcomeContainer = new Div();
        welcomeContainer.setSclass("goldenbee-welcome-container");
        Label welcomeTitle = new Label("Chào mừng trở lại!");
        welcomeTitle.setSclass("goldenbee-welcome-title");
        Label welcomeSubtitle = new Label(" Đăng nhập để tiếp tục công việc của bạn");
        welcomeSubtitle.setSclass("goldenbee-welcome-subtitle");

        welcomeContainer.appendChild(welcomeTitle);
        welcomeContainer.appendChild(welcomeSubtitle);

        leftSection.appendChild(logoContainer);
        leftSection.appendChild(welcomeContainer);

        return leftSection;
    }

    private Div createRightSection() {
        Div rightSection = new Div();
        rightSection.setSclass("goldenbee-right-section");

        // Form Container
        Div formContainer = new Div();
        formContainer.setSclass("goldenbee-form-container");

        // User ID field với layout mới
        Div userField = createModernFormField("Tên đăng nhập", "user", "user-icon");
        txtUserId = (org.adempiere.webui.component.Textbox) new Textbox();
        txtUserId.setSclass("goldenbee-input-field");
        txtUserId.setPlaceholder("Nhập tên đăng nhập");
        ((Div) userField.getLastChild()).appendChild(txtUserId);
        formContainer.appendChild(userField);

        // Password field
        Div pwdField = createModernFormField("Mật khẩu", "password", "lock-icon");
        txtPassword = (org.adempiere.webui.component.Textbox) new Textbox();
        txtPassword.setType("password");
        txtPassword.setSclass("goldenbee-input-field");
        txtPassword.setPlaceholder("Nhập mật khẩu");
        ((Div) pwdField.getLastChild()).appendChild(txtPassword);
        formContainer.appendChild(pwdField);

        // Language field
        Div langField = createComboboxContainer("Ngôn ngữ", "language", "language-icon");
        formContainer.appendChild(langField);

        // Options Container
        Div optionsContainer = new Div();
        optionsContainer.setSclass("goldenbee-options-container");

        // Left side - Checkboxes
        Hbox checkboxesContainer = new Hbox();
        checkboxesContainer.setSclass("goldenbee-checkboxes-container");

        // Select Role checkbox
        Div roleCheckboxContainer = new Div();
        roleCheckboxContainer.setSclass("goldenbee-role-checkbox-container");
        chkSelectRole = new Checkbox(Msg.getMsg(Env.getCtx(), "SelectRole"));
        chkSelectRole.setSclass("goldenbee-checkbox");
        roleCheckboxContainer.appendChild(chkSelectRole);

        // Remember Me checkbox
        Div rememberMeCheckboxContainer = new Div();
        rememberMeCheckboxContainer.setSclass("goldenbee-rememberme-checkbox-container");
        if (MSystem.isZKRememberUserAllowed()) {
            chkRememberMe = new Checkbox(Msg.getMsg(Env.getCtx(), "RememberMe"));
            chkRememberMe.setSclass("goldenbee-checkbox");
            rememberMeCheckboxContainer.appendChild(chkRememberMe);
        }

        // Right side - Reset Password
        Div resetContainer = new Div();
        resetContainer.setSclass("goldenbee-reset-container");
        if (MSysConfig.getBooleanValue(MSysConfig.LOGIN_SHOW_RESETPASSWORD, true)) {
            btnResetPassword = new A(Msg.getMsg(Env.getCtx(), "ResetPassword"));
            btnResetPassword.setSclass("goldenbee-reset-link");
            btnResetPassword.addEventListener(Events.ON_CLICK, this);
            resetContainer.appendChild(btnResetPassword);
        }

        optionsContainer.appendChild(checkboxesContainer);
        optionsContainer.appendChild(roleCheckboxContainer);
        optionsContainer.appendChild(rememberMeCheckboxContainer);
        optionsContainer.appendChild(resetContainer);
        formContainer.appendChild(optionsContainer);

        // SSO Integration
        boolean isShowLoginPage = handleSSOIntegration(formContainer);

        // Buttons Container
        Div buttonsContainer = createButtonsContainer();
        formContainer.appendChild(buttonsContainer);

        rightSection.appendChild(formContainer);

        return rightSection;
    }

    private Div createModernFormField(String labelText, String fieldType, String iconClass) {
        Div fieldContainer = new Div();
        fieldContainer.setSclass("goldenbee-field-container");

        // Field header với label
        Div fieldHeader = new Div();
        fieldHeader.setSclass("goldenbee-field-header");

        Label label = new Label(labelText);
        label.setSclass("goldenbee-field-label");
        fieldHeader.appendChild(label);

        // Input container với icon
        Div inputContainer = new Div();
        inputContainer.setSclass("goldenbee-input-container " + iconClass);

        fieldContainer.appendChild(fieldHeader);
        fieldContainer.appendChild(inputContainer);

        return fieldContainer;
    }

    private Div createComboboxContainer(String labelText, String fieldType, String iconClass) {
        Div comboContainer = new Div();
        comboContainer.setSclass("goldenbee-combobox-container");

        Div comboHeader = new Div();
        comboHeader.setSclass("goldenbee-field-header");
        lblLanguage = new Label(labelText);
        lblLanguage.setSclass("goldenbee-field-label");
        comboHeader.appendChild(lblLanguage);

        lstLanguage.setSclass("goldenbee-input-field goldenbee-combobox" + iconClass);
        ZKUpdateUtil.setHflex(lstLanguage, "1");
        comboContainer.appendChild(lstLanguage);

        return comboContainer;
    }

    private Div createButtonsContainer() {
        Div buttonsContainer = new Div();
        buttonsContainer.setSclass("goldenbee-buttons-container");

        pnlButtons = new ConfirmPanel(false, false, false, false, false, false, true);
        pnlButtons.addActionListener(this);

        Button okBtn = pnlButtons.getButton(ConfirmPanel.A_OK);
        okBtn.setSclass("goldenbee-login-button primary");
        okBtn.setLabel("Đăng nhập");
        okBtn.setWidgetListener("onClick", "zAu.cmd0.showBusy(null)");
        okBtn.addCallback(AbstractComponent.AFTER_PAGE_DETACHED,
                t -> ((AbstractComponent) t).setWidgetListener("onClick", null));

        // Help button
        Button helpButton = pnlButtons.createButton(ConfirmPanel.A_HELP);
        helpButton.addEventListener(Events.ON_CLICK, this);
        helpButton.setSclass("goldenbee-login-button secondary");
        helpButton.setLabel("Trợ giúp");
        pnlButtons.addComponentsRight((org.adempiere.webui.component.Button) helpButton);

        buttonsContainer.appendChild(pnlButtons);
        return buttonsContainer;
    }

    private boolean handleSSOIntegration(Div formContainer) {
        boolean isShowLoginPage = MSysConfig.getBooleanValue(MSysConfig.SSO_SHOW_LOGINPAGE, true);
        boolean isSSOEnable = MSysConfig.getBooleanValue(MSysConfig.ENABLE_SSO, false);

        if (isSSOEnable) {
            List<MSSOPrincipalConfig> configs = MSSOPrincipalConfig.getAllSSOPrincipalConfig();
            if (configs != null && !configs.isEmpty()) {
                Div ssoContainer = new Div();
                ssoContainer.setSclass("goldenbee-sso-container");

                // Separator
                Div separator = new Div();
                separator.setSclass("goldenbee-sso-separator");
                Label separatorText = new Label("Hoặc đăng nhập với");
                separatorText.setSclass("goldenbee-sso-separator-text");
                separator.appendChild(separatorText);
                ssoContainer.appendChild(separator);

                // SSO buttons
                Div ssoButtonsContainer = new Div();
                ssoButtonsContainer.setSclass("goldenbee-sso-buttons");

                for (MSSOPrincipalConfig config : configs) {
                    Button ssoButton = createSSOLoginButton(config);
                    ssoButtonsContainer.appendChild(ssoButton);
                }

                ssoContainer.appendChild(ssoButtonsContainer);
                formContainer.appendChild(ssoContainer);
            }
        }

        return isShowLoginPage;
    }

    private Button createSSOLoginButton(MSSOPrincipalConfig config) {
        Button button = new Button(config.getName());
        button.setSclass("goldenbee-sso-button");
        button.addEventListener(Events.ON_CLICK, e -> {
            Clients.evalJavaScript("window.location.href='" + config.getSSO_ApplicationDiscoveryURI() + "'");
        });
        return button;
    }

    private void updateVisibilityBasedOnSSO(boolean isShowLoginPage) {
        // Lấy giá trị cấu hình SSO
        boolean isSSOEnable = MSysConfig.getBooleanValue(MSysConfig.ENABLE_SSO, false);

        if (isSSOEnable) {
            List<MSSOPrincipalConfig> configs = MSSOPrincipalConfig.getAllSSOPrincipalConfig();
            if (configs != null && !configs.isEmpty()) {
                // Ẩn các field truyền thống nếu không show login page
                boolean showTraditionalLogin = isShowLoginPage;

                // Ẩn/hiện các component
                if (lblLanguage != null)
                    lblLanguage.setVisible(showTraditionalLogin);
                if (lstLanguage != null)
                    lstLanguage.setVisible(showTraditionalLogin);
                if (txtUserId != null)
                    txtUserId.setVisible(showTraditionalLogin);
                if (txtPassword != null)
                    txtPassword.setVisible(showTraditionalLogin);
                if (chkRememberMe != null)
                    chkRememberMe.setVisible(showTraditionalLogin);
                if (chkSelectRole != null)
                    chkSelectRole.setVisible(showTraditionalLogin);

                // Ẩn/hiện nút OK
                if (pnlButtons != null) {
                    Button okBtn = pnlButtons.getButton(ConfirmPanel.A_OK);
                    if (okBtn != null)
                        okBtn.setVisible(showTraditionalLogin);
                }
            }
        }
    }
}