package org.alfresco.extension.utils;

import org.alfresco.service.namespace.QName;

public class Constants {

    public static final String NAMESPACE_PHOTO_ANALYZER = "http://www.mimacom.com/alfresco/alfrescoPhotoAnalyzer/1.0";

    public static final QName PROP_NUM_FACES = QName.createQName(NAMESPACE_PHOTO_ANALYZER, "numFaces");
    public static final QName PROP_EMOTION = QName.createQName(NAMESPACE_PHOTO_ANALYZER, "emotion");
    public static final QName PROP_AGE = QName.createQName(NAMESPACE_PHOTO_ANALYZER, "age");
    public static final QName PROP_GENDER = QName.createQName(NAMESPACE_PHOTO_ANALYZER, "gender");
    public static final QName PROP_ACCESSORIES = QName.createQName(NAMESPACE_PHOTO_ANALYZER, "accessories");

    public static final String FACE_PHOTO_TYPE = "pa:facePhoto";

    public static final String ANGER = "ANGER";
    public static final String CONTEMPT = "CONTEMPT";
    public static final String DISGUST = "DISGUST";
    public static final String FEAR = "FEAR";
    public static final String HAPPINESS = "HAPPINESS";
    public static final String NEUTRAL = "NEUTRAL";
    public static final String SADNESS = "SADNESS";
    public static final String SURPRISE = "SURPRISE";

    public static final String FACE_RECTANGLE = "faceRectangle";
    public static final String FACE_ATTRIBUTES = "faceAttributes";
    public static final String GENDER = "gender";
    public static final String EMOTION = "emotion";
    public static final String ACCESSORIES = "accessories";
    public static final String TYPE = "type";
    public static final String GLASSES = "glasses";
    public static final String TOP = "top";
    public static final String LEFT = "left";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String SEPARATOR = " | ";
    public static final String COMMA = ",";
    public static final String IMAGE_STRING = "image";

    public static final String AZURE_FACE_API_URL = "https://westeurope.api.cognitive.microsoft.com/face/v1.0/detect";
    public static final String FACE_ATTRIBUTES_LIST_TO_EXTRACT = "age,gender,emotion,hair,accessories,glasses";
    public static final String API_SUBSCRIPTION_KEY = "4ef1782a6a0f473a86b3f139dc7457f3";
    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

}
