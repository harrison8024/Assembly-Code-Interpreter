package sample;

import java.util.Arrays;

public class Instruction {
    final private String instruction; //Source in text
    private String code;        //Code in text
    private String address;     //Address in text
    private String opcode;
    private Integer register1;
    private Integer register2;
    private Integer register3;
    private String function;
    private Integer immediate;
    public Instruction(String instruction, int index){
        this.instruction = instruction;
        translate();
        setAddress(index);
        System.out.println("Address: " + this.address);
    }
    public void translate() {
        String copyInst = String.valueOf(this.instruction);
        // get first word
        String firstWord = copyInst.substring(0, copyInst.indexOf(" "));
        System.out.println("Firstword: " + firstWord);
        copyInst = copyInst.substring(copyInst.indexOf(" ")+1, copyInst.length());
        // get opcode
        this.opcode = OpCode.opcodeMap.get(firstWord);
        this.function = OpCode.functionCodeMap.get(firstWord);
        System.out.println("OPCODE: " + this.opcode);

        if(this.opcode.equals("11011")|| this.opcode.equals("11101")){
            // get immediate
            this.immediate = Integer.parseInt(copyInst);
            System.out.println("int: " + this.immediate);
            //get code
            this.code = this.opcode + "000" + getImmediateBinary(8);

        } else {
            String secondWord;
            String thirdWord;
            String fourthWord;
            // get
            switch (opcode){
                case "00000":
                case "00001":
                case "00010":
                case "00011":
                    // get secondWord
                    secondWord = copyInst.substring(0, copyInst.indexOf(","));
                    System.out.println("second Word: " + secondWord);
                    // get register1
                    this.register1 = OpCode.registerMap.get(secondWord);
                    System.out.println("register 1: " + this.register1);
                    //get register2
                    copyInst = copyInst.substring(copyInst.indexOf(" ")+1, copyInst.length());
                    thirdWord = copyInst.substring(0, copyInst.indexOf(","));
                    System.out.println("third Word: " + thirdWord);
                    this.register2 = OpCode.registerMap.get(thirdWord);
                    System.out.println("register 2: " + this.register2);
                    //get register3
                    fourthWord = copyInst.substring(copyInst.indexOf(" ")+1, copyInst.length());
                    System.out.println("third Word: " + fourthWord);
                    this.register3 = OpCode.registerMap.get(fourthWord);
                    System.out.println("register 3: " + this.register3);
                    //get function
                    this.function = OpCode.functionCodeMap.get(firstWord);
                    System.out.println("Function: " + this.function);
                    // get code
                    this.code = this.opcode + getBinaryRegister(this.register1) +
                            getBinaryRegister(this.register2) + getBinaryRegister(this.register3) +
                            this.function;

                    break;
                case "00100":
                case "11110":
                    secondWord = copyInst.substring(0, copyInst.indexOf(","));
                    System.out.println("second Word: " + secondWord);
                    // get register1
                    this.register1 = OpCode.registerMap.get(secondWord);
                    System.out.println("register 1: " + this.register1);
                    //get register2
                    thirdWord = copyInst.substring(copyInst.indexOf(" ")+1, copyInst.length());
                    System.out.println("third Word: " + thirdWord);
                    this.register2 = OpCode.registerMap.get(thirdWord);
                    System.out.println("register 2: " + this.register2);
                    if(!firstWord.equals("move")) {
                        //get function
                        this.function = OpCode.functionCodeMap.get(firstWord);
                        System.out.println("Function: " + this.function);
                    }
                    //get code
                    this.code = this.opcode + getBinaryRegister(this.register1) +
                            getBinaryRegister(this.register2) + "000" + this.function;
                    break;
                case "00101":
                    secondWord = copyInst;
                    System.out.println("second Word: " + secondWord);
                    // get register1
                    this.register1 = OpCode.registerMap.get(secondWord);
                    System.out.println("register 1: " + this.register1);
                    //get function
                    this.function = OpCode.functionCodeMap.get(firstWord);
                    System.out.println("Function: " + this.function);
                    // get code
                    this.code = this.opcode + getBinaryRegister(this.register1) + "000000" + this.function;
                    break;
                case "00110":
                case "00111":
                    secondWord = copyInst.substring(0, copyInst.indexOf(","));
                    System.out.println("second Word: " + secondWord);
                    // get register1
                    this.register1 = OpCode.registerMap.get(secondWord);
                    System.out.println("register 1: " + this.register1);
                    // get imm
                    thirdWord = copyInst.substring(copyInst.indexOf(" ")+1, copyInst.length());
                    System.out.println("third Word: " + thirdWord);
                    this.immediate = Integer.parseInt(thirdWord);
                    System.out.println("Imm: " + this.immediate);
                    // get function
                    this.function = OpCode.functionCodeMap.get(firstWord);
                    System.out.println("Function: " + this.function);
                    //get code
                    this.code = this.opcode + getBinaryRegister(this.register1) + getImmediateBinary(8) + this.function;
                    break;
                case "01000":
                case "01001":
                case "01010":
                case "01011":
                case "01100":
                case "01101":
                case "10000":
                case "10001":
                case "10010":
                case "10011":
                case "10100":
                case "10101":
                case "10110":
                case "10111":
                case "11000":
                case "11001":
                case "11010":
                    secondWord = copyInst.substring(0, copyInst.indexOf(","));
                    System.out.println("second Word: " + secondWord);
                    // get register1
                    this.register1 = OpCode.registerMap.get(secondWord);
                    System.out.println("register 1: " + this.register1);
                    //get register2
                    copyInst = copyInst.substring(copyInst.indexOf(" ")+1, copyInst.length());
                    thirdWord = copyInst.substring(0, copyInst.indexOf(","));
                    System.out.println("third Word: " + thirdWord);
                    this.register2 = OpCode.registerMap.get(thirdWord);
                    System.out.println("register 2: " + this.register2);
                    //get imm
                    fourthWord = copyInst.substring(copyInst.indexOf(" ")+1, copyInst.length());
                    System.out.println("fourth Word: " + fourthWord);
                    this.immediate = Integer.parseInt(fourthWord);
                    System.out.println("Imm: " + this.immediate);
                    // get code
                    this.code = this.opcode + getBinaryRegister(this.register1) +
                            getBinaryRegister(this.register2) + getImmediateBinary(5);
                    break;
                case "01110":
                case "01111":
                    secondWord = copyInst.substring(0, copyInst.indexOf(","));
                    System.out.println("second Word: " + secondWord);
                    // get register1
                    this.register1 = OpCode.registerMap.get(secondWord);
                    System.out.println("register 1: " + this.register1);
                    // get thirdWord
                    thirdWord = copyInst.substring(copyInst.indexOf(" ")+1, copyInst.length());
                    System.out.println("third Word: " + thirdWord);
                    // get imm
                    String immTemp = thirdWord.substring(0,thirdWord.indexOf("$")-1);
                    this.immediate = Integer.parseInt(immTemp);
                    System.out.println("Imm: " + this.immediate);
                    // get register2
                    String regTemp = thirdWord.substring(thirdWord.indexOf("$"), thirdWord.indexOf("$")+3);
                    System.out.println("regtemp: " + regTemp);
                    this.register2 = OpCode.registerMap.get(regTemp);
                    System.out.println("register2: " + this.register2);
                    // get code
                    this.code = this.opcode + getBinaryRegister(this.register1) +
                            getBinaryRegister(this.register2) + getImmediateBinary(5);
                    break;
                case "11100":
                    secondWord = copyInst;
                    System.out.println("second Word: " + secondWord);
                    // get register1
                    this.register1 = OpCode.registerMap.get(secondWord);
                    System.out.println("register 1: " + this.register1);
                    // get code
                    this.code = this.opcode + getBinaryRegister(this.register1) + getImmediateBinary(8);
                    break;
            }
        }
        System.out.println("Code String: " + this.code);
        System.out.println("Code: " + getCode());
    }

