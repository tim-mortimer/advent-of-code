package dockingdata;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InitializationProgramTest {

    @Test
    public void example_1() {
        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X");
        instructions.add("mem[8] = 11");
        instructions.add("mem[7] = 101");
        instructions.add("mem[8] = 0");

        InitializationProgram program = new InitializationProgram();
        Map<Integer, Long> output = program.execute(instructions);

        assertEquals(Map.of(
                7, 101,
                8, 64
        ), output);
    }
}
