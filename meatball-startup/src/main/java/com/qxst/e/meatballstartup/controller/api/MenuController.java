package com.qxst.e.meatballstartup.controller.api;

import com.qxst.e.meatballstartup.service.SysMenuService;
import com.qxst.e.meatballstartup.state.ClientApiFinal;
import com.qxst.e.meatballstartup.util.json.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = ClientApiFinal.version+"sys/")
@ResponseBody
public class MenuController {
    @Resource
    private SysMenuService sysMenuService;

    @RequestMapping(value="findMenuList",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public @ResponseBody
    JsonUtil findCarList(@RequestBody JsonUtil jsonUtil) throws Exception {
        Map map = (Map) jsonUtil.getData();
        JsonUtil params = sysMenuService.getMenu(map,jsonUtil);
        return params;
    }
}
