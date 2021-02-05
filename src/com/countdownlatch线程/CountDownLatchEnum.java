package com.countdownlatch线程;

public enum CountDownLatchEnum {
    ONE(1,"春"),TWO(2,"夏"),THREE(3,"秋"),FOUR(4,"冬");
    private int code;
    private String msg;

    CountDownLatchEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static CountDownLatchEnum count_downLatchMsg(int index){
        CountDownLatchEnum[] downLatchEnums = CountDownLatchEnum.values();
        for (CountDownLatchEnum anEnum : downLatchEnums) {
             if(index==anEnum.getCode()){
                 return anEnum;
             }
        }
        return null;
    }
}
