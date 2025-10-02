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

public class CalloutScanBarcode extends CalloutEngine {

	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		System.out.println("=== CALLOUT STARTED ===");

		if (value == null || value.toString().trim().isEmpty()) {
			return "";
		}

		String barcode = value.toString().trim();
		System.out.println("Barcode scanned: " + barcode);

		int adClientId = Env.getAD_Client_ID(ctx);
		int adOrgId = Env.getAD_Org_ID(ctx);

		try {
			// 1. Tìm sản phẩm theo barcode
			Object[] productInfo = findProductByBarcode(barcode, adClientId);
			if (productInfo == null) {
				mTab.setValue(mField.getColumnName(), "");
				return "Không tìm thấy sản phẩm: " + barcode;
			}

			int zProductId = (Integer) productInfo[0];
			BigDecimal price = (BigDecimal) productInfo[1];
			System.out.println("Found product - ID: " + zProductId + ", Price: " + price);

			// 2. Lấy stock_in_id từ tab cha
			Integer zStockInId = getStockInIdFromCurrentTab(mTab);
			if (zStockInId == null) {
				mTab.setValue(mField.getColumnName(), "");
				return "Không xác định được phiếu nhập (z_stock_in_id)";
			}
			System.out.println("StockIn ID from tab: " + zStockInId);

			// 3. Tìm dòng đã có sản phẩm
			Integer existingRowIndex = findExistingProductInGrid(mTab, zStockInId, zProductId);

			if (existingRowIndex != null) {
				System.out.println("Updating existing row...");
				return updateExistingRow(mTab, existingRowIndex, price, mField);
			} else {
				System.out.println("Adding new row...");
				return addNewRow(mTab, zProductId, zStockInId, barcode, price, adClientId, adOrgId, mField);
			}

		} catch (Exception e) {
			System.err.println("Callout error: " + e.getMessage());
			e.printStackTrace();
			return "Lỗi xử lý: " + e.getMessage();
		}
	}

	/** Lấy z_stock_in_id từ tab cha */
	private Integer getStockInIdFromCurrentTab(GridTab mTab) {
		Object stockInId = mTab.getParentTab().getValue("z_stock_in_ID");
		if (stockInId instanceof Integer) {
			return (Integer) stockInId;
		}
		if (stockInId != null) {
			try {
				return Integer.parseInt(stockInId.toString());
			} catch (NumberFormatException e) {
				System.err.println("Cannot parse z_stock_in_id: " + stockInId);
			}
		}
		return null;
	}

	/** Tìm sản phẩm theo barcode */
	private Object[] findProductByBarcode(String barcode, int adClientId) {
		String sql = "SELECT z_product_id, cost_price FROM z_product WHERE barcode=? AND AD_Client_ID=? AND IsActive='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, barcode);
			pstmt.setInt(2, adClientId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int productId = rs.getInt(1);
				BigDecimal costPrice = rs.getBigDecimal(2);
				return new Object[] { productId, costPrice != null ? costPrice : BigDecimal.ZERO };
			}
		} catch (SQLException e) {
			System.err.println("Database error: " + e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}
		return null;
	}

	/** Tìm sản phẩm trong grid (theo stock_in_id + product_id) */
	private Integer findExistingProductInGrid(GridTab mTab, int zStockInId, int zProductId) {
		int currentRow = mTab.getCurrentRow();
		int rowCount = mTab.getRowCount();

		try {
			for (int i = 0; i < rowCount; i++) {
				mTab.navigate(i);

				Object currentStockInId = mTab.getValue("z_stock_in_id");
				Object currentProductId = mTab.getValue("z_product_id");

				boolean sameStockIn = currentStockInId != null
						&& currentStockInId.toString().equals(String.valueOf(zStockInId));
				boolean sameProduct = currentProductId != null
						&& currentProductId.toString().equals(String.valueOf(zProductId));

				if (sameStockIn && sameProduct) {
					return i;
				}
			}
		} catch (Exception e) {
			System.err.println("Search error: " + e.getMessage());
		} finally {
			mTab.navigate(currentRow);
		}

		return null;
	}

	/** Cập nhật dòng đã tồn tại */
	private String updateExistingRow(GridTab mTab, int rowIndex, BigDecimal price, GridField mField) {
		int currentRow = mTab.getCurrentRow();

		try {
			mTab.navigate(rowIndex);

			Object currentQtyObj = mTab.getValue("quantity");
			BigDecimal currentQty = BigDecimal.ONE;

			if (currentQtyObj instanceof BigDecimal) {
				currentQty = (BigDecimal) currentQtyObj;
			} else if (currentQtyObj instanceof Integer) {
				currentQty = BigDecimal.valueOf((Integer) currentQtyObj);
			} else if (currentQtyObj != null) {
				currentQty = new BigDecimal(currentQtyObj.toString());
			}

			BigDecimal newQty = currentQty.add(BigDecimal.ONE);
			BigDecimal newLineTotal = price.multiply(newQty);

			mTab.setValue("quantity", newQty);
			mTab.setValue("line_total", newLineTotal);

			// Clear barcode field
			mTab.setValue(mField.getColumnName(), "");

			return "";

		} catch (Exception e) {
			System.err.println("Update error: " + e.getMessage());
			return "Lỗi cập nhật: " + e.getMessage();
		} finally {
			mTab.navigate(currentRow);
		}
	}

	/** Thêm dòng mới */
	private String addNewRow(GridTab mTab, int zProductId, int zStockInId, String barcode, BigDecimal price,
			int adClientId, int adOrgId, GridField mField) {
		try {
			mTab.dataNew(false);

			mTab.setValue("z_stock_in_id", zStockInId);
			mTab.setValue("z_product_id", zProductId);
			mTab.setValue("AD_Client_ID", adClientId);
			mTab.setValue("AD_Org_ID", adOrgId);
			mTab.setValue("IsActive", "Y");

			mTab.setValue("barcode", barcode);
			mTab.setValue("quantity", BigDecimal.ONE);
			mTab.setValue("cost_price", price);
			mTab.setValue("line_total", price);

			// Clear barcode field
			mTab.setValue(mField.getColumnName(), "");

			return "";

		} catch (Exception e) {
			System.err.println("Add new row error: " + e.getMessage());
			return "Lỗi thêm mới: " + e.getMessage();
		}
	}
}
