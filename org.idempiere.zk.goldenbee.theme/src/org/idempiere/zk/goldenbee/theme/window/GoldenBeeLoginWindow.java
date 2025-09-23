package org.idempiere.zk.goldenbee.theme.window;

import java.util.Properties;

import org.idempiere.zk.goldenbee.theme.panel.GoldenBeeLoginPanel;

public class GoldenBeeLoginWindow extends org.adempiere.webui.window.LoginWindow {

    protected GoldenBeeLoginPanel pnlLogin;

    @Override
    protected void createLoginPanel() {
        pnlLogin = new GoldenBeeLoginPanel(ctx, this);
        pnlLogin.setId("pnlLogin");
        this.appendChild(pnlLogin);
    }

}
