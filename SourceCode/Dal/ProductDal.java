package com.lxy.customerinfomanager.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lxy.customerinfomanager.model.Product;
import com.lxy.customerinfomanager.util.StringUtil;

/**
 * ��Ʒ�����ݿ������
 * 
 * @author xiaoyang
 *
 */
public class ProductDal {
	/**
	 * ��Ʒ����
	 * 
	 * @param con
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con, Product product) throws Exception {
		String sql = "insert into t_product values(null,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, product.getProductName());
		pstmt.setInt(2, product.getCompanyId());
		pstmt.setString(3, product.getProductDesc());
		pstmt.setFloat(4, product.getPrice());
		return pstmt.executeUpdate();
	}

	/**
	 * ��Ʒ��ѯ �����ѯ��д��������...���������ﻨ��������Сʱ
	 * 
	 * @param con
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public ResultSet query(Connection con, Product product) throws Exception {
		StringBuffer stringBuffer = new StringBuffer(
				"select t_product.id,productName,companyName,productDesc,productPrice from t_product,t_company where t_product.companyId=t_company.id");
		if (StringUtil.isNotEmpty(product.getProductName())) {
			stringBuffer.append(" and t_product.productName like '%" + product.getProductName() + "%'");
		}
		if (product.getCompanyId() != null && product.getCompanyId() != -1) {
			stringBuffer.append(" and t_product.companyId=" + product.getCompanyId());
		}

		if (product.getPrice() != 0 && product.getPrice2() != 0) {
			stringBuffer.append(
					" and t_product.productPrice between " + product.getPrice() + " and " + product.getPrice2());
		}
		stringBuffer.append(" group by t_product.id");
		PreparedStatement pstmt = con.prepareStatement(stringBuffer.toString());
		return pstmt.executeQuery();
	}
	/**
	 * ��Ʒ�޸�
	 * @param con
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public int modify(Connection con, Product product) throws Exception {
		String sql = "update t_product set productName=?,companyId=?,productDesc=?,productPrice=? where id =?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,product.getProductName() );
		pstmt.setInt(2, product.getCompanyId());
		pstmt.setString(3, product.getProductDesc());
		pstmt.setFloat(4, product.getPrice());
		pstmt.setInt(5, product.getId());
		return pstmt.executeUpdate();
	}
	/**
	 * ͼ��ɾ��
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con,String id) throws Exception{
		String sql="delete from t_product where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
}
