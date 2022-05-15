package com.admsoft.mdsaml;

import lombok.Value;


public class tmpClient {
    public String getTmpName() {
        return tmpName;
    }

    public void setTmpName(String tmpName) {
        this.tmpName = tmpName;
    }

    public String getTmpType() {
        return tmpType;
    }

    public void setTmpType(String tmpType) {
        this.tmpType = tmpType;
    }

    String tmpName;
    String tmpType;


}
