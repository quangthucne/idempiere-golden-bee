package org.idempiere.zk.goldenbee.theme.window;

import java.util.Properties;

import org.adempiere.base.sso.SSOUtils;
import org.adempiere.webui.IWebClient;
import org.adempiere.webui.panel.ValidateMFAPanel;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Util;
import org.idempiere.zk.goldenbee.theme.panel.GoldenBeeLoginPanel;
import org.idempiere.zk.goldenbee.theme.panel.GoldenBeeRolePanel;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;

public class GoldenBeeLoginWindow extends org.adempiere.webui.window.LoginWindow {

    protected GoldenBeeLoginPanel pnlLogin;
    
    @Override
    public void init(IWebClient app) {
    	// TODO Auto-generated method stub
    	super.init(app);
    }

    @Override
    protected void createLoginPanel() {
        pnlLogin = new GoldenBeeLoginPanel(ctx, this);
        pnlLogin.setId("pnlLogin");
        this.appendChild(pnlLogin);
    }
    
    @Override
    public void loginOk(String userName, boolean show, KeyNamePair[] clientsKNPairs, boolean isSSOLogin) {
        boolean isClientDefined = (clientsKNPairs.length == 1 
                || !Util.isEmpty(Env.getContext(ctx, Env.AD_USER_ID)));

        if (pnlRole == null) {
            // Đổi RolePanel thành GoldenBeeRolePanel
            pnlRole = new GoldenBeeRolePanel(ctx, this, userName, show, clientsKNPairs, isClientDefined);
        }

        if (isSSOLogin) {
            Executions.schedule(getDesktop(),
                e -> validateMFPanel(userName, show, clientsKNPairs, isClientDefined),
                new Event(SSOUtils.EVENT_ON_AFTER_SSOLOGIN));
        } else {
            validateMFPanel(userName, show, clientsKNPairs, isClientDefined);
        }
    }
    
    
    private void validateMFPanel(String userName, boolean show, KeyNamePair[] clientsKNPairs, boolean isClientDefined)
	{
		if (isClientDefined) {
    		createValidateMFAPanel(null, isClientDefined, userName, show, clientsKNPairs);
    	} else {
            showRolePanel(userName, show, clientsKNPairs, isClientDefined, false);
			if (!pnlRole.show())
            	createValidateMFAPanel(null, isClientDefined, userName, show, clientsKNPairs);
    	}
	}
    
	private void createValidateMFAPanel(KeyNamePair orgKNPair, boolean isClientDefined, String userName, boolean show, KeyNamePair[] clientsKNPairs) {
		if (pnlValidateMFA == null)
			pnlValidateMFA = new ValidateMFAPanel(ctx, this, orgKNPair, isClientDefined, userName, show, clientsKNPairs);
		if (pnlValidateMFA.show()) {
	        this.getChildren().clear();
	        this.appendChild(pnlValidateMFA);
		}
	}

}
