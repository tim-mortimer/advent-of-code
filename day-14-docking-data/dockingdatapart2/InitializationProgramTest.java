package dockingdatapart2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InitializationProgramTest {

    @Test
    public void example_1() {
        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("mask = 000000000000000000000000000000X1001X");
        instructions.add("mem[42] = 100");
        instructions.add("mask = 00000000000000000000000000000000X0XX");
        instructions.add("mem[26] = 1");

        InitializationProgram program = new InitializationProgram();
        Map<Long, Long> output = program.execute(instructions);

        assertEquals(Map.of(
                16L, 1L,
                17L, 1L,
                18L, 1L,
                19L, 1L,
                24L, 1L,
                25L, 1L,
                26L, 1L,
                27L, 1L,
                58L, 100L,
                59L, 100L
        ), output);
    }
}
