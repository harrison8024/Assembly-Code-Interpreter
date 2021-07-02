package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.ArrayList;

public class Controller {

    // Variable for menu and text edit
    @FXML
    private TextArea textArea;
    @FXML
    private MenuItem buildButton;
    @FXML
    private MenuItem runButton;

    // Variable for Register Table
    @FXML
    private TableView<Register> registers;
    @FXML
    private TableColumn<Register, String> nameColumn;
    @FXML
    private TableColumn<Register, Integer> numberColumn;
    @FXML
    private TableColumn<Register, String> valueColumn;

    //Variable for Address Table
    @FXML
    private TableView<Address> addresses;
    @FXML
    private TableColumn<Address, String> addressColumn;
    @FXML
    private TableColumn<Address, String> valueP0Column;
    @FXML
    private TableColumn<Address, String> valueP2Column;
    @FXML
    private TableColumn<Address, String> valueP4Column;
    @FXML
    private TableColumn<Address, String> valueP6Column;
    @FXML
    private TableColumn<Address, String> valueP8Column;
    @FXML
    private TableColumn<Address, String> valuePAColumn;
    @FXML
    private TableColumn<Address, String> valuePCColumn;
    @FXML
    private TableColumn<Address, String> valuePEColumn;

    @FXML
    private TableView<Instruction> execute;
    @FXML
    private TableColumn<Instruction, String> exeAddCol;
    @FXML
    private TableColumn<Instruction, String> exeCodeCol;
    @FXML
    private TableColumn<Instruction, String> exeSourCol;


    private ArrayList<Register> registerArrayList;

    private ArrayList<Instruction> instructionArrayList;

    private ArrayList<Address> addressArrayList;

    private int programCounter;

    public void build(){
        System.out.println("Build is Clicked");
        // Get text from edit window
        String text = textArea.getText();
        System.out.println("Text: " + text);

        String[] lines = text.split("\n");
        // Convert text to code
        OpCode op = new OpCode();
        instructionArrayList = new ArrayList<Instruction>();
        for(int i=0; i < lines.length; i++) {
            instructionArrayList.add(new Instruction(lines[i], i));
        }


    }

    public void run(){
        programCounter = 0;
        System.out.println("Running...");
        for(Instruction item:instructionArrayList){
            executeIns(item);
            programCounter++;
        }
        registerArrayList.get(8).setValue(1024+programCounter*2);
        updateExecute();
        updateRegister();

    }

