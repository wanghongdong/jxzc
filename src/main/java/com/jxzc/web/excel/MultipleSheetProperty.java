package com.jxzc.web.excel;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import lombok.Data;

import java.util.List;

/**
 * @ClassPath com.jxzc.web.excel.MultipleSheetProperty
 * @ClassName MultipleSheetProperty
 * @Description excel导出多sheet
 * @Author whd
 * @Date 2019/8/27 20:11
 * @Version 1.0
 */
@Data
public class MultipleSheetProperty {

    private List<? extends BaseRowModel> data;

    private Sheet sheet;

    public boolean isNull(){
        return data == null && sheet == null;
    }
}
