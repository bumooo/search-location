package com.search.location.helper;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

@ExtendWith(MockitoExtension.class)
public abstract class UnitTestHelper {

    protected <T> OngoingStubbing<T> when(T methodCall) {
        return Mockito.when(methodCall);
    }

    protected <T> ObjectAssert<?> assertThat(T actual) {
        return Assertions.assertThat(actual);
    }
}
