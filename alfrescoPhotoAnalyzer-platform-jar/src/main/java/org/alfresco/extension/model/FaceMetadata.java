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
    private Set<String> hair;
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
                    faceMetadata.setHair(getHairFromJson(jsonFaceAttributes));

                    faceMetadataList.add(faceMetadata);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return faceMetadataList;
    }

    private static Set<String> getHairFromJson(JSONObject jsonFaceAttributes) throws JSONException {
        Set<String> hairSet = new HashSet<>();
        if (jsonFaceAttributes.has(Constants.HAIR.toLowerCase())) {
            JSONObject jsonHair = jsonFaceAttributes.getJSONObject(Constants.HAIR.toLowerCase());
            if (jsonHair != null) {
                if (hasHairProperty(Constants.BALD, jsonHair)) {
                    hairSet.add(Constants.BALD.toUpperCase());
                }

                if (jsonHair.has(Constants.HAIR_COLOR)) {
                    JSONArray jsonHairColors = jsonHair.getJSONArray(Constants.HAIR_COLOR);
                    if (jsonHairColors != null && jsonHairColors.length() > 0) {
                        Double maxValue = new Double(0);
                        String maxHairKey = "";
                        Double currentHairValue;
                        String currentKey;
                        for (int i = 0; i < jsonHairColors.length(); i++) {
                            currentKey = jsonHairColors.getJSONObject(i).getString(Constants.COLOR);
                            currentHairValue = jsonHairColors.getJSONObject(i).getDouble(Constants.CONFIDENCE);
                            if (!currentKey.toUpperCase().equals(Constants.OTHER) && currentHairValue >= maxValue) {
                                maxValue = currentHairValue;
                                maxHairKey = currentKey;
                            }
                        }
                        hairSet.add(maxHairKey.toUpperCase());
                    }
                }
            }
        }

        if (jsonFaceAttributes.has(Constants.FACIAL_HAIR)) {
            JSONObject jsonFacialHair = jsonFaceAttributes.getJSONObject(Constants.FACIAL_HAIR);
            if (jsonFacialHair != null) {
                if (hasHairProperty(Constants.MOUSTACHE, jsonFacialHair)) {
                    hairSet.add(Constants.MOUSTACHE.toUpperCase());
                }
                if (hasHairProperty(Constants.BEARD, jsonFacialHair)) {
                    hairSet.add(Constants.BEARD.toUpperCase());
                }
                if (hasHairProperty(Constants.SIDEBURNS, jsonFacialHair)) {
                    hairSet.add(Constants.SIDEBURNS.toUpperCase());
                }
            }
        }
        return hairSet;
    }

    private static boolean hasHairProperty(String property, JSONObject json) throws JSONException {
        return json.has(property.toLowerCase()) && json.getDouble(property.toLowerCase()) >= 0.7;
    }

    private static String getEmotionFromJson(JSONObject jsonFaceAttributes) throws JSONException {
        Double maxValue = new Double(0);
        String maxEmotionKey = "";
        if (jsonFaceAttributes.has(Constants.EMOTION)) {
            JSONObject emotionsList = jsonFaceAttributes.getJSONObject(Constants.EMOTION);
            if (emotionsList != null && emotionsList.length() > 0) {
                Iterator keyIterator = emotionsList.keys();
                Double currentEmotionValue;
                String currentKey;
                while (keyIterator.hasNext()) {
                    currentKey = (String) keyIterator.next();
                    currentEmotionValue = emotionsList.getDouble(currentKey);
                    if (currentEmotionValue >= maxValue) {
                        maxValue = currentEmotionValue;
                        maxEmotionKey = currentKey;
                    }
                }
            }
        }
        return maxEmotionKey.toUpperCase();
    }

    private static Set<String> getAccesoriesFromJson(JSONObject jsonFaceAttributes) throws JSONException {
        Set<String> accessoriesSet = new HashSet<>();
        if (jsonFaceAttributes.has(Constants.ACCESSORIES)) {
            JSONArray jsonAccessories = jsonFaceAttributes.getJSONArray(Constants.ACCESSORIES);
            if (jsonAccessories != null && jsonAccessories.length() > 0) {
                String accessoryType;
                for (int i = 0; i < jsonAccessories.length(); i++) {
                    accessoryType = jsonAccessories.getJSONObject(i).getString(Constants.TYPE);
                    if (accessoryType.equals(Constants.GLASSES)) {
                        accessoriesSet.add(jsonFaceAttributes.getString(Constants.GLASSES).toUpperCase());
                    } else {
                        accessoriesSet.add(accessoryType.toUpperCase());
                    }
                }
            }
        }
        return accessoriesSet;
    }

    private static String getCoordinatesFromJson(JSONObject jsonFaceRectangle) throws JSONException {
        if (jsonFaceRectangle != null && jsonFaceRectangle.has(Constants.TOP) && jsonFaceRectangle.has(Constants.LEFT)
                && jsonFaceRectangle.has(Constants.WIDTH) && jsonFaceRectangle.has(Constants.HEIGHT)) {
            return new StringBuilder().append(jsonFaceRectangle.getString(Constants.TOP)).append(Constants.COMMA)
                    .append(jsonFaceRectangle.getString(Constants.LEFT)).append(Constants.COMMA)
                    .append(jsonFaceRectangle.getString(Constants.WIDTH)).append(Constants.COMMA)
                    .append(jsonFaceRectangle.getString(Constants.HEIGHT)).append(Constants.COMMA).toString();
        } else return "";
    }

    @Override
    public String toString() {
        StringBuilder stringValue = new StringBuilder()
                .append(this.getGender()).append(Constants.SEPARATOR)
                .append(this.getAge()).append(Constants.SEPARATOR)
                .append(this.getEmotion()).append(Constants.SEPARATOR)
                .append(this.getHair()).append(Constants.SEPARATOR)
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

    public Set<String> getHair() {
        return hair;
    }

    public void setHair(Set<String> hair) {
        this.hair = hair;
    }
}
