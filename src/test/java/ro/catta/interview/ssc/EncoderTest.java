package ro.catta.interview.ssc;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EncoderTest {

    static Stream<Arguments> testEncodeParams() {
        return Stream.of(//
                Arguments.of("aaaabbbccc", "a4b3c3"), //
                Arguments.of("abbbcdddd", "a1b3c1d4"), //
                Arguments.of("aaabbwwaa", "a3b2w2a2"), //
                Arguments.of("a", "a1"), //
                Arguments.of("aa", "a2") //
        );
    }

    @ParameterizedTest
    @MethodSource("testEncodeParams")
    public void testEncode(String input, String expected) {
        // Arrange
        Encoder underTest = new Encoder();

        // Act
        String actual = underTest.encode(input);

        // Assert
        Assertions.assertEquals(expected, actual, () -> String.format("for input %s", input));

    }

    @ParameterizedTest
    @MethodSource("testEncodeParams")
    public void testEncodeWithStream(String input, String expected) {
        // Arrange
        Encoder underTest = new Encoder();

        // Act
        String actual = underTest.encodeWithStream(input);

        // Assert
        Assertions.assertEquals(expected, actual, () -> String.format("for input %s", input));

    }

}
