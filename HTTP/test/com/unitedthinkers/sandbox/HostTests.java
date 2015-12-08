package com.unitedthinkers.sandbox;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class HostTests {

    private HttpsClient httpsClient;

    @Before
    public void init() {
        httpsClient = new HttpsClient();
    }

    @Test
    public void saleRequestWithAccountNumberResponseCodeA01() {
        String httpsRequest = "https://sandbox.unitedthinkers.com/gates/xurl?" +
                "requestType=sale&" +
                "userName=test_api_user&" +
                "password=C8v20gAdHjig3LMRWGhm5PK1G00v08V1&" +
                "merchantAccountCode=2001&" +
                "accountType=R&" +
                "holderName=John+Smith&" +
                "accountNumber=4111111111111111&" +
                "accountAccessory=1017&" +
                "amount=500&" +
                "transactionIndustryType=RE";

        String fullResponse = httpsClient.getContentOfTheUrl(httpsRequest);
        String fieldResponse = httpsClient.getFieldResponse("&responseCode=", fullResponse);

        if (!fieldResponse.equals("A01")) {
            httpsClient.printFullResponse(fullResponse);
        }

        assertTrue("The Response Code should be A01 instead of " + fieldResponse, fieldResponse.equals("A01"));
    }

    @Test
    public void saleRequestWithAccountNumberResponseCodeD03() {
        String httpsRequest = "https://sandbox.unitedthinkers.com/gates/xurl?" +
                "requestType=sale&" +
                "userName=test_api_user&" +
                "password=C8v20gAdHjig3LMRWGhm5PK1G00v08V1&" +
                "merchantAccountCode=2001&" +
                "accountType=R&" +
                "holderName=John+Smith&" +
                "accountNumber=4111111111111111&" +
                "accountAccessory=1017&" +
                "amount=12000&" +
                "transactionIndustryType=RE";

        String fullResponse = httpsClient.getContentOfTheUrl(httpsRequest);
        String fieldResponse = httpsClient.getFieldResponse("&responseCode=", fullResponse);

        if (!fieldResponse.equals("D03")) {
            httpsClient.printFullResponse(fullResponse);
        }

        assertTrue("The Response Code should be D03 instead of " + fieldResponse, fieldResponse.equals("D03"));
    }

    @Test
    public void saleRequestWithTrackDataResponseCodeD05(){
        String httpsRequest = "https://sandbox.unitedthinkers.com/gates/xurl?" +
                "requestType=sale&" +
                "userName=test_api_user&" +
                "password=C8v20gAdHjig3LMRWGhm5PK1G00v08V1&" +
                "merchantAccountCode=2001&" +
                "accountType=R&" +
                "accountData=%25B4111111111111111%5ESmith%2FJohn%5E16041011000%201111A123456789012%3F&" +
                "amount=7000&" +
                "transactionIndustryType=RE";

        String fullResponse = httpsClient.getContentOfTheUrl(httpsRequest);
        String fieldResponse = httpsClient.getFieldResponse("&responseCode=", fullResponse);

        if (!fieldResponse.equals("D05")) {
            httpsClient.printFullResponse(fullResponse);
        }

        assertTrue("The Response Code should be D05 instead of " + fieldResponse, fieldResponse.equals("D05"));

    }

    @Test
    public void saleRequestWithTrackDataResponseCodeE02(){
        String httpsRequest = "https://sandbox.unitedthinkers.com/gates/xurl?" +
                "requestType=sale&" +
                "userName=test_api_user&" +
                "password=C8v20gAdHjig3LMRWGhm5PK1G00v08V1&" +
                "merchantAccountCode=2001&" +
                "accountType=R&" +
                "accountData=%25B4111111111111111%5ESmith%2FJohn%5E16041011000%201111A123456789012%3F&" +
                "amount=13000&" +
                "transactionIndustryType=RE";

        String fullResponse = httpsClient.getContentOfTheUrl(httpsRequest);
        String fieldResponse = httpsClient.getFieldResponse("&responseCode=", fullResponse);

        if (!fieldResponse.equals("E02")){
            httpsClient.printFullResponse(fullResponse);
        }

        assertTrue("The Response Code should be E02 instead of " + fieldResponse, fieldResponse.equals("E02"));


    }

    @Test
    public void saleRequestWithAccountNumberAvsResponseCode00(){
        String httpsRequest = "https://sandbox.unitedthinkers.com/gates/xurl?" +
                "requestType=sale&" +
                "userName=test_api_user&" +
                "password=C8v20gAdHjig3LMRWGhm5PK1G00v08V1&" +
                "merchantAccountCode=2001&" +
                "accountType=R&" +
                "holderName=John+Smith&" +
                "accountNumber=4111111111111111&" +
                "accountAccessory=1017&" +
                "amount=500&" +
                "transactionIndustryType=RE&" +
                "zipCode=11111";

        String fullResponse = httpsClient.getContentOfTheUrl(httpsRequest);
        String fieldResponse = httpsClient.getFieldResponse("&avsResponseCode=", fullResponse);

        if (!fieldResponse.equals("00")) {
            httpsClient.printFullResponse(fullResponse);
        }

        assertTrue("The AVS Response Code should be 00 instead of " + fieldResponse, fieldResponse.equals("00"));

    }

    @Test
    public void saleRequestWithAccountNumberAvsResponseCode46(){
        String httpsRequest = "https://sandbox.unitedthinkers.com/gates/xurl?" +
                "requestType=sale&" +
                "userName=test_api_user&" +
                "password=C8v20gAdHjig3LMRWGhm5PK1G00v08V1&" +
                "merchantAccountCode=2001&" +
                "accountType=R&" +
                "holderName=John+Smith&" +
                "accountNumber=4111111111111111&" +
                "accountAccessory=1017&" +
                "amount=500&" +
                "transactionIndustryType=RE&" +
                "zipCode=22222";

        String fullResponse = httpsClient.getContentOfTheUrl(httpsRequest);
        String fieldResponse = httpsClient.getFieldResponse("&avsResponseCode=", fullResponse);

        if (!fieldResponse.equals("46")) {
            httpsClient.printFullResponse(fullResponse);
        }

        assertTrue("The AVS Response Code should be 46 instead of " + fieldResponse, fieldResponse.equals("46"));

    }

    @Test
    public void saleRequestWithTrackDataAvsResponseCode43(){
        String httpsRequest = "https://sandbox.unitedthinkers.com/gates/xurl?" +
                "requestType=sale&" +
                "userName=test_api_user&" +
                "password=C8v20gAdHjig3LMRWGhm5PK1G00v08V1&" +
                "merchantAccountCode=2001&" +
                "accountType=R&" +
                "accountData=%25B4111111111111111%5ESmith%2FJohn%5E16041011000%201111A123456789012%3F&" +
                "amount=500&" +
                "transactionIndustryType=RE&" +
                "zipCode=33333";

        String fullResponse = httpsClient.getContentOfTheUrl(httpsRequest);
        String fieldResponse = httpsClient.getFieldResponse("&avsResponseCode=", fullResponse);

        if (!fieldResponse.equals("43")) {
            httpsClient.printFullResponse(fullResponse);
        }

        assertTrue("The AVS Response Code should be 43 instead of " + fieldResponse, fieldResponse.equals("43"));
    }

    @Test
    public void saleRequestWithTrackDataAvsResponseCode40(){
        String httpsRequest = "https://sandbox.unitedthinkers.com/gates/xurl?" +
                "requestType=sale&" +
                "userName=test_api_user&" +
                "password=C8v20gAdHjig3LMRWGhm5PK1G00v08V1&" +
                "merchantAccountCode=2001&" +
                "accountType=R&" +
                "accountData=%25B4111111111111111%5ESmith%2FJohn%5E16041011000%201111A123456789012%3F&" +
                "amount=500&" +
                "transactionIndustryType=RE&" +
                "zipCode=44444";

        String fullResponse = httpsClient.getContentOfTheUrl(httpsRequest);
        String fieldResponse = httpsClient.getFieldResponse("&avsResponseCode=", fullResponse);

        if (!fieldResponse.equals("40")) {
            httpsClient.printFullResponse(fullResponse);
        }

        assertTrue("The AVS Response Code should be 40 instead of " + fieldResponse, fieldResponse.equals("40"));
    }

    @Test
    public void saleRequestWithAccountNumberCscResponseCodeM(){
        String httpsRequest = "https://sandbox.unitedthinkers.com/gates/xurl?" +
                "requestType=sale&" +
                "userName=test_api_user&" +
                "password=C8v20gAdHjig3LMRWGhm5PK1G00v08V1&" +
                "merchantAccountCode=2001&" +
                "accountType=R&" +
                "holderName=John+Smith&" +
                "accountNumber=4111111111111111&" +
                "accountAccessory=1017&" +
                "amount=500&" +
                "transactionIndustryType=RE&" +
                "csc=111";

        String fullResponse = httpsClient.getContentOfTheUrl(httpsRequest);
        String fieldResponse = httpsClient.getFieldResponse("&cscResponseCode=", fullResponse);

        if (!fieldResponse.equals("M")) {
            httpsClient.printFullResponse(fullResponse);
        }

        assertTrue("The CSC Response Code should be M instead of " + fieldResponse, fieldResponse.equals("M"));
    }

    @Test
    public void saleRequestWithAccountNumberCscResponseCodeN(){
        String httpsRequest = "https://sandbox.unitedthinkers.com/gates/xurl?" +
                "requestType=sale&" +
                "userName=test_api_user&" +
                "password=C8v20gAdHjig3LMRWGhm5PK1G00v08V1&" +
                "merchantAccountCode=2001&" +
                "accountType=R&" +
                "holderName=John+Smith&" +
                "accountNumber=4111111111111111&" +
                "accountAccessory=1017&" +
                "amount=500&" +
                "transactionIndustryType=RE&" +
                "csc=222";

        String fullResponse = httpsClient.getContentOfTheUrl(httpsRequest);
        String fieldResponse = httpsClient.getFieldResponse("&cscResponseCode=", fullResponse);

        if (!fieldResponse.equals("N")) {
            httpsClient.printFullResponse(fullResponse);
        }

        assertTrue("The CSC Response Code should be N instead of " + fieldResponse, fieldResponse.equals("N"));
    }

    @Test
    public void saleRequestWithTrackDataCscResponseCodeP(){
        String httpsRequest = "https://sandbox.unitedthinkers.com/gates/xurl?" +
                "requestType=sale&" +
                "userName=test_api_user&" +
                "password=C8v20gAdHjig3LMRWGhm5PK1G00v08V1&" +
                "merchantAccountCode=2001&" +
                "accountType=R&" +
                "accountData=%25B4111111111111111%5ESmith%2FJohn%5E16041011000%201111A123456789012%3F&" +
                "amount=500&" +
                "transactionIndustryType=RE&" +
                "csc=333";

        String fullResponse = httpsClient.getContentOfTheUrl(httpsRequest);
        String fieldResponse = httpsClient.getFieldResponse("&cscResponseCode=", fullResponse);

        if (!fieldResponse.equals("P")) {
            httpsClient.printFullResponse(fullResponse);
        }

        assertTrue("The CSC Response Code should be P instead of " + fieldResponse, fieldResponse.equals("P"));
    }

    @Test
    public void saleRequestWithTrackDataCscResponseCodeS(){
        String httpsRequest = "https://sandbox.unitedthinkers.com/gates/xurl?" +
                "requestType=sale&" +
                "userName=test_api_user&" +
                "password=C8v20gAdHjig3LMRWGhm5PK1G00v08V1&" +
                "merchantAccountCode=2001&" +
                "accountType=R&" +
                "accountData=%25B4111111111111111%5ESmith%2FJohn%5E16041011000%201111A123456789012%3F&" +
                "amount=500&" +
                "transactionIndustryType=RE&" +
                "csc=444";

        String fullResponse = httpsClient.getContentOfTheUrl(httpsRequest);
        String fieldResponse = httpsClient.getFieldResponse("&cscResponseCode=", fullResponse);

        if (!fieldResponse.equals("S")) {
            httpsClient.printFullResponse(fullResponse);
        }

        assertTrue("The CSC Response Code should be S instead of " + fieldResponse, fieldResponse.equals("S"));
    }
}
