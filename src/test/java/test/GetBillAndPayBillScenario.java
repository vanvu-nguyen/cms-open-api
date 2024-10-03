package test;

import Commons.RequestBodyGenerator;
import Commons.RequestCapability;
import Commons.RequestHeaderGenerator;
import RequestBodyModal.GetBillRequestBody;
import RequestBodyModal.LoginEngineRequestBody;
import RequestBodyModal.PayBillRequestBody;
import ResponseBodyModal.GetBillSuccessResponseBody;
import ResponseBodyModal.LoginEngineSuccessResponseBody;
import ResponseBodyModal.PayBillResponseBody;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdbcTest.MariaDBConnUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBillAndPayBillScenario {

    private RequestSpecification request;
    private Response response;

    @Test
    public void TC_01_LogInToEngine() {
        LoginEngineRequestBody loginEngineRequestBody = RequestBodyGenerator.getLoginEngineRequestBody();
        request = RequestHeaderGenerator.getEngineLoginRequestHeader();
        response = request.body(loginEngineRequestBody).post();
        response.prettyPrint();
        LoginEngineSuccessResponseBody loginEngineSuccessResponseBody = response.as(LoginEngineSuccessResponseBody.class);
        RequestCapability.engineAccessToken = loginEngineSuccessResponseBody.data.accessToken;
    }

    @Test
    public void TC_02_GetBill() {
        GetBillRequestBody getBillRequestBody = RequestBodyGenerator.getGetBillRequestBody();
        request = RequestHeaderGenerator.getGetbillRequestHeader();
        response = request.body(getBillRequestBody).post();
        response.prettyPrint();
        GetBillSuccessResponseBody getBillSuccessResponseBody = response.as(GetBillSuccessResponseBody.class);
        RequestCapability.billId = getBillSuccessResponseBody.data.get(0).billId;
    }

    @Test
    public void TC_03_PayBill() {
        PayBillRequestBody payBillRequestBody = RequestBodyGenerator.getPayBillRequestBody();
        request = RequestHeaderGenerator.getPaybillRequestHeader();
        response = request.body(payBillRequestBody).post();
        response.prettyPrint();
        PayBillResponseBody payBillResponseBody = response.as(PayBillResponseBody.class);
    }

    @Test
    public void TC_04_VerifyTransactionInDatabase() throws SQLException {
        Connection connection = MariaDBConnUtils.getMariaDBConnection();
        String queryingString = "SELECT * FROM TB_ECOLLECTION_TRANS WHERE BILL_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(queryingString);
        pstm.setString(1, RequestCapability.billId);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            Assert.assertEquals(5000, resultSet.getInt("DEPOSIT_AMT"));
        }
    }
}
