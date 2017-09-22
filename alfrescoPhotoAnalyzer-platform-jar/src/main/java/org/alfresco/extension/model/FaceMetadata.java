package org.alfresco.extension.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class FaceMetadata {
    private static String SEPARATOR = " | ";
    private static String COMMA = ",";

    private String gender;
    private String emotion;
    private int age;
    private Set<String> accessories;
    private String faceCoordinates;

    public FaceMetadata() {}

    public static List<FaceMetadata> getInstance(String jsonString) {
        List<FaceMetadata> faceMetadataList = new ArrayList<>();

        try {
            JSONArray jsonFaceArray = new JSONArray(jsonString);

            FaceMetadata faceMetadata;
            JSONObject jsonFaceRectangle;
            JSONObject jsonFaceAttributes;
            if (jsonFaceArray != null && jsonFaceArray.length() > 0) {
                for (int i = 0; i < jsonFaceArray.length(); i++) {
                    faceMetadata = new FaceMetadata();

                    jsonFaceRectangle = jsonFaceArray.getJSONObject(i).getJSONObject("faceRectangle");
                    jsonFaceAttributes = jsonFaceArray.getJSONObject(i).getJSONObject("faceAttributes");

                    faceMetadata.setGender(jsonFaceAttributes.getString("gender").toUpperCase());
                    faceMetadata.setFaceCoordinates(getCoordinatesFromJson(jsonFaceRectangle));
                    faceMetadata.setAccessories(getAccesoriesFromJson(jsonFaceAttributes));
                    faceMetadata.setAge(jsonFaceAttributes.getInt("age"));
                    faceMetadata.setEmotion(getEmotionFromJson(jsonFaceAttributes));

                    faceMetadataList.add(faceMetadata);
                }
            }
            //faceMetadata.setFaceCoordinates();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return faceMetadataList;
    }

    private static String getEmotionFromJson(JSONObject jsonFaceAttributes) throws JSONException {
        Double maxValue = new Double(0);
        String maxEmotionKey = "";
        JSONObject emotionsList = jsonFaceAttributes.getJSONObject("emotion");

        Iterator keyIterator = emotionsList.keys();
        Double currentEmotionValue;
        String currentKey;
        while (keyIterator.hasNext()){
            currentKey = (String) keyIterator.next();
            currentEmotionValue = emotionsList.getDouble(currentKey);
            if (currentEmotionValue.longValue() >= maxValue.longValue()){
                maxValue = currentEmotionValue;
                maxEmotionKey = currentKey;
            }
        }
        return maxEmotionKey.toUpperCase();
    }

    private static Set<String> getAccesoriesFromJson(JSONObject jsonFaceAttributes) throws JSONException {
        JSONArray jsonAccessories = jsonFaceAttributes.getJSONArray("accessories");
        Set<String> accessoriesSet = new HashSet<>();

        for (int i = 0; i < jsonAccessories.length(); i++) {
            accessoriesSet.add(jsonAccessories.getJSONObject(i).getString("type"));
        }

        return accessoriesSet;
    }

    private static String getCoordinatesFromJson(JSONObject jsonFaceRectangle) throws JSONException {
        return new StringBuilder().append(jsonFaceRectangle.getString("top")).append(COMMA)
                .append(jsonFaceRectangle.getString("left")).append(COMMA)
                .append(jsonFaceRectangle.getString("width")).append(COMMA)
                .append(jsonFaceRectangle.getString("height")).append(COMMA).toString();
    }

    @Override
    public String toString() {
        StringBuilder stringValue = new StringBuilder()
                .append(this.getGender()).append(SEPARATOR)
                .append(this.getAge()).append(SEPARATOR)
                .append(this.getEmotion()).append(SEPARATOR)
                .append(this.getFaceCoordinates()).append(SEPARATOR);

        accessories.forEach(stringValue::append);
        return stringValue.toString();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public float getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<String> getAccessories() {
        return accessories;
    }

    public void setAccessories(Set<String> accessories) {
        this.accessories = accessories;
    }

    public String getFaceCoordinates() {
        return faceCoordinates;
    }

    public void setFaceCoordinates(String faceCoordinates) {
        this.faceCoordinates = faceCoordinates;
    }
}
