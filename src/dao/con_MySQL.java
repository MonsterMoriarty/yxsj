package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class con_MySQL {

	// ������
	static final String driverName = "com.mysql.jdbc.Driver";
	// ���ݿ�·��
	static final String dbURL = "jdbc:mysql://localhost:3306/yxsj";
	// ���ݿ��û���
	static final String userName = "root";
	// ���ݿ�����
	static final String userPwd = "root";
	// ���ݿ����Ӷ���
	static Connection dbConn = null;

	// �������ݿ�����
	static {
		try {
			Class.forName(driverName);
			System.out.println("�������ݿ������ɹ�");
		} catch (Exception e) {
			e.printStackTrace();

			System.out.print("�������ݿ�����ʧ��");

		}
	}

	// �õ����ݿ����Ӷ���
	public static Connection getCon() throws SQLException {
		if (dbConn == null) {
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			return dbConn;
		}
		return dbConn;
	}

}
