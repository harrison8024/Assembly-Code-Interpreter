package sample;

public class Register {

    private String name;
    private Integer number;
    private String value;

    public Register(){
        this.name = "";
        this.number = 0;
        this.value = "";
    }

    public Register(String name, Integer number, String value){
        this.name = name;
        this.number = number;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public String getNumber(){
        if(number < 0){
            return "";
        }
        return String.valueOf(number);
    }

    public String getValue(){
        return "0x" + value;
    }

//    public void setUnsignedValue(Integer num){
//        String temp = Integer.toString(num, 16);
//        if(temp.length() == 1){
//            this.value = "000" + temp;
//        } else if(temp.length() == 2){
//            this.value = "00" + temp;
//        } else if(temp.length() == 3){
//            this.value = "0" + temp;
//        } else {
//            this.value = temp;
//        }
//        System.out.println("SET value unsigned: " + this.value);
//    }
    public void setValueString(String value){
        this.value = value.substring(2);
    }

    public void setValue(Integer num){
        String temp = Integer.toHexString(num & 0xFFFF);
        if(temp.length() == 1){
            this.value = "000" + temp;
        } else if(temp.length() == 2){
            this.value = "00" + temp;
        } else if(temp.length() == 3){
            this.value = "0" + temp;
        } else {
            this.value = temp;
        }
        System.out.println("SET value signed: " + this.value);
    }

    public Integer getUnsignedValue(){
        return Integer.parseInt(this.value, 16);
    }

    public Short getSignedValue(){
        return (short) Integer.parseInt(this.value, 16);
    }
}
