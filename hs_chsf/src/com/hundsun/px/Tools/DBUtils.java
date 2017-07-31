package com.hundsun.px.Tools;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.spec.PSource.PSpecified;

import com.hundsun.px.Bean.RepBean;
import com.hundsun.px.Bean.ReqBean;

public class DBUtils
{
	private static Connection con;

	private PreparedStatement pstm;

	private String user = "sa";

	private String password = "123456";

	private String className = "net.sourceforge.jtds.jdbc.Driver";

	private String url = "jdbc:jtds:sqlserver://localhost:1433;databaseName=db_chaoshi";

	public DBUtils()
	{
		try
		{
			Class.forName(className);
		} catch (ClassNotFoundException e)
		{
			System.out.println("加载数据库驱动失败！");
			e.printStackTrace();
		}
	}

	/** 创建数据库连接 */
	public Connection getCon()
	{
		try
		{
			con = DriverManager.getConnection(url,user,password);
		} catch (SQLException e)
		{
			System.out.println("创建数据库连接失败！");
			con = null;
			e.printStackTrace();
		}
		return con;
	}

	public void doPstm(String sql, Object[] params)
	{
		if (sql != null && !sql.equals(""))
		{
			if (params == null)
				params = new Object[0];

			getCon();
			if (con != null)
			{
				try
				{
					System.out.println(sql);
					pstm = con.prepareStatement(sql,
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					for (int i = 0; i < params.length; i++)
					{
						pstm.setObject(i + 1, params[i]);
					}
					pstm.execute();
				} catch (SQLException e)
				{
					System.out.println("doPstm()方法出错！");
					e.printStackTrace();
				}
			}
		}
	}

	public ResultSet getRs() throws SQLException
	{
		return pstm.getResultSet();
	}

	public int getCount() throws SQLException
	{
		return pstm.getUpdateCount();
	}

	public void closed()
	{
		try
		{
			if (pstm != null)
				pstm.close();
		} catch (SQLException e)
		{
			System.out.println("关闭pstm对象失败！");
			e.printStackTrace();
		}
		try
		{
			if (con != null)
			{
				con.close();
			}
		} catch (SQLException e)
		{
			System.out.println("关闭con对象失败！");
			e.printStackTrace();
		}
	}




	//获取SqlServer数据库连接
	public static Connection getSqlSerCon(){

		String url = "jdbc:jtds:sqlserver://localhost:1433;databaseName=db_pzz";
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			con = DriverManager.getConnection(url,"sa","123456");
			if(con==null){
				System.out.println("创建Oracle数据库连接失败！");
			}else{
				System.out.println("创建Oracel数据库成功");
			}
		} catch (SQLException e)
		{
			System.out.println("创建Oracle数据库连接失败！");
			con = null;
			e.printStackTrace();
		}
		return con;
	}

	//获取Oracle数据库连接
	public static Connection getOracleCon(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String url = "";
			con = DriverManager.getConnection(url,"", "");
			//con = DriverManager.getConnection(url,user,password);
			if(con==null){
				System.out.println("创建SqlServer数据库连接失败！");
			}else{
				System.out.println("创建SqlServer数据库连接成功");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("创建SqlServer数据库连接失败！");
			con = null;
		}
		return con;
	}

//	//插入行对象到当前连接的数据库指定表中
//	public static void insertIntoTb(TestBean tb,String tbName){
//		PreparedStatement psInsert = null;
//		StringBuilder sqlInsert = new StringBuilder("insert into tbName(");
//		Map<String,Object> map = new HashMap<>();
//		TestBean obj = null;
////		try {
////			//obj = (TestBean) t.newInstance();
////		} catch (InstantiationException | IllegalAccessException e1) {
////			// TODO Auto-generated catch block
////			e1.printStackTrace();
////		}
//		try {
//			//Method[] methods = t.getMethods();
//			//ps = con.prepareStatement("insert into "+ tbName + "");
////			for(Method method : methods){
////				String methodName = method.getName();
////				if(methodName.contains("get")){
////					String key = methodName.substring(3, methodName.length()-1).toLowerCase();
////					String value = (String) method.invoke(obj);
////					map.put(key, value);
////					sqlInsert.append(key+",");
////				}
////			}
//			sqlInsert.append(")"+" values(");
//			for(Entry entry:map.entrySet()){
//				sqlInsert.append(entry.getValue()+",");
//			}
//			sqlInsert.append(")");
//
//			System.out.println(sqlInsert.toString());
//
//			psInsert = con.prepareStatement(sqlInsert.toString());
//			psInsert.executeUpdate();
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	//更新当前连接的数据库中指定表数据
	public static void updateTbByParams(Map<String,Object> params,String tbName,Map<String,Object> whereParams){
		PreparedStatement psUpdate = null;
		//组装查询sql
		StringBuilder sqlUpdate = new StringBuilder("update " + tbName+" set ");
		for(Entry<String, Object> entry:params.entrySet()){
			if(entry.getValue() instanceof String){
				sqlUpdate.append(entry.getKey()+"="+"\'"+entry.getValue()+"\'"+",");
			}else{
				sqlUpdate.append(entry.getKey()+"="+entry.getValue()+",");
			}
		}
		sqlUpdate.deleteCharAt(sqlUpdate.length()-1);
		sqlUpdate.append(" where ");
		for(Entry<String, Object> entry:whereParams.entrySet()){
			if(entry.getValue() instanceof String){
				sqlUpdate.append(entry.getKey()+"="+"\'"+entry.getValue()+"\'"+" and ");
			}
		}
		sqlUpdate.delete(sqlUpdate.length()-3, sqlUpdate.length());
		sqlUpdate.append(";");
		System.out.println(sqlUpdate.toString());

		try {
			psUpdate = con.prepareStatement(sqlUpdate.toString());
			psUpdate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				psUpdate.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//查询委托记录
	public static List<ReqBean> queryAllWt(String tbName){
		PreparedStatement psQuery = null;
		List<ReqBean> list = new ArrayList<>();
		try {
			psQuery = con.prepareStatement("select * from "+tbName +" order by serialid");
			ResultSet rs = psQuery.executeQuery();
			//System.out.println(rs.getString(1));
			while(rs.next()) {
			    ResultSetMetaData rsMeta=rs.getMetaData();
			    ReqBean rp = new ReqBean();
			 //   int columnCount=rsMeta.getColumnCount();
//			    for (int i=1; i<=columnCount; i++) {
//			        System.out.println(rsMeta.getColumnLabel(i)+rs.getObject(i));
//
//			    }
			    rp.setZqdm(rs.getObject(8)+"");
			    rp.setKhzh(rs.getObject(6)+"");
			    rp.setBs(rs.getObject(10).toString().toCharArray()[0]);
			    rp.setSbsj(rs.getObject(4)+"");
			    rp.setWtjg(Double.valueOf(rs.getObject(12).toString()));
			    rp.setWtls(rs.getObject(5)+"");
			    rp.setWtsl(Integer.valueOf(rs.getObject(11).toString()));
//			    System.out.println(rp.getZqdm() + " " + rp.getKhzh() + " " + rp.getBs()
//			    +" " + rp.getSbsj()+ " " +rp.getWtjg()+" "+rp.getWtls()+" "+ rp.getWtsl() + "");
			    list.add(rp);
			   // break;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				psQuery.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println(System.currentTimeMillis() - start);
		return list;

	}



	public static void closeCon(){
		if(con!=null){
			try {
				con.close();
				System.out.println("SQLServer数据库连接断开");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



}
