package org.alfresco.extension.behaviours;

import org.alfresco.extension.model.FaceMetadata;
import org.alfresco.extension.utils.Constants;
import org.alfresco.extension.utils.FaceRecognitionUtils;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.node.NodeServicePolicies;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.repository.*;
import org.alfresco.service.namespace.NamespacePrefixResolver;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.TempFileProvider;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PhotoBehaviour implements NodeServicePolicies.OnCreateNodePolicy {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private NamespacePrefixResolver namespacePrefixResolver;
    private NodeService nodeService;
    private ContentService contentService;
    private PolicyComponent policyComponent;

    private Behaviour onCreateNode;

    public void init() {
        this.onCreateNode = new JavaBehaviour(this, "onCreateNode",
                Behaviour.NotificationFrequency.TRANSACTION_COMMIT);

        this.policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateNode"), ContentModel.TYPE_CONTENT, this.onCreateNode);
    }

    @Override
    public void onCreateNode(ChildAssociationRef childAssociationRef) {
        NodeRef nodeRef = childAssociationRef.getChildRef();
        if (this.nodeService.exists(nodeRef)) {
            String mimeType = ((ContentData)this.nodeService.getProperty(nodeRef, ContentModel.PROP_CONTENT)).getMimetype();
            if (mimeType != null && mimeType.startsWith(Constants.IMAGE_STRING)){
                this.nodeService.setType(nodeRef, QName.resolveToQName(this.namespacePrefixResolver, Constants.FACE_PHOTO_TYPE));
                try {
                    File file = getContentAsFile(nodeRef);
                    List<FaceMetadata> faceMetadata =  new FaceRecognitionUtils().prepareResponse(file);

                    List<String> genderList = new ArrayList();
                    List<Float> ageList = new ArrayList();
                    List<String> hairList = new ArrayList();
                    List<String> emotionList = new ArrayList();
                    List<String> accesoriesList = new ArrayList();
                    for (int i=0; i<faceMetadata.size(); i++){
                        genderList.add(faceMetadata.get(i).getGender());
                        ageList.add(faceMetadata.get(i).getAge());
                        hairList.addAll(faceMetadata.get(i).getHair());
                        emotionList.add(faceMetadata.get(i).getEmotion());
                        accesoriesList.addAll(faceMetadata.get(i).getAccessories());
                    }

                    genderList = genderList.stream().distinct().collect(Collectors.toList());
                    emotionList = emotionList.stream().distinct().collect(Collectors.toList());
                    accesoriesList = accesoriesList.stream().distinct().collect(Collectors.toList());
                    hairList = hairList.stream().distinct().collect(Collectors.toList());

                    this.nodeService.setProperty(nodeRef, Constants.PROP_NUM_FACES, faceMetadata.size());
                    if (ageList.size() > 0) this.nodeService.setProperty(nodeRef, Constants.PROP_AGE, (Serializable) ageList);
                    if (genderList.size() > 0) this.nodeService.setProperty(nodeRef, Constants.PROP_GENDER, (Serializable) genderList);
                    if (hairList.size() > 0) this.nodeService.setProperty(nodeRef, Constants.PROP_HAIR, (Serializable) hairList);
                    if (emotionList.size() > 0) this.nodeService.setProperty(nodeRef, Constants.PROP_EMOTION, (Serializable) emotionList);
                    if (accesoriesList.size() > 0) this.nodeService.setProperty(nodeRef, Constants.PROP_ACCESSORIES, (Serializable) accesoriesList);
                } catch (Exception e){
                    logger.error(e.getMessage());
                }
            }
        }
    }

    private File getContentAsFile(NodeRef nodeRef)throws IOException{
        String name = (String)this.nodeService.getProperty(nodeRef,ContentModel.PROP_NAME);
        File tempFile = TempFileProvider.createTempFile(nodeRef.getId(), name.substring(name.lastIndexOf(".")));
        InputStream is = this.contentService.getReader(nodeRef, ContentModel.PROP_CONTENT).getContentInputStream();
        FileUtils.copyInputStreamToFile(is, tempFile);
        return tempFile;
    }

    public void setPolicyComponent(PolicyComponent policyComponent) {
        this.policyComponent = policyComponent;
    }

    public void setNodeService(NodeService nodeService) { this.nodeService = nodeService;}

    public void setContentService(ContentService contentService) { this.contentService = contentService;}

    public void setNamespacePrefixResolver(NamespacePrefixResolver namespacePrefixResolver) {
        this.namespacePrefixResolver = namespacePrefixResolver;
    }
}

