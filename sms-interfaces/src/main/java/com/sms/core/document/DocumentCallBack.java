package com.sms.core.document;

/**
 * Created by Ganesan on 20/06/16.
 */
public interface DocumentCallBack {

    String getUploaderName(String uploaderId);

    void updateUploader(UpdateInfo updateInfo);

}
