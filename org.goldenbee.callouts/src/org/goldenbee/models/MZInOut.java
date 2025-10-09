package org.goldenbee.models;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

public class MZInOut extends X_z_inoutline{

	private static final long serialVersionUID = 1L;
	
	public MZInOut(Properties ctx, int Z_InOut_ID, String trxName) {
        super(ctx, Z_InOut_ID, trxName);
    }

    public MZInOut(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Thêm sản phẩm bằng barcode - 1 dòng
     */
    public String scanBarcode(String barcode) {
        String sql = "SELECT adempiere.z_process_barcode(?, ?)";
        return DB.getSQLValueStringEx(get_TrxName(), sql, barcode, getz_inout_ID());
    }

}
