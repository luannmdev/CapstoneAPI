package com.screens.shelf.controller;

import com.screens.shelf.form.RequestShelfListForm;
import com.screens.shelf.form.ResponseShelfListForm;
import com.screens.shelf.service.ShelfService;
import com.util.ResponseSupporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("admin/manager/store")
public class ShelfController {
    private static final String MSG_009 = "MSG-009";
    private static final String MSG_063 = "MSG-063";

    @Autowired
    private ShelfService shelfService;

    @RequestMapping(value = "/shelves", method = RequestMethod.POST)
    public String getShelves(@Validated @RequestBody RequestShelfListForm requestForm,
                             BindingResult result){

        if(result.hasErrors()){
            return ResponseSupporter.responseErrorResult(result);
        }

        ResponseShelfListForm responseForm = shelfService.getShelfList(requestForm);

        if(responseForm == null){
            List<String> errorCodes = new ArrayList<>();
            errorCodes.add(MSG_009);
            return ResponseSupporter.responseErrorResult(errorCodes);
        }
        return ResponseSupporter.resonpseResult(responseForm);
    }
}