    // execute instruction
    public void executeIns(Instruction inst){
        System.out.println("Instruction Name: " + inst.getInstructionName());
        Short num1Short;
        Short num2Short;
        Integer num1Int;
        Integer num2Int;
        switch(inst.getInstructionName()){
            case "add":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = registerArrayList.get(inst.getRegister3()).getUnsignedValue();
                if(num1Int+num2Int > 0xffff){
                    System.out.println("Exception: ADD Overflow");
                }
                registerArrayList.get(inst.getRegister1()).setValue(num1Int+num2Int);
                break;
            case "addu":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = registerArrayList.get(inst.getRegister3()).getUnsignedValue();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int+num2Int);
                break;
            case "sub":
            case "subu":
                num1Short = registerArrayList.get(inst.getRegister2()).getSignedValue();
                num2Short = registerArrayList.get(inst.getRegister3()).getSignedValue();
                registerArrayList.get(inst.getRegister1()).setValue(num1Short-num2Short);
                break;
            case "and":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = registerArrayList.get(inst.getRegister3()).getUnsignedValue();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int&num2Int);
                break;
            case "or":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = registerArrayList.get(inst.getRegister3()).getUnsignedValue();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int|num2Int);
                break;
            case "xor":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = registerArrayList.get(inst.getRegister3()).getUnsignedValue();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int^num2Int);
                break;
            case "nor":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = registerArrayList.get(inst.getRegister3()).getUnsignedValue();
                registerArrayList.get(inst.getRegister1()).setValue(~(num1Int|num2Int));
                break;
            case "slt":
                num1Short = registerArrayList.get(inst.getRegister2()).getSignedValue();
                num2Short = registerArrayList.get(inst.getRegister3()).getSignedValue();
                if(num1Short<num2Short){
                    registerArrayList.get(inst.getRegister1()).setValue(1);
                } else {
                    registerArrayList.get(inst.getRegister1()).setValue(0);
                }
                break;
            case "sltu":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = registerArrayList.get(inst.getRegister3()).getUnsignedValue();
                if(num1Int<num2Int){
                    registerArrayList.get(inst.getRegister1()).setValue(1);
                } else {
                    registerArrayList.get(inst.getRegister1()).setValue(0);
                }
                break;
            case "mul":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = registerArrayList.get(inst.getRegister3()).getUnsignedValue();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int*num2Int);
                break;
            case "sllv":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = registerArrayList.get(inst.getRegister3()).getUnsignedValue();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int<<num2Int);
                break;
            case "srlv":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = registerArrayList.get(inst.getRegister3()).getUnsignedValue();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int>>>num2Int);
                break;
            case "srav":
                num1Short = registerArrayList.get(inst.getRegister2()).getSignedValue();
                num2Short = registerArrayList.get(inst.getRegister3()).getSignedValue();
                registerArrayList.get(inst.getRegister1()).setValue(num1Short>>num2Short);
                break;
            case "mult":
            case "multu":
                num1Int = registerArrayList.get(inst.getRegister1()).getUnsignedValue();
                num2Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                registerArrayList.get(9).setValue(((num1Int*num2Int)>>16)&0xffff);
                registerArrayList.get(10).setValue((num1Int*num2Int)&0xffff);
                break;
            case "div":
            case "divu":
                num1Short = registerArrayList.get(inst.getRegister1()).getSignedValue();
                num2Short = registerArrayList.get(inst.getRegister2()).getSignedValue();
                registerArrayList.get(9).setValue(num1Short/num2Short);
                registerArrayList.get(10).setValue(num1Short%num2Short);
                break;
            case "move":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int);
                break;
            case "mfhi":
                num1Int = registerArrayList.get(9).getUnsignedValue();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int);
                break;
            case "mflo":
                num1Int = registerArrayList.get(10).getUnsignedValue();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int);
                break;
            case "mthi":
                num1Int = registerArrayList.get(inst.getRegister1()).getUnsignedValue();
                registerArrayList.get(9).setValue(num1Int);
                break;
            case "mtlo":
                num1Int = registerArrayList.get(inst.getRegister1()).getUnsignedValue();
                registerArrayList.get(10).setValue(num1Int);
                break;
            case "lui":
                num1Int = inst.getImmediate();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int<<8);
                break;
            case "la":
            case "li":
                registerArrayList.get(inst.getRegister1()).setValue(inst.getImmediate());
                break;
            case "blez":
            case "bgtz":
                // Do something
                break;
            case "addi":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = inst.getImmediate();
                if(num1Int+num2Int > 0xffff){
                    System.out.println("Exception: ADD Overflow");
                }
                registerArrayList.get(inst.getRegister1()).setValue(num1Int+num2Int);
                break;
            case "addiu":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = inst.getImmediate();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int+num2Int);
                break;
            case "andi":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = inst.getImmediate();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int&num2Int);
                break;
            case "ori":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = inst.getImmediate();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int|num2Int);
                break;
            case "nori":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = inst.getImmediate();
                registerArrayList.get(inst.getRegister1()).setValue(~(num1Int|num2Int));
                break;
            case "xori":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = inst.getImmediate();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int^num2Int);
                break;
            case "sll":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = inst.getImmediate();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int<<num2Int);
                break;
            case "srl":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = inst.getImmediate();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int>>>num2Int);
                break;
            case "sra":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = inst.getImmediate();
                registerArrayList.get(inst.getRegister1()).setValue(num1Int>>num2Int);
                break;
            case "slti":
                num1Short = registerArrayList.get(inst.getRegister2()).getSignedValue();
                num2Int = inst.getImmediate();
                if(num1Short<num2Int){
                    registerArrayList.get(inst.getRegister1()).setValue(1);
                } else {
                    registerArrayList.get(inst.getRegister1()).setValue(0);
                }
                break;
            case "sltiu":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = inst.getImmediate();
                String temp = Integer.toHexString(num2Int & 0xFFFF);
                num2Int = Integer.parseInt(temp, 16);
                System.out.println("NUM " + num2Int);
                if(num1Int<num2Int){
                    registerArrayList.get(inst.getRegister1()).setValue(1);
                } else {
                    registerArrayList.get(inst.getRegister1()).setValue(0);
                }
                break;
            case "beq":
            case "bne":
            case "bgt":
            case "bge":
            case "blt":
            case "ble":
                break;
            case "lw":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = inst.getImmediate();
                int v = num2Int+num1Int;
                int r = (v-64)/16;
                System.out.println("ROW" + r);
                int c = (v-64)%16;
                System.out.println("COL" + c);
                String word = addressArrayList.get(r).getV(c);
                registerArrayList.get(inst.getRegister1()).setValueString(word);
                break;
            case "sw":
                num1Int = registerArrayList.get(inst.getRegister2()).getUnsignedValue();
                num2Int = inst.getImmediate();
                int value = num2Int+num1Int;
                int row = (value-64)/16;
                System.out.println("ROW" + row);
                int col = (value-64)%16;
                System.out.println("COL" + col);
                System.out.println("VAL" + registerArrayList.get(inst.getRegister1()).getValue());
                addressArrayList.get(row).setV(col, registerArrayList.get(inst.getRegister1()).getValue());
                updateAddress();
                break;
            case "j":
            case "jr":
            case "jal":

        }
    }

    // update execute code
    public void updateExecute(){
        exeAddCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        exeCodeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        exeSourCol.setCellValueFactory(new PropertyValueFactory<>("source"));
        ObservableList<Instruction> observableList = FXCollections.observableArrayList();
        for(int i=0; i < instructionArrayList.size(); i++){
            observableList.add(instructionArrayList.get(i));
        }
        execute.setItems(observableList);
    }

    // update value in register
    public void updateRegister(){
        // clear all values
        registers.getItems().clear();
        //Register Name column
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        //Register Number column
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        //Register Value column
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        ObservableList<Register> observableList = FXCollections.observableArrayList();
        for(int i=0; i < registerArrayList.size(); i++){
            observableList.add(registerArrayList.get(i));
        }
        registers.setItems(observableList);
    }

    public void updateAddress(){
        // clear all values
        addresses.getItems().clear();
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        valueP0Column.setCellValueFactory(new PropertyValueFactory<>("v0"));
        valueP2Column.setCellValueFactory(new PropertyValueFactory<>("v2"));
        valueP4Column.setCellValueFactory(new PropertyValueFactory<>("v4"));
        valueP6Column.setCellValueFactory(new PropertyValueFactory<>("v6"));
        valueP8Column.setCellValueFactory(new PropertyValueFactory<>("v8"));
        valuePAColumn.setCellValueFactory(new PropertyValueFactory<>("vA"));
        valuePCColumn.setCellValueFactory(new PropertyValueFactory<>("vC"));
        valuePEColumn.setCellValueFactory(new PropertyValueFactory<>("vE"));
        ObservableList<Address> observableList = FXCollections.observableArrayList();
        for(int i=0; i < addressArrayList.size(); i++){
            observableList.add(addressArrayList.get(i));
        }
        addresses.setItems(observableList);
    }

    //List for Address
    public ObservableList<Address> initializeAddress(){
        addressArrayList = new ArrayList<Address>();
        addressArrayList.add(new Address("0x0040"));
        addressArrayList.add(new Address("0x0050"));
        addressArrayList.add(new Address("0x0060"));
        addressArrayList.add(new Address("0x0070"));
        addressArrayList.add(new Address("0x0080"));

        ObservableList<Address> observableList = FXCollections.observableArrayList();
        for(int i=0; i < addressArrayList.size(); i++){
            observableList.add(addressArrayList.get(i));
        }
        return observableList;
    }

    public void setInitialAddress(){
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        valueP0Column.setCellValueFactory(new PropertyValueFactory<>("v0"));
        valueP2Column.setCellValueFactory(new PropertyValueFactory<>("v2"));
        valueP4Column.setCellValueFactory(new PropertyValueFactory<>("v4"));
        valueP6Column.setCellValueFactory(new PropertyValueFactory<>("v6"));
        valueP8Column.setCellValueFactory(new PropertyValueFactory<>("v8"));
        valuePAColumn.setCellValueFactory(new PropertyValueFactory<>("vA"));
        valuePCColumn.setCellValueFactory(new PropertyValueFactory<>("vC"));
        valuePEColumn.setCellValueFactory(new PropertyValueFactory<>("vE"));

        addresses.setItems(initializeAddress());
    }

    //List for Registers
    public ObservableList<Register> initializeRegisters(){
        registerArrayList = new ArrayList<Register>();
        registerArrayList.add(new Register("$r1", 0, "0000"));
        registerArrayList.add(new Register("$r2", 1, "0000"));
        registerArrayList.add(new Register("$r3", 2, "0000"));
        registerArrayList.add(new Register("$r4", 3, "0000"));
        registerArrayList.add(new Register("$r5", 4, "0000"));
        registerArrayList.add(new Register("$r6", 5, "0000"));
        registerArrayList.add(new Register("$r7", 6, "0000"));
        registerArrayList.add(new Register("$r8", 7, "0000"));
        registerArrayList.add(new Register("pc", -1, "4000"));
        registerArrayList.add(new Register("hi", -1, "0000"));
        registerArrayList.add(new Register("lo", -1, "0000"));
        ObservableList<Register> observableList = FXCollections.observableArrayList();
        for(int i=0; i < registerArrayList.size(); i++){
            observableList.add(registerArrayList.get(i));
        }
        return observableList;
    }

    public void setInitialRegisters(){
        //Register Name column
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        //Register Number column
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        //Register Value column
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        registers.setItems(initializeRegisters());
    }

}
