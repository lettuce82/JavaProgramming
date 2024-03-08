import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.nhnacademy.Ball;

public class MyTestBall {
    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> {
            new Ball(0, 0, 1);
            new Ball(0, 0, Integer.MAX_VALUE);
            new Ball(100, 100, 100);
            new Ball(100, -100, 100);
            new Ball(-100, 100, 100);
            new Ball(-100, -100, 100);
            new Ball(Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1, 1);
            new Ball(Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1, 1);
            new Ball(Integer.MAX_VALUE - 1, Integer.MIN_VALUE + 1, 1);
            new Ball(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, 1);
        });
    }

    @ParameterizedTest
    @MethodSource("illegalArgumentExceptionProvider")
    void testConstructorWidthIllegalArgumentException(int x, int y, int radius) {
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            new Ball(x, y, radius);
        });
    }

    static Stream<Arguments> illegalArgumentExceptionProvider() {
        return Stream.of(
            Arguments.arguments(1,2,0), //반지름이 0일 때
            Arguments.arguments(1,2,-1), //반지름이 음수일 때
            Arguments.arguments(Integer.MAX_VALUE, Integer.MIN_VALUE, 1), //좌표가 int max 값
            Arguments.arguments(1, -1, Integer.MAX_VALUE), //반지름이 int max 값
            Arguments.arguments(0,0,0), //반지름이 0일 때
            Arguments.arguments(0,0,-0), //반지름이 음수일 때
            Arguments.arguments(0, 0, Integer.MIN_VALUE)); //좌표가 int max 값
    }

    @ParameterizedTest
    @MethodSource("toStringProvider")
    void testToString(int x, int y, int radius, String target) {
        assertDoesNotThrow(() -> {
            Ball ball = new Ball(x, y, radius);

            assertEquals(target, ball.toString());
        });
    }

    static Stream<Arguments> toStringProvider() {
        return Stream.of(
            Arguments.arguments(1,2,1, "(1,2,1)")); //반지름이 0일 때
    }
}
