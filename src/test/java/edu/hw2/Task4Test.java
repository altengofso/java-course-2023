package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Calling Info")
    void testCallingInfo() {
        assertThat(Task4.callingInfo()).isEqualTo(
            new Task4.CallingInfo("edu.hw2.Task4Test", "testCallingInfo"));
    }
}
