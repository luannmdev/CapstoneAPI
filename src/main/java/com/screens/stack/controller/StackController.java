package com.screens.stack.controller;

import com.screens.stack.form.RequestGetStackDetailForm;
import com.screens.stack.form.ResponseStackDetailForm;
import com.screens.stack.service.StackService;
import com.util.ResponseSupporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StackController {

    @Autowired
    private StackService stackService;


    private static final String MSG_022 = "MSG-022";

    @GetMapping(value = "/admin/manager/store/shelf/stack")
    public String getStackDetail(
            @Validated RequestGetStackDetailForm requestForm,
            BindingResult result){
        // Check Validate
        if(result.hasErrors()){
            return ResponseSupporter.responseErrorResult(result);
        }
        // Do Get Store Detail
        ResponseStackDetailForm responseStackDetailForm = stackService.getStackDetail(requestForm);
        if(responseStackDetailForm == null){
            List<String> errorCodes = new ArrayList<>();
            errorCodes.add(MSG_022);
            return ResponseSupporter.responseErrorResult(errorCodes);
        }
        // Return result
        return ResponseSupporter.resonpseResult(responseStackDetailForm);
    }

}
