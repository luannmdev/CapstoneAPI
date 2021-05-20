package com.screens.manager.controller;

import com.screenname_example.form.RequestGetAccountForm;
import com.screenname_example.form.ResponseGetAccountForm;
import com.screens.manager.form.RequestManagerDetailForm;
import com.screens.manager.form.RequestManagerListForm;
import com.screens.manager.form.ResponseManagerDetailForm;
import com.screens.manager.form.ResponseManagerListForm;
import com.screens.manager.service.ManagerService;
import com.util.ResponseSupporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("admin")
public class ManagerController {
    private static String MSG_009 = "MSG-009";
    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/managers", method = RequestMethod.POST)
    public String getAccount(Model model,//
                             @Validated @RequestBody RequestManagerListForm requestManagerListForm, //
                             BindingResult result) {
        if(result.hasErrors()){
            return ResponseSupporter.responseErrorResult(result);
        }

        ResponseManagerListForm response = managerService.getManagerList(requestManagerListForm);
        if(response == null){
            List<String> errorCodes = new ArrayList<>();
            errorCodes.add(MSG_009);
            return ResponseSupporter.responseErrorResult(errorCodes);
        }

        return ResponseSupporter.resonpseResult(response);
    }

    @GetMapping(value = "/manager")
    public String getAccount(@Valid RequestManagerDetailForm requestForm, BindingResult result) {
        if(result.hasErrors()){
            return ResponseSupporter.responseErrorResult(result);
        }

        ResponseManagerDetailForm response = managerService.getManagerDetail(requestForm);
        if(response == null){
            List<String> errorCodes = new ArrayList<>();
            errorCodes.add(MSG_009);
            return ResponseSupporter.responseErrorResult(errorCodes);
        }

        return ResponseSupporter.resonpseResult(response);
    }

}
