package org.alfresco.extension.behaviours;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.node.NodeServicePolicies;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhotoBehaviour implements NodeServicePolicies.OnCreateNodePolicy {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private PolicyComponent policyComponent;
    private Behaviour onCreateNode;


    public void init() {
        this.onCreateNode = new JavaBehaviour(this, "onCreateNode",
                Behaviour.NotificationFrequency.TRANSACTION_COMMIT);


        this.policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onCreateNode"), ContentModel.TYPE_CONTENT, this.onCreateNode);
    }

    @Override
    public void onCreateNode(ChildAssociationRef childAssociationRef) {

    }


    public void setPolicyComponent(PolicyComponent policyComponent) {
        this.policyComponent = policyComponent;
    }
}

