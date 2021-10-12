package cn.zm.tk.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;


/**
 * @author yeehaw
 */
@Data
public class Pagination implements Serializable {
    @Transient
    @JsonIgnore
    private Integer pageNum;
    @Transient
    @JsonIgnore
    private Integer pageSize;
    @Transient
    @JsonIgnore
    private Integer rows = 0;

    public Pagination() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

    @JsonSetter
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    @JsonSetter
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getRows() {
        return rows;
    }

    @JsonSetter
    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
