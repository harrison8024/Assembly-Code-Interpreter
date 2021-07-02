package sample;

public class Address {
    private String address;
    private String v0;
    private String v2;
    private String v4;
    private String v6;
    private String v8;
    private String vA;
    private String vC;
    private String vE;

    public Address(){
        this.address = "";
        this.v0 = "0x0000";
        this.v2 = "0x0000";
        this.v4 = "0x0000";
        this.v6 = "0x0000";
        this.v8 = "0x0000";
        this.vA = "0x0000";
        this.vC = "0x0000";
        this.vE = "0x0000";
    }

    public Address(String address){
        this.address = address;
        this.v0 = "0x0000";
        this.v2 = "0x0000";
        this.v4 = "0x0000";
        this.v6 = "0x0000";
        this.v8 = "0x0000";
        this.vA = "0x0000";
        this.vC = "0x0000";
        this.vE = "0x0000";
    }

    public String getAddress(){
        return address;
    }

    public void setV(int num, String value){
        switch(num){
            case 0:
                this.v0 = value;
                break;
            case 2:
                this.v2 = value;
                break;
            case 4:
                this.v4 = value;
                break;
            case 6:
                this.v6 = value;
                break;
            case 8:
                this.v8 = value;
                break;
            case 10:
                this.vA = value;
                break;
            case 12:
                this.vC = value;
                break;
            case 14:
                this.vE = value;
                break;
        }
    }

    public String getV(int num){
        switch(num){
            case 0:
                return this.v0;
            case 2:
                return this.v2;
            case 4:
                return this.v4;
            case 6:
                return this.v6;
            case 8:
                return this.v8;
            case 10:
                return this.vA;
            case 12:
                return this.vC;
            case 14:
                return this.vE;
        }
        return "ERROR";
    }
    public String getV0(){
        return v0;
    }
    public String getV2(){
        return v2;
    }
    public String getV4(){
        return v4;
    }
    public String getV6(){
        return v6;
    }
    public String getV8(){
        return v8;
    }
    public String getVA(){
        return vA;
    }
    public String getVC(){
        return vC;
    }
    public String getVE(){
        return vE;
    }

}
