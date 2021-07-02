package sample;

import java.util.HashMap;

public class OpCode {
    public static HashMap<String, String> opcodeMap;
    public static HashMap<String, String> functionCodeMap;
    public static HashMap<String, Integer> registerMap;

    public OpCode(){
        initializeMap();
    }

    public void initializeMap(){
        opcodeMap = new HashMap<String, String>();
        functionCodeMap = new HashMap<String, String>();
        registerMap = new HashMap<String, Integer>();
        // 3-registers
        // 00000
        opcodeMap.put("add", "00000");
        opcodeMap.put("addu", "00000");
        opcodeMap.put("sub", "00000");
        opcodeMap.put("subu", "00000");
        // 00001
        opcodeMap.put("and", "00001");
        opcodeMap.put("or", "00001");
        opcodeMap.put("xor", "00001");
        opcodeMap.put("nor", "00001");
        // 00010
        opcodeMap.put("slt", "00010");
        opcodeMap.put("sltu", "00010");
        opcodeMap.put("mul", "00010");
        opcodeMap.put("sllv", "00010");
        // 00011
        opcodeMap.put("srlv", "00011");
        opcodeMap.put("srav", "00011");
        // 2-registers
        opcodeMap.put("mult", "00100");
        opcodeMap.put("multu", "00100");
        opcodeMap.put("div", "00100");
        opcodeMap.put("divu", "00100");
        opcodeMap.put("move", "11110");
        // 1-register
        opcodeMap.put("mfhi", "00101");
        opcodeMap.put("mflo", "00101");
        opcodeMap.put("mthi", "00101");
        opcodeMap.put("mtlo", "00101");
        //1-register-immediate
        opcodeMap.put("lui", "00110");
        opcodeMap.put("la", "00110");
        opcodeMap.put("li", "00110");
        // 00111
        opcodeMap.put("blez", "00111");
        opcodeMap.put("bjtz", "00111");
        // 2-registers-immediate
        opcodeMap.put("addi", "01000");
        opcodeMap.put("addiu", "01001");
        opcodeMap.put("andi", "01010");
        opcodeMap.put("ori", "01011");
        opcodeMap.put("nori", "01100");
        opcodeMap.put("xori", "01101");
        opcodeMap.put("lw", "01110");
        opcodeMap.put("sw", "01111");
        opcodeMap.put("sll", "10000");
        opcodeMap.put("srl", "10001");
        opcodeMap.put("sra", "10010");
        opcodeMap.put("slti", "10011");
        opcodeMap.put("sltiu", "10100");
        opcodeMap.put("beq", "10101");
        opcodeMap.put("bne", "10110");
        opcodeMap.put("bgt", "10111");
        opcodeMap.put("bge", "11000");
        opcodeMap.put("blt", "11001");
        opcodeMap.put("ble", "11010");
        // jump
        opcodeMap.put("j", "11011");
        opcodeMap.put("jr", "11100");
        opcodeMap.put("jal", "11101");

        // function code map
        // opcode=00000
        functionCodeMap.put("add", "00");
        functionCodeMap.put("addu", "01");
        functionCodeMap.put("sub", "10");
        functionCodeMap.put("subu", "11");
        // opcode=00001
        functionCodeMap.put("and", "00");
        functionCodeMap.put("or", "01");
        functionCodeMap.put("xor", "10");
        functionCodeMap.put("nor", "11");
        // opcode=00010
        functionCodeMap.put("slt", "00");
        functionCodeMap.put("sltu", "01");
        functionCodeMap.put("mul", "10");
        functionCodeMap.put("sllv", "11");
        // opcode=00011
        functionCodeMap.put("srlv", "00");
        functionCodeMap.put("srav", "00");
        // opcode=00100
        functionCodeMap.put("mult", "00");
        functionCodeMap.put("multu", "01");
        functionCodeMap.put("div", "10");
        functionCodeMap.put("divu", "11");
        // opcode=00101
        functionCodeMap.put("mfhi", "00");
        functionCodeMap.put("mflo", "01");
        functionCodeMap.put("mthi", "10");
        functionCodeMap.put("mtlo", "11");
        // opcode=00110
        functionCodeMap.put("lui", "00");
        functionCodeMap.put("la", "01");
        functionCodeMap.put("li", "10");
        functionCodeMap.put("move", "11");
        // opcode=00111
        functionCodeMap.put("blez", "00");
        functionCodeMap.put("bgtz", "01");

        // Registers
        registerMap.put("$r1", 0);
        registerMap.put("$r2", 1);
        registerMap.put("$r3", 2);
        registerMap.put("$r4", 3);
        registerMap.put("$r5", 4);
        registerMap.put("$r6", 5);
        registerMap.put("$r7", 6);
        registerMap.put("$r8", 7);


    }


}



























