    public String getCode() {
        String temp = Integer.toString(Integer.parseInt(this.code, 2), 16);
        if(temp.length() == 1){
            return "0x000" + temp;
        } else if(temp.length() == 2){
            return "0x00" + temp;
        } else if(temp.length() == 3){
            return "0x0" + temp;
        } else {
            return "0x" + temp;
        }
    }
    public String getAddress(){
        return this.address;
    }
    public String getSource(){
        return this.instruction;
    }

    public String getBinaryRegister(Integer reg) {
        String temp = Integer.toBinaryString(reg);
        if(temp.length() == 1) {
            return "00"+temp;
        } else if(temp.length() == 2) {
            return "0" + temp;
        } else {
            return temp;
        }
    }

    public void setAddress(int index){
        String temp = Integer.toString(16384 + (2 * index), 16);
        this.address = "0x" + temp;
    }

    public String getImmediateBinary(int size) {
        String temp;
        if(size == 8){
            temp = Integer.toBinaryString(this.immediate & 0B11111111);
            if(temp.length() == 1){
                return "0000000" + temp;
            } else if(temp.length() == 2){
                return "000000" + temp;
            } else if(temp.length() == 3){
                return "00000" + temp;
            } else if(temp.length() == 4){
                return "0000" + temp;
            } else if(temp.length() == 5){
                return "000" + temp;
            } else if(temp.length() == 6){
                return "00" + temp;
            } else if(temp.length() == 7){
                return "0" + temp;
            } else {
                return temp;
            }
        } else if(size == 5) {
            temp = Integer.toBinaryString(this.immediate & 0B11111);
            if(temp.length() == 1){
                return "0000" + temp;
            } else if(temp.length() == 2){
                return "000" + temp;
            } else if(temp.length() == 3){
                return "00" + temp;
            } else if(temp.length() == 4){
                return "0" + temp;
            } else {
                return temp;
            }
        }
        return "ERROR";
    }

    public String getInstructionName(){
        return this.instruction.substring(0,this.instruction.indexOf(" "));
    }

    public Integer getRegister1(){ return this.register1; }
    public Integer getRegister2(){ return this.register2; }
    public Integer getRegister3(){ return this.register3; }
    public Integer getImmediate(){ return this.immediate; }

}
