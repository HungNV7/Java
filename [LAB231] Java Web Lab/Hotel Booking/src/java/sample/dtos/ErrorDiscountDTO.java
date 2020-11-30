/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

/**
 *
 * @author ASUS
 */
public class ErrorDiscountDTO {
    private String errorCode, errorName, errorPercentage, errorDate;

    public ErrorDiscountDTO() {
    }

    public ErrorDiscountDTO(String errorCode, String errorName, String errorPercentage, String errorDate) {
        this.errorCode = errorCode;
        this.errorName = errorName;
        this.errorPercentage = errorPercentage;
        this.errorDate = errorDate;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorPercentage() {
        return errorPercentage;
    }

    public void setErrorPercentage(String errorPercentage) {
        this.errorPercentage = errorPercentage;
    }

    public String getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(String errorDate) {
        this.errorDate = errorDate;
    }
    
    
}
