package org.idempiere.zk.goldenbee.theme.panel;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.GlobalSearch;
import org.adempiere.webui.event.ZoomEvent;
import org.adempiere.webui.panel.HeaderPanel;
import org.compiere.model.MQuery;
import org.idempiere.zk.goldenbee.theme.window.GoldenBeeAboutWindow;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.util.Clients;

public class GoldenBeeHeaderPanel extends HeaderPanel{

	private static final long serialVersionUID = 1L;
	
	private GlobalSearch globalSearch;
	
	@Override
	public void onEvent(Event event) throws Exception {
		if (Events.ON_CLICK.equals(event.getName())) {
			if(event.getTarget() == image)
			{
				GoldenBeeAboutWindow w = new GoldenBeeAboutWindow();
				w.setPage(this.getPage());
				w.doHighlighted();
			}
			else if(event.getTarget() == btnMenu )
			{
				Long ts = (Long) popMenu.removeAttribute(popMenu.getUuid());
				if (ts != null) {
					if ((System.currentTimeMillis()-ts.longValue()) < 500)
						return;
				}
				popMenu.open(btnMenu, "after_start");
			}
		} else if (Events.ON_CREATE.equals(event.getName())) {
			onCreate();
		}else if (event instanceof KeyEvent)
		{			
			KeyEvent ke = (KeyEvent) event;
			if (ke.getKeyCode() == 77) // alt+m for the menu
			{
				popMenu.open(btnMenu, "after_start");
				popMenu.setFocus(true);
			}
			else if (ke.getKeyCode() == 27) // esc to close menu 
			{ 
				popMenu.close();
			}
			else if (ke.getKeyCode() == 71) // alt+g for the search 
			{ 
				globalSearch.setFocus(true);
			}
		} else if(event.getName().equals(ZoomEvent.EVENT_NAME)) {
			Clients.clearBusy();
			ZoomEvent ze = (ZoomEvent) event;
			if (ze.getData() != null && ze.getData() instanceof MQuery) {
				AEnv.zoom((MQuery) ze.getData());
			}
		}
	}

}
