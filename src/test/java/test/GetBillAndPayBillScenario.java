package test;

import Commons.RequestBodyGenerator;
import Commons.RequestCapability;
import Commons.RequestHeaderGenerator;
import Commons.SampleData;
import ReportConfig.ExtentTestManager;
import RequestBodyModal.GetBillRequestBody;
import RequestBodyModal.LoginEngineRequestBody;
import RequestBodyModal.PayBillRequestBody;
import ResponseBodyModal.GetBillSuccessResponseBody;
import ResponseBodyModal.LoginEngineSuccessResponseBody;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import JDBCTest.MariaDBConnUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBillAndPayBillScenario {

    private RequestSpecification request;
    private Response response;

    @Test
    public void TC_01_LogInToEngine(Method method) {
        ExtentTestManager.startTest(method.getName(), "LogInToEngine");
        LoginEngineRequestBody loginEngineRequestBody = RequestBodyGenerator.getLoginEngineRequestBody();
        request = RequestHeaderGenerator.getEngineLoginRequestHeader();
        response = request.body(loginEngineRequestBody).post();
        response.prettyPrint();
        LoginEngineSuccessResponseBody loginEngineSuccessResponseBody = response.as(LoginEngineSuccessResponseBody.class);
        RequestCapability.engineAccessToken = loginEngineSuccessResponseBody.data.accessToken;
    }

    @Test
    public void TC_02_GetBill(Method method) {
        ExtentTestManager.startTest(method.getName(), "GetBill");
        GetBillRequestBody getBillRequestBody = RequestBodyGenerator.getGetBillRequestBody();
        request = RequestHeaderGenerator.getGetbillRequestHeader();
        response = request.body(getBillRequestBody).post();
        response.prettyPrint();
        GetBillSuccessResponseBody getBillSuccessResponseBody = response.as(GetBillSuccessResponseBody.class);
        SampleData.billId = getBillSuccessResponseBody.data.get(0).billId;
    }

    @Test
    public void TC_03_PayBill(Method method) {
        ExtentTestManager.startTest(method.getName(), "PayBill");
        PayBillRequestBody payBillRequestBody = RequestBodyGenerator.getPayBillRequestBody();
        request = RequestHeaderGenerator.getPaybillRequestHeader();
        response = request.body(payBillRequestBody).post();
        response.prettyPrint();
    }

    @Test
    public void TC_04_VerifyTransactionInDatabase(Method method) throws SQLException {
        ExtentTestManager.startTest(method.getName(), "VerifyTransactionInDatabase");
        Connection connection = MariaDBConnUtils.getMariaDBConnection();
        String queryingString = "SELECT * FROM TB_ECOLLECTION_TRANS WHERE BILL_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(queryingString);
        pstm.setString(1, SampleData.billId);
        System.out.println(SampleData.billId);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            Assert.assertEquals(SampleData.payAmount, resultSet.getInt("DEPOSIT_AMT"));
        }
    }
}
