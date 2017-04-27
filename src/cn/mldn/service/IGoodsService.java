package cn.mldn.service;

import java.util.Map;
import java.util.Set;

import cn.mldn.vo.Goods;

public interface IGoodsService {
	/**
	* 进行商品信息数据的批量删除处理，调用IGoodsDAO.doRemoveBatch()方法
	* 由于此刻存在有外键级联删除关系，所以只需要删除主表即可，如果没有则还需要调用ITagDAO.doRemoveByGoods()
	    方法
	* @param ids 所有要删除的编号
	* * @return
    * @throws Exception
    */
    public boolean delete(Set<Integer> ids) throws Exception ;
	/**
	* 进行商品数据添加前的信息查找操作，该操作会执行如下调用： <br>
	* 1、查询所有的商品分类信息，调用IItemDAO.findAll()； <br>
	* 2、查询所有的商品标签信息，调用ITagDAO.findAll()； <br>
	* 3、根据商品编号查询出商品信息，调用IGoods.findById()方法； <br>
	* 4、查询出该商品已有的标签信息，调用ITagDAO.findAllByGoods()方法。
	* @param gid 要修改的商品编号
	* @return 返回的数据包含有以下内容： <br>
	* 1、 key = allItems、 value = 所有的Item的分类信息； <br>
	* 2、 key = allTags、 value = 所有的商品的标签信息； <br>
	* 3、 key = goods、 value = 单个商品信息； <br>
	* 4、 key = goodsTag、 value = 已有的商品标签。
	* @throws Exception
	*/
	public Map<String,Object> getEditPre(int gid) throws Exception ;
	
	/**
	* 实现商品数据的添加处理，基本的处理流程如下： <br>
	* 1、首先一定要保证商品有标签，以及商品有所属分类，商品的价格一定要大于0； <br>
	* 2、首先要使用IGoodsDAO.findByName()方法判断商品的名称是否重复，但是不对自己的编号判断； <br>
	* 3、如果商品名称不存在，则使用IGoodsDAO.doUpdate()进行信息保存； <br>
	* 4、商品与标签的关系由于可能产生变化，则应该先删除； <br>
	* 5、调用ITagDAO.doCreateGoodsAndTag()方法保存商品和标签的对应关系
	* @param vo 商品信息
	* @param tids 所有的标签的ID信息
	* @return 保存成功返回true，否则返回false
	* @throws Exception
	*/
	public boolean edit(Goods vo,Set<Integer> tids) throws Exception ;
	/**
	* 进行数据的分页显示，如果没有模糊查询的关键字则进行全部数据的分页处理
	* @param currentPage
	* @param lineSize
	* @param column
	* @param keyWord
	* @return 返回的数据包含有以下信息： <br>
	* 1、 key = allGoodss、 value = 所有的商品数据信息； <br>
	* 2、 key = goodsCount、 value = 商品的数量信息； <br>
	* 3、 key = allItems、 value = 所有的商品分类数据（ Map集合）
	* @throws Exception
	*/
	public Map<String, Object> list(int currentPage, int lineSize,
	String column, String keyWord) throws Exception;
	/**
	* 进行数据的分页显示，如果没有模糊查询的关键字则进行全部数据的分页处理
	* @param currentPage
	* @param lineSize
	* @param column
	* @param keyWord
	* @return 返回的数据包含有以下信息： <br>
	* 1、 key = allGoodss、 value = 所有的商品数据信息； <br>
	* 2、 key = goodsCount、 value = 商品的数量信息； <br>
	* @throws Exception
	*/
	public Map<String, Object> listWithBug(int currentPage, int lineSize,
	      String column, String keyWord) throws Exception;
	/**
	* 实现商品数据的添加处理，基本的处理流程如下： <br>
	* 1、首先一定要保证商品有标签，以及商品有所属分类，商品的价格一定要大于0； <br>
	* 2、首先要使用IGoodsDAO.findByName()方法判断商品的名称是否重复； <br>
	* 3、如果商品名称不存在，则使用IGoodsDAO.doCreate()进行信息保存； <br>
	* 4、为了可以向goods_tag关系表中进行数据的保存，所以一定要首先取得增长后的商品编号，调用
	IGoodsDAO.findCreateId()； <br>
	* 5、调用ITagDAO.doCreateGoodsAndTag()方法保存商品和标签的对应关系
	* @param vo 商品信息
	* @param tids 所有的标签的ID信息
	* @return 保存成功返回true，否则返回false
	* @throws Exception
	*/
	public boolean add(Goods vo,Set<Integer> tids) throws Exception ;
	
	/**
	* 进行商品数据添加前的信息查找操作，该操作会执行如下调用： <br>
	* 1、查询所有的商品分类信息，调用IItemDAO.findAll()； <br>
	* 2、查询所有的商品标签信息，调用ITagDAO.findAll()； <br>
	* @return 返回的数据包含有以下内容： <br>
	* 1、 key = allItems、 value = 所有的Item的分类信息； <br>
	* 2、 key = allTags、 value = 所有的商品的标签信息； <br>
	* @throws Exception
	*/
	public Map<String,Object> getAddPre() throws Exception ;
	
}
