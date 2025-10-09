package org.goldenbee.callouts;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MRole;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

public class CalloutScanBarcode extends CalloutEngine {

	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		System.out.println("=== CALLOUT STARTED ===");

		if (value == null)
			return "";

		String barcode = value.toString().trim();
		int inoutID = Env.getContextAsInt(ctx, WindowNo, "z_inout_ID");

		if (inoutID == 0) {
			mTab.fireDataStatusEEvent("Chưa chọn phiếu nhập kho", null, false);
			return "";
		}

		// Sử dụng transaction riêng
		Trx trx = Trx.get(Trx.createTrxName("BarcodeScan"), true);
		try {
			trx.start();

			String sql = "SELECT adempiere.z_process_barcode(?, ?)";
			String result = DB.getSQLValueStringEx(trx.getTrxName(), sql, barcode, inoutID);

			if ("SUCCESS".equals(result)) {
				trx.commit();
				mTab.setValue("ScanBarcode", null);
				mTab.fireDataStatusEEvent("Đã thêm: " + barcode, null, true);
				mTab.dataRefreshAll();
				mTab.dataNew(false);
			} else {
				trx.rollback();
				mTab.fireDataStatusEEvent(result, null, false);
			}

		} catch (Exception e) {
			trx.rollback();
			mTab.fireDataStatusEEvent("Lỗi: " + e.getMessage(), null, false);
		} finally {
			trx.close();
		}

		return "";
	}
}
