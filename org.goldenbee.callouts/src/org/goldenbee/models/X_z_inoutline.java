/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.goldenbee.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for z_inoutline
 *  @author iDempiere (generated)
 *  @version Release 13 - $Id$ */
@org.adempiere.base.Model(table="z_inoutline")
public class X_z_inoutline extends PO implements I_z_inoutline, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20251007L;

    /** Standard Constructor */
    public X_z_inoutline (Properties ctx, int z_inoutline_ID, String trxName)
    {
      super (ctx, z_inoutline_ID, trxName);
      /** if (z_inoutline_ID == 0)
        {
			setLine (0);
			setMovementQty (Env.ZERO);
			setQtyEntered (Env.ZERO);
			setz_inout_ID (0);
			setz_inoutline_ID (0);
			setz_product_ID (0);
			setz_uom_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_z_inoutline (Properties ctx, int z_inoutline_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, z_inoutline_ID, trxName, virtualColumns);
      /** if (z_inoutline_ID == 0)
        {
			setLine (0);
			setMovementQty (Env.ZERO);
			setQtyEntered (Env.ZERO);
			setz_inout_ID (0);
			setz_inoutline_ID (0);
			setz_product_ID (0);
			setz_uom_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_z_inoutline (Properties ctx, String z_inoutline_UU, String trxName)
    {
      super (ctx, z_inoutline_UU, trxName);
      /** if (z_inoutline_UU == null)
        {
			setLine (0);
			setMovementQty (Env.ZERO);
			setQtyEntered (Env.ZERO);
			setz_inout_ID (0);
			setz_inoutline_ID (0);
			setz_product_ID (0);
			setz_uom_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_z_inoutline (Properties ctx, String z_inoutline_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, z_inoutline_UU, trxName, virtualColumns);
      /** if (z_inoutline_UU == null)
        {
			setLine (0);
			setMovementQty (Env.ZERO);
			setQtyEntered (Env.ZERO);
			setz_inout_ID (0);
			setz_inoutline_ID (0);
			setz_product_ID (0);
			setz_uom_ID (0);
        } */
    }

    /** Load Constructor */
    public X_z_inoutline (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_z_inoutline[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Line No.
		@param Line Unique line for this document
	*/
	public void setLine (int Line)
	{
		set_ValueNoCheck (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line Amount.
		@param LineNetAmt Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	*/
	public void setLineNetAmt (BigDecimal LineNetAmt)
	{
		set_ValueNoCheck (COLUMNNAME_LineNetAmt, LineNetAmt);
	}

	/** Get Line Amount.
		@return Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public BigDecimal getLineNetAmt()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LineNetAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Movement Quantity.
		@param MovementQty Quantity of a product moved.
	*/
	public void setMovementQty (BigDecimal MovementQty)
	{
		set_ValueNoCheck (COLUMNNAME_MovementQty, MovementQty);
	}

	/** Get Movement Quantity.
		@return Quantity of a product moved.
	  */
	public BigDecimal getMovementQty()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MovementQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param QtyEntered The Quantity Entered is based on the selected UoM
	*/
	public void setQtyEntered (BigDecimal QtyEntered)
	{
		set_ValueNoCheck (COLUMNNAME_QtyEntered, QtyEntered);
	}

	/** Get Quantity.
		@return The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyEntered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set barcode.
		@param barcode barcode
	*/
	public void setbarcode (String barcode)
	{
		set_Value (COLUMNNAME_barcode, barcode);
	}

	/** Get barcode.
		@return barcode	  */
	public String getbarcode()
	{
		return (String)get_Value(COLUMNNAME_barcode);
	}

	public I_z_inout getz_inout() throws RuntimeException
	{
		return (I_z_inout)MTable.get(getCtx(), I_z_inout.Table_ID)
			.getPO(getz_inout_ID(), get_TrxName());
	}

	/** Set Phi&#7871;u nh&#7853;p kho.
		@param z_inout_ID Phi&#7871;u nh&#7853;p kho
	*/
	public void setz_inout_ID (int z_inout_ID)
	{
		if (z_inout_ID < 1)
			set_ValueNoCheck (COLUMNNAME_z_inout_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_z_inout_ID, Integer.valueOf(z_inout_ID));
	}

	/** Get Phi&#7871;u nh&#7853;p kho.
		@return Phi&#7871;u nh&#7853;p kho	  */
	public int getz_inout_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_z_inout_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Chi ti&#7871;t phi&#7871;u nh&#7853;p.
		@param z_inoutline_ID Chi ti&#7871;t phi&#7871;u nh&#7853;p
	*/
	public void setz_inoutline_ID (int z_inoutline_ID)
	{
		if (z_inoutline_ID < 1)
			set_ValueNoCheck (COLUMNNAME_z_inoutline_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_z_inoutline_ID, Integer.valueOf(z_inoutline_ID));
	}

	/** Get Chi ti&#7871;t phi&#7871;u nh&#7853;p.
		@return Chi ti&#7871;t phi&#7871;u nh&#7853;p	  */
	public int getz_inoutline_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_z_inoutline_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_z_product getz_product() throws RuntimeException
	{
		return (I_z_product)MTable.get(getCtx(), I_z_product.Table_ID)
			.getPO(getz_product_ID(), get_TrxName());
	}

	/** Set S&#7843;n ph&#7849;m.
		@param z_product_ID S&#7843;n ph&#7849;m
	*/
	public void setz_product_ID (int z_product_ID)
	{
		if (z_product_ID < 1)
			set_ValueNoCheck (COLUMNNAME_z_product_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_z_product_ID, Integer.valueOf(z_product_ID));
	}

	/** Get S&#7843;n ph&#7849;m.
		@return S&#7843;n ph&#7849;m	  */
	public int getz_product_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_z_product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_z_uom getz_uom() throws RuntimeException
	{
		return (I_z_uom)MTable.get(getCtx(), I_z_uom.Table_ID)
			.getPO(getz_uom_ID(), get_TrxName());
	}

	/** Set &#272;&#417;n v&#7883; tính.
		@param z_uom_ID &#272;&#417;n v&#7883; tính
	*/
	public void setz_uom_ID (int z_uom_ID)
	{
		if (z_uom_ID < 1)
			set_ValueNoCheck (COLUMNNAME_z_uom_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_z_uom_ID, Integer.valueOf(z_uom_ID));
	}

	/** Get &#272;&#417;n v&#7883; tính.
		@return &#272;&#417;n v&#7883; tính	  */
	public int getz_uom_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_z_uom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}