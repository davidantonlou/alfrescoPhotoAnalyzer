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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FaceRecognitionUtils {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public HttpPost prepareRequest(File file) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(Constants.getPropertyValue(Constants.AZURE_FACE_API_URL));
        builder.setParameter("returnFaceId", "true");
        builder.setParameter("returnFaceRectangle", "true");
        builder.setParameter("returnFaceAttributes", Constants.getPropertyValue(Constants.FACE_ATTRIBUTES_LIST_TO_EXTRACT));

        URI uri = builder.build();
        HttpPost request = new HttpPost(uri);
        request.setHeader("Content-Type", Constants.APPLICATION_OCTET_STREAM);
        request.setHeader("Ocp-Apim-Subscription-Key", Constants.getPropertyValue(Constants.API_SUBSCRIPTION_KEY));

        FileEntity reqEntity = new FileEntity(file, ContentType.APPLICATION_OCTET_STREAM);
        request.setEntity(reqEntity);

        return request;
    }

    public List<FaceMetadata> prepareResponse(File photo){
        List<FaceMetadata> faceMetadataList = new ArrayList<>();
        HttpClient httpclient = HttpClients.createDefault();

        try {
            HttpPost postRequest = prepareRequest(photo);

            HttpResponse response = httpclient.execute(postRequest);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                faceMetadataList = FaceMetadata.getInstance(EntityUtils.toString(entity));
                if (logger.isDebugEnabled()) {
                    faceMetadataList.forEach(faceMetadata -> logger.debug(faceMetadata.toString()));
                }
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }

        return faceMetadataList;
    }
}
