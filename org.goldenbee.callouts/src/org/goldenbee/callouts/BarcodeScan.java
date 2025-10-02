//package org.goldenbee.callouts;
//
//import java.math.BigDecimal;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Properties;
//
//import org.adempiere.base.IColumnCallout;
//import org.compiere.model.CalloutEngine;
//import org.compiere.model.GridField;
//import org.compiere.model.GridTab;
//import org.compiere.model.PO;
//import org.compiere.model.Query;
//import org.compiere.util.DB;
//import org.compiere.util.Env;
//
//public class BarcodeScan extends CalloutEngine{
//
//	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
//		if (value == null || value.toString().trim().isEmpty()) {
//            return null;
//        }
//
//        String barcode = value.toString().trim();
//        String fieldName = mField.getColumnName();
//        int adClientId = (Integer) mTab.getValue("AD_Client_ID");
//        int stockInId = (Integer) mTab.getValue("z_stock_in_ID");
//        String trxName = mTab.getTrxInfo();
//
//        // Chỉ xử lý cho trường barcode
//        if (!"barcode_field".equals(fieldName)) {
//            return null;
//        }
//        
//        
//        // Từ barcode lấy thông tin sản phẩm
//        String barcodeQuery = "SELECT z_product_id FORM z_prodcut WHERE barcode= ? AND ad_client_id=? AND IsActive='Y' ";
//        
//        PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int productId;
//		
//		try {
//			pstmt = DB.prepareStatement(barcodeQuery, null);
//			pstmt.setString(1, barcode);
//			pstmt.setInt(2, adClientId);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				productId = rs.getInt(1);
//				BigDecimal costPrice = rs.getBigDecimal(2);
//			}
//		} catch (SQLException e) {
//			System.err.println("Database error: " + e.getMessage());
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		
//		
//		// từ productId check tồn tại
//		PO line = new Query(ctx, "Z_Stock_In_Line",
//		        "Z_Stock_In_ID=? AND Z_Product_ID=?", trxName)
//		        .setParameters(stockInId, productId)
//		        .setOnlyActiveRecords(true)
//		        .first();
//
//		if (line != null) {
//		    BigDecimal qty = (BigDecimal) line.get_Value("Qty");
//		    line.set_ValueOfColumn("Qty", qty.add(Env.ONE));
//		    line.saveEx();
//		} else {
//		    // Tạo record mới
//		    PO newLine = new Query(ctx, "Z_Stock_In_Line", "", trxName)
//		            .getPO(0, trxName); // Trick để lấy PO new
//		    newLine.set_ValueOfColumn("Z_Stock_In_ID", stockInId);
//		    newLine.set_ValueOfColumn("Z_Product_ID", productId);
//		    newLine.set_ValueOfColumn("Qty", Env.ONE);
//		    newLine.set_ValueOfColumn("AD_Client_ID", adClientId);
//		    newLine.set_ValueOfColumn("AD_Org_ID", adOrgId);
//		    newLine.saveEx();
//		}
//
//        
//		return "";
//    }
//	
//	
//}
