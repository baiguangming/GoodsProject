package cn.mldn.dao;

import java.sql.SQLException;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vo.Goods;

public interface IGoodsDAO extends IBaseDAO<Integer, Goods> {
	/**
	* 通过goods_seq序列取得增长后的序列内容
	* @return
	* @throws SQLException
	*/
	public Integer findCreateId() throws SQLException ;
	
	/**
	* 增加商品信息的名字不允许重复，所以需要追加一个根据名称查询商品的操作方法
	* @param name 商品名称
	* @return 如果查找到则以Goods对象形式返回，否则返回null
	* @throws SQLException
	*/
	public Goods findByName(String name) throws SQLException ;
}
