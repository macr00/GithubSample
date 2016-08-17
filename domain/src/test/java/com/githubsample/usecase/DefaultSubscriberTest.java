package com.githubsample.usecase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import retrofit2.adapter.rxjava.HttpException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;

/**
 * Created by Rory_McCormack on 17/08/2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(HttpException.class)
public class DefaultSubscriberTest {

    @Mock private UseCase.BaseCallBack callback;
    @Mock private Throwable throwable;

    private HttpException httpException;
    private DefaultSubscriber<Object> defaultSubscriber;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        httpException = PowerMockito.mock(HttpException.class);
        Mockito.when(throwable.getCause()).thenReturn(httpException);

        defaultSubscriber = new DefaultSubscriber<>(callback);
    }

    @Test
    public void should_Call_OnGenericError_When_ThrowableIsNotHttpException() {
        defaultSubscriber.onError(new Throwable());

        Mockito.verify(callback).onGenericError();
        Mockito.verify(callback, never()).onServerError();
    }

    @Test
    public void should_Call_OnConnectionError_When_IsHttpException_And_ErrorCode_IsEqualHigher_500_And_Connected() {
        Mockito.when(httpException.code()).thenReturn(500);

        defaultSubscriber.onError(throwable);

        Mockito.verify(callback, never()).onGenericError();
        Mockito.verify(callback).onServerError();
    }

    @Test
    public void should_Call_OnGenericError_When_IsHttpException_And_ErrorCode_IsLessThan_500() {
        Mockito.when(httpException.code()).thenReturn(300);

        defaultSubscriber.onError(throwable);

        Mockito.verify(callback).onGenericError();
        Mockito.verify(callback, never()).onServerError();
    }
}