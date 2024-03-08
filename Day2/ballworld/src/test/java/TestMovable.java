import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.awt.Color;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.nhnacademy.MovableBall;

public class TestMovable {
    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> {
            MovableBall ball = new MovableBall(0, 0, 1, Color.BLACK);
            
            assertEquals(MovableBall.DEFAULT_DX, ball.getDX());
            assertEquals(MovableBall.DEFAULT_DY, ball.getDY());
        });
    }
    
    @ParameterizedTest
    @MethodSource("deltaProvider")
    void testDeltaXY(int x, int y, int radius, int DX, int DY) {
        assertDoesNotThrow(() -> {
            MovableBall ball = new MovableBall(x, y, radius, Color.BLACK);
            
            ball.setDX(DX);
            ball.setDY(DY);
            assertEquals(DX, ball.getDX());
            assertEquals(DY, ball.getDY());
        });
    }

    static Stream<Arguments> deltaProvider() {
        return Stream.of(
            Arguments.arguments(0,0,10, 0, 0),
            Arguments.arguments(0,0,10, Integer.MAX_VALUE, 0),
            Arguments.arguments(0,0,10, 0, Integer.MAX_VALUE),
            Arguments.arguments(0,0,10, 0, Integer.MAX_VALUE)
        );
    }

    @ParameterizedTest
    @MethodSource("moveProvider")
    void testDeltaXY(int x, int y, int radius, int dx, int dy, int count) {
        assertDoesNotThrow(() -> {
            MovableBall ball = new MovableBall(x, y, radius, Color.BLACK);
            
            ball.setDX(dx);
            ball.setDY(dy);

            int currentX = x;
            int currentY = y;
            
            for (int i = 0; i < count; i++) {
                ball.move();
                currentX += dx;
                currentY += dy;

                assertEquals(currentX, ball.getX());
                assertEquals(currentY, ball.getY());
            }

            assertEquals(x + dx * count, ball.getX());
            assertEquals(y + dy * count, ball.getY());
        });
    }

    static Stream<Arguments> moveProvider() {
        return Stream.of(
            Arguments.arguments(10,20,5, 5, 10, 10),
            Arguments.arguments(10,20,5, -5, 10, 100),
            Arguments.arguments(10,2,5, 5, -10, 1000)
        );
    }

    MovableBall ball;
    int startX = 0;
    int startY = 0;
    int deltaX = 10;
    int deltaY = 100;

    @BeforeAll
    void beforeRepeatedMove() {
        ball = new MovableBall(startX, startY, 0, Color.RED);
        ball.setDX(deltaX);
        ball.setDY(deltaY);
    }
    @RepeatedTest(10)
    void testRepeatedMove(RepetitionInfo repetitionInfo) {
        ball.move();
        assertEquals(startX + deltaX * repetitionInfo.getCurrentRepetition(), ball.getX());
        assertEquals(startY + deltaY * repetitionInfo.getCurrentRepetition(), ball.getY());
    }

}
