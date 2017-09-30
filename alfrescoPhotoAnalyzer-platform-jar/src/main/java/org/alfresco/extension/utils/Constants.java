package org.alfresco.extension.utils;

import org.alfresco.service.namespace.QName;

import java.util.Properties;

public class Constants {

    protected Properties properties;

    private static Constants instance;

    private void init() {
        Constants.instance = this;
    }

    private void destroy() { Constants.instance = null; }

    public static final String NAMESPACE_PHOTO_ANALYZER = "http://www.mimacom.com/alfresco/alfrescoPhotoAnalyzer/1.0";

    public static final QName PROP_NUM_FACES = QName.createQName(NAMESPACE_PHOTO_ANALYZER, "numFaces");
    public static final QName PROP_EMOTION = QName.createQName(NAMESPACE_PHOTO_ANALYZER, "emotion");
    public static final QName PROP_AGE = QName.createQName(NAMESPACE_PHOTO_ANALYZER, "age");
    public static final QName PROP_GENDER = QName.createQName(NAMESPACE_PHOTO_ANALYZER, "gender");
    public static final QName PROP_HAIR = QName.createQName(NAMESPACE_PHOTO_ANALYZER, "hair");
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

    public static final String BLACK = "BLACK";
    public static final String BLOND = "BLOND";
    public static final String BROWN = "BROWN";
    public static final String RED = "RED";
    public static final String GRAY = "GRAY";
    public static final String OTHER = "OTHER";
    public static final String BEARD = "BEARD";
    public static final String MOUSTACHE = "MOUSTACHE";
    public static final String SIDEBURNS = "SIDEBURNS";
    public static final String BALD = "BALD";

    public static final String FACE_RECTANGLE = "faceRectangle";
    public static final String FACE_ATTRIBUTES = "faceAttributes";
    public static final String GENDER = "gender";
    public static final String FACIAL_HAIR = "facialHair";
    public static final String HAIR = "hair";
    public static final String HAIR_COLOR = "hairColor";
    public static final String COLOR = "color";
    public static final String CONFIDENCE = "confidence";
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

    public static final String AZURE_FACE_API_URL = "photo_analyzer.azure.api.url";
    public static final String FACE_ATTRIBUTES_LIST_TO_EXTRACT = "photo_analyzer.azure.api.attribute_list";
    public static final String API_SUBSCRIPTION_KEY = "photo_analyzer.azure.api.subscription_key";
    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

    public static String getPropertyValue(String propertyName) {
        String propertyValue = instance.properties.getProperty(propertyName);
        if (propertyValue == null) {
            throw new RuntimeException("The property " + propertyName + " is not configured at alfresco-global.properties");
        }
        return propertyValue;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
