package org.ricardo.wms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    public JsonResult(String msg){
        this.msg = msg;
    }

    private boolean success;
    private String msg;
}
