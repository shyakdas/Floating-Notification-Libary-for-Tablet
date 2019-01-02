package com.example.floatingnotification.models;

import com.example.floatingnotification.utils.Constants;

import java.util.ArrayList;

import static com.example.floatingnotification.utils.Constants.SUCCESS;

public class MessageParser {

    public static DataModel<String> addSingleMessage(String message, @Constants.MessageType String type) {
        return DataModel.singleMessage(message, type, message);
    }

    public static DataModel<ArrayList<DataModel<String>>> addMultipleMessage(ConflictData data, String message) {
        @Constants.MessageType String type = null;
        ArrayList<DataModel<String>> dataModels = new ArrayList<>();
        boolean isSuccessAvailable = false;
        if (!data.getSuccess_messages().isEmpty()) {
            type = SUCCESS;
            isSuccessAvailable = true;
            for (String singleMessage : data.getSuccess_messages()) {
                dataModels.add(DataModel.singleMessage(singleMessage, Constants.SUCCESS, message));
            }
        }
        if (!data.getError_messages().isEmpty()) {
            ArrayList<DataModel<String>> errors = new ArrayList<>();
            if (isSuccessAvailable)
                type = Constants.CONFLICT;
            else
                type = Constants.FAILED;

            for (String singleMessage : data.getError_messages()) {
                if (isSuccessAvailable)
                    errors.add(DataModel.singleMessage(singleMessage, Constants.CONFLICT, message));
                else {
                    errors.add(DataModel.singleMessage(singleMessage, Constants.FAILED, message));

                }
            }
            dataModels.addAll(0, errors);
        }

        return DataModel.multipleMessage(message, type, dataModels);
    }
}