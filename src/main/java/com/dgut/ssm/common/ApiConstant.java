package com.dgut.ssm.common;

/**
 * The type Api constant.
 */
public class ApiConstant {
    public enum Code {
        OK(0, "success"),
        NG(101, "fail");
        public int code;
        public String msg;

        Code(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }


    }
}
