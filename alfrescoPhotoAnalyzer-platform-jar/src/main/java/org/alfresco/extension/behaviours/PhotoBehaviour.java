package org.alfresco.extension.behaviours;

import org.alfresco.extension.model.FaceMetadata;
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
import java.util.List;


public class PhotoBehaviour implements NodeServicePolicies.OnCreateNodePolicy {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private NamespacePrefixResolver namespacePrefixResolver;
    private NodeService nodeService;
    private ContentService contentService;
    private PolicyComponent policyComponent;

    private Behaviour onCreateNode;

    private static final String IMAGE_STRING = "image";
    private static final String FACE_PHOTO_TYPE = "pa:facePhoto";

    private static final QName PROP_EMOTION = QName.createQName("http://www.mimacom.com/alfresco/alfrescoPhotoAnalyzer/1.0", "emotion");

    public void init() {
        this.onCreateNode = new JavaBehaviour(this, "onCreateNode",
                Behaviour.NotificationFrequency.TRANSACTION_COMMIT);


        this.policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateNode"), ContentModel.TYPE_CONTENT, this.onCreateNode);
    }

    @Override
    public void onCreateNode(ChildAssociationRef childAssociationRef) {
        NodeRef nodeRef = childAssociationRef.getChildRef();
        logger.info("+++++++++++++ onCreateNode ++++++++++++");
        if(this.nodeService.exists(nodeRef)) {
            logger.info("+++++++++++++ inside if ");
            String mimeType = ((ContentData)this.nodeService.getProperty(nodeRef, ContentModel.PROP_CONTENT)).getMimetype();
            logger.info("+++++++++++++ mimeType: " + mimeType);
            if (mimeType!=null && mimeType.startsWith(IMAGE_STRING)){
                logger.info("+++++++++++++ changing type ...");
                this.nodeService.setType(nodeRef, QName.resolveToQName(this.namespacePrefixResolver, FACE_PHOTO_TYPE));
                try {
                    File file = getContentAsFile(nodeRef);
                    List<FaceMetadata> faceMetadata =  FaceRecognitionUtils.prepareResponse(file);

                    //A saco
                    this.nodeService.setProperty(nodeRef, PROP_EMOTION, faceMetadata.get(0).getEmotion());
                }catch (Exception e){
                    logger.error(e.getMessage());
                }
            }
        }
    }

    private File getContentAsFile(NodeRef nodeRef)throws IOException{
        String name = (String)this.nodeService.getProperty(nodeRef,ContentModel.PROP_NAME);
        File tempFile = TempFileProvider.createTempFile("temp_"+nodeRef.getId(), name.substring(name.lastIndexOf(".")+1));
        InputStream is = this.contentService.getReader(nodeRef, ContentModel.PROP_CONTENT).getContentInputStream())
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

