
package com.ifpb.dac.entidades;

import java.util.List;

/**
 *
 * @author rodrigobento
 */
public class DadoSala {
    
    private int code;
    private int status;
    private List<Sala> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Sala> getData() {
        return data;
    }

    public void setData(List<Sala> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "" + data + '}';
    }
    
}
