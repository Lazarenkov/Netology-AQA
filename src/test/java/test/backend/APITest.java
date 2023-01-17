package test.backend;

import data.DataHelper;
import data.RequestHelper;
import data.QueryHelper;
import dto.Dto;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

public class APITest {

    @Test
    void shouldBeApproveResponseWhenPurchasingWithApprovedCard() {
        Dto.Request request = DataHelper.getValidApprovedUserDataRequest();
        ResponseSpecification response = RequestHelper.setApprovedResponse();

        RequestHelper.sendPurchaseRequest(request, response);

        QueryHelper.checkApprovedStatusOfLastPurchaseRequest();
    }

    @Test
    void shouldBeApproveResponseWhenLoanWithApprovedCard() {
        Dto.Request request = DataHelper.getValidApprovedUserDataRequest();
        ResponseSpecification response = RequestHelper.setApprovedResponse();

        RequestHelper.sendLoanRequest(request, response);

        QueryHelper.checkApprovedStatusOfLastLoanRequest();
    }

    @Test
    void shouldBeDeclineResponseWhenPurchasingWithDeclinedCard() {
        Dto.Request request = DataHelper.getValidDeclinedUserDataRequest();
        ResponseSpecification response = RequestHelper.setDeclinedResponse();

        RequestHelper.sendPurchaseRequest(request, response);

        QueryHelper.checkDeclinedStatusOfLastPurchaseRequest();

    }

    @Test
    void shouldBeDeclineResponseWhenLoanWithDeclinedCard() {
        Dto.Request request = DataHelper.getValidDeclinedUserDataRequest();
        ResponseSpecification response = RequestHelper.setDeclinedResponse();

        RequestHelper.sendLoanRequest(request, response);

        QueryHelper.checkDeclinedStatusOfLastLoanRequest();
    }

    @Test
    void shouldBeErrorResponseWhenPurchasingWithUnregisteredCard() {
        Dto.Request request = DataHelper.getValidUnregisteredUserDataRequest();
        ResponseSpecification response = RequestHelper.setError500Response();

        RequestHelper.sendPurchaseRequest(request, response);

    }

    @Test
    void shouldBeErrorResponseWhenLoanWithUnregisteredCard() {
        Dto.Request request = DataHelper.getValidUnregisteredUserDataRequest();
        ResponseSpecification response = RequestHelper.setError500Response();

        RequestHelper.sendLoanRequest(request, response);
    }
}
