package tpplc.xebium.payments.igpayment;

import java.sql.*;

public class ConfirmPurchaseOrderStatusAndEmailSent {

	private int actId;
	private boolean actEmailSent;
	private String actStatus;

	private String connString = String
			.format("jdbc:sqlserver://;servername=up0mud01;databaseName=UATSiteCore_AMPOrders;integratedSecurity=true");
	private String queryString = "select TOP 1 id, emailSent, status from orderAction order by created desc";

	public void execute() {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(connString);
			System.out.println("connected");
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);

			while (rs.next()) {

				int resulta = rs.getInt("id");
				boolean resultb = rs.getBoolean("emailSent");
				String resultc = rs.getString("status");
				actId = resulta;
				actEmailSent = resultb;
				actStatus = resultc;
			}

			System.out.println("Order ID (id in orderAction): " + actId
					+ "\nactEmailSent: " + actEmailSent + "\nactStatus: "
					+ actStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String IsPaymentAuthorised() {
		return actStatus;
	}

	public boolean HasEmailSent() {
		return actEmailSent;
	}

}
