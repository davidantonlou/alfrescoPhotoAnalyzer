package org.alfresco.extension.model;

import org.alfresco.extension.utils.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class FaceMetadata {
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

                    jsonFaceRectangle = jsonFaceArray.getJSONObject(i).getJSONObject(Constants.FACE_RECTANGLE);
                    jsonFaceAttributes = jsonFaceArray.getJSONObject(i).getJSONObject(Constants.FACE_ATTRIBUTES);

                    faceMetadata.setGender(jsonFaceAttributes.getString(Constants.GENDER).toUpperCase());
                    faceMetadata.setFaceCoordinates(getCoordinatesFromJson(jsonFaceRectangle));
                    faceMetadata.setAccessories(getAccesoriesFromJson(jsonFaceAttributes));
                    faceMetadata.setAge(jsonFaceAttributes.getInt("age"));
                    faceMetadata.setEmotion(getEmotionFromJson(jsonFaceAttributes));

                    faceMetadataList.add(faceMetadata);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return faceMetadataList;
    }

    private static String getEmotionFromJson(JSONObject jsonFaceAttributes) throws JSONException {
        Double maxValue = new Double(0);
        String maxEmotionKey = "";
        JSONObject emotionsList = jsonFaceAttributes.getJSONObject(Constants.EMOTION);

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
        JSONArray jsonAccessories = jsonFaceAttributes.getJSONArray(Constants.ACCESSORIES);
        Set<String> accessoriesSet = new HashSet<>();
        String accessoryType;
        for (int i = 0; i < jsonAccessories.length(); i++) {
            accessoryType = jsonAccessories.getJSONObject(i).getString(Constants.TYPE);
            if (accessoryType.equals(Constants.GLASSES)) {
                accessoriesSet.add(jsonFaceAttributes.getString(Constants.GLASSES).toUpperCase());
            } else {
                accessoriesSet.add(accessoryType.toUpperCase());
            }
        }

        return accessoriesSet;
    }

    private static String getCoordinatesFromJson(JSONObject jsonFaceRectangle) throws JSONException {
        return new StringBuilder().append(jsonFaceRectangle.getString(Constants.TOP)).append(Constants.COMMA)
                .append(jsonFaceRectangle.getString(Constants.LEFT)).append(Constants.COMMA)
                .append(jsonFaceRectangle.getString(Constants.WIDTH)).append(Constants.COMMA)
                .append(jsonFaceRectangle.getString(Constants.HEIGHT)).append(Constants.COMMA).toString();
    }

    @Override
    public String toString() {
        StringBuilder stringValue = new StringBuilder()
                .append(this.getGender()).append(Constants.SEPARATOR)
                .append(this.getAge()).append(Constants.SEPARATOR)
                .append(this.getEmotion()).append(Constants.SEPARATOR)
                .append(this.getAccessories()).append(Constants.SEPARATOR)
                .append(this.getFaceCoordinates()).append(Constants.SEPARATOR);

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
