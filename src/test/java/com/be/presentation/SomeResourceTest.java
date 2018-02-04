package com.be.presentation;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import com.be.business.SomeBean;
import com.be.business.SomeBeanService;
import com.be.helpers.OperationResult;
import org.junit.Rule;
import org.junit.rules.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class SomeResourceTest
{
    private SomeBean correctsmbTest;
    private SomeBean copysmbTest;
    private SomeBean deletedsmbTest;

    @Mock
    private SomeBeanService someBeanService;

    @InjectMocks
    SomeResource someResource;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void initsmbTest() throws Exception
    {
        correctsmbTest = new SomeBean();
        correctsmbTest.setId("1");
        copysmbTest = new SomeBean();
        copysmbTest.setId("1");
        deletedsmbTest = new SomeBean();
        deletedsmbTest.setId("100");

        when(someBeanService.getsmbTest("1")).thenReturn(correctsmbTest);
        when(someBeanService.getsmbTest("-1")).thenReturn(null);
        when(someBeanService.deletesmbTest("1")).thenReturn(OperationResult.ok());
        when(someBeanService.deletesmbTest("-1")).thenReturn(OperationResult.error("SomeBean is not found"));
        when(someBeanService.addsmbTest(deletedsmbTest)).thenReturn(OperationResult.ok("Success"));
        when(someBeanService.addsmbTest(copysmbTest)).thenReturn(OperationResult.error("SomeBean already exists"));
        when(someBeanService.updatesmbTest(correctsmbTest)).thenReturn(OperationResult.ok());
        when(someBeanService.updatesmbTest(deletedsmbTest)).thenReturn(OperationResult.error("SomeBean is not found"));
    }

    @Test
    public void getTestsmbTest() throws Exception
    {
        SomeBean someBean = someResource.getsmbTest("test");

        assertThat("We can get test SomeBean", someBean.getId(), equalTo("Test SomeBean Id"));
    }


    @Test
    public void getExistentsmbTest() throws Exception
    {
        SomeBean someBean = someResource.getsmbTest("1");

        assertThat("We can get existent SomeBean", someBean.getId(), equalTo(correctsmbTest.getId()));
    }

    @Test
    public void getNonexistentsmbTest() throws Exception
    {
        exceptionRule.expect(WebApplicationException.class);
        exceptionRule.expectMessage("404");

        someResource.getsmbTest("-1");
    }

    @Test
    public void deleteExistentsmbTest()
    {
        Response response = someResource.deletesmbTest("1");

        assertThat("We can delete existent SomeBean", response.getStatus(), equalTo(204));
    }

    @Test
    public void deleteNonexistentRobot()
    {
        Response response = someResource.deletesmbTest("-1");

        assertThat("We can't delete SomeBean that doesn't exist", response.getStatus(), equalTo(404));
    }

    @Test
    public void postNonexistentsmbTest() throws Exception
    {
        Response response = someResource.postsmbTest(deletedsmbTest);

        assertThat("We can add correct SomeBean", response.getStatus(), equalTo(204));
    }

    @Test
    public void postExistentsmbTest() throws Exception
    {
        Response response = someResource.postsmbTest(copysmbTest);

        assertThat("We can't add SomeBean that already exists", response.getStatus(), equalTo(400));
    }

    @Test
    public void putExistentsmbTest() throws Exception
    {
        Response response = someResource.putsmbTest(correctsmbTest.getId(), correctsmbTest);

        assertThat("We can update SomeBean that exists", response.getStatus(), equalTo(204));
    }

    @Test
    public void putNonexistentsmbTest() throws Exception
    {
        Response response = someResource.putsmbTest(correctsmbTest.getId(), deletedsmbTest);

        assertThat("We can't update SomeBean that doesn't exist", response.getStatus(), equalTo(404));
    }

    @Test
    public void putNullAssmbTest() throws Exception
    {
        exceptionRule.expect(WebApplicationException.class);
        exceptionRule.expectMessage("404");

        Response response = someResource.putsmbTest("", null);
    }

}

