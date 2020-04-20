package model.pojos;

import java.util.List;

public class ChangeSkuDtls {
    public ChangeSkuDtls() {
        super();
    }
    
    private List<ChangeSku> chnageSkuDtls;
    private String statusCode;
    private String statusMsg;

    public void setChnageSkuDtls(List<ChangeSku> chnageSkuDtls) {
        this.chnageSkuDtls = chnageSkuDtls;
    }

    public List<ChangeSku> getChnageSkuDtls() {
        return chnageSkuDtls;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

}
