package org.alfresco.extension.utils;

import org.alfresco.extension.model.FaceMetadata;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class FaceRecognitionUtils {

    public HttpPost prepareRequest(){
        return null;
    }

    public static List<FaceMetadata> prepareResponse(File photo){
        List<FaceMetadata> faceMetadataList = new ArrayList<>();
        HttpClient httpclient = HttpClients.createDefault();

        try {
            URIBuilder builder = new URIBuilder("https://westeurope.api.cognitive.microsoft.com/face/v1.0/detect");

            builder.setParameter("returnFaceId", "true");
            builder.setParameter("returnFaceRectangle", "true");
            builder.setParameter("returnFaceAttributes", "age,gender,emotion,hair,accessories");
//            builder.setParameter("returnFaceAttributes", "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "4ef1782a6a0f473a86b3f139dc7457f3");

            FileEntity reqEntity = new FileEntity(photo, ContentType.APPLICATION_OCTET_STREAM);

            // Request body
//            StringEntity reqEntity = new StringEntity("{\"url\":\"" + url + "\"}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                faceMetadataList = FaceMetadata.getInstance(EntityUtils.toString(entity));
                faceMetadataList.forEach(faceMetadata -> System.out.println(faceMetadata.toString()));
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return faceMetadataList;
    }
}
