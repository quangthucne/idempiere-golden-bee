
package org.goldenbee.callouts;

import java.util.Properties;
import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CalloutStockInLine implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
        if (value == null) {
            return "";
        }

        String barcode = value.toString();
        if (barcode.trim().isEmpty()) {
            return "";
        }

        // Get AD_Client_ID from context
        int ad_client_id = Env.getAD_Client_ID(ctx);

        // Create where clause
        String whereClause = "barcode = ? AND AD_Client_ID = ?";

        // Query z_product table
        MProduct product = new Query(ctx, "z_product", whereClause, null)
                                .setParameters(barcode, ad_client_id)
                                .first();

        if (product != null) {
            // Set values on the grid tab
            mTab.setValue("Z_Product_ID", product.get_ID());
            mTab.setValue("cost_price", product.get_Value("cost_price"));
        } else {
            // Product not found, clear the fields
            mTab.setValue("Z_Product_ID", null);
            mTab.setValue("cost_price", null);
            return "Sản phẩm với barcode '" + barcode + "' không tồn tại."; // Product with barcode '...' not found.
        }

        return "";
    }
}
