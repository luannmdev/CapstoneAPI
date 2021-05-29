package com.screens.camera.service;

import com.common.service.BaseService;
import com.screens.camera.dao.mapper.CameraMapper;
import com.screens.camera.dto.CameraDTO;
import com.screens.camera.form.RequestAvailableCameraListForm;
import com.screens.camera.form.ResponseAvailableCameraListForm;
import com.screens.manager.service.ManagerService;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CameraService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(CameraService.class);
    @Autowired
    private CameraMapper cameraMapper;

    public ResponseAvailableCameraListForm getAvailableCameraList(RequestAvailableCameraListForm requestForm){
        ResponseAvailableCameraListForm responseForm = null;
        CameraDTO cameraDTO = new CameraDTO();
        convertRequestAvailableCameraListFormToCameraDTO(requestForm, cameraDTO);

        try{
            responseForm = cameraMapper.getAvailableCameraList(cameraDTO);
        }catch (PersistenceException e){
            logger.error("Error at ManagerService: " + e.getMessage());
        }

        return responseForm;
    }

    private void convertRequestAvailableCameraListFormToCameraDTO(RequestAvailableCameraListForm requestForm, CameraDTO cameraDTO){
        cameraDTO.setTypeDetect(requestForm.getTypeDetect());

        cameraDTO.setOffSet(DEFAULT_OFF_SET);
        if(requestForm.getPageNum() > 0){
            cameraDTO.setOffSet((requestForm.getPageNum() - 1) * requestForm.getFetchNext());
        }

        cameraDTO.setFetchNext(DEFAULT_FETCH_NEXT);
        if(requestForm.getFetchNext() > 0){
            cameraDTO.setFetchNext(requestForm.getFetchNext());
        }
    }
}
