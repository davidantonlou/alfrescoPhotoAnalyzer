package org.alfresco.extension.utils;

import org.alfresco.service.namespace.QName;

public class Constants {

    public static final String NAMESPACE_PHOTO_ANALYZER = "http://www.mimacom.com/alfresco/alfrescoPhotoAnalyzer/1.0";

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

    public static final String IMAGE_STRING = "image";

}
